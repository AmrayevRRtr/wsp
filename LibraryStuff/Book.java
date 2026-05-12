package LibraryStuff;

import java.io.Serializable;

public class Book implements Serializable {
   public String title;
   public String authour;
   public int pages;
   public int publishedYear;

   public Book(String title,String authour, int pages, int publishedYear){
    this.title=title;
    this.authour=authour;
    this.pages=pages;
    this.publishedYear=publishedYear;
   }

   

}
