import java.util.Random;

public class CycleCrossoverRoute extends Route{
	CycleCrossoverRoute(int[][] theMatrix){
		super(theMatrix);
	}

   public Chromosome createNew(){
      return new CycleCrossoverRoute(matrix);
   }
	/**
	* Will run the Single point crossover method of mating
	* As long as b is an instance of Route
	*/
	public Chromosome[] mate(Chromosome b){
		Chromosome[] returnArray = new Chromosome[2];
		Random r = new Random();
		returnArray[0] = new CycleCrossoverRoute(this.matrix);
		returnArray[1] = new CycleCrossoverRoute(this.matrix);
		char[] oldRoute = this.route;
		char[] otherRoute = ((Route)b).route;

		int crossover = 0;
		boolean cycling = true;
		char tempChar = oldRoute[crossover];
		oldRoute[crossover] = otherRoute[crossover];
		otherRoute[crossover] = tempChar;
		while(cycling){
			cycling = false;
			for(int i = 0; i < oldRoute.length; i++){
				if(i == crossover)
					continue;
				if(oldRoute[i] == oldRoute[crossover]){
					crossover = i;
					tempChar = oldRoute[crossover];
					oldRoute[crossover] = otherRoute[crossover];
					otherRoute[crossover] = tempChar;
					cycling = true;
					break;
				}
			}
		}
    //This controls the mutation factor.
    if(r.nextInt(100) >= 92){
      //Swap the middle destinations.
      //This may seem like a random mutation, but should actually work pretty great
      char tempC = oldRoute[oldRoute.length/2];
      oldRoute[oldRoute.length/2] = oldRoute[(oldRoute.length/2) - 1];
      oldRoute[(oldRoute.length/2) - 1] = tempC;
    }
		((Route) returnArray[0]).route = oldRoute;
		((Route) returnArray[1]).route = otherRoute;
		return returnArray;
	}
}
