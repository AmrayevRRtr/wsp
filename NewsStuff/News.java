package NewsStuff;
import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private String topic;              
    private String content;            
    private boolean isPinned;          
    private String author;             
    private Date publicationDate;     
    
    public News(String topic, String content, String author, Date publicationDate) {
        this.topic = topic;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isPinned = false;  
    }


    public void pinNews() {
        isPinned = true;
    }

    public void unpinNews() {
        isPinned = false;
    }

    // Getter and Setter methods for encapsulation
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    
public String toString() {
    String result;
    if (isPinned) {
        result = "[Pinned] ";
    } else {
        result = "";
    }
    result += "Topic: " + topic + "\n" +
              "Author: " + author + ", Published on: " + publicationDate + "\n" +
              "Content: " + content;
    return result;
}

}
