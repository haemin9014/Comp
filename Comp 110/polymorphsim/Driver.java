//Name: Haemin Lee
//Date: 04/15/2017
//application:abstract,polymorphsim 
//purpose:learn how to use abstract and polymorphsim

class Driver{

   public static void main(String args[]){
      //Declare/init a new object of type triangle
      Triangle tr = new Triangle();
      //Declare/init a new object of type rectangle
      Rectangle re = new Rectangle();
      //Declare and object of type Shape and assign it to the triangle object
      Shape sh = tr;
      sh.setSize(7.7, 9.9);
      //using the triange object, print out the area of the triangle by calling getArea()
      double objTr = sh.getArea();
      System.out.print("when trianlge's width is 7.7 and height is 9.9, then area is ");
      System.out.println(objTr);
      
      //Declare and object of type Shape and assign it to the Rectangle object
      Shape sh2 = re;
      sh2.setSize(8.8,11.11);
      //using the triange object, print out the area of the triangle by calling getArea()
      double objRe = sh2.getArea();
      System.out.print("when rectangle's width is 8.8 and height is 11.11, then area is ");
      System.out.println(objRe);
   }
}      
      

      
      