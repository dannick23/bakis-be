package com.example.bakis.services;

import com.example.bakis.apis.model.RegistrationDTO;
import com.example.bakis.apis.model.RegistrationDTOLight;
import com.example.bakis.apis.model.WorkerShiftDTO;
import com.example.bakis.database.entity.RegistrationEntity;
import com.example.bakis.database.entity.UserEntity;
import com.example.bakis.database.entity.WorkerShiftEntity;
import com.example.bakis.database.repository.RegistrationRepository;
import com.example.bakis.database.repository.UserRepository;
import com.example.bakis.database.repository.WorkerShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final WorkerShiftRepository workerShiftRepository;

    public RegistrationDTO createNewRegistration(RegistrationDTO registrationDTO, String systemName) {
        registrationRepository.save(RegistrationEntity
                .builder()
                .description(registrationDTO.description())
                .firstName(registrationDTO.firstName())
                .lastName(registrationDTO.lastName())
                .timeFrom(registrationDTO.timeFrom())
                .timeTo(registrationDTO.timeTo())
                .clientPhoneNumber(registrationDTO.clientPhoneNumber())
                .user(userRepository.findByEmail(registrationDTO.userEmail()+";"+systemName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")))
                .build()
        );

        return registrationDTO;
    }

    public List<RegistrationDTOLight> getAllMonthRegistrationsForUser(String email, String systemName) {
        return registrationRepository.findAllByUser(userRepository.findByEmail(email+";"+systemName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")))
                .stream()
                .map(RegistrationDTOLight::from)
                .toList();
    }

    public List<RegistrationDTOLight> getAllDayRegistrationsForUser(String email, LocalDate day, String systemName) {
        return registrationRepository.findAllByUser(userRepository.findByEmail(email+";"+systemName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")))
                .stream()
                .filter(event -> event.getTimeFrom().getDayOfYear() == day.getDayOfYear() && event.getTimeTo().getDayOfYear() == day.getDayOfYear())
                .map(RegistrationDTOLight::from)
                .toList();
    }

    public List<WorkerShiftDTO> getWorkersShift(String email, String systemName) {
        return workerShiftRepository.findAllByUser(userRepository.findByEmail(email+";"+systemName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")))
                .stream()
                .map(WorkerShiftDTO::from)
                .toList();
    }

    public List<WorkerShiftDTO> getWorkersBookedShift(String email, String systemName) {
        UserEntity user = userRepository.findByEmail(email+";"+systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));

        List<RegistrationEntity> registrations = registrationRepository.findAllByUser(user);
        List<WorkerShiftEntity> workerShifts = workerShiftRepository.findAllByUser(user);

        Set<LocalDate> registrationDates = registrations.stream()
                .map(reg -> reg.getTimeFrom().toLocalDate())
                .collect(Collectors.toSet());

        return workerShifts.stream()
                .filter(shift -> registrationDates.contains(shift.getTimeFrom().toLocalDate()))
                .map(WorkerShiftDTO::from)
                .toList();
    }

    public RegistrationDTO getSpecificRegistration(String userEmail, LocalDateTime from, LocalDateTime to, String systemName) {
        return registrationRepository.findSpecificByUser(
                        userRepository.findByEmail(userEmail+";"+systemName)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")), from, to)
                .map(RegistrationDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));
    }

    public void deleteSpecificRegistration(String userEmail, LocalDateTime from, LocalDateTime to, String systemName) {
        registrationRepository.delete(registrationRepository.findSpecificByUser(
                        userRepository.findByEmail(userEmail+";"+systemName)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")), from, to)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found")));
    }
}
