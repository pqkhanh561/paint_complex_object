/** 
   *  =====================================================================
   *  PolygonGUI.java Simple GUI with canvas connected to a mouse listener
   *  and buttons connected to button listeners
   * 
   *  Written By : Mark Austin                                November 2005
   *  =====================================================================
   */ 

import java.lang.Math.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.font.*;
import java.awt.image.*;
import java.awt.geom.*;   // Needed for affine transformation....
import java.net.URL;

public class PolygonGUI extends JFrame {
    static DemoGraphicsScreen gs;
    static JFrame frame;

    public PolygonGUI() {

      // 1. Create a graphics screen and mouse listener,
      //    and add content pane to center of applet.

      DemoGraphicsScreen gs = new DemoGraphicsScreen();
      gs.addMouseListener( new GraphicsListener( gs ));

      // 2. Create polygon toolbar ....

      JToolBar toolBar = new PolygonToolBar( gs );

      // 3. Create buttons and button listeners.

      final int NoButtons = 5;
      JButton buttons[] = new JButton [ NoButtons ]; 

      buttons[0] = new JButton ("Clear");
      buttons[0].addActionListener( new ButtonAction( buttons[0], gs ));

      buttons[1] = new JButton ("Load");
      buttons[1].addActionListener( new ButtonAction( buttons[1], gs ));

      buttons[2] = new JButton ("Print");
      buttons[2].addActionListener( new ButtonAction( buttons[2], gs ));

      buttons[3] = new JButton ("Draw");
      buttons[3].addActionListener( new ButtonAction( buttons[3], gs ));

      buttons[4] = new JButton ("Properties");
      buttons[4].addActionListener( new ButtonAction( buttons[4], gs ));

      // 3. Create panel. Add buttons to panel.

      Panel p1 = new Panel();
      for(int ii = 1; ii <= NoButtons; ii++ )
          p1.add( buttons[ii-1] );

      // 4. Position canvas and panel within the frame.

      JPanel panel = new JPanel();
      panel.setLayout( new BorderLayout() );
      panel.add( "North", toolBar );
      panel.add( "South", p1 );
      panel.add("Center", gs );

      // 5. Size and display GUI ....

      frame = new JFrame("Polygon Visualization Package");
      frame.getContentPane().setLayout( new BorderLayout() );
      frame.getContentPane().add( panel );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.setSize( 900, 700);
      frame.setVisible(true);
   }

   // Get frame for GUI ....

   public static JFrame getPolygonGUIFrame () {
      return frame;
   }

   /**
    * @param args the command line arguments
    */

   public static void main( String args[] ) {
       try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       } catch (Exception e) {}

       new PolygonGUI();
   }
}

/*
 *  =================================================================
 *  This class listens for action events associated with the buttons.
 *  =================================================================
 */

class ButtonAction implements ActionListener {
      private JButton b;
      private DemoGraphicsScreen gs;

      public ButtonAction ( JButton b, DemoGraphicsScreen gs ) {
         this.b  = b;
         this.gs = gs;
      }

      public void actionPerformed ( ActionEvent e ) {
         String s = new String( e.getActionCommand() );

         // Clear Screen ....

         if( s.compareTo("Clear") == 0 ) {
             gs.clearScreen();
         }

         // Load polygon from XML file ...

         if( s.compareTo("Load") == 0 ) {
             gs.loadPolygon();
         }

         if( s.compareTo("Print") == 0 ) {
             gs.printPolygon();
         }

         // Draw polygon cross section....

         if( s.compareTo("Draw") == 0 ) {
             System.out.println("*** go to drawPolygon()...");
             gs.drawPolygon();
         }

         // Compute engineering properties of polygon cross section....
         // Draw on the screen ...

         if( s.compareTo("Properties") == 0 ) {
             gs.drawProperties();
             gs.drawCentroid();
             gs.drawPrincipalAxes();
         }
      }
}

/*
 *  ==============================================================
 *  This class listens for mouse events associated with the canvas
 *  ==============================================================
 */

class GraphicsListener implements MouseListener {
      private DemoGraphicsScreen gs;

      public GraphicsListener( DemoGraphicsScreen gs ) {
         this.gs = gs;
      }

      // When the mouse is clicked we check validity
      // of the design point ....

      public void mouseClicked ( MouseEvent e ) {
         int b;  
         int xx[] = new int[1]; 
         int yy[] = new int[1]; 

         xx[0] = e.getX();
         yy[0] = e.getY();

         // Snap pixel coordinate to nearest grid coordinate ....

         if( gs.grid.getGridSnap() == true) { 
             xx[0] = xx[0] - 20;
             yy[0] = gs.getHeight()-20-yy[0];
             gs.grid.snapxy( xx, yy );
             xx[0] = xx[0] + 20;
             yy[0] = gs.getHeight()-20-yy[0];
         }       

         // Check design point ....

         gs.checkDesignPoint( xx[0], yy[0] );

         // Find closest node in polygon ...

         if ( gs.moveEnabled == true ) {
              System.out.println("*** gs.moveEnabled = true!!!" );
         }
      }

      public void mousePressed ( MouseEvent e ) {
         int b;  
         int xx[] = new int[1]; 
         int yy[] = new int[1]; 

         xx[0] = e.getX();
         yy[0] = e.getY();

         // Snap pixel coordinate to nearest grid coordinate ....

         if( gs.grid.getGridSnap() == true) { 
             xx[0] = xx[0] - 20;
             yy[0] = gs.getHeight()-20-yy[0];
             gs.grid.snapxy( xx, yy );
             xx[0] = xx[0] + 20;
             yy[0] = gs.getHeight()-20-yy[0];
         }       

         // Check design point ....

         gs.checkDesignPoint( xx[0], yy[0] );

         // Find closest node in polygon ...

         if ( gs.moveEnabled == true ) {
              System.out.println("*** gs.moveEnabled = true!!!" );
         }
      }

      public void mouseReleased ( MouseEvent e ) {
         int b;  
         int xx[] = new int[1]; 
         int yy[] = new int[1]; 

         xx[0] = e.getX();
         yy[0] = e.getY();

         // Snap pixel coordinate to nearest grid coordinate ....

         if( gs.grid.getGridSnap() == true) { 
             xx[0] = xx[0] - 20;
             yy[0] = gs.getHeight()-20-yy[0];
             gs.grid.snapxy( xx, yy );
             xx[0] = xx[0] + 20;
             yy[0] = gs.getHeight()-20-yy[0];
         }       

         // Check design point ....

         gs.checkDesignPoint( xx[0], yy[0] );

         // Find closest node in polygon ...

         if ( gs.moveEnabled == true ) {
              System.out.println("*** gs.moveEnabled = true!!!" );
         }
      }

      public void mouseEntered ( MouseEvent e ) {};
      public void mouseExited ( MouseEvent e ) {};
}

/*
 *  =======================
 *  Create graphics screen.
 *  =======================
 */

class DemoGraphicsScreen extends JPanel {
    BufferedImage bi;
    private Dimension size;
    private Graphics gs;
    GraphicsGrid      grid;
    GraphicsOperation grop;
    boolean moveEnabled = false;

    PolygonAnalysis poly;
    String  errorMessage = null;
    int iXcoord, iYcoord;
    int width, height;

    DemoGraphicsScreen () {

       //  Create empty polygon and graphics grid ....

       poly = new PolygonAnalysis("poly 1");
       grid = new GraphicsGrid();

       //  Create object for management of graphics operations 

       grop = new GraphicsOperation();

       //  Declare variables for graphics operations ...

       moveEnabled = false;
    }

    //  paint() and repaint() methods ....

    public void paint(Graphics g) {
       System.out.println("*** In gs.paint()...");
       paintComponent(g);
    }

    public void repaint(Graphics g) {
       System.out.println("*** In gs.repaint()...");
       // clearScreen();
    }

    // Get graphics and fill in background ....

    public void clearScreen () {
       gs = getGraphics();
       Graphics2D g2D = (Graphics2D) gs;

       g2D.setColor( Color.white );
       g2D.fillRect( 0, 0, size.width-1, size.height-1 );
    }

    // ===========================================================
    // Retrieve canvas height and width....
    // ===========================================================

    public int getHeight() {
           size = getSize();
           return size.height;
    } 

    public int getWidth() {
           size = getSize();
           return size.width;
    } 

    // ===========================================================
    // Method to load polygon coordinates from XML input file ....
    // ===========================================================

    public void loadPolygon() {
       poly.load( "polygon.xml" );
    }

    public void loadPolygon( String sFileName ) {
       poly.load( sFileName );
    }

    // Print triangle mesh .....

    public void printPolygon() {
        poly.print();
    }

    // Paint graphics componet

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        size   = getSize();
        height = size.height;
        width  = size.width;

        // Draw grid .....
       
        if ( grid.getDrawGrid() == true )
             grid.drawGrid( g, getWidth(), getHeight() );

        // Draw polygon 

        drawPolygon();
    }

    // Method to draw grid ...

    public void drawGrid() {
       gs = getGraphics();
       grid.drawGrid(gs, getWidth(), getHeight() );
    }

    // Method to draw polygon cross section ... 

    public void drawPolygon () {
       int iX1 = 0; int iX2 = 0;
       int iY1 = 0; int iY2 = 0;
       int iXFirst = 0;
       int iYFirst = 0;

       System.out.println("Enter drawPolygon()...       " );
       System.out.println("============================ " );

       // Translate graphics coordinate system .....

       AffineTransform at = new AffineTransform();
       at.translate(  20, getHeight() - 20 ); 
       at.scale( 1, -1); 

       gs = getGraphics();
       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Change color for drawing polygon ...

       g2D.setColor( Color.red );
       BasicStroke stroke = new BasicStroke( 2 );
       g2D.setStroke(stroke);

       // Walk along list of loops in polygon ....

       Iterator iterator1 = poly.loopList.iterator();
       while ( iterator1.hasNext() != false ) {
            Loop lp = (Loop) iterator1.next();

            // Walk along nodes in loop.....

            int iNodeNo = 0;
            Iterator iterator2 = lp.nodeList.iterator();
            while ( iterator2.hasNext() != false ) {
                Vertex ns = (Vertex) iterator2.next();

                if( iNodeNo == 0) {
                    iX1 = (int) ns.getX();
                    iY1 = (int) ns.getY();
                    iXFirst = iX1;
                    iYFirst = iY1;
                } else {

                    // Retrieve (x,y) coordinates at end of segment ...

                    iX2 = (int) ns.getX();
                    iY2 = (int) ns.getY();

                    // Draw line .....

                    g2D.drawLine ( iX1, iY1, iX2, iY2 );

                    // Update coordinates for drawing ....

                    iX1 = iX2; iY1 = iY2;
                }

                // Draw node as a filled dot ....

                g2D.fillRect( iX1-4, iY1-4, 8, 8);

                // Update node counter ....

                iNodeNo = iNodeNo + 1;
            }

            // Close loop by drawing last segment ...

            g2D.drawLine ( iX1, iY1, iXFirst, iYFirst );
       }

       // Walk along list of loops in polygon and label nodes ...

       Font f = new Font("Courier", Font.BOLD, 14 );
       g2D.setFont( f );
       g2D.setColor( Color.black );

       Iterator iterator3 = poly.loopList.iterator();
       while ( iterator3.hasNext() != false ) {
            Loop lp = (Loop) iterator3.next();

            // Walk along nodes in loop.....

            Iterator iterator4 = lp.nodeList.iterator();
            while ( iterator4.hasNext() != false ) {
                Vertex ns = (Vertex) iterator4.next();

                iX1 = (int) ns.getX();
                iY1 = (int) ns.getY();

                // INSERT MISSING DETAILS HERE ....
            }
       }
    }

    // Method to compute and draw properties of polygon cross section ... 

    public void drawProperties () {
       double dArea = poly.polygonArea();
       double dMinX = poly.getMinX();
       double dMaxX = poly.getMaxX();
       double dMinY = poly.getMinY();
       double dMaxY = poly.getMaxY();
       double dIxx  = poly.polygonIxx();
       double dIyy  = poly.polygonIyy();
       double dIxy  = poly.polygonIxy();
       double dCentroidX = poly.polygonCentroidX();
       double dCentroidY = poly.polygonCentroidY();

       System.out.println("Enter drawProperties()...     " );
       System.out.println("============================ " );

       // Translate graphics coordinate system .....

       AffineTransform at = new AffineTransform();
       at.translate(  20, getHeight() - 20 ); 
       at.scale( 1, -1); 

       gs = getGraphics();
       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Let's use a large "black" font ....

       Font f = new Font("Courier", Font.BOLD, 18 );
       g2D.setFont( f );
       g2D.setColor( Color.black );

       // Draw area and perimeter on graphics screen  ....

       g2D.drawString( "Polygon Area      = " + dArea,      400, -350 );

       // Test for round-off errors and answers close to zero...
       
       if ( Math.abs ( dIxx ) < 0.0001 ) dIxx = 0.00;
       if ( Math.abs ( dIyy ) < 0.0001 ) dIyy = 0.00;
       if ( Math.abs ( dIxy ) < 0.0001 ) dIxy = 0.00;

       // Draw max/min polygon properties ....

       g2D.translate(  0, 520 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Min X = " + dMinX, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate( -0, -520 );

       g2D.translate(  0, 490 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Max X = " + dMaxX, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate( -0, -490 );

       g2D.translate(  0,  460 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Min Y = " + dMinY, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate( -0, -460 );

       g2D.translate(  0,  430 );
       g2D.scale( 1, -1);
       g2D.drawString( "Max Y = " + dMaxY, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate( -0, -430 );

       g2D.translate(  0, 400 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Area = " + dArea, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate( -0,-400 );

       g2D.translate(   0,  370 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Centroid(X) = " + dCentroidX, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate(  -0, -370 );

       g2D.translate(   0,  340 );
       g2D.scale( 1, -1); 
       g2D.drawString( "Centroid(Y) = " + dCentroidY, 0, 0 );
       g2D.scale( 1, -1); 
       g2D.translate(  -0, -340 );

       // INSERT MISSING DETAILS HERE ....

       // Compute moments of inertia about the centroid...

       // INSERT MISSING DETAILS HERE ....

       // Test for round-off errors and answers close to zero...
       
       // INSERT MISSING DETAILS HERE ....

    }

    // Method to draw centroid of polygon .....

    public void drawCentroid () {
       double dCentroidX = poly.polygonCentroidX();
       double dCentroidY = poly.polygonCentroidY();

       AffineTransform at = new AffineTransform();
       at.translate(  20, getHeight() - 20 ); 
       at.scale( 1, -1); 

       gs = getGraphics();
       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       g2D.setColor( Color.black );
       g2D.fillRect( (int) (dCentroidX-3), (int) (dCentroidY-3), 6, 6);
    }

    // Method to compute and draw principal axes ....

    public void drawPrincipalAxes () {
       double dCentroidX = poly.polygonCentroidX();
       double dCentroidY = poly.polygonCentroidY();
       double dArea      = poly.polygonArea();
       double dIxx = poly.polygonIxx();
       double dIyy = poly.polygonIyy();
       double dIxy = poly.polygonIxy();
       double dIxxCentroid = 0.0;
       double dIyyCentroid = 0.0;
       double dIxyCentroid = 0.0;
       double AxisLength = 40;

       // Transform graphics coordinate system ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, getHeight() - 20 ); 
       at.scale( 1, -1); 

       gs = getGraphics();
       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Compute moments of inertia about the centroid...

       // INSERT MISSING DETAILS HERE ....

       // Compute and print angle of principle axes....

       double dAngle = Math.atan( 2*dIxyCentroid/(dIxxCentroid-dIyyCentroid));

       // Draw principal axes and "blue" centroid ......

       g2D.setColor( Color.blue );
       g2D.fillRect( (int) (dCentroidX - 3), (int) (dCentroidY - 3), 6, 6);
      
       // INSERT MISSING DETAILS HERE ....

    }

    public void checkDesignPoint( int xCoord, int yCoord ) {
       System.out.println("*** In checkDesignPoint()");

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, getHeight() - 20 );
       at.scale( 1, -1);

       // Transform pixel coordinates to viewpoint coordinates ...

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       double[] theMatrix = new double[6];
       at.getMatrix( theMatrix );

       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       double xViewpoint =  (( theMatrix[3]*(xCoord - theMatrix[4]) -
                               theMatrix[2]*(yCoord - theMatrix[5]) )/dDet );
       double yViewpoint =  ((-theMatrix[1]*(xCoord - theMatrix[4]) +
                               theMatrix[0]*(yCoord - theMatrix[5]) )/dDet);

       System.out.println("xViewpoint = " + xViewpoint );
       System.out.println("yViewpoint = " + yViewpoint );
    }
}
