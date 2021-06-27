package furkan.hrmssystem.entities.dtos;

import furkan.hrmssystem.entities.concretes.EmployerUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserOperationDtoForAction {
    private EmployerUser newUser;
    private EmployerUser oldUser;
    private int operationTypeId;
}
