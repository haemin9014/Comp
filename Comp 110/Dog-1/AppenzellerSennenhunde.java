//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class AppenzellerSennenhunde extends Dog{

   public AppenzellerSennenhunde(String name){
      super(name);
   }

   //default bark
   
   //override color method:
   public String getColor(){
      return "brown,white and black";
   }   
   //override speed method:
   public int getSpeed(){
      return 5;
   }
   
   //override size method:
   public String getSize(){
      return "between normal and large";
   }

} 