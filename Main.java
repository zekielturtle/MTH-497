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
    /*Book a = books.get(11);
    Book b1 = books.get(99);
    String callA = a.getCallNumber();
    String callB = b1.getCallNumber();
    System.out.println(callA);
    System.out.println(callB);
    Scanner scan1 = new Scanner(callA);
    ArrayList<String> thisList = new ArrayList<>();
    scan1.useDelimiter(" |\\.");
    String curr;
    while(scan1.hasNext()){
        curr = scan1.next();
        if(!curr.isBlank()){
            System.out.println(curr);
            thisList.add(curr);
        }
    }
    Scanner scan2 = new Scanner(callB);
    ArrayList<String> oList = new ArrayList<>();
    scan2.useDelimiter(" |\\.");
    while(scan2.hasNext()){
        curr=scan2.next();
        if(!curr.isBlank()){
            System.out.println(curr);
            oList.add(curr);
        }
    }
    String oIn = oList.get(0);
            String thisIn = thisList.get(0);
            oList.add(0,Book.getABC(oIn));
            oList.set(1,Book.getNum(oIn));
            thisList.add(0,Book.getABC(thisIn));
            thisList.set(1,Book.getNum(thisIn));
            for(String s : thisList){
                System.out.println(s);
            }
            for(String s : oList){
                System.out.println(s);
            }
            if(thisList.size()>=oList.size()){
                for(int i=0; i<thisList.size();i++){
                    System.out.println("comparing " + thisList.get(i) + " and " + oList.get(i));
                    if(thisList.get(i) != oList.get(i)){
                        if(Character.isDigit(thisList.get(i).charAt(0))){
                            System.out.println("Int comparison: " + (Integer.parseInt(oList.get(i)) - Integer.parseInt(thisList.get(i))));
                        }
                        else{System.out.println("String comparison: " + thisList.get(i).compareTo(oList.get(i)));}
                    }
                }
            }else{
                for(int i=0; i<oList.size();i++){
                    System.out.println("comparing " + thisList.get(i) + " and " + oList.get(i));
                    if(thisList.get(i) != oList.get(i)){
                        if(Character.isDigit(thisList.get(i).charAt(0))){
                            if((Double.parseDouble(thisList.get(i)) - Double.parseDouble(oList.get(i)))<0){
                                System.out.println("-1");
                            }else{System.out.println("1");}
                        }
                        else{System.out.println("String comparison: " + thisList.get(i).compareTo(oList.get(i)));}
                    }
                }
            }*/
            //System.out.println(a.compareTo(b1));
     //placeholder sorting
        //scan.close();
        //scan2.close();

    Collections.sort(books);

    Library.newLibrary(books);

    for (Book b : books){
        System.out.println(b.getCallNumber());
    }
    books.clear();
}
}