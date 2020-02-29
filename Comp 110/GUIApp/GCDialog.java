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

class GCDialog extends JInternalFrame{

   private static GCDialog instance = null;
   
   private JTextField tf, tf2;
   private JButton btn;
   private JLabel lb1, lb12;
   private JPanel upperPanel, lowerPanel;
   
   public static GCDialog getInstance(){
      if(instance == null){
         instance = new GCDialog();
      }
      return instance;
   }
   
   private void GCActionPerformed(){
   
      int input;
      int input2;
      lb1.setText("");
      try{
         input = Integer.parseInt(tf.getText());
         input2 = Integer.parseInt(tf2.getText());
         Recursion re = new Recursion();
         lb1.setText("" + re.ea(input, input2));
         
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(this, "Bad input! Try again.");
      }
   }
   
   //private constructor
   private GCDialog(){
   
      //call constructor of JInternalFrame
      //arguments: title, resizablity, closability,
      //       maximizability, and iconifiabilty
      super("GC", false, true, false, false);
      
      tf = new JTextField(10);
      tf2 = new JTextField(10);
      btn = new JButton("GC?");
      lb1 = new JLabel("Answer: ");
      lb12 = new JLabel();
      upperPanel = new JPanel();
      lowerPanel = new JPanel();
      upperPanel.setLayout(new FlowLayout());
      upperPanel.setLayout(new FlowLayout());
      
      upperPanel.add(tf);
      upperPanel.add(tf2);
      upperPanel.add(btn);
      
      lowerPanel.add(lb1);
      lowerPanel.add(lb12);
      
      add(upperPanel, BorderLayout.NORTH);
      add(lowerPanel, BorderLayout.SOUTH);
      
      //add button listener
      btn.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            GCActionPerformed();
         }
      });
      
      setBounds(60, 60, 350, 350);
      setLocation(100, 100);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
}