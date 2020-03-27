package Component;
import java.util.Scanner;

import Component.Calculation;

import java.io.IOException;

public class CheckOut{
      
   public void information(){
      Scanner myScanner = new Scanner(System.in);
      String name = "";
      String address = "";
      String phone = "";
      Calculation cal = new Calculation();
      ////////////////////////////////////////////////////////////////name address phone///////////////////////////////////////////////////////////////////////
      do{//do
         try{//try
        	 System.out.println("please enter your name, with more than 3 alphabet");
            name = myScanner.nextLine();                                  
            }//try
            catch(Exception e){//catch                     
            	System.out.println("error! please re-enter your name");
            }//catch
         }while(name.length()<=3);//do
      do{
         try{    
        	 System.out.println("enter your address");
            address = myScanner.nextLine();
         }
         catch(Exception e){
        	 System.out.println("error! please re-enter your address it must be over 10 letter!");
         }
      }while(address.length()<=10);
      do{
         try{
        	System.out.println("enter your phone number");
            phone = myScanner.nextLine();
         }
         catch(Exception e){
        	System.out.println("error! please type in number with area code!");
         }
      }
      while(phone.length()<=6);   
      System.out.println("thank you for ordering!");
      System.out.print("your total payment will be ");
      cal.totalCost();
      System.out.println("and it will take " + Math.random()*150 + "hours to arrive");
   }

}