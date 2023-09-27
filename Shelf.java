import java.util.*;

public class Shelf {
    private double size;
    ArrayList<Book> books;


    public Shelf(){
        size=0;
        books = new ArrayList<>();
    }

    public double getSize(){
        return size;
    }

    public void addSize(double s){
        size+=s;
    }



    /*public Shelf(Shelf s){
        this.books = s.books;
    }*/

    public ArrayList<Book> getBooks(){return books;}
    public void addBook(Book b){
        books.add(b);
    }

    public void clear(){
        books.clear();
    }

    @Override
    public String toString(){
        return books.toString();
    }
}
