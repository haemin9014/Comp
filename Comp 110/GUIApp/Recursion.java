//Name:Haemin Lee
//Date:04/08/2017
//application:learn how to use Recursion
//purpose:to use Recursion
class Recursion{
////////////////////////////////////////3 recursive methods from presentation////////////////////////////////////////////////
   //Facotrial
   public int factorial(int n){
      if(n <= 1){
         return 1;
      }return n * factorial(n - 1);
   }
   //Fibonacci
   public int fibonacci(int n1){
      if(n1 <= 1){
         return 1;
      }return fibonacci(n1 - 1) + fibonacci(n1 - 2);
   }
   //educlidean algorithm(gcd)
   public int ea(int j, int k){
      if(k == 0){
         return j;
      }return ea(k, (j % k));
   }
//////////////////////////////////////////doing natural log with factorial.......////////////////////////////////////////////
   public double nLog(double p){
      if(p <= 0){
         System.out.println("undefined"); //because natural log could not be 0 or negative number.
      }
      if(p == 1){
         return 0;
      }return Math.log(p) + nLog(p - 1);
   }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                  
}//class end         