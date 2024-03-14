import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
    //read in database
    System.out.println("Update KW - 3-13 at ");
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

    }catch(FileNotFoundException fnfe){
        System.out.println("something went wrong");
    }

    Collections.sort(books);
    List<Library> libs = new ArrayList<>(0);
for(int i = 300; i<=500; i+=20){
    Library l = new Library(books, i); //create new library with shelf break point at i mm
    System.out.println("Library: " + i + ": " + l.getFitness());
    libs.add(l); //add to full library
}

algo algo = new algo(libs); //new genetic algorithm on the set of libraries
algo.run();
Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Press 'x' to quit:");

        boolean flag = true;
        while (flag) {
            String cont = myObj.nextLine();  // Read user input
            System.out.println(cont);

            if (cont.equalsIgnoreCase("x")) {
                System.out.println("Exiting the program. Goodbye!");
                flag = false;  // Set flag to false to exit the loop
            } else {
                // Perform other actions based on user input
                algo.kill();
                algo.run();
                System.out.println("Press 'x' to quit:");
            }
        }
    myObj.close();
    books.clear();
    libs.clear();
}
}