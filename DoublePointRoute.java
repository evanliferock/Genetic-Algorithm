public class DoublePointRoute extends Route{
   DoublePointRoute(int[][] theMatrix){
      super(theMatrix);
   }
   
   /**
    * Will run the Double point crossover method of mating
    * As long as b is an instance of Route
    */
   public Chromosome[] mate(Chromosome b){
	   Chromosome[] returnArray = new Chromosome[2];
	   Random r = new Random();
	   Chromosome[0] = new SinglePointRoute(this.matrix);
	   Chromosome[1] = new SinglePointRoute(b.matrix);
	   oldRoute = this.route;
	   
	   int crossFirst = r.nextInt(oldRoute.length());
	   int crossSecond = r.nextInt(oldRoute.length());
	   
	   if(crossFirst > crossSecond){
		   int temp = crossFirst;
		   crossFirst = crossSecond;
		   crossSecond = temp;
	   }
	   
	   for(int i = 0; i < oldRoute.length(); i++){
		   if(i < crossFirst){
			   Chromosome[0].route[i] = oldRoute[i];
			   Chromosome[1].route[i] = b.route[i];
		   }else if(i < crossSecond){
			   Chromosome[0].route[i] = b.route[i];
			   Chromosome[1].route[i] = oldRoute[i];
		   }else{
			   Chromosome[0].route[i] = oldRoute[i];
			   Chromosome[1].route[i] = b.route[i];
		   }
	   }
      return returnArray;
   }
}