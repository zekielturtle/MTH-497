import java.util.*;

public class Library implements Comparable<Library>{
    private ArrayList<Shelf> shelves = new ArrayList<>();
    private int numShelves;
    private int fitness;
    private ArrayList<Integer> fits = new ArrayList<>();
    private ArrayList<Book> firsts = new ArrayList<>();

    //you ready for copy constructors galore??

    //regular constructor that takes a list of books and makes shelves out of them, splitting to a new shelf on the minSize
    public Library(ArrayList<Book> books, int minSize){ 
        Shelf currShelf = new Shelf();
        //shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>minSize){
                //System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                firsts.add(currShelf.getFirst());
                currShelf = new Shelf();
            }
        }
        numShelves = shelves.size();
        calcFitness();
    }

    //cross constructor to make a new library based on 2 other libraries and where they should mix
    public Library(Library other1, Library other2, Book split){
        //int idx = 0;
        /*Shelf split1 = other1.getShelf(split);
        Shelf split2 = other2.getShelf(split);
        Shelf currShelf = other1.shelves.get(idx);
        while(currShelf.getFirst() != split1.getFirst()){
            this.shelves.add(currShelf);
            idx++;
            currShelf = other1.shelves.get(idx);
        }
        idx = other2.getShelfIdx(split2);
        while(idx < other2.numShelves - 1){
            this.shelves.add(currShelf);
            idx++;
            currShelf = other2.shelves.get(idx);
        }
        this.shelves.add(currShelf);*/

        int l1idxSplit = other1.getShelfIdx(other1.getShelf(split));
        int l2idxSplit = other2.getShelfIdx(other2.getShelf(split)); //this feels wonky idk

        for(int i=0; i<l1idxSplit; i++){
            addShelf(new Shelf(other1.getShelf(i))); //add shelves from l1 until the split
        }
        for(int i=l2idxSplit; i<other2.numShelves; i++){ //switch to l2 and add shelves until the end
            addShelf(new Shelf(other2.getShelf(i)));
        }
        numShelves = shelves.size();
        calcFirsts();
        calcFitness();
        //System.out.println(fits);
    }

    //copy constructor yippee
    public Library(Library other){
        for (Shelf element : other.shelves) {
            shelves.add(new Shelf(element));
        }
        this.numShelves = other.numShelves;
        this.fitness = other.fitness;
        for (int element : other.fits) {
            fits.add(Integer.valueOf(element));
        }
        //System.out.println("Duplicating this array: " + other.fits);
        //System.out.println("To this array: " + fits);
        for (Book element : other.firsts) {
            firsts.add(new Book(element));
        }
        /*this.shelves = other.shelves;
        
        
        this.fits = other.fits;
        this.firsts = other.firsts;*/
        //System.out.println("Duplicating " + other.numShelves + " to new library");
        //System.out.println(this.numShelves + " in new library");
    }

    //self explanatory, idk why i needed it necessarily but it's here
    public List<Book> getFirstsCopy(){
        List<Book> newFirsts = new ArrayList<>();
        for(Book b : firsts){
            newFirsts.add(new Book(b));
        }
        return newFirsts;
    }

    public List<Book> getFirsts(){
        return firsts;
    }

    //default constructor with no minSize (auto-set to 610)
    public Library(ArrayList<Book> books){
        Shelf currShelf = new Shelf();
        //shelves = new ArrayList<>();
        for (Book b : books){
            currShelf.addSize(b.getSize());
            currShelf.addLast(b);
            if (currShelf.getSize()>610){
                //System.out.println("shelf size: " + currShelf.getSize());
                //System.out.println(currShelf);
                shelves.add(currShelf);
                firsts.add(currShelf.getFirst());
                currShelf = new Shelf();
            }
        }
        numShelves = shelves.size();
        calcFitness();
    }

    public Shelf getShelf(int i){
        return shelves.get(i);
    }

    //get a list of all the first books of shelves
    private void calcFirsts(){ //probably should rework so this can be private
        firsts.clear();
        for (Shelf s : shelves){
            firsts.add(new Book(s.getFirst()));
        }
    }

    public void removeEmpty(){
        List<Shelf> empty = new ArrayList<>();
        int i = 0;
        for (Shelf s : shelves){
            if(s.getBooks().isEmpty()){
                empty.add(s);
                System.out.println("goteem");
                numShelves--;
                fits.remove(i);
                firsts.remove(i);
            }
            i++;
        }
    }

    public void reCalcFirsts(){ //probably should rework so this can be private
        firsts.clear();
        List<Shelf> empty = new ArrayList<>();
        for (Shelf s : shelves){
            if(s.getBooks().isEmpty()){
                empty.add(s);
                System.out.println("goteem");
            } else{
                firsts.add(new Book(s.getFirst()));
            }
        }
        shelves.removeAll(empty);
        numShelves -= empty.size();
    }

    //search for a shelf based on a first book (binary search)
    public Shelf getShelf(Book b){
        int l = 0, r = firsts.size() - 1;
        int m = -1;
        System.out.println("First books: " + firsts + " looking for book: " + b);
        while (l <= r) {
            System.out.println(l + " " + r);
            m = l + (r - l) / 2;
            //Check if x is present at end points
            /*if(firsts.get(l).compareTo(b) == 0){
                System.out.println("101");
                return shelves.get(l);
            }
            if(firsts.get(r).compareTo(b) == 0){
                System.out.println("106");
                return shelves.get(r);
            }*/
            // Check if x is present at mid
            if (firsts.get(m).compareTo(b) == 0){
                System.out.println("111");
                return shelves.get(m);
            }
            // If x greater, ignore left half
            if (firsts.get(m).compareTo(b) < 0){
                System.out.println("116, ignoring left half");
                l = m + 1;
                System.out.println("New values: " + l + " " + r);
            }
            // If x is smaller, ignore right half
            else{
                System.out.println("122, ignoring right half");
                r = m - 1;
                System.out.println("New values: " + l + " " + r);
            }
        }
        return shelves.get(m);
    }

    //search for a shelf idx (also binary search)
    public int getShelfIdx(Shelf s){
        int l = 0, r = firsts.size() - 1;
        int m = -1;
        Book b = s.getFirst();
        while (l <= r) {
            m = l + (r - l) / 2;
 
            // Check if x is present at mid
            if (firsts.get(m) == b){
                break;
            }
            // If x greater, ignore left half
            if (firsts.get(m).compareTo(b) < 0){
                l = m + 1;
            }
            // If x is smaller, ignore right half
            else{
                r = m - 1;
            }
        }
        return m;
    }

    //these next two are also problem children
    public void goBack(int shelfNum){
        //remove first book of shelf #shelfNum and put it on the shelf before
        if(shelfNum>0 && shelfNum<shelves.size()){
            Book b = shelves.get(shelfNum).getFirst();
            shelves.get(shelfNum-1).addLast(b);
        }else if(shelfNum==0){
            System.out.println("there's no shelf to go back to");
        }else{
            System.out.println("how did you even manage this");
        }
    }

    public void goForward(int shelfNum){
        System.out.println("...IN move a book method forward....");
        //remove last book and put it on shelf after
        if(shelfNum>=0 && shelfNum<shelves.size()-1){
            if(shelves.get(shelfNum+1).getSize()>0 & shelves.get(shelfNum).getSize()>0){
                Book b = shelves.get(shelfNum).getLast();
                shelves.get(shelfNum+1).addFirst(b);
                System.out.println("Moved the book...............");
            }
        }
    }

    public int getFitness(){
        return fitness;
    }
    public int getNumShelves(){
        return numShelves;
    }

    public ArrayList<Shelf> getShelves(){
        return shelves;
    }

    //quick summary of how the fitness is calculated: if its a shelf that isn't entirely 
    //in the middle of a collection, the score is the distance to 610mm; if it is, it's the distance to 0mm
    private void calcFitness() { //also rework to make it private or at least protected
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
            shelves.get(i).setFitness(currScore);
            //System.out.println("Size: " + shelves.get(i).getSize());
            //System.out.println("Score: " + fits.get(i));
            fitness+= (int)currScore;
        }
        fitness = fitness / numShelves; //average it over all the shelves
    }

    public void reCalcFitness(){
        fits.clear();
        fitness = 0;
        calcFitness();
    }

    public boolean insertShelfAfter(Shelf s){
        for(int i = 0; i<numShelves; i++){
            if(shelves.get(i).equals(s)){
                shelves.add(i, new Shelf());
                numShelves++;
                return true;
            }
        }
        return false;
    }
    
    public List<Integer> getFits(){
        return fits;
    }

    //add a new shelf to the end (used in constructor)
    public void addShelf(Shelf s){
        numShelves++;
        shelves.add(s);
        firsts.add(s.getFirst());
    }

    @Override
    public String toString(){
        return shelves.toString();
    }

    @Override 
    public int compareTo(Library o){
        return this.fitness - o.fitness;
    }
}
