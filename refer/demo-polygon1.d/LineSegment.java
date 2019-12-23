/*
 *  =======================================================================
 *  LineSegment.java: A line segment is defined by the (x,y) coordinates of
 *              its two end points. 
 * 
 *  Written By: Mark Austin                                   November 2005 
 *  =======================================================================
 */

import java.lang.Math;

public class LineSegment {
   protected Node n1, n2; // nodal points defining the LineSegment

   // Constructor method : just create instance of class.

   public LineSegment() { }

   // Create instance of class and set (x,y) coordinates.

   public LineSegment( double dX1, double dY1,
                       double dX2, double dY2 ) {

      setLineSegment( dX1, dY1, dX2, dY2 );
   }

   // Set x and y coordinates of LineSegment.

   public void setLineSegment( double dX1, double dY1,
                               double dX2, double dY2 ) {
      n1 = new Node( dX1, dY1 );
      n2 = new Node( dX2, dY2 );
   }

   // Get end points 1 and 2.

   public Node getNode1() { return n1; }  
   public Node getNode2() { return n2; }

   // Print details of line segment.

   public void printSegment() {

      System.out.println("Line Segment");
      System.out.println("Node 1 : (x,y) = " + n1.toString() );
      System.out.println("Node 2 : (x,y) = " + n2.toString() );
   }

   // Compute length of line segment.

   public double segmentLength() {
      double dLength;

      dLength = (n1.getX() - n2.getX())*(n1.getX() - n2.getX()) +
                (n1.getY() - n2.getY())*(n1.getY() - n2.getY());

      return ((double) Math.sqrt(dLength));
   }

   // Compute min distance from line segment to point ...

   public double distanceToPoint( Node pt1 ) {

      // Get vector direction along line segment ...

      Vector2D direction = n2.sub(n1);

      // Compute intercept point ....

      double rhs = pt1.sub(n1).dotProduct ( direction );
      double lhs =   direction.dotProduct ( direction );
      double t = rhs/lhs;

      // If intercept lies in "end zones" compute Euclidean distance
      // to end points of line segments....

      if ( 0.0 < t && t < 1.0 )
           return pt1.sub( n1.add( direction.scale(t)) ).length();
      else 
           return Math.min ( pt1.sub( n1 ).length(), pt1.sub( n2 ).length() );
   }

   // ---------------------------------------
   // Exercise methods in line segment class.
   // ---------------------------------------

   public static void main( String args[] ) {
   double dX, dY;

      System.out.println("LineSegment test program");
      System.out.println("===============================");

      // Create two new line segments.

      LineSegment s1 = new LineSegment();
      s1.setLineSegment( 1.0, 1.0, 4.0, 4.0 );

      LineSegment s2 = new LineSegment( 1.5, 1.5, 1.5, 4.5 );
      s2.n1.setName( "n1" );
      s2.n2.setName( "n2" );

      // Print details of line segments.

      s1.printSegment();
      s2.printSegment();

      // Compute length of line segments.

      System.out.println("Segment1 has length : " + s1.segmentLength());
      System.out.println("Segment2 has length : " + s2.segmentLength());

      // Compute distance of a point from a length segment....

      Node pt1 = new Node ( "pt1", 0.0,  0.0 );
      System.out.println( pt1.toString() + ": Distance to line = " +
                          s1.distanceToPoint( pt1 ) );
      Node pt2 = new Node ( "pt2", 1.0,  0.0 );
      System.out.println( pt2.toString() + ": Distance to line = " +
                          s1.distanceToPoint( pt2 ) );
      Node pt3 = new Node ( "pt3", 5.0,  1.0 );
      System.out.println( pt3.toString() + ": Distance to line = " +
                          s1.distanceToPoint( pt3 ) );
      Node pt4 = new Node ( "pt4", 6.0, -1.0 );
      System.out.println( pt4.toString() + ": Distance to line = " +
                          s1.distanceToPoint( pt4 ) );

      // End of exercise.

      System.out.println("===============================");
      System.out.println("End of LineSegment test program");
   }
}
