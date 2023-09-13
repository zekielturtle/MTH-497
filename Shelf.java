import java.util.*;

public class Shelf {
    //private int size;
    ArrayList<Book> books = new ArrayList<>();


    public Shelf(){}

    public void addBook(Book b){
        books.add(b);
    }

    public void clear(){
        books.clear();
    }
}
