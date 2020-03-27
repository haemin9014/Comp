//name: Haemin Lee
//date: 04/22/2017
//application: Singleton
//purpose:learn how to use Singleton

class Singleton{
   
   private int x;
   
   private static Singleton instance = new Singleton();
   
   private Singleton(){
   }
   
   public static Singleton getInstance(){
      return instance;
   }
   public void set(int x){
      this.x = x;
   }
   public int get(){
      return x;
   }
   /*
   public String toString(){
      return x+("");
   } <----this method make number print out
   */
}   