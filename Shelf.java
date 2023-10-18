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

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addLast(Book b){
        books.add(b);
    }

    public Book getLast(){
        return books.get(books.size()-1);
    }

    public Book getFirst(){
        return books.get(0);
    }

    public void addFirst(Book b){
        books.add(0,b);
    }

    public void clear(){
        books.clear();
    }

    @Override
    public String toString(){
        return books.toString();
    }
}
