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

class FactorialDialog extends JInternalFrame{

   private static FactorialDialog instance = null;
   
   private JTextField tf;
   private JButton btn;
   private JLabel lb1, lb12;
   private JPanel upperPanel, lowerPanel;
   
   public static FactorialDialog getInstance(){
      if(instance == null){
         instance = new FactorialDialog();
      }
      return instance;
   }
   
   private void FactorialActionPerformed(){
   
      int input;
      lb1.setText("");
      try{
         input = Integer.parseInt(tf.getText());
         Recursion re = new Recursion();
         lb1.setText("" + re.factorial(input));
         
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(this, "Bad input! Try again.");
      }
   }
   
   //private constructor
   private FactorialDialog(){
   
      //call constructor of JInternalFrame
      //arguments: title, resizablity, closability,
      //       maximizability, and iconifiabilty
      super("Factorial", false, true, false, false);
      
      tf = new JTextField(10);
      btn = new JButton("Factorial?");
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
            FactorialActionPerformed();
         }
      });
      
      setBounds(25, 25, 250, 120);
      setLocation(100, 100);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
}