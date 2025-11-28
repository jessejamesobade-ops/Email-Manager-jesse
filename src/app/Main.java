package app;

import model.Email;
import service.EmailService;
import service.EmailServiceImp;
import repository.EmailRepository;
import repository.EmailRepositoryImp;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
   public static void main(String[] args) {


        EmailRepository repo = new EmailRepositoryImp( "jdbc:mysql://127.0.0.2:3306/emanager_schema",
                "root",
                "Torad0r@"
                );

        EmailService emailService = new EmailServiceImp(repo);

        /* {

            @Override
            public boolean save(Email email) {
                return false;
            }

            @Override
            public Email findById(String messageId) {
                return null;
            }

            @Override
            public List<Email> findAll() {
                return List.of();
            }

            @Override
            public boolean delete(String messageId) {
                return false;
            }
        }*/

        Email email = new Email(
                "123abc",
                "tester@example.com",
                "Hello world",
                "This is a test snippet",
                LocalDateTime.now()
        );

       Email saved = emailService.saveEmail(email);
        System.out.println("saved: "+saved);

        Email found = emailService.getEmailById("123abc");
        System.out.println("Found: "+found);

        System.out.println("\nAll Emails:");
        emailService.getAllEmails().forEach(System.out::println);

        boolean deleted = emailService.deleteEmail("123abc");
        System.out.println("DELETED: "+deleted);

    }
}

