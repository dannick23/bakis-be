package com.example.bakis.services;

import com.example.bakis.database.entity.SettingsEntity;
import com.example.bakis.database.entity.SystemEntity;
import com.example.bakis.database.model.UserAuthority;
import com.example.bakis.database.repository.RegistrationRepository;
import com.example.bakis.database.repository.SystemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CronJobs {
    private final RegistrationRepository registrationRepository;
    private final SystemRepository systemRepository;
    private final SmsService smsService;

    @Scheduled(cron = "20 35 20 * * ?")
    public void sendNecessarySms(){
        var allRegistrations = systemRepository.findAll()
                .stream()
                .map(SystemEntity::getSettings)
                .filter(SettingsEntity::isSendSmsToClient)
                .map(SettingsEntity::getSystem)
                .map(SystemEntity::getUsers)
                .flatMap(List::stream)
                .filter(user -> user.getAuthority().equals(UserAuthority.WORKER))
                .map(registrationRepository::findAllByUser)
                .flatMap(List::stream)
                .filter(registration -> registration.getTimeFrom().toLocalDate().isEqual(LocalDate.now().plusDays(1)))
                .toList();

        for(var registration : allRegistrations){
            try{
                log.info("Message sending...");
                smsService.sendSms(registration.getTimeFrom(), registration.getClientPhoneNumber());
            }catch (Exception e){
                log.error("Failed to send sms for client: " + registration.getClientPhoneNumber() + ", registration id: " + registration.getId() + ";\n" + e.getMessage());
            }
        }
    }

}
