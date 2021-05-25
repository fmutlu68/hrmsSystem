package furkan.hrmssystem.adapters;

import furkan.hrmssystem.adapters.abstracts.UserCheckService;
import org.springframework.stereotype.Component;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

@Component
public class MernisServiceAdapter implements UserCheckService {
    @Override
    public boolean checkIfRealPerson(long tcNo, String firstName, String lastName, Date birthDate) throws RemoteException {
        KPSPublicSoapProxy proxy = new KPSPublicSoapProxy();
        var result = false;
        result = proxy.TCKimlikNoDogrula(tcNo, firstName, lastName, convertToCalendar(birthDate).get(Calendar.YEAR));
        return result;
    }

    private Calendar convertToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
