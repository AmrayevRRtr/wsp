package LibraryStuff;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Library implements Serializable{
   private Map<Book,Integer> allBooks;

   public Library(){
    allBooks=new HashMap<>();
   }

   public Map<Book,Integer> getAllBooks(){
    return allBooks;
   }

   public void addBook(Book book){
    allBooks.put(book, allBooks.getOrDefault(book, 0) + 1);
   }

   public void removeBook(Book book){
        allBooks.remove(book);
   }

   public boolean isAvailable(Book book){
        for (Entry<Book, Integer> entry : allBooks.entrySet()) {
            if(entry.getKey().title.equals(book.title)){
                if(entry.getValue()>0){
                    return true;
                }
            }
        }

        return false;
   }

   public void lendBook(Book book){
    if(isAvailable(book)){
    for (Entry<Book, Integer> entry : allBooks.entrySet()) {
        if(entry.getKey().title.equals(book.title)){
            entry.setValue(entry.getValue()-1);
            return;
        }
    }
   }
   else{
    System.out.println("This Book is not avaliable");
   }

}
public void returnBook(Book book){
    for (Entry<Book, Integer> entry : allBooks.entrySet()) {
        if(entry.getKey().title.equals(book.title)){
            entry.setValue(entry.getValue()+1);
            return;
        }
    }
}
}
