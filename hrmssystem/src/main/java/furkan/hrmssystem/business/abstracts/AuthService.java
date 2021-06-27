package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.entities.UserDtoForLogin;
import furkan.hrmssystem.core.utilities.results.DataResult;

public interface AuthService {
    DataResult<Object> loginUser(UserDtoForLogin user);
}
