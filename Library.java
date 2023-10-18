import java.util.*;

public class Library {
    private static ArrayList<Shelf> shelves;
    private int numShelves=shelves.size();

    public static void newLibrary(ArrayList<Book> books, int minSize){
        Shelf currShelf = new Shelf();
        shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>minSize){
                System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                currShelf = new Shelf();
            }
        }
    }

    public static void newLibrary(ArrayList<Book> books){
        Shelf currShelf = new Shelf();
        shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>610){
                System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                currShelf = new Shelf();
            }
        }
    }

    public void goBack(int shelfNum){
        //TODO: verify that the shelfNum is within 1 and shelves.size() - 1
        //remove first book and put it on the shelf before
        Book b = shelves.get(shelfNum).getFirst();
        shelves.get(shelfNum-1).addLast(b);
    }

    public void goForward(int shelfNum){
        //TODO: verify that the shelfNum is within 0 and shelves.size() - 2
        //remove last book and put it on shelf after
        Book b = shelves.get(shelfNum).getLast();
        shelves.get(shelfNum+1).addFirst(b);
    }

    @Override
    public String toString(){
        return shelves.toString();
    }
}
