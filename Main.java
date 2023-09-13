import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
    //read in database
    ArrayList<Book> books = new ArrayList<>();
    try(Scanner file = new Scanner(new FileInputStream("sample.tsv"))){
        while (file.hasNext()){
            String stringRead = file.nextLine();
            Scanner scan = new Scanner(stringRead);
            scan.useDelimiter("\t");
            while (scan.hasNext()){
                int id = scan.nextInt();
                System.out.println("id: " + id);
                String title = scan.next();
                System.out.println("title: " + title);
                String author = scan.next();
                System.out.println("author: " + author);
                String e = scan.next();
                System.out.println("e: " + e);
                String pub = scan.next();
                System.out.println("pub: " + pub);
                String vol = scan.next();
                System.out.println("vol: " + vol);
                Book book;
                if(scan.hasNextInt()){
                    int pages = scan.nextInt();
                    System.out.println("pages: " + pages);
                    String call = scan.next();
                    System.out.println("call: " + call);
                    book = new Book(title, author, call, pages, id);
                } else{
                    String k = scan.next();
                    System.out.println("k: " + k);
                    String call = scan.next();
                    System.out.println("call: " + call);
                    book = new Book(title,author,call,id);
                }
            
            books.add(book);
            System.out.println("book.getTitle(): " + book.getTitle());
            System.out.println("book.getCallNumber(): " + book.getCallNumber());
            }
    }
    //some sort of SLL of books to keep them in order
    //prompt user to enter # and size of shelves
    //randomly split books to shelves
    }catch(FileNotFoundException fnfe){
        System.out.println("something went wrong");
    }
    // kw trial 
    int currSize = 0;
 
    Shelf currShelf = new Shelf();
    ArrayList<Shelf> shelves = new ArrayList<>();
    for (Book b : books){
        currSize += b.getSize();
        currShelf.addBook(b);
        if (currSize>61){
            System.out.println("shelf size: " + currSize);
            shelves.add(currShelf);
            currSize = 0;
            currShelf.clear();
        }
    }
    


}
}