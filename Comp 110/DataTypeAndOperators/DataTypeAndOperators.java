//name: Haemin Lee
//date: 1/ 28 /2017
//application: Data type & operator
//purpose: to learn how to use Data type, operator, string reference in java

class DataTypeAndOperators{

   //primitive types
   
   public static void main (String args[]){
   
   boolean b = true;
   
   char c = '#';
   
   byte y = 125;
   
   short s = 32000;
   
   int i = 7;
   
   long l = 77777777777777l;
   
   float f = 1375.77777f;
   
   double d = -165491.77777;
      
   System.out.println("boolean = " + b);
   
   System.out.println("char = " + c);
   
   System.out.println("byte = " + y);
   
   System.out.println("short = " + s);
   
   System.out.println("int = " + i);
   
   System.out.println("long = " +l);
   
   System.out.println("float = " +f);
   
   System.out.println("double = " +d);
   
   System.out.println("\n\n");
   
   System.out.println("starting String Reference");
   
   //String Reference type
     
   String firstString = "try to remember this part";
   
   String secondString = " it is important";
   
   System.out.println("My first string = " + firstString);
   
   System.out.println("My second String = " +secondString);
   
   System.out.println("firstString + scondString = " + firstString + secondString);
   
   System.out.println("\n\n");
   
   System.out.println("Starting Operator");
   
   //Operator
   
   String b1 = "37";
   String b2 = "81";
   String a1= b1 + b2;
   // String part for operator (overload)
   
   int i1 = 12;
   int i2 = 24;
   int i3 = 2;
   int a2 = i2 / i1 + i3;
   // int part for operator
   
   int someVariable = (i1 + i2) / i3;
   
   System.out.println(" String b1 + String b2 is a1 which is = " + a1);
   
   System.out.println(" int i2 / int i1 + int i3 is = " + a2);
   
   System.out.println(" (i1 + i2) / i3 = " + someVariable);
   
   //trying short hand operator
   
   int y1 = 7;
   int y2 = 11;
   int y3 = 2;
   int x1 = 8;
   int x2 = 26;
   int x3 = 4;
   int g1 = 33;
   int g2 = 3;
   int res1 = y1++ + ++y2 + x1-- + --x2;
   int res2 = (y2*y3)%y1;
   y2%=y3;
   g1/= g2; 
   
   System.out.println(" int res = trying  y1++ + ++y2 + x1-- + --x2 = " + res1);
   
   System.out.println(" int res2 = (y2*y3)%y1 which is (12*2)/ 8 = " + res2);
   
   System.out.println(" y2%=y3 = " + y2);
   
   System.out.println(" g1/=g2 = " + g1);
   }
   
}
