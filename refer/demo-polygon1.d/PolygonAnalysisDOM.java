/*
 *  ===========================================================================
 *  PolygonAnalysisDOM.java: Document Object Model for engineering polygon ...
 *  
 *  Modified for JAXP: By Mark Austin                             November 2005
 *  ===========================================================================
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.URL;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolygonAnalysisDOM {
  
   private Document document;    // document node object

   URL url;
   URL codeBase;

   // Create empty polygon data structure....

   PolygonAnalysis p1 = new PolygonAnalysis();
   Loop lp;
   int iLoopNo;
   int iNodeNo;

   // Variables to store the query parameters and the result

   public PolygonAnalysisDOM( String sPolygonName ) {
      System.out.println("*** In PolygonAnalysisDOM(): flag 0");

      try {

         // Obtain the default parser

         System.out.println("*** In PolygonAnalysisDOM(): flag 1");

         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setValidating( true );
         DocumentBuilder builder = factory.newDocumentBuilder();

         System.out.println("*** In PolygonAnalysisDOM(): flag 2");

         // Set error handler for validation errors

         builder.setErrorHandler( new MyErrorHandler() );

         // Obtain Document Object from XML Document

         document = builder.parse( new File( sPolygonName ) );

         // Create empty data stuctures/initialize variables ...

         System.out.println("*** In PolygonAnalysisDOM(): flag 5");
         p1 = new PolygonAnalysis();
         iLoopNo = 0;

         // Traverse DOM tree and assemble problem data structure...

         buildDataStructure( document );
      } 

      // Catch errors .....

      catch ( SAXParseException spe ) {
         System.err.println( "Parse error: " + 
            spe.getMessage() );
         System.exit( 1 );
      }

      catch ( SAXException se ) {
         se.printStackTrace();         
      }

      catch ( FileNotFoundException fne ) {
         System.err.println( "File \"polygon.xml\" not found." );
         System.exit( 1 );
      }

      catch ( Exception e ) {
         e.printStackTrace();
      }
   }

   // Get reference to new polygon ....

   public PolygonAnalysis getPolygon() {
      return p1;
   }

   // Method to load polygon description ....

   public void buildDataStructure ( Node node ) {

      // Create empty polygon data structure ....

      double dX = 0.0; double dY = 0.0;

      // Recursively process each type of node...

      switch ( node.getNodeType() ) {

         // If it is a Document node process its children

         case Node.DOCUMENT_NODE:
              Document doc = ( Document ) node;
              buildDataStructure ( doc.getDocumentElement() );
              break;

         // Process element node according to its tag name

         case Node.ELEMENT_NODE:

              if ( node.getNodeName().equals( "polygon" ) )
                 processChildNodes( node.getChildNodes() );
              else if ( node.getNodeName().equals( "loop" ) ) {
                 NamedNodeMap spaceAttributes = node.getAttributes();
                 Node value = spaceAttributes.item( 0 );

                 iNodeNo = 0;
                 iLoopNo = iLoopNo + 1;
                 lp = new Loop ( "h" + iLoopNo );

                 if ( value.getNodeValue().trim().equals("exterior") == true )
                      lp.clockwiseOrientation = true; 
                 else
                      lp.clockwiseOrientation = false; 

                 processChildNodes( node.getChildNodes() );

                 p1.add( lp );
                 return;

              } else if ( node.getNodeName().equals( "coordinate" ) ) {

                 Node child   = ( node.getChildNodes() ).item( 0 );
                 String sLine = child.getNodeValue().trim();     

                 StringTokenizer st1 = new StringTokenizer( sLine );

                 if ( st1.hasMoreTokens() == true )
                      dX = Double.parseDouble( st1.nextToken() );
                 if ( st1.hasMoreTokens() == true ) 
                      dY = Double.parseDouble( st1.nextToken() );

                 iNodeNo = iNodeNo + 1;
                 lp.add ( new Vertex ( "n" + iNodeNo, dX,  dY ) );
                 return;

              } else if ( node.getNodeName().equals( "note" ) ) {
                 return;

              } else
                     return;
          }
   }
  
   // Method to process child nodes

   public void processChildNodes( NodeList children ) {

      if ( children.getLength() != 0 )
         for ( int i = 0; i < children.getLength(); i++ )
               buildDataStructure( children.item( i ) );

      return;
   }
}
