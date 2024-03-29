import java.util.*;

public class algo {
    List<Library> pop;
    Random random = new Random();

    public algo(List<Library> list){
        pop = new ArrayList<Library>(list); //this might be an issue i hate copy constructors why did i do java
    }

    public Library getTop(){
        Collections.sort(pop);
        return pop.get(0);
    }

    public void run(){
        //kill();
        System.out.println("Running " + pop.size());
        //int i = 0;
        int j = 0;
        while(j<9){
            System.out.println("WHILE: Running");
            cross(pop.get(j),pop.get(j+1)); //cross the 1st and 2nd libs, then the 3rd and 4th, so on and so forth
            //i+=2; //tbh i might need to hardcode the size bc i think cross adds them back into pop
            j+=2;
        }
        for(Library l : pop){
            double rand = random.nextDouble();
            System.out.println("Algo: Works Random number " + Double.toString(rand));
            if(rand<0.05 && rand>0.0){ 
                System.out.println("Attempt to mutate");
                mutate(l);

            }
            smallMutate(l); 
        }
        Collections.sort(pop); //sort by fitness
        System.out.println("Top Fitness: " + pop.get(0).getFitness());
        kill();
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
        System.out.println("Attempt to Cross");
        //List<Book> results = i1.getFirstsCopy();
        //results.retainAll(i2.getFirsts()); //find all the shelves that have the same first book in both libs
        //System.out.println(results.size()); //see how many there were
        //if(results.size()>0){ //cross only if they had some in common
            Library l1 = new Library(i1); //keep the ogs fine (copy constructor issue mayhaps???)
            Library l2 = new Library(i2);
            //TODO: RANDOMLY SELECT A BOOK
            int r = random.nextInt(l1.getNumShelves());
            int ra = random.nextInt(l1.getShelf(r).getNumBooks());
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + r + " >>> " + ra );
            Book b = l1.getShelf(r).getBook(ra);
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> PICK BOOK " + r + " >>> " + ra );
            //for (Book b : results){
                Library result1 = new Library(l1,l2,b); //constructor that makes a new library out of the 
                //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> LIB1 " + r + " >>> " + ra );
                Library result2 = new Library(l2,l1,b); //two libraries but swap their second halves at b
                l1 = new Library(result1);
                l2 = new Library(result2);
            //}
            pop.add(l1); //might be an issue from earlier
            pop.add(l2);
        }   
    public void smallMutate(Library lib){
        if(random.nextDouble()<0.25){
            for(int i=0; i<lib.getNumShelves()-1; i++){
                if(random.nextDouble()<0.05){
                    lib.goForward(i);
                }
            }
            for(int i=1; i<lib.getNumShelves(); i++){
                if(random.nextDouble()<0.05){
                    lib.goBack(i);
                }
            }
        }
    }

    public void mutate(Library lib){
        System.out.println("Attempt to Mutate - method");
        lib.removeEmpty();
        
        // Calculate the total fitness score
        //tbh most of this was AI generated but it works to pick a random shelf weighted by fitness 
        //(a worse one is more likely to get mutated)

        //I think this was buggy so I changed it to below but idk -> double totalFitnessScore = l.getShelves().stream().mapToDouble(Shelf::getFitness).sum();
        double totalFitnessScore = 0.0;
        for(int i : lib.getFits()){
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
        int cumulativeFitness = 0;
        int i = 0;
        for (Shelf shelf : lib.getShelves()) {
            System.out.println("i: " + i);
            //System.out.println("Shelf fit: " + shelf.getFitness());
            cumulativeFitness += lib.getFits().get(i);
            //System.out.println("Cumulative: " + cumulativeFitness);
            if (randomValue < cumulativeFitness) {
                System.out.println("break");
                break;
            }
            i++;
        }
        //selectedShelf = lib.getShelf(0); // Will pick the first shelf always
        // Now, 'selectedShelf' contains the shelf selected based on its fitness score
        //System.out.println("Selected Shelf: " + selectedShelf);
        System.out.println("number of books on shelf before: " + lib.getShelf(i).getNumBooks());
            int idx = i;
            Shelf s = lib.shelves.get(idx);
            System.out.println("Algo: books on shelf " + s.getNumBooks());
            
            lib.insertShelfAfter(idx);
            if(lib.getShelf(i).getNumBooks()>1){
                //for(int j = selectedShelf.getNumBooks()-1; j>selectedShelf.getNumBooks()-3 ; j--){ //move 5 books
                           //why did this move half the books? 
                int num = lib.getShelf(i).getNumBooks();

                for(int j = 0; j<=num/2; j++){ //split the selected shelf in half to a new shelf after it
                    System.out.println("On shelf " + idx + " with " + num + " books to start and pulling "+ (num/2 + 1) + "books");
                    lib.goForward(idx);
                }
            lib.removeEmpty();
            lib.reCalcFirsts();
            lib.reCalcFitness();
            }
            System.out.println("number of books on shelf after: " + lib.getShelf(i).getNumBooks());
            System.out.println("number of books on shelf 2 after: " + lib.getShelf(idx+1).getNumBooks());
        /*} else{ 
            System.out.println("Problem in InsertShelf");
        }*/
    }

}
