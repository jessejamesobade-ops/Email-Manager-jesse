package service;

import model.Email;
import repository.EmailRepository;

import  java.util.List;

public class EmailServiceImp implements EmailService {

    private final EmailRepository emailRepository;
    
    //Dependency injection through repository
    public EmailServiceImp(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email saveEmail(Email email) {
        if (email == null) {
            System.out.println("Cannot save null email.");
            return null;
        }
        if (email.getMessageId() == null || email.getMessageId().isEmpty()){
            System.out.println("Email must have a messageId.");
            return null;
        }

        boolean saved = emailRepository.save(email);
        return saved ? email : null;

    }

    @Override
    public Email getEmailById(String messageId) {

        if (messageId == null || messageId.isEmpty()) {
            System.out.println("messageId cannot be Empty");
            return null;
        }
        return emailRepository.findById(messageId);
    }

    @Override
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @Override
    public boolean deleteEmail(String messageId) {

        if (messageId == null || messageId.isEmpty()) {
            System.out.println("messageId cannot be Empty.");
            return false;
        }

        return emailRepository.delete(messageId);
    }
}
