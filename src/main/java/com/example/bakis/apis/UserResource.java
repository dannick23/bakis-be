package com.example.bakis.apis;

import com.example.bakis.apis.model.LoginDTO;
import com.example.bakis.apis.model.UserDTO;
import com.example.bakis.security.DefaultAuthenticationService;
import com.example.bakis.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user/")
@RequiredArgsConstructor
public class UserResource {
    private final DefaultAuthenticationService defaultAuthenticationService;
    private final UserService userService;

    @PostMapping("{system-name}/new")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO,
                              @PathVariable("system-name") String systemName) {
        return defaultAuthenticationService.register(userDTO, systemName);
    }

    @PostMapping("{system-name}/login")
    public UserDTO login(@RequestBody @Valid LoginDTO login,
                         @PathVariable("system-name") String systemName) {
        return defaultAuthenticationService.login(login.email(), login.password(), systemName);
    }

    @PostMapping("{system-name}/logout")
    @SecurityRequirement(name = "Bearer Authentication")
    public void logout(@PathVariable("system-name") String systemName,
                       @RequestHeader("Authorization") String token) {
        defaultAuthenticationService.logout(token);
    }

    @PostMapping("{system-name}/validate-token")
    @SecurityRequirement(name = "Bearer Authentication")
    public UserDTO validateToken(@PathVariable("system-name") String systemName,
                                 @RequestHeader("Authorization") String token) {
        return userService.getUserDetailsByToken(systemName, token.substring(7));
    }

    @GetMapping("{system-name}/get-user-details/{user-email}")
    public UserDTO getUserDetails(@PathVariable("system-name") String systemName,
                                  @PathVariable("user-email") String userEmail) {
        return userService.getUserDetails(userEmail, systemName);
    }

    @GetMapping("{system-name}/unconfirmed-workers")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<UserDTO> getWorkerAwaitingForConfirmation(@PathVariable("system-name") String systemName) {
        return userService.getWorkerAwaitingForConfirmation(systemName);
    }

    @PostMapping("{system-name}/confirm-worker/{user-email}")
    @SecurityRequirement(name = "Bearer Authentication")
    public UserDTO confirmWorker(@PathVariable("system-name") String systemName,
                                 @PathVariable("user-email") String userEmail) {
        return defaultAuthenticationService.confirmWorker(userEmail, systemName);
    }
}
