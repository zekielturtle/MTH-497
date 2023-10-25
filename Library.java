import java.util.*;

public class Library {
    private static ArrayList<Shelf> shelves;
    private int numShelves;
    private int fitness;
    private ArrayList<Integer> fits = new ArrayList<>();


    public Library(ArrayList<Book> books, int minSize){
        Shelf currShelf = new Shelf();
        shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>minSize){
                //System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                currShelf = new Shelf();
            }
        }
        numShelves = shelves.size();
        calcFitness();
    }

    public Library(ArrayList<Book> books){
        Shelf currShelf = new Shelf();
        shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>610){
                //System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                currShelf = new Shelf();
            }
        }
        numShelves = shelves.size();
        calcFitness();
    }

    public void goBack(int shelfNum){
        //remove first book and put it on the shelf before
        if(shelfNum>0 & shelfNum<shelves.size()){
            Book b = shelves.get(shelfNum).getFirst();
            shelves.get(shelfNum-1).addLast(b);
        }
    }

    public void goForward(int shelfNum){
        //remove last book and put it on shelf after
        if(shelfNum>=0 & shelfNum<shelves.size()-1){
            Book b = shelves.get(shelfNum).getLast();
            shelves.get(shelfNum+1).addFirst(b);
        }
    }

    public int getFitness(){
        return fitness;
    }
    public int getShelves(){
        return numShelves;
    }

    private void calcFitness() {
        double currScore = 0;
        for (int i=0; i<shelves.size()-2; i++){
            if(shelves.get(i).getLast().sameCol(shelves.get(i).getFirst()) & shelves.get(i).getLast().sameCol(shelves.get(i+1).getFirst())){
                currScore = 914 - shelves.get(i).getSize();
            } else{
                currScore = 610 - shelves.get(i).getSize();
                //I'm too lazy to import the Math class so this takes the absolute value
                if(currScore<0){
                    currScore = 0-currScore;
                }
            }
            if(shelves.get(i).getSize()>914){
                currScore += 1000;
            }
            if(shelves.get(i).getLast().sameCol(shelves.get(i+1).getFirst()) & !shelves.get(i).getLast().sameCol(shelves.get(i).getFirst())){
                currScore+=5;
            }
            fits.add((int)currScore);
            System.out.println("Size: " + shelves.get(i).getSize());
            System.out.println("Score: " + fits.get(i));
            fitness+= (int)currScore;
        }
        fitness = fitness / numShelves;
    }

    @Override
    public String toString(){
        return shelves.toString();
    }
}
