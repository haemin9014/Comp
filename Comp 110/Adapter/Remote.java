//name: Haemin Lee
//date: 04/22/2017
//application: Adapter
//purpose:learn how to use Adapter pattern

public class Remote implements RemoteControl{

   RemoteAdapter ra;
   
   public void power(String model, String action){
   
      if(model.equalsIgnoreCase("supersonic")||model.equalsIgnoreCase("upstar")||model.equalsIgnoreCase("samsung")){
         ra = new RemoteAdapter(model);
         ra.power(model, action);
      }
      else{
         System.out.println(model + " could not support this model!");            
      }
   }
}      