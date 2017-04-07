public abstract class Chromosome{
   double mutationRate;
   Chromosome(double mutRate){
      this.mutationRate = mutRate;
   }
   /**
    * Returns the cost for the given Chromosome
    */
   public abstract int cost();
   
   /**
    * Creates a new randomized Chromosome.
    */
   public abstract Chromosome createNew();
   
   /**
    * Mates the calling object and the passed in object
    */
   public abstract Chromosome[] mate(Chromosome b);
   
   /**
    * Has a chance to mutate Chromosome
    */
   protected abstract void mutate();
}
