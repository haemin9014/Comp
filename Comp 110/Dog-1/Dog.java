//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

public class Dog{

   private String name; 
   private final int speed = 5; // default speed in mph
   private final String size = "Normal"; // default size
   private final String color = "White"; // default color
   
   public Dog(){
      name = "May";
   }
 
   public Dog(String name){
      this.name = name;
   }
   
   public String getName(){
      return name;
   }
   
   public String bark(){
      return "Wooof - wooof";
   }
   
   public String getColor(){
      return color;
   }
   
   public int getSpeed(){
      return speed;
   }
   
   public String getSize(){
      return size;
   }
}