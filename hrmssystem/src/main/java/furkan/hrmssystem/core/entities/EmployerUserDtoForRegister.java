package furkan.hrmssystem.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployerUserDtoForRegister {
    private String email;
    private String password;
    private String companyName;
    private String companyWebSite;
    private String companyPhone;
}
