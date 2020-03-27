//name: Haemin Lee
//date: 04/22/2017
//application: Adapter
//purpose:learn how to use Adapter pattern

public class RemoteAdapter implements RemoteControl{
   
   UniversalRemoteControl uc;
   
   public RemoteAdapter(String model){
      if(model.equalsIgnoreCase("supersonic")){
         uc = new SuperSonic();
      }
      else if(model.equalsIgnoreCase("upstar")){
         uc = new Upstar();
      }
      else if(model.equalsIgnoreCase("samsung")){
         uc = new Samsung();
      }
   }         
   public void power(String model, String action){
      if(model.equalsIgnoreCase("supersonic")){
         uc.powerSuperSonic(action);
      }
      else if(model.equalsIgnoreCase("upstar")){
         uc.powerUpstar(action);
      }
      else if(model.equalsIgnoreCase("samsung")){
         uc.powerSamsung(action);
      }
   }
}