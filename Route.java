public abstract class Route extends Chromosome{
   char[] nodes;
   final int[][] matrix;
   
   Route(int[][] theMatrix){
      matrix = theMatrix;
      nodes = new char[matrix.length];
      for(int i = 0; i < nodes.length; i++){
         nodes[i] = (char)(97 + i);
      }
   }
}