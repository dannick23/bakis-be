package com.example.bakis.apis;

import com.example.bakis.apis.model.*;
import com.example.bakis.services.SystemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://bakis-fe.herokuapp.com/")
@RequestMapping("system/")
public class SystemResource {
    private final SystemService systemService;

    public SystemResource(SystemService systemService) {
        this.systemService = systemService;
    }

    @PostMapping("{system-name}/new")
    @SecurityRequirement(name = "Bearer Authentication")
    public void createNewSystem(@RequestBody SystemDTO systemDTO){
        systemService.createNewSystem(systemDTO.name(), systemDTO.description());
    }

    @GetMapping("{system-name}/all-workers")
    public List<UserDTO> getAllUsersForSystem(@PathVariable("system-name") String systemName){
        return systemService.getAllWorkersFromSystem(systemName);
    }
    @PostMapping("{system-name}/add-image")
    @SecurityRequirement(name = "Bearer Authentication")
    public void addImage(@RequestParam("file") MultipartFile file,
                         @PathVariable("system-name") String systemName,
                         @RequestParam String name,
                         @RequestParam String description){
        systemService.uploadFile(file, systemName, name, description);
    }
    @GetMapping("{system-name}/get-all-images")
    public List<ImageInfoDTO> getAllImages(@PathVariable("system-name") String systemName){
        return systemService.getAllImagesBySystem(systemName);
    }
    @GetMapping("{system-name}/get-settings")
    public SettingsDTO getSettingsForSystem(@PathVariable("system-name") String systemName){
        return systemService.getSystemSettings(systemName);
    }
    @GetMapping(value = "{system-name}/get-image/{image-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageById(@PathVariable("image-id") Integer imageId,
                               @PathVariable("system-name") String systemName){
        return systemService.getImageById(imageId);
    }
    @DeleteMapping("{system-name}/delete-image/{image-id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteImage(@PathVariable("image-id") Integer imageId,
                            @PathVariable("system-name") String systemName){
        systemService.deleteImage(imageId);
    }
    @PostMapping("{system-name}/change-about-us")
    @SecurityRequirement(name = "Bearer Authentication")
    public void changeAboutUs(@PathVariable("system-name") String systemName,
                                @RequestBody String newText){
        systemService.changeAboutUs(systemName, newText);
    }
    @PostMapping(value = "{system-name}/change-description")
    @SecurityRequirement(name = "Bearer Authentication")
    public void changeDescription(@PathVariable("system-name") String systemName,
                                    @RequestBody String newText){
        systemService.changeDescription(systemName, newText);
    }
    @GetMapping("{system-name}/get-about-us")
    public String getAboutUsForSystem(@PathVariable("system-name") String systemName){
        return systemService.getAboutUs(systemName);
    }
    @GetMapping("{system-name}/get-description")
    public String getDescriptionForSystem(@PathVariable("system-name") String systemName){
        return systemService.getDescription(systemName);
    }
    @GetMapping("{system-name}/get-contact-us")
    public ContactUsDTO getContactUs(@PathVariable("system-name") String systemName){
        return systemService.getContactUs(systemName);
    }
    @PostMapping("{system-name}/create-default-working-hours")
    @SecurityRequirement(name = "Bearer Authentication")
    public void createDefaultWorkingHours(@PathVariable("system-name") String systemName){
        systemService.createDefaultWorkingHours(systemName);
    }
}
