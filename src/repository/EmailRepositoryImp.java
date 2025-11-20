package repository;

import model.Email;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailRepositoryImp implements EmailRepository {

    private final String url;
    private final String username;
    private final String password;

    public EmailRepositoryImp(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }


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
        return new ArrayList<>();
    }

    @Override
    public boolean delete(String messageId) {
        return false;
    }
}
