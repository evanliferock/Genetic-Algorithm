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
      ArrayList<Chromosome> bestChromosomes = new ArrayList<Chromosome>();
      for(int i = 0; i < chromosomes.length; i++){
         indexesUsed.add(i);
      }
      Random rand = new Random();
      
      // Needs four variabels because otherwise List will remove based on
      // index instead of object
      int listIndex1, listIndex2, theSize;
      for(int i = 0; i < chromosomes.length; i+=2) {
         theSize = indexesUsed.size();
         listIndex1 = Math.abs(rand.nextInt(theSize));
         listIndex2 = Math.abs(rand.nextInt(theSize));
         
         if(chromosomes[indexesUsed.get(listIndex1)].cost() <
            chromosomes[indexesUsed.get(listIndex2)].cost()){
            bestChromosomes.add(chromosomes[indexesUsed.get(listIndex1)]);
            indexesUsed.remove(listIndex1);
         }else{
            bestChromosomes.add(chromosomes[indexesUsed.get(listIndex2)]);
            indexesUsed.remove(listIndex2);
         }
      }
      Chromosome[] thePair;
      theSize = bestChromosomes.size();
      for(int i = 0; i < bestChromosomes.size(); i+=2){
         thePair = bestChromosomes.get(i).mate(bestChromosomes.get(i+1));
         chromosomes[i] = thePair[0];
         chromosomes[i + theSize] = thePair[1];
         
         thePair = bestChromosomes.get(i + 1).mate(bestChromosomes.get(i));
         chromosomes[i + 1] = thePair[0];
         chromosomes[i + theSize + 1] = thePair[1];
         
      }
   }
}