package NewsStuff;
import java.util.Date;

public class Message {
    private String sender;               
    private String recipient;             
    private String messageContent;        
    private Date sentTime;                
    private boolean isRead;   
    
    public Message(String sender, String recipient, String messageContent) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.sentTime = new Date();  
        this.isRead = false;         
    }

    
    public void sendMessage() {
        sentTime = new Date();            
        isRead = false;                   
        System.out.println("Message sent from " + sender + " to " + recipient + ": " + messageContent);
    }

    public void markAsRead() {
        isRead = true;                   
        System.out.println("Message from " + sender + " to " + recipient + " has been marked as read.");
    }

    // getters and setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "Message from " + sender + " to " + recipient + " [" + (isRead ? "Read" : "Unread") + "] sent on " + sentTime;
    }
}
