import java.util.ArrayList;
public class Populous{
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
    */
   private void nextGeneration(){
      int i = 5;
   }
   
   /**
    * Generates the specified number of generations
    */
   public void runGenerations(int numTimes){
      for(int i = 0; i < numTimes; i++)
         nextGeneration();
   }
}
