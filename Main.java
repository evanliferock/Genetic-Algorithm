import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main{
  /**
   * This will call whatever is neccessary to start our program
   */
   public static void main(String[] args){
      int[][] matrix = readMatrix();
      /*for(int i = 0; i < matrix.length; i++){
         for(int j = 0; j < matrix[0].length; j++){
            System.out.print(matrix[i][j] + ", ");
         }
         System.out.println();
      }*/
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
