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
	   returnArray[0] = this.createNew();
	   returnArray[1] = this.createNew();
	   char[] oldRoute = this.route;
	   
	   int crossover = r.nextInt(oldRoute.length);
	   for(int i = 0; i < oldRoute.length; i++){
		   if(i < crossover){
			   ((Route)returnArray[0]).route[i] = oldRoute[i];
			   ((Route)returnArray[1]).route[i] = ((Route)b).route[i];
		   }else{
			   ((Route)returnArray[0]).route[i] = ((Route)b).route[i];
			   ((Route)returnArray[1]).route[i] = oldRoute[i];
		   }
	   }
      return returnArray;
   }
}