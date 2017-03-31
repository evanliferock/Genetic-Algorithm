import java.util.ArrayList;
public class Populous{
   Chromosome[] chromosomes;
   Class<Chromosome> theType;
   
   /**
    * Pass in a Chromosome Class and the number of Chomosomes to have
    * Initializes the type of Chromosome working with and fills the 
    * Chromosome array
    */
   Populous(Class<Chromosome> theClass, int numValues){
      try{
         theType = theClass;
         chromosomes = new Chromosome[numValues];
         for(int i = 0; i < numValues; i++)
            chromosomes[i] = theType.getConstructor().newInstance();
      }catch (Exception e){
         System.out.println("Class passed into Populous caused Error\n" + e.getMessage());
      }
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
