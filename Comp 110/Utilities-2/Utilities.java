//Name:Haemin Lee
//Date:2/04/2017
//Application: string, Math, Scanner, and GUI
//purpose: this LAb make us to learn how to use String, Math, Scanner, and GUI in java.

//starting part of Scanner , GUI---> always top of class
import java.util.Scanner;

import javax.swing.JOptionPane;

//starting class

class Utilities{

   public static void main (String args[]){
   
      //String Class
   
      JOptionPane.showMessageDialog(null, "starting String Class Part");
   
      //d
      String firstExampleString;
      String secondExampleString;
      String thirdExampleString;
   
      //i
      firstExampleString = "This..";
      secondExampleString = "This..";
      thirdExampleString = "is great!";
   
      //u
      secondExampleString = secondExampleString.concat (thirdExampleString);
           
      //length part of string
      JOptionPane.showMessageDialog(null, "firstExampleString = " + firstExampleString.length());
   
      //concat part of String
      JOptionPane.showMessageDialog(null,"when we concat string second and third = " + secondExampleString);
   
      //CompareTo part of String
      JOptionPane.showMessageDialog(null, "when we compare first and second string = " + firstExampleString.compareTo(secondExampleString));
   
               
      //Math Class
   
      JOptionPane.showMessageDialog(null, "Starting Math Class");
   
      //d
      int a;
   
      int b;
   
      double sinVal;
      
      double result1;
      
      double result2;
   
      //i
 
      a = 100;
   
      b = 99;
   
      sinVal = 88.6781;   
  
      result1 = 0;
   
      result2 = 0;
   
      // d + i specially place for random math
   
      double random = Math.random() * 98 + 1;
   
      //u
   
      result1 = Math.max(a, b);
      result2 = Math.sin(sinVal);
   
      //output
   
      JOptionPane.showMessageDialog(null, "max of a and b is = " + result1);
   
      JOptionPane.showMessageDialog(null, "sin value of 88.6781 is = " + result2);
   
      JOptionPane.showMessageDialog(null, " the randome number between 0~100 is = " + random);   
        
           
      //Scanner input 
   
      JOptionPane.showMessageDialog(null, "starting Scanner");
   
      //d1
      
      String input = "";
      Scanner scanner = new Scanner(System.in);
      
      JOptionPane.showMessageDialog(null, "Enter a number"); 
      
      int i = Integer.parseInt(scanner.nextLine());
      
      JOptionPane.showMessageDialog(null, "You entered: " + i);
      
      JOptionPane.showMessageDialog(null, "Enter any number");
      
      double j = Double.parseDouble(scanner.nextLine());
      
      JOptionPane.showMessageDialog(null, "you enetered " + j);
      
      JOptionPane.showMessageDialog(null, "please, enter your name");
            
      String n = (scanner.nextLine());
      
      JOptionPane.showMessageDialog(null, "your name is " + n);           
      
      scanner.close();
      
      //GUI Class
   
      JOptionPane.showMessageDialog(null, "Starting GUI");
      
      JOptionPane.showConfirmDialog(null, "begin");
      
      JOptionPane.showInputDialog(null,"and end it");
   
      JOptionPane.showMessageDialog(null, "this is the end of this Java!");
      
      
      
   }
   
} 