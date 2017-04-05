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
      
      // Needs four variabels because otherwise List will remove based on
      // index instead of object
      int chromosomeIndex1, chromosomeIndex2, listIndex1, listIndex2;
      int theSize = indexesUsed.size();
      Chromosome[] thePair;
      while(0 < theSize){
         listIndex1 = Math.abs(rand.nextInt(theSize));
         chromosomeIndex1 = indexesUsed.get(listIndex1);
         indexesUsed.remove(listIndex1);
         
         theSize = indexesUsed.size();
         
         listIndex2 = Math.abs(rand.nextInt(theSize));
         chromosomeIndex2 = indexesUsed.get(listIndex2);
         indexesUsed.remove(listIndex2);
         
         theSize = indexesUsed.size();
         
         thePair = chromosomes[chromosomeIndex1].mate(chromosomes[chromosomeIndex2]);
         chromosomes[chromosomeIndex1] = thePair[0];
         chromosomes[chromosomeIndex2] = thePair[1];
      }
   }
}