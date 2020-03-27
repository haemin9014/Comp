//name: Haemin Lee
//date: 04/22/2017
//application: Adapter
//purpose:learn how to use Adapter pattern 

class Driver{

   public static void main(String args[]){
   
      Remote linkedln = new Remote();

      System.out.println("remote controling SuperSonic device"); 
      linkedln.power("supersonic", "on");
      linkedln.power("supersonic", "3");
      linkedln.power("supersonic", "+");
      linkedln.power("supersonic", "-");
      linkedln.power("supersonic", "off");
      System.out.println("\n");
      
      System.out.println("remote controling Upstar device"); 
      linkedln.power("upstar", "on");
      System.out.println("upstar does not have channel 5, and when i type in 5, it print out");
      linkedln.power("upstar", "5");
      linkedln.power("upstar", "+");
      linkedln.power("upstar", "-");
      linkedln.power("upstar", "off");
      System.out.println("\n");
      
      System.out.println("remote controling Samsung device"); 
      linkedln.power("samsung", "on");
      linkedln.power("samsung", "7");
      linkedln.power("samsung", "+");
      linkedln.power("samsung", "-");
      linkedln.power("samsung", "off");
      
   }
}      
   