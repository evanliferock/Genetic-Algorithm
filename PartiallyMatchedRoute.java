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
    char[] oldRoute = this.route;
    char[] otherRoute = ((Route)b).route;

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
    for(int i = crossFirst + 1; i <= crossSecond; i++){
      int indexA = -1;
      int indexB = -1;
      for(int j = 0; j < oldRoute.length; j++){
        if(j > crossFirst && j <= crossSecond)
          continue;
        if(oldRoute[i] == oldRoute[j])
          indexA = j;
        if(otherRoute[i] == otherRoute[j])
          indexB = j;
      }
      if(indexA == -1 || indexB == -1)
        continue;
      char tempChar = oldRoute[indexA];
      oldRoute[indexA] = otherRoute[indexB];
      otherRoute[indexB] = tempChar;
    }
    ((Route) returnArray[0]).route = oldRoute;
		((Route) returnArray[1]).route = otherRoute;
    return returnArray;
  }
}
