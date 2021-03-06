import java.util.Collections;
import java.util.Arrays;
import java.util.Random;
import java.lang.reflect.Constructor;

public abstract class Route extends Chromosome{
   final char[] nodes;
   final int[][] matrix;
   protected char[] route;
   
   Route(int[][] theMatrix, double mutRate){
      super(mutRate);
      matrix = theMatrix;
      nodes = new char[matrix.length];
      for(int i = 0; i < nodes.length; i++){
         nodes[i] = (char)(97 + i);
      }
      route = nodes.clone();
      randomizeRoute();
   }
   
   public String getRoute(){
      return new String(route);
   }
   
   protected void mutate(){
      Random r = new Random();
      //This controls the mutation factor.
      if(r.nextInt(100) >= 99 - (mutationRate * 100)){
         //Swap the middle destinations.
         //This may seem like a random mutation, but should actually work pretty great
         int secondIndex, firstIndex = r.nextInt(route.length);
         do{
            secondIndex = r.nextInt(route.length);
         }while(firstIndex == secondIndex);
         
         char tempC = route[firstIndex];
         route[firstIndex] = route[secondIndex];
         route[secondIndex] = tempC;
      }
   }
   
   public int cost(){
      int cost = 0;
      //cost of route
      for(int i = 1; i < route.length; i++){
         cost += matrix[route[i-1] - 97][route[i] - 97];
      }
      // cost for returning back home
      cost += matrix[route[0] - 97][route[route.length - 1] - 97];
      return cost;
   }
   
   // modified code from
   // http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
   protected void randomizeRoute(){
      char temp;
      int index;
      Random random = new Random();
      for (int i = route.length - 1; i > 0; i--){
        index = random.nextInt(route.length);
        temp = route[index];
        route[index] = route[i];
        route[i] = temp;
      }
   }
}