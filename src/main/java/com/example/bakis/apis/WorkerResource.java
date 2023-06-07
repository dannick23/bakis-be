package com.example.bakis.apis;

import com.example.bakis.apis.model.WorkerShiftChangeRequest;
import com.example.bakis.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("worker/")
@RequiredArgsConstructor
public class WorkerResource {
    private final UserService userService;
    @PostMapping("{system-name}/change-shift/{user-email}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void requestToChangeWorkingHoursForWorker(@PathVariable("system-name") String systemName,
                                                     @PathVariable("user-email") String userEmail,
                                                     @RequestBody WorkerShiftChangeRequest workerShiftChange){
        userService.changeShift(systemName, userEmail, workerShiftChange);
    }
}
