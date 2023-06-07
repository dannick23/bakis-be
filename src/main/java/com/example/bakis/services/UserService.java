package com.example.bakis.services;

import com.example.bakis.apis.model.UserDTO;
import com.example.bakis.apis.model.WorkerShiftChangeRequest;
import com.example.bakis.database.entity.TokenEntity;
import com.example.bakis.database.entity.WorkerShiftEntity;
import com.example.bakis.database.model.ShiftType;
import com.example.bakis.database.model.UserAuthority;
import com.example.bakis.database.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SystemRepository systemRepository;
    private final TokenRepository tokenRepository;
    private final WorkerShiftRepository workerShiftRepository;
    private final RegistrationRepository registrationRepository;

    public List<UserDTO> getWorkerAwaitingForConfirmation(String systemName){
        return userRepository.findAllBySystem(systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found")))
                .stream()
                .filter(user -> user.getAuthority().equals(UserAuthority.AWAITING_CONFIRMATION))
                .map(UserDTO::from)
                .toList();
    }
    public UserDTO getUserDetails(String userEmail, String systemName){
        return userRepository.findByEmail(userEmail+";"+systemName)
                .map(UserDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));
    }

    public UserDTO getUserDetailsByToken(String systemName, String token) {
        return tokenRepository.findByToken(token)
                .map(TokenEntity::getUser)
                .filter(user -> user.getSystem().getName().equals(systemName))
                .map(UserDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));
    }

    public void changeShift(String systemName,String email,WorkerShiftChangeRequest workerShiftChange) {
        var user = userRepository.findByEmail(email+";"+systemName)
                .filter(u -> u.getAuthority().equals(UserAuthority.WORKER))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));

        var workerShifts = workerShiftRepository.findAllByUser(user);
        var registrations = registrationRepository.findAllByUser(user);

        switch (workerShiftChange.type()) {
            case REGULAR:
                if (workerShifts.stream().anyMatch(shift -> shift.getTimeFrom().isBefore(workerShiftChange.to()) && shift.getTimeTo().isAfter(workerShiftChange.from()))
                    || registrations.stream().anyMatch(registration -> registration.getTimeFrom().isBefore(workerShiftChange.to()) && registration.getTimeTo().isAfter(workerShiftChange.from()))) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Requested shift conflicts with an existing shift or registration.");
                }

                var shiftToModify = workerShifts.stream()
                        .filter(shift -> shift.getTimeFrom().isAfter(workerShiftChange.from()) && shift.getTimeTo().isBefore(workerShiftChange.to()))
                        .findFirst()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something really messed up just happened"));

                workerShiftRepository.save(WorkerShiftEntity.builder()
                                .user(user)
                                .shiftType(ShiftType.REGULAR)
                                .timeFrom(workerShiftChange.to())
                                .timeTo(shiftToModify.getTimeTo())
                        .build());

                shiftToModify.setTimeTo(workerShiftChange.from());
                workerShiftRepository.save(shiftToModify);

                break;
            case OVERTIME:
                if (workerShifts.stream().anyMatch(shift -> shift.getTimeFrom().isBefore(workerShiftChange.to()) && shift.getTimeTo().isAfter(workerShiftChange.from()))) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Requested shift conflicts with an existing shift or registration.");
                }
                workerShiftRepository.save(WorkerShiftEntity.builder()
                                .user(user)
                        .shiftType(ShiftType.OVERTIME)
                        .timeFrom(workerShiftChange.from())
                        .timeTo(workerShiftChange.to())
                        .build());
                break;
        }
    }

}
