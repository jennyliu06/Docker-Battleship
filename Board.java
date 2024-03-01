public class Board {

   private int [][] grid;
   private int size;

   //constructor
   public Board(int size){
      this.size = size;
      grid = new int [size][size];
   }
   
   //get size of board method
   public int getBoardSize(){
      return size;
   }
   //getvalue method
   public int getValue(int x, int y){
      int value = grid[x][y];
      return value;     
   }
   //setvalue method
   public void setValue(int x, int y, int value){
      grid[x][y] = value;
   }
   //tostring method
   public String toString(){
      String line = "";
      String result = "  ";
      for(int i = 0; i < grid.length; i++){
         line += (i+1) + " ";
      }
      line += "\n";
      result += line;
      for(int r = 0; r < grid.length; r++){
         line = "";
         int num = 'A' + r;
         char j = (char)num;
         line += j + " ";
         for(int c = 0; c < grid[r].length; c++){
            line += grid[r][c] + " ";
         } 
         line +="\n";
         result += line; 
      }
      return result;
   }
   //main method
   public static void main(String[]args){
      Board a = new Board(10);
      System.out.println(a);
      a.setValue(5, 5, 5);
      System.out.println(a);
   }
}