
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

class FahrenheitCelsiusDialog extends JInternalFrame{

   private static FahrenheitCelsiusDialog instance = null;
   
   private JTextField tf;
   private JButton btn;
   private JLabel lb1, lb12;
   private JPanel upperPanel, lowerPanel;
   
   public static FahrenheitCelsiusDialog getInstance(){
      if(instance == null){
         instance = new FahrenheitCelsiusDialog();
      }
      return instance;
   }
   
   private void FahrenheitCelsiusActionPerformed(){
   
      int input = 0;
      lb1.setText("");
      try{
         input = Integer.parseInt(tf.getText());
        lb1.setText(""+(input - 32) / 1.8);
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(this, "Bad input! Try again.");
      }
   }
   
   //private constructor
   private FahrenheitCelsiusDialog(){
   
      //call constructor of JInternalFrame
      //arguments: title, resizablity, closability,
      //       maximizability, and iconifiabilty
      super("F2C", false, true, false, false);
      
      tf = new JTextField(10);
      btn = new JButton("F2C");
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
            FahrenheitCelsiusActionPerformed();
         }
      });
      
      setBounds(25, 25, 250, 120);
      setLocation(100, 100);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
}            
          