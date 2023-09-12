import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
    //read in database
    try(Scanner scan = new Scanner(new FileInputStream("sample.tsv"))){
    scan.useDelimiter("\t");
    while (scan.hasNext()){
        System.out.println(scan.next());
    }
    //some sort of SLL of books to keep them in order
    //prompt user to enter # and size of shelves
    //randomly split books to shelves
    }catch(FileNotFoundException fnfe){
        System.out.println("something went wrong");
    }
    // kw trial 

}
}