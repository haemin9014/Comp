//Name:Haemin Lee
//Date:5/06/2017
//purpose: make GUI using algo
//application:remind how to use algo

import javax.swing.SwingUtilities;

class Driver{

   public static void main(String[] args){//main
   
      //build and show app using the event-dispatching thread
      javax.swing.SwingUtilities.invokeLater(new Runnable(){//java open
         public void run(){//run open
            GUIApp app = new GUIApp();
         }//run close
      });//java close
   }//main
} 