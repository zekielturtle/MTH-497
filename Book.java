import java.util.ArrayList;
import java.util.Scanner;

public class Book implements Comparable<Book> {
    private String title;
    private String callNumber;
    private String author;
    private int pages;
    private double size; //in mm
    private int id;
    //maybe a bool isSet or something with other books in the set?

    public Book(){
        title = "";
        author = "";
        callNumber = "";
        pages = 0;
        id = 0;
    }

    public Book(String title, String author, String callNumber, int pages, int id){
        this.title = title;
        this.author = author;
        this.callNumber = callNumber;
        this.pages = pages;
        this.id = id;
        this.size = calcSize();
    }

    public Book(String title, String author, String callNumber, int id){
        this.title = title;
        this.author = author;
        this.callNumber = callNumber;
        this.id = id;
        pages = 300; //placeholder value
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getCallNumber(){
        return callNumber;
    }

    public void setCallNumber(String callNumber){
        this.callNumber = callNumber;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public double getSize(){
        return size;
    }

    public void setSize(double size){
        this.size = size;
    }

    private double calcSize() {
        double p = pages*0.097; //average mm per page for standard copy paper (likely slightly larger than in this sample)
        return p; 
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override public int compareTo(Book o) {
        String call = this.callNumber;
        System.out.println(call);
        System.out.println(o.callNumber);
        try(Scanner scan = new Scanner(call)){
            ArrayList<String> thisList = new ArrayList<>();
            scan.useDelimiter(" |\\.");
            String curr;
            while(scan.hasNext()){
                curr= scan.next();
                if(!curr.isEmpty()){
                    thisList.add(curr);
                }
            }
            scan.close();
            Scanner scan2 = new Scanner(o.callNumber);
            ArrayList<String> oList = new ArrayList<>();
            scan2.useDelimiter(" |\\.");
            while(scan2.hasNext()){
                curr=scan2.next();
                if(!curr.isEmpty()){
                    oList.add(curr);
                }
            }
            scan2.close();
            if(thisList.size()>=oList.size()){
                for(int i=0; i<thisList.size();i++){
                    if(thisList.get(i) != oList.get(i)){
                        return thisList.get(i).compareTo(oList.get(i));
                    }
                }
            }else{
                for(int i=0; i<oList.size();i++){
                    if(thisList.get(i) != oList.get(i)){
                        return thisList.get(i).compareTo(oList.get(i));
                    }
                }
            }
        } catch(Exception e){System.out.println("aaaaaa");}
        return 0; //placeholder sorting
    }

    @Override public String toString(){
        
        return title + " by " + author;
    }
}