//Name:Haemin Lee
//Date:5/06/2017
//purpose: make GUI using algo
//application:remind how to use algo

//java awt
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//java swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.JOptionPane;



public class MyFirstGUIApp{//class open
   private JFrame frame;
   private JDesktopPane desktop;
   private JScrollPane scrollPane;
   private JLabel statusLabel;
   private JPanel panel;
   private JPanel labelPanel;
   private JMenuBar memuBar;
   private JMenu fileMenu;
   private JMenu helpMenu;
   private JSplitPane splitPane;

   public MyFirstGUIApp(){//constructor
      initComponents();
      statusLabel.setText("Initialization complete.");
   }//consturctor
   
   private void initComponents(){//open
      try{
         UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName()
         );
      }
      catch(Exception e){
         e.printStackTrace();
      }
               
      //create new frame and give it a title
      frame.setDefaultLookAndFeelDecorated(true);
      frame = new JFrame("My First GUI Application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());      
      desktop = new JDesktopPane();
      scrollPane = new JScrollPane();
      statusLabel = new JLabel();
      panel = new JPanel();
      labelPanel = new JPanel();
      
      //set border, size and layout
      labelPanel.setLayout(new BorderLayout());
      statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
      statusLabel.setMinimumSize(new Dimension(0,18));
      statusLabel.setPreferredSize(new Dimension(0,18));
      
      //build menu
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      JMenu helpMenu = new JMenu("Help");
      JMenuItem exitItem = new JMenuItem("Exit");
      JMenuItem aboutItem = new JMenuItem("About");
      fileMenu.add(exitItem);
      helpMenu.add(aboutItem);
      menuBar.add(fileMenu);
      menuBar.add(helpMenu);

      //add lisnter to exit menu item      
      exitItem.addActionListener(
         new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
               exitActionPerformed();
            }
         }
      );

      //add listner to about menu item
      aboutItem.addActionListener(
         new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
               aboutActionPerformed();
            }
         }
      );                        

      //setup the panel
      panel.setLayout(new BorderLayout());
      splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      splitPane.setOneTouchExpandable(true);
      splitPane.setDividerLocation(200);
      splitPane.setContinuousLayout(true);
      
      splitPane.add(desktop, JSplitPane.RIGHT);
      splitPane.add(scrollPane, JSplitPane.LEFT);
      
      panel.add(splitPane, BorderLayout.CENTER);
      labelPanel.add(statusLabel, BorderLayout.CENTER);
      
      //add a label to the GUI
      JLabel label = new JLabel("Hey this is my first GUI app!");
      frame.getContentPane().add(label);
      
      //setup and show frame
      frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
      frame.getContentPane().add(panel, BorderLayout.CENTER);
      
      //show GUI
      frame.setSize(1280, 800);
      frame.setJMenuBar(menuBar);
      frame.setVisible(true);
   }//close
   
   public void exitActionPerformed(){
      frame.dispose();
   }
   
   public void aboutActionPerformed(){
      JOptionPane.showMessageDialog(null, "thanks for using my app!");
   }
   
}//class               