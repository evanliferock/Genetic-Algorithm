import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

public abstract class Route extends Chromosome{
   final char[] nodes;
   final int[][] matrix;
   protected char[] route;
   
   Route(int[][] theMatrix){
      matrix = theMatrix;
      nodes = new char[matrix.length];
      for(int i = 0; i < nodes.length; i++){
         nodes[i] = (char)(97 + i);
      }
      route = nodes.clone();
      randomizeRoute();
   }
   
   public Route(Route otherRoute){
      this.nodes = otherRoute.nodes;
      this.matrix = otherRoute.matrix;
      this.route = otherRoute.route.clone();
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
   
   public Chromosome createNew(){
      Route a = this;
      a.randomizeRoute();
      return a;
   }
   
   protected void randomizeRoute(){
      char temp;
      int index;
      Random random = new Random();
      for (int i = route.length - 1; i > 0; i--){
        index = random.nextInt(i + 1);
        temp = route[index];
        route[index] = route[i];
        route[i] = temp;
      }
   }
}