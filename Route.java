import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

public abstract class Route extends Chromosome{
   char[] nodes;
   final int[][] matrix;
   char[] route;
   
   Route(int[][] theMatrix){
      matrix = theMatrix;
      nodes = new char[matrix.length];
      for(int i = 0; i < nodes.length; i++){
         nodes[i] = (char)(97 + i);
      }
      
      route = nodes.clone();
      
      //copied from http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
      //because I was too lazy to write it.
      char temp;
      int index;
      Random random = new Random();
      for (int i = route.length - 1; i > 0; i--){
        index = random.nextInt(i + 1);
        temp = route[index];
        route[index] = route[i];
        route[i] = temp;
      }
      for(char a : route)
         System.out.println(a);
   }
   
   public int cost(){
      //for(int i = 0;
      return 0;
   }

}