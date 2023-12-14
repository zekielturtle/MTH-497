import java.util.*;

public class Algo {
    List<Library> pop = new ArrayList<Library>();
    Random random = new Random();

    public Algo(List<Library> list){
        pop = list;
    }

    public void run(){
        //kill();
        //System.out.println("Algo 13");
        int i = 0;
        int j = 1;
        System.out.println(pop.size());
        while(j<pop.size()){
            cross(pop.get(i),pop.get(j));
            //System.out.println(i+ " Algo 18 j: " + j);
            i+=2;
            j+=2;
        }
        for(Library l : pop){
            double rand = random.nextDouble();
            if(rand<0.95 && rand>0.0){
                //System.out.println("yes mutate");
                mutate(l);
                //System.out.println("Algo 23");
            } 
        }
        Collections.sort(pop);
        System.out.println("Top Fitness: " + pop.get(0).getFitness());
        for(Library l : pop){
            System.out.println(l.getFitness());
        }
    }

    public void kill(){
        Collections.sort(pop);
        for (int i = pop.size()-1; i>10; i--){
            pop.remove(i);
        }
    }
    public void cross(Library i1, Library i2){
        List<Book> results = i1.getFirstsCopy();
        results.retainAll(i2.getFirsts());
        System.out.println(results.size());
        if(results.size()>0){
            Library l1 = new Library(i1);
            System.out.println(l1.getFits());
            Library l2 = new Library(i2);
            for (Book b : results){
                Library result1 = new Library(l1,l2,b);
                Library result2 = new Library(l2,l1,b);
                System.out.println(result1.getFits());
                l1 = new Library(result1);
                l2 = new Library(result2);
            }
            pop.add(l1);
            pop.add(l2);
        }   
    }

    public void mutate(Library l){
        // Calculate the total fitness score

        //double totalFitnessScore = l.getShelves().stream().mapToDouble(Shelf::getFitness).sum();
        double totalFitnessScore = 0.0;
        for(int i : l.getFits()){
            totalFitnessScore += i;
        }
        //System.out.println(totalFitnessScore);
        // Generate a random number in the range [0, totalFitnessScore)
        double rand = random.nextDouble();
        while(rand==0){
            rand = random.nextDouble();
        }
        double randomValue = rand * totalFitnessScore;
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
                break;
            }
        }

        // Now, 'selectedShelf' contains the shelf selected based on its fitness score
        //System.out.println("Selected Shelf: " + selectedShelf);
        if(l.insertShelfAfter(selectedShelf)){
            int idx = l.getShelfIdx(selectedShelf);
            for(int j = selectedShelf.getNumBooks()-1; j>selectedShelf.getNumBooks()/2; j--){
                l.goForward(idx);
            }
        } else{ System.out.println("uh oh spaghetti-ohs");}
    }

}
