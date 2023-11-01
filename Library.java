import java.util.*;

public class Library implements Comparable<Library>{
    private ArrayList<Shelf> shelves = new ArrayList<>();
    private int numShelves;
    private int fitness;
    private ArrayList<Integer> fits = new ArrayList<>();
    private ArrayList<Book> firsts = new ArrayList<>();


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

        int l1idxSplit = other1.getShelfIdx(getShelf(split));
        int l2idxSplit = other2.getShelfIdx(getShelf(split));

        for(int i=0; i<l1idxSplit; i++){
            addShelf(new Shelf(other1.getShelf(i)));
        }
        for(int i=l2idxSplit; i<other2.numShelves; i++){
            addShelf(new Shelf(other2.getShelf(i)));
        }
        numShelves = shelves.size();
        calcFirsts();
        calcFitness();
        System.out.println(fits);
    }

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

    private void calcFirsts(){
        for (Shelf s : shelves){
            firsts.add(new Book(s.getFirst()));
        }
    }
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
    public int getNumShelves(){
        return numShelves;
    }

    public ArrayList<Shelf> getShelves(){
        return shelves;
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
            shelves.get(i).setFitness(currScore);
            //System.out.println("Size: " + shelves.get(i).getSize());
            //System.out.println("Score: " + fits.get(i));
            fitness+= (int)currScore;
        }
        fitness = fitness / numShelves;
    }
    public List<Integer> getFits(){
        return fits;
    }
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
