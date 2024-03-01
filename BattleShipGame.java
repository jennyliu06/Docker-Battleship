import java.util.ArrayList;
import java.util.Scanner;

public class BattleShipGame{
   private int boardSize = 10;
   private static Scanner scanner = new Scanner(System.in);
   private BattleShipBoard battleshipBoard;
   private ArrayList<Ship> fleet = new ArrayList<Ship>();
   private int x;
   private int y;
   
   public BattleShipGame(int boardSize){
      this.boardSize = boardSize;
      battleshipBoard = new BattleShipBoard(boardSize);
      this.battleshipBoard = battleshipBoard;
      ArrayList<Ship> fleet = new ArrayList<Ship>();
      int num = 5;
      for(int i = 0; i < num; i++){
         Ship s = new Ship(i+1);
         fleet.add(s);
      }
      this.fleet = fleet;
      battleshipBoard.placeShips(fleet);
      process();
   }
   
   private void process(){
      int max = 50;
      boolean isShipAvail= isShipAvailable();
      //battleshipBoard.displayBattleShip();
      int counter = 51;
      System.out.println("" + battleshipBoard);
      for(int i = 0; i < max; i ++){
         counter--;
         System.out.println("You have " + counter + " tries left");
         System.out.println("Please enter where you would like to place your ship: ");
         String location = scanner.nextLine();
         location = location.strip();
         if(parseStr(location) == false){
            continue;
         }
         battleshipBoard.checkHit(x,y);
         isShipAvail = isShipAvailable();
         if(isShipAvail == false){
            System.out.println("You win!");
            break;
         }
         battleshipBoard.displayBattleShip();
         System.out.println("" + battleshipBoard);
      }
      if(isShipAvail == true){
         System.out.println("You lose!");
      }
   }
   private boolean parseStr(String location){
      int length = location.length();
      if(length < 2){
         return false;
      }
      char c1 = location.charAt(0);
      if(Character.isLetter(c1) == false){
         System.out.println("Invalid input");
         return false;
      }
      int num = -1;
      c1 = Character.toUpperCase(c1);
      num = c1;
      if(num < (int)'A'  || num >= (int)'A' + boardSize){
         System.out.println("Invalid value" + location.charAt(0));
         return false;
      }
      this.x = c1 - (int)'A';
      String subStr = location.substring(1);
      try{
         num = Integer.parseInt(subStr);
         } 
      catch(Exception e){
         System.out.println("Invalid input: " + subStr);
         return false;
      }
      this.y = num-1;
      return true;
   }
   
   public boolean isShipAvailable(){
      for(int i = 0; i < fleet.size(); i++){
         Ship s = fleet.get(i);
         boolean sunk = s.isSunk();
         if(sunk == false){
            return true;
         }
      }
      return false;
   }
   
   public static void main(String[] args) {
      int boardsz=10;
	   BattleShipGame game= new BattleShipGame(boardsz);
      scanner.close();	
	}

}