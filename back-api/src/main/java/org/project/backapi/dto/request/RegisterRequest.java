package org.project.backapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String fullname;
    private String pseudo;
    private String role;
    private String email;
    private String password;
    private String teacherSpeciality;
}
