import java.util.*;

public class Algo {
    List<Library> pop;
    Random random = new Random();

    public Algo(List<Library> list){
        pop = new ArrayList<Library>(list); //this might be an issue i hate copy constructors why did i do java
    }

    public void run(){
        //kill();
        int i = 0;
        int j = 1;
        while(j<pop.size()){
            cross(pop.get(i),pop.get(j)); //cross the 1st and 2nd libs, then the 3rd and 4th, so on and so forth
            i+=2; //tbh i might need to hardcode the size bc i think cross adds them back into pop
            j+=2;
        }
        for(Library l : pop){
            double rand = random.nextDouble();
            if(rand<0.05 && rand>0.0){ 
                System.out.println("mutating...");
                mutate(l);
            } 
        }
        Collections.sort(pop); //sort by fitness
        System.out.println("Top Fitness: " + pop.get(0).getFitness());
        for(Library l : pop){
            System.out.println(l.getFitness()); //just to check the fits
        }
    }

    public void kill(){
        Collections.sort(pop);
        for (int i = pop.size()-1; i>10; i--){
            pop.remove(i); //remove the 2nd half of population
        }
    }

    //the problem child function
    public void cross(Library i1, Library i2){ 
        List<Book> results = i1.getFirstsCopy();
        results.retainAll(i2.getFirsts()); //find all the shelves that have the same first book in both libs
        System.out.println(results.size()); //see how many there were
        if(results.size()>0){ //cross only if they had some in common
            Library l1 = new Library(i1); //keep the ogs fine (copy constructor issue mayhaps???)
            Library l2 = new Library(i2);
            for (Book b : results){
                Library result1 = new Library(l1,l2,b); //constructor that makes a new library out of the 
                Library result2 = new Library(l2,l1,b); //two libraries but swap their second halves at b
                l1 = new Library(result1);
                l2 = new Library(result2);
            }
            pop.add(l1); //might be an issue from earlier
            pop.add(l2);
        }   
    }

    public void mutate(Library l){
        l.removeEmpty();
        // Calculate the total fitness score
        //tbh most of this was AI generated but it works to pick a random shelf weighted by fitness 
        //(a worse one is more likely to get mutated)

        //I think this was buggy so I changed it to below but idk -> double totalFitnessScore = l.getShelves().stream().mapToDouble(Shelf::getFitness).sum();
        double totalFitnessScore = 0.0;
        for(int i : l.getFits()){
            totalFitnessScore += i;
        }
        // Generate a random number in the range [0, totalFitnessScore)
        double rand = random.nextDouble();
        while(rand==0){
            rand = random.nextDouble();
        }
        double randomValue = rand * totalFitnessScore; //a random value rand% of the way through the shelves
        //System.out.println("randomValue: " + randomValue);
        // Perform weighted random selection
        Shelf selectedShelf = new Shelf();
        int cumulativeFitness = 0;
        int i = 0;
        for (Shelf shelf : l.getShelves()) {
            //System.out.println("i: " + i);
            i++;
            //System.out.println("Shelf fit: " + shelf.getFitness());
            cumulativeFitness += shelf.getFitness();
            //System.out.println("Cumulative: " + cumulativeFitness);
            if (randomValue < cumulativeFitness) {
                selectedShelf = shelf;
                System.out.println(randomValue + " " + cumulativeFitness + selectedShelf);
                break;
            }
        }
        System.out.println(l.getFitness());
        // Now, 'selectedShelf' contains the shelf selected based on its fitness score
        //System.out.println("Selected Shelf: " + selectedShelf);
        if(l.insertShelfAfter(selectedShelf)){
            int idx = l.getShelfIdx(selectedShelf);
            for(int j = selectedShelf.getNumBooks()-1; j>selectedShelf.getNumBooks()/2; j--){ //split the selected shelf in half to a new shelf after it
                l.goForward(idx);
            }
            l.reCalcFirsts();
            //System.out.println(l.getFitness());
            l.reCalcFitness();
            System.out.println(l.getFitness());
        } else{ 
            System.out.println("uh oh spaghetti-ohs");
        }
    }

}
