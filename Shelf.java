import java.util.*;

public class Shelf {
    private double size;
    ArrayList<Book> books;
    private double fitness;
    private int num;


    public Shelf(){
        size=0;
        books = new ArrayList<>();
        num=0;
    }

    

    public Shelf(Shelf s){
        this.size=s.size;
        this.num=s.num;
        books = new ArrayList<>();
        for (Book element : s.books) {
            books.add(new Book(element));
        }
        //System.out.println("Duplicated Shelf " + s);
        //System.out.println("To Shelf: " + this);
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
        num++;
    }

    public Book getLast(){
        if(books.size()>0){
            return books.get(books.size()-1);
        } else{return null;}
    }

    public Book getFirst(){
        return books.get(0);
    }

    public void addFirst(Book b){
        books.add(0,b);
        num++;
    }

    public void clear(){
        books.clear();
    }

    public void setFitness(double i){
        this.fitness = i;
    }

    public double getFitness(){
        return fitness;
    }

    public int getNumBooks(){
        return num;
    }

    @Override
    public String toString(){
        return books.toString();
    }
}
