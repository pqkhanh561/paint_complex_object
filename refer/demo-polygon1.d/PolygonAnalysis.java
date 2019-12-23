/*
 *  ============================================================================
 *  PolygonAnalysis.java: Create general polygons suitable for civil engineering 
 *                        applications.
 *
 *  Written By : Mark Austin                                       November 2005
 *  ============================================================================
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

public class PolygonAnalysis {
    private PolygonAnalysisDOM handler;
    protected String             sName;
    protected List loopList = new ArrayList();

    // Constructor methods ....

    public PolygonAnalysis() {}

    public PolygonAnalysis( String sName ) {
       this.sName = sName;
    }

    // Add nodes to loop description ...

    public void add ( Loop lp ) {
       loopList.add( lp );
    }

    // Load details of polygon from XML file ...

    public void load ( String sPolygonName ) {

       System.out.println("*** In p1.load(): sPolygonName =" + sPolygonName );

       handler = new PolygonAnalysisDOM( sPolygonName );
       PolygonAnalysis p1 = handler.getPolygon();
       this.loopList = p1.loopList;
    }

    // Print details of polygon ....

    public void print() {

        System.out.println("Polygon: " + sName );
        System.out.println("==========================" );

        Iterator iterator1 = loopList.iterator();
        while ( iterator1.hasNext() != false ) {
            Loop lp = (Loop) iterator1.next();
            lp.print();
        }
    }

    //  ===============================
    //  Retrieve details of polygon ...
    //  ===============================

    public Vertex getClosestNode( double dX, double dY ) {
       Vertex c = new Vertex();
       return c;
    }

    //  ===============================
    //  Compute max/min x,y coordinates
    //  ===============================

    public double getMinX() {
        double dMinX = 0.0;
        boolean firstNode = true;

        // Walk along list of loops in polygon ....

        Iterator iterator1 = loopList.iterator();
        while ( iterator1.hasNext() != false ) {
            Loop lp = (Loop) iterator1.next();

            // Walk along nodes in loop.....
            
            Iterator iterator2 = lp.nodeList.iterator();
            while ( iterator2.hasNext() != false ) {
                Vertex ns = (Vertex) iterator2.next();

                // INSERT MISSING DETAILS HERE ....

            }
       }

       return dMinX;
    }

    public double getMaxX() {
       double dMaxX = 0.0;
       boolean firstNode = true;

       // Walk along list of loops and nodes in polygon ....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }
       }

       return dMaxX;
    }

    public double getMinY() {
       double dMinY = 0.0;
       boolean firstNode = true;

       // Walk along list of loops and nodes in polygon ....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....
          }
       }

       return dMinY;
    }

    public double getMaxY() {
       double dMaxY = 0.0;
       boolean firstNode = true;

       // Walk along list of loops and nodes in polygon ....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }
       }

       return dMaxY;
    }

    //  ============================================================
    //  Method polygonArea() : compute area of polygon
    //  ============================================================

    public double polygonArea() {
       double dArea = 0.0;
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }

          // INSERT MISSING DETAILS HERE ....

        }

        return dArea;
    }

    //  ============================================================
    //  Compute (x,y) coordinates of polygon centroid
    //  ============================================================

    public double polygonCentroidX() {
       double dCentroidX = 0.0;
       double dArea = polygonArea();
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;
       double factor;

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }

          // INSERT MISSING DETAILS HERE ....

       }

       return dCentroidX/dArea;
    }

    public double polygonCentroidY() {
       double dCentroidY = 0.0;
       double dArea = polygonArea();
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;
       double factor;

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }

          // INSERT MISSING DETAILS HERE ....

       }

       return dCentroidY/dArea;
    }

    //  ============================================================
    //  Compute Principal Moments of Inertia Ixx, Iyy and Ixy...
    //  ============================================================

    public double polygonIyy() {
       double dIxx = 0.0;
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;
       double factor;

       // Walk along loops of nodes and compute Ixx....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....
          }

          // INSERT MISSING DETAILS HERE ....

       }

       return dIxx;
    }

    public double polygonIxx() {
       double dIyy = 0.0;
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;
       double factor;

       // Walk along loops of nodes and compute Iyy....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }

          // INSERT MISSING DETAILS HERE ....

       }

       return dIyy;
    }

    public double polygonIxy() {
       double dIxy = 0.0;
       boolean firstNode = false;
       double dFirstX = 0.0; double dFirstY = 0.0;
       double dX1 = 0.0; double dX2 = 0.0;
       double dY1 = 0.0; double dY2 = 0.0;
       double factor;

       // Walk along loops of nodes and compute Iyy....

       Iterator iterator1 = loopList.iterator();
       while ( iterator1.hasNext() != false ) {
          Loop lp = (Loop) iterator1.next();

          firstNode = true;

          Iterator iterator2 = lp.nodeList.iterator();
          while ( iterator2.hasNext() != false ) {
             Vertex ns = (Vertex) iterator2.next();
             
             // INSERT MISSING DETAILS HERE ....

          }

          // INSERT MISSING DETAILS HERE ....

       }

       return dIxy;
    }

    // ===============================================================
    // Exercise methods in loop class ....
    // ===============================================================

    public static void main( String args[] ) {

       // Part 1. Create and print details of polygon (clockwise) ... 
       // ===========================================================

       Loop loop1 = new Loop ("loop1");
       loop1.clockwiseOrientation = true;
       loop1.add ( new Vertex ( "n1", -10.0,   0.0 ) );
       loop1.add ( new Vertex ( "n2", -10.0, 100.0 ) );
       loop1.add ( new Vertex ( "n3", 100.0, 100.0 ) );
       loop1.add ( new Vertex ( "n4", 130.0, -20.0 ) );

       // Define hole inside polygon (anticlockwise) ...

       Loop h1 = new Loop ("h1");
       h1.clockwiseOrientation = false;
       h1.add ( new Vertex ( "n1", 20.0,  20.0 ) );
       h1.add ( new Vertex ( "n2", 40.0,  20.0 ) );
       h1.add ( new Vertex ( "n3", 40.0,  80.0 ) );
       h1.add ( new Vertex ( "n4", 20.0,  80.0 ) );

       Loop h2 = new Loop ("h2");
       h2.clockwiseOrientation = false;
       h2.add ( new Vertex ( "n1", 80.0,  80.0 ) );
       h2.add ( new Vertex ( "n2", 90.0,  80.0 ) );
       h2.add ( new Vertex ( "n3", 90.0,  85.0 ) );
       h2.add ( new Vertex ( "n4", 80.0,  85.0 ) );
       
       // Create/print polygon with exterior loop and hole "h1" ...

       PolygonAnalysis p1 = new PolygonAnalysis ("p1");
       p1.add(loop1);
       p1.add(h1);
       p1.add(h2);
       p1.print();

       // Compute closest nodes

       System.out.println("");
       System.out.println("Find closest nodes in polygon            ");
       System.out.println("======================================== ");

       Vertex c = p1.getClosestNode( 0.0, 0.0 );
       System.out.println("(x,y) = (" + c.getX() + "," + c.getY() + ")");
       Vertex d = p1.getClosestNode( 1.0, 1.0 );
       System.out.println("(x,y) = (" + d.getX() + "," + d.getY() + ")");

       // Compute and print min/max "x" and "y" coordinates

       System.out.println("");
       System.out.println("Engineering Properties for polygon \"p1\"");
       System.out.println("======================================== ");

       System.out.println("Min X coord = " + p1.getMinX() );
       System.out.println("Max X coord = " + p1.getMaxX() );
       System.out.println("Min Y coord = " + p1.getMinY() );
       System.out.println("Max Y coord = " + p1.getMaxY() );

       // Compute and print the polygon area

       System.out.println("Polygon area = " + p1.polygonArea() );

       // Compute and print (x,y) coordinates of polygon centroid

       System.out.println("Centroid: X coordinate = " + p1.polygonCentroidX());
       System.out.println("          Y coordinate = " + p1.polygonCentroidY());

       // Compute and print moments of inertia I_xx, I_yy, and I_xy about
       // the x- and y- axes and the origin ...

       System.out.println("Inertia: I_xx() = " + p1.polygonIxx());
       System.out.println("         I_yy() = " + p1.polygonIyy());
       System.out.println("         I_xy() = " + p1.polygonIxy());
    }
}
