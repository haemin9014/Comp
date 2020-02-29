//name: Haemin Lee
//date: 04/22/2017
//application: Monostate
//purpose:learn how to use Monostate pattern

class Driver{
   
   public static void main(String args[]){
   
      Monostate m1, m2, m3;
      m1 = new Monostate(7);
      m2 = new Monostate(77);
      m3 = new Monostate(777);
      
      System.out.println("getting number of instances");
      System.out.println(m1.getNumOfInstances());
      System.out.println(m2.getNumOfInstances());
      System.out.println(m3.getNumOfInstances());
      System.out.println("\ngetting value");
      System.out.println(m1.getValue());
      System.out.println(m2.getValue());
      System.out.println(m3.getValue());
      
   }
}      