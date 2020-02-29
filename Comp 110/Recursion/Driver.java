//Name:Haemin Lee
//Date:04/08/2017
//application:learn how to use Recursion
//purpose:to use Recursion
class Driver{

   public static void main (String args[]){
   
      Recursion re = new Recursion();
      System.out.print("factorial of 7 is = ");
      System.out.println(re.factorial(7));
      System.out.print("Fibonacci of 7 is = ");
      System.out.println(re.fibonacci(7));
      System.out.print("educlidean algorithm of 7 and 17 is = ");
      System.out.println(re.ea(7, 17));
      System.out.print("when we put 7 in log with factorial is = ");
      System.out.println(re.nLog(7));
      
   }
   
}      