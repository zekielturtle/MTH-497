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

        double s = 0; 
        for(Book b : books) {
            s = s + b.getSize();
        }


        this.size = s; 
        return this.size;
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
        size+=b.getSize();
    }

    public Book getLast() {
        if (books.isEmpty()) {
            throw new IllegalStateException("Shelf: The list of books is unexpectedly empty - GET");
        }
        return books.get(books.size() - 1);
        /// DID THIS POP THE BOOK? 

    }

    public Book popLast() {
        if (books.isEmpty()) {
            throw new IllegalStateException("Shelf: The list of books is unexpectedly empty - POP");
        }
        Book b = new Book(books.get(books.size() - 1));
    
        books.remove(books.size()-1);
        num = books.size();
        System.out.println(".....SHELF: Number of books left " + books.size());
        return b;

        /// DID THIS POP THE BOOK? 
        
    }


    public Book getFirst(){
        return books.get(0);
    }

    public Book popFirst(){
        if (books.isEmpty()) {
            throw new IllegalStateException("The list of books is unexpectedly empty.");
        }
        Book pop = new Book(books.get(0));
        books.remove(0);
        size-=pop.getSize();
        num--;
        return pop;
    }

    public void addFirst(Book b){
        books.add(0,b);
        num++;
        size+=b.getSize();
    }

    public Book getBook(int i){
        return books.get(i);
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
        return books.size();
    }

    @Override
    public String toString(){
        return books.toString();
    }

    //uh yeah i totally remember writing this
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Same object reference
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Different classes or null object
        }

        Shelf other = (Shelf) obj;
        return books == other.books && Objects.equals(books, other.books);
    }

}
