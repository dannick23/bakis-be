package com.example.bakis.apis.model;

import java.util.List;

public record ContactUsDTO(List<UserDTO> admins, List<WorkingHoursDTO> workingHours, String address) {
}
