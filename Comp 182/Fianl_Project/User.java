package Component;
import java.util.ArrayList;
import java.lang.NullPointerException;
import java.util.Scanner;

import Component.Calculation;

import java.io.IOException;

public class User{
   
   public static void log(String s){
      System.out.println(s); 
   }
   public static void log(String s1, int v1) {
      System.out.println(s1 + v1);
   }
   public static void log(int[] array) {
      for(int i = 0; i < array.length; i++) {
         System.out.println("array[" + i + "]=" + array[i]);
      }
   }
   public static void printOption(){
            log(" [0]Exit \n [1]Computer Parts \n [2]List \n [3]total payment \n [4]Check Out \n [5]Clear");
   }

   
   public void main(){//start main
	   Calculation cal = new Calculation();
	   MakeLists makeList = new MakeLists();
      ////////////////////////////////////////////////////////////////stating menu/////////////////////////////////////////////////////////////////////////////
      boolean exitC = false;
      int optC = 0;
      while(!exitC){//start while
          Scanner myScannerOne = new Scanner(System.in);
            printOption();            
            boolean selection;
            do{//do
               try{//try   
                  optC = Integer.parseInt(myScannerOne.nextLine());
                  selection = false;
               }//try
               catch(Exception e){//catch
                  log("please type in number!");
                  selection = true;
               }//catch
            }while(selection);//do +while
         switch(optC){//start switch
            case 0:
               exitC = true; //this make while loop end
               log("you just clicked exit, have a good day!");

               break;
            case 1 :
               log("Loading Component List..");
               List list = new List();
               list.sList();
               break;
               
            case 2 : 
               log("what is in your list");
               cal.currentChosen();       
               break;
            
            case 3 :
               log("your total payment is ");
               cal.totalCost();
         	   System.out.println("going back to list.....");
                break;  
               
            case 4 :
               log("you entered to check out! thanks for buying our product"); 
               CheckOut co = new CheckOut();
               co.information();
               exitC = true;
               break;
            case 5 :
               log("clear the list"); 
               cal.removeAll();
               break;   
                        
            default :             
               log("you typed wrong! you must type between 0~5!");
               break;
               
         }//end switch
        // myScannerOne.close();
      }//end while
 ////////////////////////////////////////////////////////finish save setting/////////////////////////////////////////////////
   }//end main
}//end class