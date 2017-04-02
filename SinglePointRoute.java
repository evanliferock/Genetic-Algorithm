public class SinglePointRoute extends Route{
   SinglePointRoute(int[][] theMatrix){
      super(theMatrix);
   }
   
   /**
    * Will run the Single point crossover method of mating
    * As long as b is an instance of Route
    */
   public Chromosome mate(Chromosome b){
      return this.createNew();
   }
}