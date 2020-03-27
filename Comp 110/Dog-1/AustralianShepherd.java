//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class AustralianShepherd extends Dog{

   public AustralianShepherd(String name){
      super(name);
   }
   
   //override bark method:
   public String bark(){
     return "arf-arf";
   }   

   //override color method:
   public String getColor(){
      return "brown,white and dark brown";
   }   

   //default speed
   
   //override size method:
   public String getSize(){
      return "between large and x-large";
   }
   
} 