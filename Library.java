import java.util.*;

public class Library {
    private static ArrayList<Shelf> shelves;
    private int numShelves=shelves.size();

    public static void newLibrary(ArrayList<Book> books){
        Shelf currShelf = new Shelf();
        shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addBook(b);
            if (currShelf.getSize()>610){
                System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                currShelf = new Shelf();
            }
        }
    }

    public void goBack(Shelf s){
        //remove first book and put it on the shelf before
    }

    public void goForward(Shelf s){
        //remove last book and put it on shelf after
    }

    @Override
    public String toString(){
        return shelves.toString();
    }
}
