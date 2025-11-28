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

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

    private Email mapRow(ResultSet rs) throws SQLException{
        return new Email(
          rs.getString("messageId"),
                rs.getString("sender"),
                rs.getString("subject"),
                rs.getString("snippet"),
                rs.getTimestamp("receive_at").toLocalDateTime()
        );
    }

    @Override
    public Email save(Email email) {
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

            return affected > 0 ? email : null;

        } catch(SQLException e) {

            //  e.printStackTrace();
            System.out.println("connected [ X ]");
           // return null;
            throw new RuntimeException("Failed to save email: "+ email.getMessageId(), e);
        }
    }

    @Override
    public Email findById(String messageId) {

        String sql = "SELECT message_Id, sender, subject, snippet, received_at FROM emails WHERE message_Id = ?";

        try(Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, messageId);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    return mapRow(rs);

                }
            }
            return  null;

        }catch (SQLException e){
           // e.printStackTrace();
            //return null;
            throw new RuntimeException("Failed to find email by id: "+messageId, e);

        }
    }

    @Override
    public List<Email> findAll() {

        String sql = "SELECT message_Id, sender, subject, snippet, received_at FROM emails";
        List<Email> emails = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                 emails.add(mapRow(rs));
              }
            return emails;
            } catch (SQLException e){
          throw new RuntimeException("Failed to fetch all emails", e);
        }
    }

    @Override
    public boolean delete(String messageId) {

        String sql = "DELETE FROM emails WHERE message_Id = ?";

        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, messageId);

            int affected =  stmt.executeUpdate();
            return affected > 0;

        }catch(SQLException e){
            throw new RuntimeException("Failed to delete email"+ messageId, e);
        }
    }
}
