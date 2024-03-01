public class Ship {
   private int size;
   private int type;
   private int x;
   private int y;
   private int orientation;
   private boolean sunk;
   private String[] typeName = {"aircraft carrier", "battleship", "destroyer", "submarine", "patrol boat"};
   private int[] arrSize = {5,4,3,3,2};
   public static char[] symbols = {'a', 'b', 'd', 's', 'p'};
   
   
   public Ship(int type){
      this.type = type;
      this.size = arrSize[type-1];
   }
   
   public boolean isHit(int x2, int y2){
      if(orientation == 0){
        if(x2 != x){
         return false;
        }
        if(y2>y+size-1){
         return false;
        }
        if(y2<y){
         return false;
        }
      return true;
     }
     else{
      if(y2 != y){
         return false;
      }
      if(x2>x+size-1){
         return false;
      }
      if(x2<x){
         return false;
      }
    return true;
     }    
   }
   
   public boolean isSunk(){
      return sunk;
   }
   
   public int getSize(){
      return size;
   }
   
   public int getType(){
      return type;
   }
   
   public int[] getLocation(){
      int[] location = {x,y};
      return location;
   }
   
   public int getOrientation(){
      return orientation;
   }
   
   public void setLocation(int x, int y){
      this.x = x;
      this.y = y;
   }
   
   public void setOrientation(int orientation){
      this.orientation = orientation;  
   }
   
   public void setSunk(){
      sunk = true;
   }
   
   public String toString(){
      String name = typeName[type-1];
      int size = arrSize[type-1];
      String result = name + ", " + size + "\n";
      return result;
   }
   
   public static void main(String[]args){
      Ship s = new Ship(2);
      System.out.print(s);
      s.setLocation(2,3);
      int[] location;
      location = s.getLocation();
      System.out.println("x: " + location[0] + " y: " +location[1]);
      boolean hit = s.isHit(2,7);
      System.out.println(hit);
      int a = s.getSize();
      System.out.println(a);
   }
}