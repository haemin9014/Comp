package Component;

import java.util.Scanner;

public class MonitorList {
	 public void monitor() {
			MakeLists make = new MakeLists();
		    Calculation cal = new Calculation();
		    System.out.print("Enter a number: ");
			   boolean exit = false;
			   int opt =0;
			   while(!exit){
					System.out.println("List of Monitor's: ");
					System.out.println("[0]Return to Select screen");
				   for(int i = 0; i < make.monitorList.size(); i++) {
					   int number = i + 1;
						System.out.println("[" + number + "]"+make.monitorList.get(i) + " price" + make.monitorPriceList.get(i));
					}

				   Scanner myScanner = new Scanner(System.in);
			            
			       boolean selection;
			       do{//do
			    	  try{//try   
			    		   opt = Integer.parseInt(myScanner.nextLine());
			    		   selection = false;
			    	   }//try
			    	   catch(Exception e){//catch
			    		 System.out.println("please type in number");
			    		 selection = true;
			    	   }//catch
			       }while(selection);//do +while
			       
			       switch(opt){
			          case 0:
			         
			        	  exit = true;
			        	  System.out.println("you just clicked exit, we will go back to main menu");
			
			        	  break;
			
			          case 1 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;
			         

			        	  break;
			
					  case 2 : 
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;
						  	
						  break;
					
					  case 3 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

			     
						  break;  
					
					  case 4 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;
					  

			
						  break;

					
					  case 5 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

			
						  break;   
					            
					  case 6 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

			
						  break;   
					
					  case 7 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

			
						  break;   
					
					  case 8 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

						  break;
					       
					  case 9 :
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

						  break;   
							               
					  case 10 :			  
			            	cal.addList(make.monitorList.get(opt));
			            	cal.addCost(make.monitorPriceList.get(opt));
			            	System.out.println("you chose to buy " + make.monitorList.get(opt) + " and price will be " + make.monitorPriceList.get(opt)+"$");
			            	exit = true;

						  break;
					  
					  default :
						  		               
						  System.out.println("you typed wrong! you must type between 0~10!");
						  break;
					}//end switch
			   }//end while 		 
	 }

}