import java.util.ArrayList;
public class Populous{
   Chromosome[] chromosomes;
   Class<Chromosome> theType;
   
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
}