//name: Haemin Lee
//date: 04/22/2017
//application: Singleton
//purpose:learn how to use Singleton

class Driver{
   
   public static void main(String args[]){
   
      Singleton sl;
      sl = Singleton.getInstance();
      sl.set(7777);
      System.out.println("memory address of sl(7777) is " + sl);//print sl
      System.out.println("number of sl is " + sl.get());
      Singleton sl2 = Singleton.getInstance();
      sl2.set(2379);
      System.out.println("memory address of sl2(2379) is " + sl2);//pirnt sl2
      System.out.println("number of sl2 is " + sl2.get());
      
      System.out.println("after setting sl and sl2, when you type in sl, int print out " + sl.get());
      System.out.println("after setting sl and sl2, when you type in sl2, int print out " + sl2.get());
      /*
      System.out.println("after printing out sl2, line 9(change the value of sl2) will change a value of s1");
      System.out.println(sl);
      */
   }
}