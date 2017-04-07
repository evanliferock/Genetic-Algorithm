/*
 * CPSC 427-02
 * Assignment # 11
 * Dev 1: Evan Srock
 * Dev 2: Patrick Chadbourne
 * Dev 3: Katie Phillips
 *
 * Program to run Genetic Algorithm on the Traveling
 * Salesperson problem
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main{
  /**
   * This will call whatever is neccessary to start our program
   */
   public static void main(String[] args){
      int[][] matrix = readMatrix();

      //primsCost[0] = lower-bound, primsCost[1] = upper-bound
      int[] primsCost = primsAlgorithm(matrix);

      CycleCrossoverRoute cc = new CycleCrossoverRoute(matrix);
      CycleCrossoverRoute cc2 = new CycleCrossoverRoute(matrix);
      PartiallyMatchedRoute pm = new PartiallyMatchedRoute(matrix);
      PartiallyMatchedRoute pm2 = new PartiallyMatchedRoute(matrix);
      
      Populous[] pops = {new TopDownPopulous(cc, 64), new TopDownPopulous(pm, 64),
                         new TournamentPopulous(cc2, 64), new TournamentPopulous(pm2, 64)};
      
      /*TopDownPopulous topCycle = new TopDownPopulous(cc, 64);
      TopDownPopulous topPartial = new TopDownPopulous(pm, 64);
      TournamentPopulous tourCycle = new TournamentPopulous(cc2, 64);
      TournamentPopulous tourPartial = new TournamentPopulous(pm2, 64);*/
      
      System.out.println("0: Top-Down pairing with Cycle Crossover mating");
      System.out.println("1: Top-Down pairing with Partial Matched mating");
      System.out.println("2: Tournament pairing with Cycle Crossover mating");
      System.out.println("3: Tournament pairing with Partial Matched mating");
      System.out.println("Prims lower bound: " + primsCost[0]);
      System.out.println("Type | # of Generations | Circuit Produced | Cost");
      System.out.println("-------------------------------------------------");


      
      int numRuns;
      Route best;
      for(int i = 0; i < pops.length; i++){
         numRuns = 0;
         do{
            pops[i].runGenerations(5);
            numRuns += 5;
            best = (Route)pops[i].getBest();
         }while(best.cost() > 7000);
         System.out.println(i + "    |   " + numRuns + "            | " +
                        best.getRoute() +  "         | " 
                        + best.cost() + "   ");

      }
      
      /*topCycle.runGenerations(numRuns);
      topPartial.runGenerations(numRuns);
      tourCycle.runGenerations(numRuns);
      tourPartial.runGenerations(numRuns);

      Route bestTopCycle = (Route) topCycle.getBest();
      Route bestTopPartial = (Route) topPartial.getBest();
      Route bestTourCycle = (Route) tourCycle.getBest();
      Route bestTourPartial = (Route) tourPartial.getBest();
            
      System.out.println("0    |   " + numRuns + "            | " +
                        bestTopCycle.getRoute() +  "         | " + bestTopCycle.cost() + "   ");
      System.out.println("1    |   " + numRuns + "            | " +
                        bestTopPartial.getRoute() +  "         | " + bestTopPartial.cost()  + "   ");
      System.out.println("2    |   " + numRuns + "            | " +
                        bestTourCycle.getRoute() +  "         | " + bestTourCycle.cost()  + "   ");
      System.out.println("3    |   " + numRuns + "            | " +
                        bestTourPartial.getRoute() +  "         | " + bestTourPartial.cost() + "   ");*/
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

   /**
    * Performs Prim's Algorithm on graph given by matrix. Returns
    * the total cost of the spanning tree (lower bound), as well
    * as the total cost plus the edge between the two end points
    * of the tree (upper bound).
    */
   private static int[] primsAlgorithm(int[][] matrix){
     int[] visitedNodes = new int[matrix.length];
     int[] unvisitedNodes = new int[matrix.length];
     for(int i = 0; i < unvisitedNodes.length; i++){
        unvisitedNodes[i] = i;
     }

     visitedNodes[0] = unvisitedNodes[0]; //Set arbitrary root
     int numVisited = 1;
     unvisitedNodes[0] = unvisitedNodes[unvisitedNodes.length - 1];
     int numUnvisited = unvisitedNodes.length - 1;

     int totalCost = 0;

     //Keeps track of how many nodes a certain node is attached to so
     //that can later find the two ends of the tree
     int[] numNodesAttached = new int[matrix.length];
     for(int i = 0; i < numNodesAttached.length; i++){
       numNodesAttached[i] = 0;
     }

     while(numUnvisited > 0){
       int minCost = matrix[visitedNodes[0]][unvisitedNodes[0]];
       int minNode = 0;
       int fromNode = 0;

       //Find minimum cost edge out of all edges with one visited vertex
       //and one unvisited vertex
       for(int i = 0; i < numVisited; i++){
         for(int j = 0; j < numUnvisited; j++){
           if(matrix[visitedNodes[i]][unvisitedNodes[j]] < minCost
              && visitedNodes[i] != unvisitedNodes[j]){
             minCost = matrix[visitedNodes[i]][unvisitedNodes[j]];
             minNode = j;
             fromNode = i;
           }
         }
       }

       //Visit the vertex that is connected to least cost edge, update
       //cost and node arrays to reflect this change
       totalCost += minCost;
       numNodesAttached[visitedNodes[fromNode]]++;
       numNodesAttached[unvisitedNodes[minNode]]++;
       visitedNodes[numVisited] = unvisitedNodes[minNode];
       numVisited++;
       unvisitedNodes[minNode] = unvisitedNodes[numUnvisited - 1];
       numUnvisited--;
     }

     //Find the two end-points of tree
     int i = 0, j = 0;
     int[] ends = new int[2];
     while(i < 2){
       if(numNodesAttached[j] == 1){
         ends[i++] = j;
       }
       j++;
     }

     int[] bounds = new int[2];
     bounds[0] = totalCost;
     //Add cost of edge between two end-nodes to get an upper bound
     bounds[1] = totalCost + matrix[ends[0]][ends[1]];
     return bounds;
   }
}
