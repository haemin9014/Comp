//Name:Haemin Lee
//Date:5/06/2017
//purpose: make GUI using algo
//application:remind how to use algo

//abstract window toolkit(awt)
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;

//awt events
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//swing
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


//swing event
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

//swing tree
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;


public class GUIApp{//class open
   
   private JFrame frame;
   private JPanel panel;
   private JScrollPane scrollPane;
   private JDesktopPane desktop;   
   private JSplitPane splitPane;
   private JPanel labelPanel;
   private JLabel statusLabel;
   private JMenuBar menuBar;
   private JMenu fileMenu;
   private JMenu helpMenu;
   private JMenuItem exitItem;
   private JMenuItem aboutItem;
   private JTree tree;

   public GUIApp(){//constructor
      initComponents();
      statusLabel.setText("Initialization complete.");
   }//consturctor
   
   private void initComponents(){
      setLookAndFeel();
      buildDesktop();
      buildTree();
      addTreeListeners();
      buildMenu();
      addMenuListeners();
      buildPanel();
      buildFrame();
   }
   
   private void buildTree(){
   
      //create data for the tree
      
      DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tools");
      DefaultMutableTreeNode alg = new DefaultMutableTreeNode("Algorithms");
      DefaultMutableTreeNode itr = new DefaultMutableTreeNode("Iterative");
      DefaultMutableTreeNode odd = new DefaultMutableTreeNode("Odd");
      DefaultMutableTreeNode even = new DefaultMutableTreeNode("Even");
      DefaultMutableTreeNode f2c = new DefaultMutableTreeNode("F2C");
      DefaultMutableTreeNode c2f = new DefaultMutableTreeNode("C2F");
      DefaultMutableTreeNode max = new DefaultMutableTreeNode("Maximum");
      DefaultMutableTreeNode min = new DefaultMutableTreeNode("Minimum");
      DefaultMutableTreeNode area = new DefaultMutableTreeNode("area of circle");
      DefaultMutableTreeNode cir = new DefaultMutableTreeNode("circumferenceCircle");
      DefaultMutableTreeNode rec = new DefaultMutableTreeNode("Recursive");
      DefaultMutableTreeNode fib = new DefaultMutableTreeNode("Fibonacci");
      DefaultMutableTreeNode fac = new DefaultMutableTreeNode("Factorial");
      DefaultMutableTreeNode gcd = new DefaultMutableTreeNode("GC");
      
      DefaultMutableTreeNode io = new DefaultMutableTreeNode("IO");
      DefaultMutableTreeNode fileInfo = new DefaultMutableTreeNode("File Info");
      
      itr.add(odd);
      itr.add(even);
      itr.add(f2c);
      itr.add(c2f);
      itr.add(max);
      itr.add(min);
      itr.add(area);
      itr.add(cir);
      
      rec.add(fib);
      rec.add(fac);
      rec.add(gcd);
      
      alg.add(itr);
      alg.add(rec);
      
      io.add(fileInfo);
      
      root.add(alg);
      root.add(io);
      
      //create a neww tree
      
      DefaultTreeModel treeModel = new DefaultTreeModel(root);
      tree = new JTree(treeModel);
   }
   
   private void addTreeListeners(){
   
      tree.addMouseListener(
         new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
               int selRow = tree.getRowForLocation(e.getX(), e.getY());
               if(selRow != -1){
                  treeClicked();
               }
            }
         }
      );
   }
   
   private void treeClicked(){
      //get the last selected tree node
      DefaultMutableTreeNode node = (DefaultMutableTreeNode)
         tree.getLastSelectedPathComponent();
         
      //if the node is a leaf(no children, keep going)
      if(node != null && node.isLeaf()){
         statusLabel.setText(node.toString() + " clicked.");
         
         if(node.toString().equals("Odd")){
            OddDialog od = OddDialog.getInstance();
            if(!od.isVisible()){            
               od.setVisible(true);
               desktop.add(od);
            }
         }
         else if(node.toString().equals("Even")){
            EvenDialog ev = EvenDialog.getInstance();
            if(!ev.isVisible()){            
               ev.setVisible(true);
               desktop.add(ev);
            }
         }
         else if(node.toString().equals("F2C")){
            FahrenheitCelsiusDialog fc = FahrenheitCelsiusDialog.getInstance();
            if(!fc.isVisible()){            
               fc.setVisible(true);
               desktop.add(fc);
            }
         }
         
         else if(node.toString().equals("C2F")){
            CelsiusFahrenheitDialog cf = CelsiusFahrenheitDialog.getInstance();
            if(!cf.isVisible()){            
               cf.setVisible(true);
               desktop.add(cf);
            }
         }
         else if(node.toString().equals("Maximum")){
             MaxDialog ma = MaxDialog.getInstance();
            if(!ma.isVisible()){            
               ma.setVisible(true);
               desktop.add(ma);
            }
         }
         else if(node.toString().equals("Minimum")){
            MinDialog mi = MinDialog.getInstance();
            if(!mi.isVisible()){            
               mi.setVisible(true);
               desktop.add(mi);
            }
         }
         
         else if(node.toString().equals("area of circle")){
            AreaCircleDialog acd = AreaCircleDialog.getInstance();
            if(!acd.isVisible()){            
               acd.setVisible(true);
               desktop.add(acd);
            }
         }
         
         else if(node.toString().equals("circumferenceCircle")){
            CircumferenceCircleDialog cfc = CircumferenceCircleDialog.getInstance();
            if(!cfc.isVisible()){            
               cfc.setVisible(true);
               desktop.add(cfc);
            }
         }

         else if(node.toString().equals("Fibonacci")){
            FibonacciDialog fbc = FibonacciDialog.getInstance();
            if(!fbc.isVisible()){            
               fbc.setVisible(true);
               desktop.add(fbc);
            }
         }

         
         
         else if(node.toString().equals("Factorial")){
            FactorialDialog fd = FactorialDialog.getInstance();
            if(!fd.isVisible()){            
               fd.setVisible(true);
               desktop.add(fd);
            }
         }
         
         else if(node.toString().equals("GC")){
            GCDialog gc = GCDialog.getInstance();
            if(!gc.isVisible()){            
               gc.setVisible(true);
               desktop.add(gc);
            }
         }
         else if(node.toString().equals("File Info")){
            ReadDialog rd = ReadDialog.getInstance();
            if(!rd.isVisible()){
               rd.setVisible(true);
               desktop.add(rd); 
            }
         }
      }
   }                     
      
   private void setLookAndFeel(){//open
      try{
         UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName()
         );
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
   
   private void buildDesktop(){
      desktop = new JDesktopPane(){
         @Override
         protected void paintComponent(Graphics g){
            ImageIcon icon = new ImageIcon("images/csun_logo.png");
            Image image = icon.getImage();
      
            int x=0, y=0;
            double imageWidth = image.getWidth(null);
            double imageHeight = image.getHeight(null);
            double screenWidth = getWidth();
            double screenHeight = getHeight();
      
            if(screenWidth != 0){
               x = (int)screenWidth / 2 - (int)imageWidth / 2;
            }   
      
            if(screenHeight != 0){
               y = (int)screenHeight / 2 - (int)imageHeight /2;
            }
      
            g.drawImage(image, x, y, this);            
         }
      };
   }      
   
   private void buildMenu(){            
      //build menu
      menuBar = new JMenuBar();
      fileMenu = new JMenu("File");
      helpMenu = new JMenu("Help");
      exitItem = new JMenuItem("Exit");
      aboutItem = new JMenuItem("About");
      fileMenu.add(exitItem);
      helpMenu.add(aboutItem);
      menuBar.add(fileMenu);
      menuBar.add(helpMenu);
      
   }
   
   private void addMenuListeners(){
   
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
   }
   
   private void buildPanel(){
      panel = new JPanel();
      splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);      
      scrollPane = new JScrollPane();
      statusLabel = new JLabel();
      labelPanel = new JPanel();
      
      scrollPane.getViewport().add(tree);

      statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
      statusLabel.setMinimumSize(new Dimension(0,18));
      statusLabel.setPreferredSize(new Dimension(0,18));

      splitPane.setOneTouchExpandable(true);
      splitPane.setDividerLocation(200);
      splitPane.setContinuousLayout(true);
      splitPane.add(desktop, JSplitPane.RIGHT);
      splitPane.add(scrollPane, JSplitPane.LEFT);

      panel.setLayout(new BorderLayout());
      panel.add(splitPane, BorderLayout.CENTER);

      labelPanel.setLayout(new BorderLayout());
      labelPanel.add(statusLabel, BorderLayout.CENTER);

   }   

   private void buildFrame(){

      //create new frame and show it
      frame.setDefaultLookAndFeelDecorated(true);
      frame = new JFrame("My GUI Application");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());      
      frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/csun.gif"));
      //add label panel
      frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
      //add main panel
      frame.getContentPane().add(panel, BorderLayout.CENTER);
      //add menu bar   
      frame.setJMenuBar(menuBar);      
      frame.setSize(1280, 800);
      frame.setVisible(true);

   }
         
 /*     
       //add a label to the GUI
      JLabel label = new JLabel("Hey this is my first GUI app!");
      frame.getContentPane().add(label);
*/      
   public void exitActionPerformed(){
      frame.dispose();
   }
   
   public void aboutActionPerformed(){
      JOptionPane.showMessageDialog(null, "thanks for using my app!");
   }
   
}//class               