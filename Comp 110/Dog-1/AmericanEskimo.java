//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class AmericanEskimo extends Dog{

   public AmericanEskimo(String name){
      super(name);
   }
   
   //override bark method:
   public String bark(){
      return "kung - kung";
   }
   
   //default color
      
   //override speed method:
   public int getSpeed(){
      return 3;
   }
      
   //override size method:
   public String getSize(){
      return "small";
   }
   
}