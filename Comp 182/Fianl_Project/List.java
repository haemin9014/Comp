package Component;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.NullPointerException;
import Component.Calculation;

import java.io.IOException;

public class List {
	
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
	   log(" [0]Exit \n [1]CPU \n [2]Case \n [3]GPU \n [4]MotherBoard \n [5]Cooler \n [6]PSU \n [7]RAM \n [8]Monitor \n [9]Peripherals List  \n [10]Storage List \n [11]remove item");
   }
	   
   public void sList(){
	   
	   MakeLists makeList = new MakeLists();
	   CaseList caseList = new CaseList(); //number 2
	   CoolerList cooler = new CoolerList();
	   CPUList cpu = new CPUList(); //number 1
	   PSUList psu = new PSUList(); //number 5
	   GPUList gpu = new GPUList(); //number 3
	   MonitorList monitor = new MonitorList(); //number7
	   MotherBoardList motherBoard = new MotherBoardList();//number 4
	   PeripheralsList peripherals = new PeripheralsList();//number 8
	   RAMList ram = new RAMList(); //number 6
	   StorageList storage = new StorageList();//number 9
	   
	   boolean exit = false;
	   int opt =0;
	   while(!exit){
		   Scanner myScanner = new Scanner(System.in);
		   printOption();            
	       boolean selection;
	       do{//do
	    	  try{//try   
	    		   opt = Integer.parseInt(myScanner.nextLine());
	    		   selection = false;
	    	   }//try
	    	   catch(Exception e){//catch
	    		 log("please type in number");
	    		 selection = true;
	    	   }//catch
	       }while(selection);//do +while
	       switch(opt){
	          case 0:
	         
	        	  exit = true;
	        	  log("you just clicked exit, we will go back to main menu");
	
	        	  break;
	
	          case 1 :
	        	  log("List of cpu is:");
	        	  cpu.cpu();
	        	  break;
	
			  case 2 : 
				  log("List of Case is:");
				  caseList.caseList();	
				  break;
			
			  case 3 :
				  log("List of Gpu is:");
				  gpu.gpu();
				  break;  
			
			  case 4 :
			  
				  log("List of MotehrBoard is:");
				  motherBoard.motherBoard();
				  break;

			  case 5 :
				  log("List of cooler is:");
				  cooler.cooler();
				  break;   
			
			  case 6 :
				  log("List of PSU is:");
				  psu.psu();
				  break;   
			            
			  case 7 :
				  log("List of RAM is:");
				  ram.ram();
				  break;   
			
			  case 8 :
				  log("List of Monitor is:");
				  monitor.monitor();
				  break;   
			
			  case 9 :
				  log("List of Peripherals List is:");
				  peripherals.peripherals();
				  break;
			       
			  case 10 :
				  log("List of Storage List is:");
				  storage.storage();
				  break;   
					               
			  case 11 :			  
				  log("the list you chose is:");
				  	Calculation cal = new Calculation();
				  	cal.currentChosen();
				  log("what do you want to remove from your list? enter the number");
				  	cal.remove();
				  break;
			  
			  default :
				  		               
				  log("you typed wrong! you must type between 0~10!");
				  break;
			}//end switch
	   }//end while 
  }
   
}
