public class Book {
    private String title;
    private String callNumber;
    private String author;
    private int pages;
    private int size;
    private Shelf shelf;

    public Book(String title, String author, String callNumber, int pages){
        this.title = title;
        this.author = author;
        this.callNumber = callNumber;
        this.pages = pages;
        calcSize();
    }

    public String getTitle(){
        return title;
    }

    public String getCallNumber(){
        return callNumber;
    }

    public String getAuthor(){
        return author;
    }

    private void calcSize() {
        int s;
        //code to calculate the size
        size = s;
    }
}