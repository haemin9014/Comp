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
   
//////////////////////////////////////////////first thread/////////////////////////////////////////////////////////////////////   
public class numberThreads implements Runnable{
   private int[] sortNumber;
   
   public numberThreads(){            
      sortNumber = randomArray();
   }   
   
   private Thread t;
   //make 100k numbers
   public int[] randomArray(){
      int[] array = new int[100000];
      for(int i = 0; i < array.length; i++){
         array[i] = (int)(Math.random() * 100001);
      }return array;
   }
   /////////////////////////////////////////////////////////////sort//////////////////////////////////////////////////////         
   public void run(){
      int s = sortNumber.length;
    
      int temp2;
      for(int i = 0; i < s; i++){//for
         for(int j = 1; j < s-i; j++){//for
            if(sortNumber[j-1] > sortNumber[j]){//if
               temp2 = sortNumber[j-1];
               sortNumber[j-1]=sortNumber[j];
               sortNumber[j] = temp2;                              
            }//if
   
         }//for
   
      }//for   
      for(int i = 0; i<sortNumber.length; i++){
         System.out.println(sortNumber[i]);
      }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
   /////////////////////////////////////////////////write//////////////////////////////////////////////////////////////////            
      String fileName = "SortedNumbers.txt";
      String temp = "";
      try {
         File file = new File(fileName);
         // creates the file
         file.createNewFile();
         // create FileWriter object
         FileWriter writer = new FileWriter(fileName); 
         // output to file
         for(int i = 0; i < sortNumber.length; i++){
    
            writer.write(sortNumber[i] + "\n" );
            System.out.println(sortNumber[i]);
         }                      
   
         writer.flush();
         writer.close();  
      }
      catch(IOException ex) {
         System.out.println("Error writing to file: " + fileName);
      }
   }   
   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
   
   public void start(){
      if(t == null){
      t.start();
      }
   }

}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
