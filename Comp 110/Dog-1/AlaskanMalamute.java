//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class AlaskanMalamute extends Dog{

   public AlaskanMalamute(String name){
      super();
   }
   
   //default name
   
   //override bark method:
   public String bark() {
      return "WOlF - WOLF";
   }
   
   //override color method:
   public String getColor(){
      return "gray, brown, and white";
   }
   
   //override speed method:
   public int getSpeed(){
      return 10;
   }
   
   //override size method:
   public String getSize(){
      return "Large";
   }
   
}