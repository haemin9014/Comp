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

///////////////////////////////////////////////////////second thread/////////////////////////////////////////////////

public class stringThread implements Runnable{
   private String[] inputData;
   private String[] sortArray;
   private Thread t2;
   public stringThread(){
      sortArray = new String[30000];
      inputData = new String[30000];

      for(int i = 0; i <inputData.length; i++){
         inputData[i] = "";
      }    
   
      for(int i = 0; i <sortArray.length; i++){
         sortArray[i] = "";
      }

   }
   public void run(){
////////////////////////////////////////////////////load file///////////////////////////////////////////////////////// 
      
      String fileName = "dictionary.txt";     
      String fileLine = "";
      int i = 0;
      try {
      // Read file
      FileReader data = new FileReader(fileName);
      // Wrap FileReader with BufferedReader
      BufferedReader br = new BufferedReader(data);
      
      while((fileLine = br.readLine()) != null) {
         inputData[i++] = fileLine;
      
          System.out.println(fileLine);
      }   
      
      // close the file
      br.close();  
   
     
             
      }
      catch(FileNotFoundException ex) {
           System.out.println("File not found: " + fileName);
      }
      catch(IOException ex) {
           System.out.println("Error reading file: " + fileName);
      }
      //use sort
      for(int k = 0; k < sortArray.length; k++){
            sortArray[k] = inputData[k];
      }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////sort file////////////////////////////////////////////////////////////////
      //sort array for IO
            
      int n = sortArray.length;
      for(int j = 0; j < n; j++){
         sortArray[j] = inputData[j];
      }
      String temp = "";
      for(int i2 = 0; i2 < n; i2++){//for
         for(int j = 1; j < n-i2; j++){//for
            if(sortArray[j-1].compareTo(sortArray[j])>0){//if
               temp = sortArray[j-1];
               sortArray[j-1]=sortArray[j];
               sortArray[j] = temp;                              
            }//if
      
         }//for
      
      }//for   
      for(int i3 = 0; i3<sortArray.length; i3++){
          System.out.println(sortArray[i3]);
      }
      
      
////////////////////////////////////////////save file///////////////////////////////////////////////////////////////
      String stringFileName = "SrotedDictionary.txt";
      String temp2 = "";
      try {
         File file = new File(stringFileName);
         // creates the file
         file.createNewFile();
         // create FileWriter object
         FileWriter writer = new FileWriter(stringFileName); 
         // output to file
         for(int j2 = 0; j2 < sortArray.length; j2++){
       
            writer.write(sortArray[j2] + "\n" );
      }                      
      
      writer.flush();
      writer.close();  
      }
      catch(IOException ex) {
         System.out.println("Error writing to file: " + stringFileName);
      }
   }
   public void start(){
      if(t2 == null){
         
         t2.start();
      }
   }   

}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
   
