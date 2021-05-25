package furkan.hrmssystem.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BU CLASS EMAİL İLE GÖNDERİLEN DOĞRULAMA KODUNU SİSTEME DOĞRULATMAK İÇİN OLUŞTURULMUŞTUR.
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForAccountValidation {
    private String validationCode;
    private String email;
    private String password;
}
