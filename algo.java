import java.util.*;

public class Algo {
    List<Library> pop = new ArrayList<Library>();
    Random random = new Random();

    public Algo(List<Library> list){
        pop = list;
    }

    public void run(){
        kill();
        int i = 0;
        int j = pop.size()-1;
        while(i<j){
            cross(pop.get(i),pop.get(j));
        }
        for(Library l : pop){
            if(random.nextDouble()<0.05){
                mutate(l);
            }
        }
        Collections.sort(pop);
        System.out.println(pop.get(0).getFitness());
    }

    public void kill(){
        Collections.sort(pop);
        Queue<Library> newPop = new LinkedList<Library>();
        for (int i = 0; i<pop.size()/2; i++){
            newPop.offer(pop.get(i));
        }
        pop.clear();
        while(newPop.size()!=0){
            pop.add(newPop.poll());
        }
    }
    public void cross(Library i1, Library i2){
        List<Book> results = i1.getFirstsCopy();
        results.retainAll(i2.getFirsts());
        Library l1 = new Library(i1);
        Library l2 = new Library(i2);
        for (Book b : results){
            Library result1 = new Library(l1,l2,b);
            Library result2 = new Library(l2,l1,b);
            l1 = new Library(result1);
            l2 = new Library(result2);
        }
        pop.add(l1);
        pop.add(l2);
    }

    public void mutate(Library l){
        // Calculate the total fitness score

        double totalFitnessScore = l.getShelves().stream().mapToDouble(Shelf::getFitness).sum();

        // Generate a random number in the range [0, totalFitnessScore)
        
        double randomValue = random.nextDouble() * totalFitnessScore;

        // Perform weighted random selection
        Shelf selectedShelf = null;
        double cumulativeFitness = 0.0;
        for (Shelf shelf : l.getShelves()) {
            cumulativeFitness += shelf.getFitness();
            if (randomValue < cumulativeFitness) {
                selectedShelf = shelf;
                break;
            }
        }

        // Now, 'selectedShelf' contains the shelf selected based on its fitness score
        System.out.println("Selected Shelf: " + selectedShelf);
    }

}
