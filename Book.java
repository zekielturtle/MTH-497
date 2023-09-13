

public class Book implements Comparable<Book> {
    private String title;
    private String callNumber;
    private String author;
    private int pages;
    private int size; //in cm
    private int id;
    //maybe a bool isSet or something with other books in the set?

    public Book(){
        title = "";
        author = "";
        callNumber = "";
        pages = 0;
        id = "";
    }

    public Book(String title, String author, String callNumber, int pages, int id){
        this.title = title;
        this.author = author;
        this.callNumber = callNumber;
        this.pages = pages;
        this.id = id;
        calcSize();
    }

    public Book(String title, String author, String callNumber, int id){
        this.title = title;
        this.author = author;
        this.callNumber = callNumber;
        this.id = id;
        size = 20; //placeholder value
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

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    private int calcSize() {
        int p = pages;
        //code to calculate the size - likely just a linear equation from a regression model determined elsewhere
        return 20; //placeholder value for now
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override public int compareTo(Book o) {
        return this.pages - o.pages; //placeholder sorting
    }

    @Override public String toString(){
        return title + " by " + author;
    }
}