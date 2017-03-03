import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import java.util.Timer; 
import javax.swing.*; 

// CS211 Winter Quarter.
// This program will be modified/expanded to include all the topics in Chapter 9 and Chapter 10.

public class GUI4Chapter910 { 
   public static void main(String[] args) { 
      new myframe();// creating main jframe instance 
   } 
} 

class myframe extends JFrame 
{ 
   Container c; 
   JPanel panel; 
   JButton addbutton, removebutton; 
   JLabel counter, ballsize; 

   JTextField size_input; 
   JComboBox cb; 
   buttonListener handle; 
   myDrawboard myboard; 
   JFrame mainFrame; 
   public myframe() 
   {   
      super("Your title"); 
      c = getContentPane(); 
      size_input = new JTextField(5); 
      counter = new JLabel("Count :  "); 
      ballsize = new JLabel("Size :  "); 
      
      size_input.setText("50");
      addbutton = new JButton("Add"); 
      removebutton = new JButton("Remove"); 
      cb = new JComboBox(); 
      cb.addItem("Ball"); 
      cb.addItem("Box"); 
      handle = new buttonListener(); 
      addbutton.addActionListener(handle); 
      removebutton.addActionListener(handle); 
      panel = new JPanel(); 
      panel.add(ballsize); 
      panel.add(size_input); 
      panel.add(addbutton); 
      panel.add(removebutton); 
      panel.add(counter); 
      panel.add(cb); 

      myboard = new myDrawboard(); 
      c.add(myboard.panel, BorderLayout.CENTER);  
      c.add(panel, BorderLayout.SOUTH); 
    
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setVisible(true);   
      // update screen (refresh)
      Timer timer = new Timer(); 
      timer.schedule(new myTimer(), 0, 16); 
   } 
   
   class myTimer extends TimerTask 
   { 
      @Override 
      public void run()  { 
         repaint(); 
      } 
   } 
   
   class buttonListener implements ActionListener { 
      int i; 
      public void actionPerformed(ActionEvent action) 
      {       
         if (action.getSource() == addbutton) { 
            if (!size_input.getText().equals("")) { 
               try 
               { 
                  myboard.additem(Integer.parseInt(size_input.getText()), cb.getSelectedItem().toString()); 
                  counter.setText(" Count : " + myboard.countItem()+ "  ");  
               } 
               catch (NumberFormatException e) 
               { 
                  System.out.println(e); 
                  JOptionPane.showMessageDialog(null, "Enter only number!", "Invalid Input", JOptionPane.INFORMATION_MESSAGE); 
               } 
            } 
            else 
            { 
               JOptionPane.showMessageDialog(null, "Enter the Object size!", "Input needed", JOptionPane.INFORMATION_MESSAGE); 
            } 
         } 
         if (action.getSource() == removebutton) 
         { 
            myboard.removeBall(); 
            counter.setText(" Count : " + myboard.countItem()+ "  "); 
         } 
      } 
   } 
} 
class myDrawboard 
{ 
   private static int count = 0; 
   Graphics2D g2; 
   MyPanel panel = new MyPanel(); 
   public void additem(int size, String shape) 
   { 
      count++; 
      panel.addShape(size, shape);  
   } 
   public String countItem() { 
      return Integer.toString(count); 
   } 
   public void removeBall() { 
      if (panel.deleteShape())  { 
         count--; 
      } 
   } 
} 

class MyPanel extends JPanel 
{ 
   ArrayList<DrawObject> myArrayList = new ArrayList<DrawObject>(); 
   public MyPanel() 
   {  
     setBackground(Color.BLACK); 
   } 
   
   public void addShape(int size, String shape) 
   { 
      Random randomGenerator = new Random(); 
      int x = randomGenerator.nextInt(200); 
      int y = randomGenerator.nextInt(200); 
      int R = randomGenerator.nextInt(256); 
      int G = randomGenerator.nextInt(256); 
      int B = randomGenerator.nextInt(256); 
      int vx = randomGenerator.nextInt(10)+2; 
      int vy = randomGenerator.nextInt(10)+2; 
      Color randomcolor = new Color(R, G, B); 
      if (shape == "Box") 
      { 
         Box box = new Box(); 
         box.setInfo(size, x, y, randomcolor, vx, vy); 
         myArrayList.add(box); 
      } 
      else    // shape==ball
      { 
         Ball ball = new Ball(); 
         ball.setInfo(size, x, y, randomcolor, vx, vy); 
         myArrayList.add(ball); 
      }  
   } 
   public boolean deleteShape() 
   { 
      if (myArrayList.size() > 0) 
      { 
         myArrayList.remove(myArrayList.size() - 1);  // remove the last one
         return true; 
      } 
      return false; 
   } 
   public void paintComponent(Graphics g) 
   { 
      Graphics2D g2 = (Graphics2D) g;   
      g2.setColor(Color.BLACK); 
      g2.fillRect(0,0,getWidth(), getHeight()); 
      for (int i = 0; i < myArrayList.size(); i++) 
      { 
         myArrayList.get(i).update(getWidth(), getHeight()); 
         myArrayList.get(i).drawObject(g2); 
      } 
   } 
} 

interface DrawObject 
{ 
   void drawObject(Graphics2D g2); 
   void update(int width, int height); 
} 
class Ball implements DrawObject 
{ 
   private int size; 
   int x; 
   int y; 
   int velX; 
   int velY; 
   private Color color; 
   public void setInfo(int size, int x, int y, Color randomcolor, int vx, int vy) 
   { 
      this.size = size; 
      this.x = x; 
      this.y = y; 
      this.velX = vx; 
      this.velY = vy; 
      this.color = randomcolor; 
   } 
   public void drawObject(Graphics2D g2) 
   { 
      g2.setColor(color); 
      g2.fillOval(x, y, size * 2, size * 2); 
      g2.setFont(new Font("SansSerif", Font.BOLD, 50));  
     // g2.drawString("cs211",x,y);
      g2.drawString("CS211",100,100);

   } 
   //Ball moving 
   public void update(int width, int height) 
   { 
      x += velX; 
      if(x < 0 || x > width-size*2) 
      velX *= -1; 
      y += velY; 
      if(y < 0 || y > height-size*2) 
      velY *= -1; 
   } 
} 
class Box implements DrawObject 
{ 
   private int size; 
   int x; 
   int y; 
   int velX; 
   int velY;
   private Color color; 
   private Rectangle square; 
   public void setInfo(int size, int x, int y, Color randomcolor, int vx, int vy) 
   { 
      square = new Rectangle(x, y, size, size); 
      this.velX = vx; 
      this.velY = vy; 
      color = randomcolor; 
   } 
   public void drawObject(Graphics2D g2) 
   { 
      g2.setColor(color); 
      g2.fillRect(square.x, square.y, square.width, square.height); 
   } 
   //box moving 
   public void update(int width, int height) 
   { 
      square.x += velX;   
      if(square.x < 0 || square.x > width-square.width) 
      velX *= -1; 
      square.y += velY;   
      if(square.y < 0 || square.y > height-square.height) 
      velY *= -1; 
   } 
} 