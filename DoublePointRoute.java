public class DoublePointRoute extends Route{
   DoublePointRoute(int[][] theMatrix){
      super(theMatrix);
   }
   
   /**
    * Will run the Double point crossover method of mating
    * As long as b is an instance of Route
    */
   public Chromosome mate(Chromosome b){
      return this.createNew();
   }
}