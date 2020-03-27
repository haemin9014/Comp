//Name: Haemin Lee
//Date: 04/15/2017
//application:abstract,polymorphsim 
//purpose:learn how to use abstract and polymorphsim

public abstract class Shape{

   public double width;
   public double height;
   public double area;
   
   public void setSize(double width, double height){
      
      this.width = width;
      this.height = height;
   }

   
   public abstract double getArea();
   
}   