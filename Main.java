import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main{
  /**
   * This will call whatever is neccessary to start our program
   */
   public static void main(String[] args){
      int[][] matrix = readMatrix();
      CycleCrossoverRoute cc = new CycleCrossoverRoute(matrix);
      PartiallyMatchedRoute pm = new PartiallyMatchedRoute(matrix);

      TopDownPopulous topCycle = new TopDownPopulous(cc, 64);
      TopDownPopulous topPartial = new TopDownPopulous(pm, 64);
      TournamentPopulous tourCycle = new TournamentPopulous(cc, 64);
      TournamentPopulous tourPartial = new TournamentPopulous(pm, 64);

      int numRuns = 600;
      topCycle.runGenerations(numRuns);
      topPartial.runGenerations(numRuns);
      tourCycle.runGenerations(numRuns);
      tourPartial.runGenerations(numRuns);
      System.out.println("Run | # of Generations | Circuit Produced | Cost");
      System.out.println("------------------------------------------------");
      System.out.println("0   |   " + numRuns + "            | Add this stuff");
      System.out.println("1   |   " + numRuns + "            | Add this stuff");
      System.out.println("2   |   " + numRuns + "            | Add this stuff");
      System.out.println("3   |   " + numRuns + "            | Add this stuff");
   }

   private static int[][] readMatrix(){
   /**
    * Each row is a,b,...h
    * Each column is a,b,...h
    */
      int[][] theMatrix;
      ArrayList<String> theLines = new ArrayList<String>();
      try{
         File file = new File("distances.csv");
         Scanner theInput = new Scanner(file);
         while(theInput.hasNextLine()){
            theLines.add(theInput.nextLine());
         }

      }catch(Exception e){
         System.out.println(e.getMessage());
         System.exit(1);
      }

      String[] aLine = theLines.get(0).split(",");
      theMatrix = new int[theLines.size()][aLine.length];
      try{
         for(int i = 0; i < theLines.size(); i++){
            aLine = theLines.get(i).split(",");
            for(int j = 0; j < aLine.length; j++){
               theMatrix[i][j] = Integer.parseInt(aLine[j]);
            }
         }
      }catch(Exception e){
         System.out.println(e.getMessage());
         System.exit(1);
      }

      return theMatrix;
   }
}
