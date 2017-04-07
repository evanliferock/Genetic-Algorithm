import java.util.Random;

public class CycleCrossoverRoute extends Route{
	CycleCrossoverRoute(int[][] theMatrix, double mutRate){
		super(theMatrix, mutRate);
	}

   public Chromosome createNew(){
      return new CycleCrossoverRoute(matrix, mutationRate);
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
		char[] oldRoute = this.route.clone();
		char[] otherRoute = ((Route)b).route.clone();

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
		((Route) returnArray[0]).route = oldRoute;
		((Route) returnArray[1]).route = otherRoute;
      returnArray[0].mutate();
      returnArray[1].mutate();
		return returnArray;
	}
}
