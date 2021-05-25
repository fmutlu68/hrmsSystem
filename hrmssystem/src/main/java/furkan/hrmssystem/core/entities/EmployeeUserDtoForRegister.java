package furkan.hrmssystem.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUserDtoForRegister {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Date birthDate;
    private String identityNo; //TcNo
}
