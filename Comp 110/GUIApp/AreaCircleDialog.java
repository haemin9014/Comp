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

class AreaCircleDialog extends JInternalFrame{

   private static AreaCircleDialog instance = null;
   
   private JTextField tf;
   private JButton btn;
   private JLabel lb1, lb12;
   private JPanel upperPanel, lowerPanel;
   
   public static AreaCircleDialog getInstance(){
      if(instance == null){
         instance = new AreaCircleDialog();
      }
      return instance;
   }
   
   private void AreaCircleActionPerformed(){
   
      int input = 0;
      lb1.setText("");
      try{
         input = Integer.parseInt(tf.getText());
         lb1.setText("when your radius is " + input + "\n your area of circle is \n " + (input * input * 3.14));
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(this, "Bad input! Try again.");
      }
   }
   
   //private constructor
   private AreaCircleDialog(){
   
      //call constructor of JInternalFrame
      //arguments: title, resizablity, closability,
      //       maximizability, and iconifiabilty
      super("area of circle", false, true, false, false);
      
      tf = new JTextField(10);
      btn = new JButton("area of circle");
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
            AreaCircleActionPerformed();
         }
      });
      
      setBounds(40, 40, 350, 220);
      setLocation(100, 100);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
}            
          