package com.example.bakis.apis;

import com.example.bakis.apis.model.RegistrationDTO;
import com.example.bakis.apis.model.RegistrationDTOLight;
import com.example.bakis.apis.model.WorkerShiftDTO;
import com.example.bakis.services.RegistrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("registration/")
@RequiredArgsConstructor
public class RegistrationResource {
    private final RegistrationService registrationService;

    @PostMapping("{system-name}/new")
    @SecurityRequirement(name = "Bearer Authentication")
    public RegistrationDTO createRegistration(@RequestBody RegistrationDTO registrationDTO,
                                              @PathVariable("system-name") String systemName) {
        return registrationService.createNewRegistration(registrationDTO, systemName);
    }

    @GetMapping("{system-name}/all-registrations/{user-email}")
    public List<RegistrationDTOLight> getAllRegistrationsForUser(@PathVariable("user-email") String userEmail,
                                                                 @PathVariable("system-name") String systemName) {
        return registrationService.getAllMonthRegistrationsForUser(userEmail, systemName);
    }

    @GetMapping("{system-name}/all-registrations/{user-email}/{day}")
    public List<RegistrationDTOLight> getAllRegistrationsForUsersSpecificDay(@PathVariable("user-email") String userEmail,
                                                                             @PathVariable("day") String day,
                                                                             @PathVariable("system-name") String systemName) {
        return registrationService.getAllDayRegistrationsForUser(userEmail, LocalDate.parse(day.substring(0, 10)), systemName);
    }

    @GetMapping("{system-name}/worker-shift/{user-email}")
    public List<WorkerShiftDTO> getWorkerAllShift(@PathVariable("user-email") String userEmail,
                                                  @PathVariable("system-name") String systemName) {
        return registrationService.getWorkersShift(userEmail, systemName);
    }

    @GetMapping("{system-name}/worker-booked-shift/{user-email}")
    public List<WorkerShiftDTO> getWorkerBookedShift(@PathVariable("user-email") String userEmail,
                                                     @PathVariable("system-name") String systemName) {
        return registrationService.getWorkersBookedShift(userEmail, systemName);
    }

    @GetMapping("{system-name}/booking/{user-email}/{time-from}/{time-to}")
    @SecurityRequirement(name = "Bearer Authentication")
    public RegistrationDTO getRegistrationByDate(@PathVariable("user-email") String userEmail,
                                                 @PathVariable("system-name") String systemName,
                                                 @PathVariable("time-from") String from,
                                                 @PathVariable("time-to") String to
    ) {
        return registrationService.getSpecificRegistration(userEmail, LocalDateTime.parse(from.substring(0, 19)), LocalDateTime.parse(to.substring(0, 19)), systemName);
    }
    @DeleteMapping("{system-name}/booking/{user-email}/{time-from}/{time-to}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteRegistrationByDate(@PathVariable("user-email") String userEmail,
                                         @PathVariable("system-name") String systemName,
                                         @PathVariable("time-from") String from,
                                         @PathVariable("time-to") String to){
        registrationService.deleteSpecificRegistration(userEmail,LocalDateTime.parse(from.substring(0, 19)),LocalDateTime.parse(to.substring(0, 19)),systemName);
    }
}
