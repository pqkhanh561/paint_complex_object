/*
 *  ==========================================================================
 *  GraphicsGrid.java: Definition and Management of the Graphics Grid....
 *
 *  Written By : Mark Austin                                      October 2005
 *  ==========================================================================
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphicsGrid {
    int startX = 0;
    int startY = 0;
    int previousX = 0;
    int previousY = 0;
    int currentX = 0;
    int currentY = 0;
    boolean tracking = false;
    boolean grid = false;    // display grid points
    boolean snap = false;    // snap mouse press/release/motion to grid

    // Matrix transform for pixel to viewpoint coordinates ....

    double[] theMatrix = new double[6];

    // Define fidelity for grid size....

    static int gridSize = 20;

    // Constructor methods ....

    public GraphicsGrid() {}

    // Set fidelity of grid size...

    void setGridSize ( int gridSize ) {
         this.gridSize = gridSize;
    }

    // Retrieve starting/final coordinates ...

    public double getGridstartX()   { return startX; }
    public double getGridstartY()   { return startY; }
    public double getGridcurrentX() { return currentX; }
    public double getGridcurrentY() { return currentY; }

    // Toggle flag for setting and retrieving grid snap ...

    public void setGridSnap () {
       if( snap == false ) {
           snap = true;
       } else {
           snap = false;
       }
    }

    public boolean getGridSnap () { return snap; }
    public boolean getDrawGrid () { return grid; }
    public void setDrawGrid ()    { grid = true; }

    // Snap (x,y) coord to nearest coord .....

    void snapxy(int x[], int y[]) {
       int xg = (x[0]+gridSize/2)/gridSize;
       int yg = (y[0]+gridSize/2)/gridSize;

       x[0] = xg*gridSize;
       y[0] = yg*gridSize;
    }

    // Record start and current coordinates ....

    void startMotion( int x, int y ) {
       tracking = true;
       startX    = x;    startY  = y;
       previousX = x; previousY  = y; 
       currentX  = x;  currentY  = y; 
    }

    void currentMotion( int x, int y ) {
        currentX = x;  currentY = y; 
    }

    // Get viewpoint coordinates from Pixel Coordinates 

    public double getViewpointX( int xCoord, int yCoord,
                                 Graphics gs, int width, int height ) {

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       // Transform pixel coordinates to viewpoint coordinates ...

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);
       at.getMatrix( theMatrix );

       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       double xViewpoint =  (( theMatrix[3]*(xCoord - theMatrix[4]) -
                               theMatrix[2]*(yCoord - theMatrix[5]) )/dDet );

       return xViewpoint;
    }

    public double getViewpointY( int xCoord, int yCoord, 
                                 Graphics gs, int width, int height ) {

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       // Transform pixel coordinates to viewpoint coordinates ...

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);
       at.getMatrix( theMatrix );

       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       double yViewpoint = ((-theMatrix[1]*(startX - theMatrix[4]) +
                              theMatrix[0]*(startY - theMatrix[5]) )/dDet);

       return yViewpoint;
    }

    // Record coordinates and start "floor definition" motion 

    public void drawGrid( Graphics gs, int width, int height ) {

       int x0, y0;

       // Set flag for grid draw ....

       if (grid == false) grid = true;

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Transform pixel coordinates to viewpoint coordinates ...

       at.getMatrix( theMatrix );

       g2D.setColor( Color.blue );
       for (int i = 0; i <=   width-40; i = i + gridSize )
       for (int j = 0; j <=  height-40; j = j + gridSize ) {
           g2D.translate(  i, j );
           g2D.scale(  (double) 1.0, (double) -1.0 );
           g2D.fillOval( -1, -1, 1, 1);
           g2D.scale(  (double) 1.0, (double) -1.0 );
           g2D.translate( -i,-j );
       }
    }

    // Draw a rubber rectangle, mouse down, tracks mouse

    public void drawRubberRectangle( Graphics gs, int width, int height ) {

       // If coordinates have not changed, then just return ...
      
       if ( currentX == previousX && currentY == previousY )
            return;

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       // Transform pixel coordinates to viewpoint coordinates ...

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);
       at.getMatrix( theMatrix );

       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       int x0T = (int) (( theMatrix[3]*(startX - theMatrix[4]) -
                          theMatrix[2]*(startY - theMatrix[5]) )/dDet);
       int y0T = (int) ((-theMatrix[1]*(startX - theMatrix[4]) +
                          theMatrix[0]*(startY - theMatrix[5]) )/dDet);
       int x1T = (int) (( theMatrix[3]*(previousX - theMatrix[4]) -
                          theMatrix[2]*(previousY - theMatrix[5]) )/dDet);
       int y1T = (int) ((-theMatrix[1]*(previousX - theMatrix[4]) +
                          theMatrix[0]*(previousY - theMatrix[5]) )/dDet);
       int x2T = (int) (( theMatrix[3]*(currentX - theMatrix[4]) -
                          theMatrix[2]*(currentY - theMatrix[5]) )/dDet);
       int y2T = (int) ((-theMatrix[1]*(currentX - theMatrix[4]) +
                          theMatrix[0]*(currentY - theMatrix[5]) )/dDet);

       // Java does not seem to have a dashed or stippled rectangle or line
       // Erase previous dashed rectangle ....

       g2D.setColor(Color.white);
       for( int xc = Math.min( x0T, x1T); xc < Math.max(x0T, x1T) -3; xc=xc+8) {
            g2D.drawLine(  xc, y0T, xc+4, y0T);
            g2D.drawLine(  xc, y1T, xc+4, y1T);
       }
       for( int yc = Math.min( y0T, y1T); yc < Math.max(y0T, y1T) -3; yc=yc+8) {
            g2D.drawLine( x0T,  yc,  x0T, yc+4);
            g2D.drawLine( x1T,  yc,  x1T, yc+4);
       }

       // Draw new rubber rectangle

       g2D.setColor(Color.black);
       for( int xc = Math.min( x0T, x2T); xc < Math.max(x0T, x2T) -3; xc=xc+8) {
            g2D.drawLine(  xc, y0T, xc+4, y0T);
            g2D.drawLine(  xc, y2T, xc+4, y2T);
       }
       for( int yc = Math.min( y0T, y2T); yc < Math.max(y0T, y2T) -3; yc=yc+8) {
            g2D.drawLine( x0T,  yc,  x0T, yc+4);
            g2D.drawLine( x2T,  yc,  x2T, yc+4);
       }

       // Update previous/current coordinates ....

       previousX = currentX;
       previousY = currentY;
   }

   public void drawRectangle( Graphics gs, int width, int height ) {

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Transform pixel coordinates to viewpoint coordinates ...

       double[] theMatrix = new double[6];
       at.getMatrix( theMatrix );

       int x0T = 0; int y0T = 0;
       int x1T = 0; int y1T = 0;
       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       x0T = (int) (( theMatrix[3]*(startX - theMatrix[4]) -
                      theMatrix[2]*(startY - theMatrix[5]) )/dDet);
       y0T = (int) ((-theMatrix[1]*(startX - theMatrix[4]) +
                      theMatrix[0]*(startY - theMatrix[5]) )/dDet);
       x1T = (int) (( theMatrix[3]*(currentX - theMatrix[4]) -
                      theMatrix[2]*(currentY - theMatrix[5]) )/dDet);
       y1T = (int) ((-theMatrix[1]*(currentX - theMatrix[4]) +
                      theMatrix[0]*(currentY - theMatrix[5]) )/dDet);

       // Java does not seem to have a dashed or stippled rectangle or line

       g2D.setColor( Color.red );
       BasicStroke stroke = new BasicStroke( 2 );
       g2D.setStroke(stroke);

       g2D.drawLine( x0T, y0T, x1T, y0T);
       g2D.drawLine( x0T, y1T, x1T, y1T);
       g2D.drawLine( x0T, y0T, x0T, y1T);
       g2D.drawLine( x1T, y0T, x1T, y1T);
   }

   // Draw a rubber line, mouse down, tracks mouse

   public void drawRubberLine( Graphics gs, int width, int height ) {

       // If coordinates have not changed, then just return ...
      
       if ( currentX == previousX && currentY == previousY )
            return;

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       // Transform pixel coordinates to viewpoint coordinates ...

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);
       at.getMatrix( theMatrix );

       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       int x0T = (int) (( theMatrix[3]*(startX - theMatrix[4]) -
                          theMatrix[2]*(startY - theMatrix[5]) )/dDet);
       int y0T = (int) ((-theMatrix[1]*(startX - theMatrix[4]) +
                          theMatrix[0]*(startY - theMatrix[5]) )/dDet);
       int x1T = (int) (( theMatrix[3]*(previousX - theMatrix[4]) -
                          theMatrix[2]*(previousY - theMatrix[5]) )/dDet);
       int y1T = (int) ((-theMatrix[1]*(previousX - theMatrix[4]) +
                          theMatrix[0]*(previousY - theMatrix[5]) )/dDet);
       int x2T = (int) (( theMatrix[3]*(currentX - theMatrix[4]) -
                          theMatrix[2]*(currentY - theMatrix[5]) )/dDet);
       int y2T = (int) ((-theMatrix[1]*(currentX - theMatrix[4]) +
                          theMatrix[0]*(currentY - theMatrix[5]) )/dDet);

       // Erase previous dashed line: (x0T,y0T) to (x1T,y1T) ....

       g2D.setColor(Color.white);

       if ( y0T == y1T) { // Horizontal line .....
          for( int xc = Math.min( x0T, x1T); xc < Math.max(x0T, x1T) -3; xc=xc+8)
               g2D.drawLine(  xc, y0T, xc+4, y0T);
       } else if ( x0T == x1T) { // Vertical line ....
          for( int yc = Math.min( y0T, y1T); yc < Math.max(y0T, y1T) -3; yc=yc+8) 
               g2D.drawLine( x0T,  yc,  x0T, yc+4);
       } else {
          double theta  = Math.atan2( y1T-y0T, x1T-x0T );
          double length = Math.sqrt ( (x0T-x1T)*(x0T-x1T) + (y0T-y1T)*(y0T-y1T) );

          for( int i = 0; i <= length; i = i + 8 ) {
               int x1 = x0T + (int) (((double) i)*Math.cos(theta));
               int x2 = x0T + (int) (((double) (i+4))*Math.cos(theta));
               int y1 = y0T + (int) (((double) i)*Math.sin(theta));
               int y2 = y0T + (int) (((double) (i+4))*Math.sin(theta));

               g2D.drawLine( x1, y1, x2, y2 );
          }
       }

       // Draw new dashed line: (x0T,y0T) to (x2T,y2T) ....

       g2D.setColor(Color.black);

       if ( y0T == y2T) { // Horizontal line .....
          for( int xc = Math.min( x0T, x2T); xc < Math.max(x0T, x2T) -3; xc=xc+8)
               g2D.drawLine(  xc, y0T, xc+4, y0T);
       } else if ( x0T == x2T) { // Vertical line ....
          for( int yc = Math.min( y0T, y2T); yc < Math.max(y0T, y2T) -3; yc=yc+8) 
               g2D.drawLine( x0T,  yc,  x0T, yc+4);
       } else {
          double theta  = Math.atan2( y2T-y0T, x2T-x0T );
          double length = Math.sqrt ( (x0T-x2T)*(x0T-x2T) + (y0T-y2T)*(y0T-y2T) );

          for( int i = 0; i <= length; i = i + 8 ) {
               int x1 = x0T + (int) (((double) i)*Math.cos(theta));
               int x2 = x0T + (int) (((double) (i+4))*Math.cos(theta));
               int y1 = y0T + (int) (((double) i)*Math.sin(theta));
               int y2 = y0T + (int) (((double) (i+4))*Math.sin(theta));

               g2D.drawLine( x1, y1, x2, y2 );
          }
       }

       // Update previous/current coordinates ....

       previousX = currentX;
       previousY = currentY;
   }

   public void drawLine( Graphics gs, int width, int height ) {

       // Setup Affine Transformation and 2D graphics context ....

       AffineTransform at = new AffineTransform();
       at.translate(  20, height - 20 );
       at.scale( 1, -1);

       Graphics2D g2D = (Graphics2D) gs;
       g2D.setTransform (at);

       // Transform pixel coordinates to viewpoint coordinates ...

       double[] theMatrix = new double[6];
       at.getMatrix( theMatrix );

       int x0T = 0; int y0T = 0;
       int x1T = 0; int y1T = 0;
       double dDet = theMatrix[0]*theMatrix[3] - theMatrix[1]*theMatrix[2];
       x0T = (int) (( theMatrix[3]*(startX - theMatrix[4]) -
                      theMatrix[2]*(startY - theMatrix[5]) )/dDet);
       y0T = (int) ((-theMatrix[1]*(startX - theMatrix[4]) +
                      theMatrix[0]*(startY - theMatrix[5]) )/dDet);
       x1T = (int) (( theMatrix[3]*(currentX - theMatrix[4]) -
                      theMatrix[2]*(currentY - theMatrix[5]) )/dDet);
       y1T = (int) ((-theMatrix[1]*(currentX - theMatrix[4]) +
                      theMatrix[0]*(currentY - theMatrix[5]) )/dDet);

       // Java does not seem to have a dashed or stippled rectangle or line

       g2D.setColor( Color.black );
       BasicStroke stroke = new BasicStroke( 2 );
       g2D.setStroke(stroke);

       g2D.drawLine( x0T, y0T, x1T, y1T);
   }

   // Exercise methods in wall segment class ....

   public static void main( String args[] ) {
        GraphicsGrid gs = new GraphicsGrid();
   }
}
