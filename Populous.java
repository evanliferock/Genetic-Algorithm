import java.util.ArrayList;
public abstract class Populous{
   Chromosome[] chromosomes;
   Chromosome base;
   
   /**
    * Pass in a Chromosome Class and the number of Chomosomes to have
    * Initializes the type of Chromosome working with and fills the 
    * Chromosome array
    */
   Populous(Chromosome init, int numValues){
      base = init;
      chromosomes = new Chromosome[numValues];
      for(int i = 0; i < numValues; i++)
         chromosomes[i] = base.createNew();
   }
   
   /**
    * Generates the next generation of Chromosomes
    * Runs the mating algorithm
    */
   protected abstract void nextGeneration();
   
   /**
    * Generates the specified number of generations
    */
   public void runGenerations(int numTimes){
      for(int i = 0; i < numTimes; i++)
         nextGeneration();
   }
   
   public Chromosome getBest(){
      int bestCost = chromosomes[0].cost();
      int bestIndex = 0;
      for(int i = 1; i < chromosomes.length; i++){
         int currentCost = chromosomes[i].cost();
         if(currentCost < bestCost){
            bestIndex = i;
            bestCost = currentCost;
         }
      }
      return chromosomes[bestIndex];
   }
}
