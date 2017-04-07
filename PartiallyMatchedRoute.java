import java.util.Random;

public class PartiallyMatchedRoute extends Route{
  PartiallyMatchedRoute(int[][] theMatrix){
    super(theMatrix);
  }

  public Chromosome createNew(){
      return new PartiallyMatchedRoute(matrix);
  }

  /**
  * Will run the Double point crossover method of mating
  * As long as b is an instance of Route
  */
  public Chromosome[] mate(Chromosome b){
    Chromosome[] returnArray = new Chromosome[2];
    Random r = new Random();

    returnArray[0] = this.createNew();
		returnArray[1] = this.createNew();
		char[] oldRoute = this.route.clone();
		char[] otherRoute = ((Route)b).route.clone();

    int crossFirst = r.nextInt(oldRoute.length-1);
    int crossSecond = r.nextInt(oldRoute.length-1);
    if(crossFirst > crossSecond){
      int crossTemp = crossFirst;
      crossFirst = crossSecond;
      crossSecond = crossTemp;
    }
    for(int i = 0; i < oldRoute.length; i++){
      if(i > crossFirst && i <= crossSecond){
        char tempChar = oldRoute[i];
        oldRoute[i] = otherRoute[i];
        otherRoute[i] = tempChar;
      }
    }

    int indexA = -1;
    int indexB = -1;
    for(int i = crossFirst + 1; i <= crossSecond; i++){
      for(int j = crossFirst + 1; j <= crossSecond; j++){
        if(indexB >= 0)
          break;
        for(int k = 0; k < otherRoute.length; k++){
          if(indexB >= 0)
            break;
          if(k > crossFirst && k <= crossSecond){
            continue;
          }
          if(otherRoute[k] == otherRoute[j]){
            indexB = k;
          }
        }
      }
      for(int k = 0; k < oldRoute.length; k++){
        if(k > crossFirst && k <= crossSecond){
          continue;
        }
        if(oldRoute[k] == oldRoute[i]){
          indexA = k;
          break;
        }
      }
      if(indexA >= 0 && indexB >= 0){
        char tempChar = oldRoute[indexA];
        oldRoute[indexA] = otherRoute[indexB];
        otherRoute[indexB] = tempChar;
        indexA = -1;
        indexB = -1;
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
