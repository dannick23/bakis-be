package com.example.bakis.services;

import com.example.bakis.apis.model.*;
import com.example.bakis.database.entity.ImageCollageEntity;
import com.example.bakis.database.entity.SystemEntity;
import com.example.bakis.database.model.UserAuthority;
import com.example.bakis.database.repository.ImageCollageRepository;
import com.example.bakis.database.repository.SystemRepository;
import com.example.bakis.database.repository.UserRepository;
import com.example.bakis.database.repository.WorkingHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import static com.example.bakis.utils.DefaultValues.defaultSettings;
import static com.example.bakis.utils.DefaultValues.defaultWorkingHours;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final SystemRepository systemRepository;
    private final UserRepository userRepository;
    private final ImageCollageRepository imageCollageRepository;
    private final WorkingHoursRepository workingHoursRepository;

    public void createNewSystem(String name, String description) {
        systemRepository.save(SystemEntity
                .builder()
                .name(name)
                .description(description)
                .settings(defaultSettings())
                .build());
    }

    public List<UserDTO> getAllWorkersFromSystem(String systemName) {
        return userRepository
                .findAllBySystem(systemRepository.findByName(systemName)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found")))
                .stream()
                .filter(user -> user.getAuthority().equals(UserAuthority.WORKER))
                .map(UserDTO::from)
                .toList();
    }

    public void uploadFile(MultipartFile multipartFile, String systemName, String name, String description) {
        try {
            imageCollageRepository.save(ImageCollageEntity
                    .builder()
                    .system(systemRepository.findByName(systemName)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found")))
                    .image(multipartFile.getBytes())
                    .name(name)
                    .description(description)
                    .build());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    public List<ImageInfoDTO> getAllImagesBySystem(String systemName) {
        return imageCollageRepository.findAllBySystemName(systemName)
                .stream()
                .map(ImageInfoDTO::from)
                .toList();
    }

    public byte[] getImageById(Integer imageId) {
        return imageCollageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "image not found")).getImage();
    }

    public void deleteImage(Integer imageId) {
        imageCollageRepository.deleteById(imageId);
    }

    public SettingsDTO getSystemSettings(String systemName) {
        return systemRepository.findByName(systemName)
                .map(SystemEntity::getSettings)
                .map(SettingsDTO::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"));
    }

    public void changeAboutUs(String systemName, String newText) {
        var system = systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"));
        system.setAboutUs(newText);
        systemRepository.save(system);
    }

    public void changeDescription(String systemName, String newText) {
        var system = systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"));
        system.setDescription(newText);
        systemRepository.save(system);
    }

    public String getAboutUs(String systemName) {
        return systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"))
                .getAboutUs();
    }

    public String getDescription(String systemName) {
        return systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"))
                .getDescription();
    }

    public ContactUsDTO getContactUs(String systemName) {
        var users = userRepository.findAllAdmins(systemName)
                .stream().filter(user -> user.getAuthority().equals(UserAuthority.ADMIN))
                .map(UserDTO::from)
                .toList();
        var address = systemRepository.findByName(systemName)
                .map(SystemEntity::getAddress)
                .orElse("Not provided");
        var workingHours = workingHoursRepository.findAllBySystemName(systemName)
                .stream()
                .map(WorkingHoursDTO::from)
                .toList();
        return new ContactUsDTO(users, workingHours, address);
    }

    public void createDefaultWorkingHours(String systemName) {
        var system = systemRepository.findByName(systemName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "System not found"));
        system.setWorkingHours(defaultWorkingHours(system));
        systemRepository.save(system);
    }
}
