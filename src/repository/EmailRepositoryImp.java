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
        String sql = "INSERT INTO emails (message_Id, sender, subject, snippet, received_at) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(url, username,password);
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,email.getMessageId());
            stmt.setString(2,email.getSender());
            stmt.setString(3,email.getSubject());
            stmt.setString(4,email.getSnippet());
            stmt.setTimestamp(5,Timestamp.valueOf(email.getReceivedAt()));

            int affected = stmt.executeUpdate();

            System.out.println("connected [good]");

            return affected > 0;
        } catch(SQLException e) {
              e.printStackTrace();
            System.out.println("connected [ X ]");
            return false;
        }
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
