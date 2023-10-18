import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
    //read in database
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<String> calls = new ArrayList<>();
    try(Scanner file = new Scanner(new FileInputStream("sample.tsv"))){
        while (file.hasNext()){
            String stringRead = file.nextLine();
            Scanner scan = new Scanner(stringRead);
            scan.useDelimiter("\t");
            while (scan.hasNext()){
                int id = scan.nextInt();
                String title = scan.next();
                String author = scan.next();
                String e = scan.next();
                String pub = scan.next();
                String vol = scan.next();
                Book book;
                if(scan.hasNextInt()){
                    int pages = scan.nextInt();
                    String call = scan.next();
                    calls.add(call);
                    book = new Book(title, author, call, pages, id);
                } else{
                    String k = scan.next();
                    String call = scan.next();
                    calls.add(call);
                    book = new Book(title,author,call,id);
                }
            
            books.add(book);
            } 
        scan.close();
    } 
    //some sort of SLL of books to keep them in order
    //prompt user to enter # and size of shelves
    //randomly split books to shelves
    }catch(FileNotFoundException fnfe){
        System.out.println("something went wrong");
    }

    Collections.sort(books);

    Library.newLibrary(books);

    for (Book b : books){
        System.out.println(b.getCallNumber());
    }
    books.clear();
}
}