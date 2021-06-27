package furkan.hrmssystem.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDtoForLogin {
    private String email;
    private String password;
}
