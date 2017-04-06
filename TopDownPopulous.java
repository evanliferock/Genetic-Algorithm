public class TopDownPopulous extends Populous{
   TopDownPopulous(Chromosome init, int numValues){
      super(init, numValues);
   }
   /**
    * Will run the Top-Down algorithm for pairing
    */
   protected void nextGeneration(){
      for(int i = 0; i < chromosomes.length - 1; i++){
        int best = getBestIndex(i);
        Chromosome temp = chromosomes[i];
        chromosomes[i] = chromosomes[best];
        chromosomes[best] = temp;
      }
      for(int i = 0; i < chromosomes.length; i+=2){
        Chromosome[] thePair = chromosomes[i].mate(chromosomes[i+1]);
        chromosomes[i] = thePair[0];
        chromosomes[i+1] = thePair[1];
      }
   }

   //Out of the chromosomes with index startIndex or larger, get
   //the index of the one with the best cost
   private int getBestIndex(int startIndex){
      int bestCost = ((Route) chromosomes[startIndex]).cost();
      int bestIndex = startIndex;
      for(int i = startIndex + 1; i < chromosomes.length; i++){
         int currentCost = ((Route) chromosomes[i]).cost();
         if(currentCost < bestCost){
            bestIndex = i;
            bestCost = currentCost;
         }
      }
      return bestIndex;
   }

}
