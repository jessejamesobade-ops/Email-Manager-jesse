package service;

import model.Email;
import java.util.List;

public interface EmailService {

      Email saveEmail(Email email);

      Email getEmailById(String messageId);

      List<Email> getAllEmails();

      boolean deleteEmail(String messageId);


}
