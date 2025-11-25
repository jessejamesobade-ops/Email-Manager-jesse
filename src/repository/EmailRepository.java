package repository;

import model.Email;
import java.util.List;

public interface EmailRepository {

    boolean save(Email email);

    Email findById(String messageId);

    List<Email> findAll();

    boolean delete(String messageId);

}
