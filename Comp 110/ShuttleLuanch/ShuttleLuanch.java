//Name:Haemin Lee
//Date:04/08/2017
//application: how to learn using sleep in words
//purpose:get used to use sleep with for loop.
class ShurttleLaunch{//class

   final static int SLEEP_TIME_MILLISECONDS = 777;
   final static int SLEEP_TIME_MILLISECOND = 9000;
   
   public static void main (String args[]){//main
   
      String j = "hello~! \nWelcome to the shuttle Luanch!\n";
      for(int i = 0; i < j.length(); i++){//for
         try{//try
            System.out.print(j.charAt(i));
            Thread.sleep(SLEEP_TIME_MILLISECONDS/10);
         }//try
         catch(InterruptedException e){//catch
            System.out.println("InterruptedException");
         }//catch
      }//for end
      
      for(int l = 10 ; l > 2; l -= 1){//for

         
         try{//try
               System.out.println("T - " + l);
               Thread.sleep(SLEEP_TIME_MILLISECOND/6);
            }//try closed
         catch(InterruptedException e){//catch
               System.out.println("InterruptedException");
         }//catch
      }//closed for
      
      String k = "Ignition.......\n";
      for(int i = 0; i < k.length(); i++){//for
         try{//try
            System.out.print(k.charAt(i));
            Thread.sleep(SLEEP_TIME_MILLISECONDS/10);
         }//try
         catch(InterruptedException e){//catch
            System.out.println("InterruptedException");
         }//catch
      }//for end
      
      for(int o = 2 ; o >= 0; o -= 1){//for

         
         try{//try
               System.out.println("T - " + o);
               Thread.sleep(SLEEP_TIME_MILLISECOND/6);
            }//try closed
         catch(InterruptedException e){//catch
               System.out.println("InterruptedException");
         }//catch
      }//closed for
      
      String m = "we have a liftoff!!";
      for(int i = 0; i < m.length(); i++){//for
         try{//try
            System.out.print(m.charAt(i));
            Thread.sleep(SLEEP_TIME_MILLISECONDS/10);
         }//try
         catch(InterruptedException e){//catch
            System.out.println("InterruptedException");
         }//catch
      }//for end

         

   }//main close
}//class close               
   