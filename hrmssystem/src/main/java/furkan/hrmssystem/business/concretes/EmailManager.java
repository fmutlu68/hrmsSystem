package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.EmailService;
import furkan.hrmssystem.core.entities.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailManager implements EmailService {
    private final JavaMailSender mailSender;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public EmailManager(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(EmailMessage email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email.getTo());
        mail.setFrom(email.getFrom());
        mail.setText(email.getContent());
        mail.setSubject(email.getSubject());
        mailSender.send(mail);
    }
}
