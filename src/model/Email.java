package model;

import java.time.LocalDateTime;

public class Email {


    private String messageId;
    private String sender;
    private String subject;
    private String snippet;
    private LocalDateTime receivedAt;

    //empty constructors
   public Email(){}

    //full constructor
    public Email(String messageId, String sender, String subject, String snippet, LocalDateTime receivedAt){

        this.messageId = messageId;
        this.sender = sender;
        this.subject = subject;
        this.snippet = snippet;
        this.receivedAt = receivedAt;
    }

    //toString
    @Override
    public String toString(){

        return "Email{"+
                "messageId='"+ messageId +'\'' +
                ", sender='"+sender+'\''+
                ", subject='"+subject+'\''+
                ", receivedAt='"+receivedAt+'}';
    }

    //setters
    public void setMessageId(String messageId){
       this.messageId = messageId;
    }
    public void setSender(String sender){
       this.sender = sender;
    }
    public void setSubject(String subject){
       this.subject = subject;
    }
    public void setSnippet(String snippet){
       this.snippet = snippet;
    }
    public void setReceivedAt(LocalDateTime receivedAt){
       this.receivedAt = receivedAt;
    }

    //getters
    public String getMessageId(){
       return messageId;
    }
    public String getSender() {
        return sender;
    }
    public String getSubject() {
        return subject;
    }
    public String getSnippet() {
        return snippet;
    }
    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }
}
