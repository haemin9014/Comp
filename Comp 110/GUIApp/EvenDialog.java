//Name:Haemin Lee
//Date:5/06/2017
//purpose: make GUI using algo
//application:remind how to use algo

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class EvenDialog extends JInternalFrame{

   private static EvenDialog instance = null;
   
   private JTextField tf;
   private JButton btn;
   private JLabel lb1, lb12;
   private JPanel upperPanel, lowerPanel;
   
   public static EvenDialog getInstance(){
      if(instance == null){
         instance = new EvenDialog();
      }
      return instance;
   }
   
   private void oddActionPerformed(){
   
      int input = 0;
      lb1.setText("");
      try{
         input = Integer.parseInt(tf.getText());
         if(input % 2 == 0){
            lb1.setText("yes!");
         }
         else{
            lb12.setText("Nope!");
         }
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(this, "Bad input! Try again.");
      }
   }
   
   //private constructor
   private EvenDialog(){
   
      //call constructor of JInternalFrame
      //arguments: title, resizablity, closability,
      //       maximizability, and iconifiabilty
      super("Even", false, true, false, false);
      
      tf = new JTextField(10);
      btn = new JButton("Even?");
      lb1 = new JLabel("Answer: ");
      lb12 = new JLabel();
      upperPanel = new JPanel();
      lowerPanel = new JPanel();
      upperPanel.setLayout(new FlowLayout());
      upperPanel.setLayout(new FlowLayout());
      
      upperPanel.add(tf);
      upperPanel.add(btn);
      
      lowerPanel.add(lb1);
      lowerPanel.add(lb12);
      
      add(upperPanel, BorderLayout.NORTH);
      add(lowerPanel, BorderLayout.SOUTH);
      
      //add button listener
      btn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            oddActionPerformed();
         }
      });
      
      setBounds(25, 25, 250, 120);
      setLocation(100, 100);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
}            
 