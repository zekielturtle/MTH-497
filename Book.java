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

    public Book(Book b){
        title = b.title;
        this.author = b.author;
        this.callNumber = b.callNumber;
        this.pages = b.pages;
        this.id = b.id;
        this.size = b.size;
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

    public static String getABC(String input){
        String output = "";
        for(int i = 0; i < input.length(); i++){  
            if (!Character.isDigit(input.charAt(i))){
                output+=input.charAt(i);
            }  
         }  
         return output;
    }

    public static String getNum(String input){
        String output = "";
        for(int i = 0; i < input.length(); i++){  
            if (Character.isDigit(input.charAt(i))){
                output+=input.charAt(i);
            }  
         }  
         return output;
    }

    public boolean sameCol(Book o){
        //if in same collection return true
        return this.id == o.id;
    }

    @Override public int compareTo(Book o) {
        String call = this.callNumber;
        //System.out.println(call);
        //System.out.println(o.callNumber);
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

            String oIn = oList.get(0);
            String thisIn = thisList.get(0);
            oList.add(0,getABC(oIn));
            oList.set(1,getNum(oIn));
            thisList.add(0,getABC(thisIn));
            thisList.set(1,getNum(thisIn));
            if(Character.isDigit(thisList.get(2).charAt(0))){
                String num = thisList.get(1);
                num+=".";
                num+=thisList.get(2);
                thisList.set(1,num);
                thisList.remove(2);
            }
            if(Character.isDigit(oList.get(2).charAt(0))){
                String num = oList.get(1);
                num+=".";
                num+=oList.get(2);
                oList.set(1,num);
                oList.remove(2);
            }

            if(thisList.size()>=oList.size()){
                for(int i=0; i<oList.size();i++){
                    if(thisList.get(i).compareTo(oList.get(i)) != 0){
                        if(Character.isDigit(thisList.get(i).charAt(0))){
                            if((Double.parseDouble(thisList.get(i)) - Double.parseDouble(oList.get(i)))<0){
                                return -1;
                            }else{return 1;}
                        }
                        else{return thisList.get(i).compareTo(oList.get(i));}
                    }
                }
            }else{
                for(int i=0; i<thisList.size();i++){
                    if(thisList.get(i).compareTo(oList.get(i)) != 0){
                        if(Character.isDigit(thisList.get(i).charAt(0))){
                            if((Double.parseDouble(thisList.get(i)) - Double.parseDouble(oList.get(i)))<0){
                                return -1;
                            }else{return 1;}
                        }
                        else{return thisList.get(i).compareTo(oList.get(i));}
                    }
                }
            }
        } catch(Exception e){System.out.println(e);}
        return 0; //placeholder sorting
    }

    @Override public String toString(){
        
        return title + " by " + author;
    }
}