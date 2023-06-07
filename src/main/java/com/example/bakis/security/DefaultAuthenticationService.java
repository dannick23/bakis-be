package com.example.bakis.security;

import com.example.bakis.database.entity.TokenEntity;
import com.example.bakis.database.entity.UserEntity;
import com.example.bakis.database.model.UserAuthority;
import com.example.bakis.database.repository.SystemRepository;
import com.example.bakis.database.repository.TokenRepository;
import com.example.bakis.database.repository.UserRepository;
import com.example.bakis.apis.model.UserDTO;
import com.example.bakis.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static com.example.bakis.utils.DefaultValues.defaultWorkerShift;

@Slf4j
@Service
public class DefaultAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SystemRepository systemRepository;

    public DefaultAuthenticationService(AuthenticationManager authenticationManager,
                                        JwtService jwtService,
                                        TokenRepository tokenRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder passwordEncoder,
                                        SystemRepository systemRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.systemRepository = systemRepository;
    }

    @Transactional
    public UserDTO login(String email, String password, String systemName) {
        var user = userRepository.findByEmail(email+";"+systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found"));
        revokeAllUserTokens(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email+";"+systemName, password));
        var token = jwtService.generateBearerToken(user);
        saveUserToken(user, token);

        return UserDTO.from(user, token);
    }

    @Transactional
    public UserDTO register(UserDTO userDTO, String systemName) {
        if (userRepository.existsByEmail(userDTO.email()+";"+systemName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken");
        }
        if(userDTO.authority().equals(UserAuthority.ADMIN)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Only the system owner can create administrators");
        }

        var isWorker = userDTO.authority().equals(UserAuthority.WORKER);

        var system = systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System does not exist"));
        var user = userRepository.save(UserEntity
                .builder()
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .authority(isWorker ? UserAuthority.AWAITING_CONFIRMATION : UserAuthority.CLIENT)
                .email(userDTO.email()+";"+systemName)
                .description(userDTO.description())
                .password(passwordEncoder.encode(userDTO.password()))
                .phoneNumber(userDTO.phoneNumber())
                .system(system)
                .build()
        );
        if(isWorker){
            user.setWorkerShifts(defaultWorkerShift(user));
            userRepository.save(user);
            return UserDTO.from(user, "");
        }
        var token = jwtService.generateBearerToken(user);
        saveUserToken(user, token);

        return UserDTO.from(user, token);
    }

    public UserDTO confirmWorker(String email, String systemName){
        var user = userRepository.findByEmail(email+";"+systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User not found!"));
        user.setAuthority(UserAuthority.WORKER);
        userRepository.save(user);

        return UserDTO.from(user, "");
    }

    public void logout(String token) {
        var user = tokenRepository.findByToken(token)
                .map(TokenEntity::getUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "User's session not found!"));
        revokeAllUserTokens(user);
    }

    private void saveUserToken(UserEntity user, String token) {
        tokenRepository.save(TokenEntity
                .builder()
                .user(user)
                .expired(false)
                .revoked(false)
                .expiryDate(LocalDateTime.now().plusDays(1))
                .token(token)
                .build());
    }

    public void revokeAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getEmail());
        for (var token : validUserTokens) {
            token.setExpired(true);
            token.setRevoked(true);
        }
        tokenRepository.saveAll(validUserTokens);
    }
}
