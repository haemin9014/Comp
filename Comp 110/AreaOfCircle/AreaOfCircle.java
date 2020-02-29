//Name: Haemin Lee
//Date: 1/28/2017
//Application: area of circle
//purpose:to learn about declaration, initialization, and compute

class AreaOfCircle{

   public static void main (String args[]){
   
      //declaration
      
      double radius;
      double area;
      final double PI = 3.14;
      
      //initialization
      
      radius = 7;
      area = 0;
      
      //compute area
      
      area = radius * radius * PI;
      
      System.out.print("Area of circle = " + area);
      
   }
      
}