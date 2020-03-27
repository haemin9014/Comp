//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class Akita extends Dog{

   public Akita(String name){
      super(name);
   }
   
   //override bark method:
   public String bark(){
      return "WOOF - WOOF";
   }
   
   //override color method:
   public String getColor(){
      return "whie, Gray and Black";
   }
 
   //override speed method:
   public int getSpeed(){
      return 7;
   }
  
   // default size
}
