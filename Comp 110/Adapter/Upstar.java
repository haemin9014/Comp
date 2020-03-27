//name: Haemin Lee
//date: 04/22/2017
//application: Adapter
//purpose:learn how to use Adapter pattern

public class Upstar implements UniversalRemoteControl{

   public void powerSuperSonic(String action){
   }
   
   public void powerUpstar(String action){
   
      if(action.equals("on")){
            System.out.println("Turning on Upstar device!");      
      }      
      else if(action.equals("+")){
         System.out.println("increasing sound of Upstar device");
      }
   
      else if(action.equals("-")){
         System.out.println("decreasing sound of Upstar device");
      }
   
      else if(action.equals("1")){
         System.out.println("changing Upstar television channel to 1");
      }
   
      else if(action.equals("3")){
         System.out.println("changing Upstar television channel to 3");
      }
   
      else if(action.equals("7")){
         System.out.println("changing Upstar television channel to 7");
      }
   
      else if(action.equals("off")){
         System.out.println("Turning off Upstar device!");
      }
      
      else{
         System.out.println("wrong order!");
      }

   }         
   
   public void powerSamsung(String action){
   } 
}