import java.util.*;

public class algo {
    List<Library> pop = new ArrayList<Library>();

    public void cross(Library i1, Library i2){
        for (int i = 0; i<i1.getShelves(); i++){
            //idk how to do this without it being O(n^2) time
        }
    }

    public void mutate(Library l){
        for(int i = 0; i<l.getShelves(); i++){
            //generate random number
            //if random number * fitness of the shelf > num, pick random book and split new shelf
        }
    }

}
