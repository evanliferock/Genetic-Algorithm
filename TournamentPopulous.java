import java.util.ArrayList;
import java.util.Random;

public class TournamentPopulous extends Populous{
   TournamentPopulous(Chromosome init, int numValues){
      super(init, numValues);
   }
   /**
    * Will run the Tournament algorithm for pairing
    */
   protected void nextGeneration(){
      ArrayList<Integer> indexesUsed = new ArrayList<Integer>();
      for(int i = 0; i < chromosomes.length; i++){
         indexesUsed.add(i);
      }
      Random rand = new Random();
      
      int indexOne, indexTwo;
      int theSize = indexesUsed.size();
      Chromosome[] thePair;
      while(0 < theSize){
         indexOne = indexesUsed.get(Math.abs(rand.nextInt() % theSize));
         indexesUsed.remove(indexOne);
         theSize = indexesUsed.size();
         indexTwo = indexesUsed.get(Math.abs(rand.nextInt() % theSize));
         indexesUsed.remove(indexTwo);
         thePair = chromosomes[indexOne].mate(chromosomes[indexTwo]);
         theSize = indexesUsed.size();
         chromosomes[indexOne] = thePair[0];
         chromosomes[indexTwo] = thePair[1];
      }
   }
}