public abstract class Chromosome{
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
   public abstract Chromosome mate(Chromosome b);
}
