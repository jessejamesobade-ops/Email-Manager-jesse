package app;

import model.Email;
import repository.EmailRepository;
import repository.EmailRepositoryImp;

import java.time.LocalDateTime;

public class Main {
    static void main(String[] args) {
        EmailRepository repo = new EmailRepositoryImp( "jdbc:mysql://127.0.0.2:3306/emanager_schema",
                "root",
                "Torad0r@"
                );

        Email email = new Email(
                "123abc",
                "tester@example.com",
                "Hello world",
                "This is a test snippet",
                LocalDateTime.now()
        );

     boolean saved = repo.save(email);
        System.out.println("saved: "+saved);

        Email e = repo.findById("123abc");
        System.out.println(e);

    }
}

