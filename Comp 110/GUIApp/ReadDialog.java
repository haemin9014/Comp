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
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class ReadDialog extends JInternalFrame{

   private static ReadDialog instance = null;
   
   private JLabel lb1, lb12;
   private JTextField tf;
   private JButton fileBtn, readBtn;
   private JFileChooser fc;
   private String fileName;
   private Task task;
   private JProgressBar progressBar;
   
   public static ReadDialog getInstance(){
      if(instance == null){
         instance = new ReadDialog();
      }
      return instance;
   }
   
   class Task extends SwingWorker<Void, Void>{
   
      /*
         *Main task.
         */
      @Override
      public Void doInBackground(){
      
         readFile();
         
         return null;
      }
      
      public void done(){
         progressBar.setIndeterminate(false);
         readBtn.setEnabled(true);
      }
   }
   
   private void chooseFile(){
      lb12.setText("");
      fileName = "";
      int returnVal = fc.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION){
         File file = fc.getSelectedFile();
         tf.setText(file.getAbsolutePath());
         fileName = file.getAbsolutePath();
      }
      else{
         JOptionPane.showMessageDialog(this, "Open command cancelled by user.");
      }
   }
   
   private void readFile() {
   
      if(fileName.equals("")) {
         JOptionPane.showMessageDialog(this, "Choose a file!");
         return;
      }
      
      lb12.setText("");
      try {
         int lines = 0;
         String fileLine = "";
         FileReader data = new FileReader(fileName);
         BufferedReader br = new BufferedReader(data);
         while((fileLine = br.readLine()) != null) {
            lines++;
         }
         lb12.setText(String.valueOf(lines));
      
         br.close();
      }
      
      catch(FileNotFoundException ex) {
      
         JOptionPane.showMessageDialog(this, "File not found!");
      
      }
      catch(IOException ex) {
      
         JOptionPane.showMessageDialog(this, "An error occured");
      
      }
   }
   
   //private constructor
   private ReadDialog() {
      
      super("File Info", false, true, false, false);

      tf = new JTextField(50);
      tf.setEditable(false);
      fileBtn = new JButton("...");
      readBtn = new JButton("Read");
      lb1 = new JLabel("Number of lines: ");
      lb12 = new JLabel();
      fc = new JFileChooser();
      progressBar = new JProgressBar(0, 100);
      progressBar.setStringPainted(false);      
      fileName = "";
      
      fileBtn.setPreferredSize(new Dimension(20, 20));
      readBtn.setPreferredSize(new Dimension(80, 20));
      
      JPanel upperPanel = new JPanel();
      JPanel midPanel = new JPanel();
      JPanel lowerPanel = new JPanel();
    
      upperPanel.setLayout(new FlowLayout());
      midPanel.setLayout(new FlowLayout());
      lowerPanel.setLayout(new FlowLayout());
      
      upperPanel.add(tf);
      upperPanel.add(fileBtn);
      upperPanel.add(readBtn);
      
      midPanel.add(progressBar);
      
      lowerPanel.add(lb1);
      lowerPanel.add(lb12);
      
      add(upperPanel, BorderLayout.NORTH);
      add(midPanel, BorderLayout.CENTER);
      add(lowerPanel, BorderLayout.SOUTH);
      
      // add button listener
      fileBtn.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) { 
            chooseFile();
         } 
      });   

      // add button listener
      readBtn.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            progressBar.setIndeterminate(true);
            readBtn.setEnabled(false);
            task = new Task();
            //task.addPropertyChangeListener(this);
            task.execute();
         } 
      });      
      
      pack();
      setBounds(25, 25, 700, 120);
      setLocation(50, 50);
      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      
   }
   
}

               