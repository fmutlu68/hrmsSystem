package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.entities.EmailMessage;

public interface EmailService {
    void sendMail(EmailMessage email);
}
