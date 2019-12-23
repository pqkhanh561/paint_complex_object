/**
  *  =========================================================
  *  Vertex.java: Java class for nodes in a simple polygon.
  *             The class node extends class vector.
  * 
  *  Written by: Mark Austin                    November, 2004 
  *  =========================================================
  */

public class Vertex extends Vector2D {
   protected String sName;

   // Constructor methods ....

   public Vertex() {
      super( 0.0, 0.0 );
   }

   public Vertex( double dX, double dY ) {
      super( dX, dY );
   }

   public Vertex( String sName, double dX, double dY ) {
      super( dX, dY );
      this.sName = sName;
   }

   // Set name for the node ...

   public void setName( String sName ) {
      this.sName = sName;
   }

   // Retrieve x and y coordinates ...

   public double getX() { return dX; }
   public double getY() { return dY; }

   // Convert node to a string ...

   public String toString() {
      return "Vertex(\"" + sName + "\") is at (" + dX + "," + dY + ")";
   }

   // Exercise methods in the Vertex class .....

   public static void main( String args[] ) {

      // Create and print "point 1", a node at coordinate (1,2)...

      Vertex nA = new Vertex();
      nA.dX = 1.0;
      nA.dY = 2.0;
      nA.sName  = "Point 1";

      System.out.println( nA.toString() );

      // Create and print "point 2", a node at coordinate (5,2)...

      Vertex nB = new Vertex ("Point 2", 5.0, 5.0 );
      System.out.println( "\n" + nB );
      System.out.println( "Vector Magnitude =" + nB.length() );

   }
}

