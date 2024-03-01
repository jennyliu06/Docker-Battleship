import java.util.ArrayList;

public class BattleShipBoard extends Board{

private ArrayList<Ship> fleet = new ArrayList<Ship>();
   public BattleShipBoard(int size){
      super(size);
   }
   
   public void placeShips(ArrayList<Ship> fleet){
      this.fleet = fleet;
      for(Ship s : fleet){
         int boardSize = getBoardSize();
         placeAShip(s, boardSize);  
      }
   } 
   
   public void placeAShip(Ship s, int boardSize){
      int shipSize = s.getSize();
      int type = s.getType();
      int orientation = 0;
      int x;
      int y;
      while(true){
         orientation = (int)(Math.random() * 2); 
         if(orientation == 0){
            x = (int)(Math.random() * boardSize);
            y = (int)(Math.random() * (boardSize - shipSize + 1));
         }
         else {
            x = (int)(Math.random() * (boardSize - shipSize + 1));
            y = (int)(Math.random() * boardSize);
         }
         boolean result = checkOverlap(x, y, orientation, shipSize);
         if(result){
            break;
         }
      }
     setCells(x, y, shipSize, orientation, type);
     s.setLocation(x, y);
     s.setOrientation(orientation);
   }     
     
   public boolean checkOverlap(int x, int y, int orientation, int shipSize){
      if(orientation == 0){
         for(int col = 0; col < shipSize; col++){
            int value = getValue(x, y + col);
            if(value != 0){
               return false;
            }  
         }
         return true;
      }
      else{
         for(int row = 0; row < shipSize; row++){
            int value = getValue(x + row, y);
            if(value != 0){
               return false;
            }
         }
         return true;
      }
   }
   
   private void setCells(int row, int column,int sizeShip,  int orientation, int value) {	
		if(orientation==0) {
			for(int j=0; j<sizeShip; j++) {		
				setValue(row, column+j,value);
			}	
		}else {
			
			for(int i=0; i<sizeShip; i++) {
				
				setValue(row+i, column,value);
			}
		}	
	}
   
   public void displayBattleShip(){
      String result = "  ";
      String line = "";
      for(int i = 0; i < getBoardSize(); i++){
         line += (i+1) + " ";
      }
      line += "\n";
      result += line;
      for(int row = 0; row < getBoardSize(); row++){
         line = "";
         int num = 'A' + row;
         char j = (char)num;
         line += j + " ";
         for(int col = 0; col < getBoardSize(); col++){
            int value = getValue(row,col);
            if(value == 0){
               line += "0 ";
            } else if(value >= -5 && value <= 5){
               int newValue = Math.abs(value);
               line += Ship.symbols[newValue - 1] + " ";
            }
         }
         line += "\n";
         result += line;
      }
      System.out.println(result);
   }
   
    
   public void checkHit(int x, int y){
      boolean bool = false;
      int value = getValue(x,y);
      if(value == 0){
         System.out.println("You missed!");
         return;
      } else if(value < 0){
         System.out.println("You already guessed this location!");
         return;
      }
      setValue(x,y,value*(-1)); 
      System.out.println("You hit a ship!");      
      for(Ship s : fleet){
         boolean hit = s.isHit(x,y);
         if(hit == true){
            bool = isSunk(s);
            if(bool == true){
               s.setSunk();
               System.out.println("You sunk " + s);
               break;
            }
            break;
         }
      }
   }
   
   public boolean isSunk(Ship ship){
		int[] xy =ship.getLocation();
		int x = xy[0]; 
      int y = xy[1];
		int orientation = ship.getOrientation();	
		int size = ship.getSize();	
		if(orientation==0) {
			for(int j=0; j<size; j++) {		
				if(getValue(x, y+j)>0){
               return false;
            }
			}
		} else {
			for(int i=0; i<size; i++) {
				if(getValue(x+i, y)>0){
               return false;
            }
			}
		}
		ship.setSunk();
		return true;
	}
   
   public String toString(){
      String line = "  ";
      String result = "";
      for(int i = 0; i < getBoardSize(); i++){
         line += (i+1) + " ";
      }
      line += "\n";
      result += line;
      for(int row = 0; row < getBoardSize();row++){
         line = "";
         int num = 'A' + row;
         char c = (char)num;
         line += c + " ";
         for(int col = 0; col < getBoardSize(); col++){
            int value = getValue(row, col);
            if(value == 0){
               line += "0 ";
            }
            else if(value > 0){
               line += "0 ";
            }
            else{
               line += "x ";
            }
         }
         line += "\n";
         result += line;
      }
      return result;
   }

 
   public static void main(String[] args){
      BattleShipBoard b = new BattleShipBoard(10);
      ArrayList<Ship> fleet = new ArrayList<Ship>();
      int q=5;
      for(int i=0; i<q; i++) {
         Ship ship = new Ship(i+1);
			fleet.add(ship);
		}
		b.placeShips(fleet);
      b.displayBattleShip();
   }     
}
