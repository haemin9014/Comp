//Name:Haemin Lee
//Date:04/08/2017
//application:using thread and driver
//purpose:to get use to thread
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.lang.Runnable;
import java.lang.Thread;

class Driver{//class open
 //////////////////////////////////////////////////log code////////////////////////////////////////////////////////////
   public static void log(String s){
      System.out.println(s);
   }
   public static void log(String s, int v){
      System.out.println(s+v);
   }
   public static void log(int array[]){
      for(int i = 0; i < array.length; i++){
         System.out.println("array[" + i + "]=" + array[i]);
      }
   }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public static void main (String args[]){
////////////////////////////////////////////////first thread////////////////////////////////////////////////////////////      
      Thread th = new Thread(new numberThreads());
      th.start();

///////////////////////////////////////////////second thread////////////////////////////////////////////////////////
      Thread th2 = new Thread(new stringThread());
      th2.start();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   }//end main

}//end class            