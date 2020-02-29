//name: Haemin Lee
//date: 04/22/2017
//application: Monostate
//purpose:learn how to use Monostate pattern

class Monostate{

   private static int numOfInstances = 0;
   private int myValue;
   
   public Monostate(int myValue){
      this.myValue = myValue;
      numOfInstances++;
   }
   
   public int getNumOfInstances(){
      return numOfInstances;
   }
   
   public int getValue(){
      return myValue;
   }
}        