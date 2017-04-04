import java.util.*;
import java.lang.*;

public class SinglePointRoute extends Route{
   SinglePointRoute(int[][] theMatrix){
      super(theMatrix);
   }
   
   /**
    * Will run the Single point crossover method of mating
    * As long as b is an instance of Route
    */
   public Chromosome[] mate(Chromosome b){
	   Chromosome[] returnArray = new Chromosome[2];
	   Random r = new Random();
	   Chromosome[0] = new SinglePointRoute(this.matrix);
	   Chromosome[1] = new SinglePointRoute(b.matrix);
	   oldRoute = this.route;
	   
	   int crossover = r.nextInt(oldRoute.length());
	   for(int i = 0; i < oldRoute.length(); i++){
		   if(i < crossover){
			   Chromosome[0].route[i] = oldRoute[i];
			   Chromosome[1].route[i] = b.route[i];
		   }else{
			   Chromosome[0].route[i] = b.route[i];
			   Chromosome[1].route[i] = oldRoute[i];
		   }
	   }
      return returnArray;
   }
}