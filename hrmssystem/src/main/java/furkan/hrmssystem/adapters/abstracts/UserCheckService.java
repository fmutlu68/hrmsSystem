package furkan.hrmssystem.adapters.abstracts;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

public interface UserCheckService {
    boolean checkIfRealPerson(long tcNo, String firstName, String lastName, Date birthDate) throws RemoteException;
}
