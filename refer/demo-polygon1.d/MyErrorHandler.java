/*
 *  ===========================================================================
 *  MyErrorHandler.java: Error Handler for validation errors.
 *  
 *  Code developed by Deitel and Deitel.                                   2000.
 *  ===========================================================================
 */

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {

   // Throw SAX Exception for fatal errors

   public void fatalError( SAXParseException exception ) throws SAXException {
      throw exception;
   }

   public void error( SAXParseException e ) throws SAXParseException {
      throw e;
   }

   // Print any warnings 

   public void warning( SAXParseException err ) throws SAXParseException {
      System.err.println( "Warning: " + err.getMessage() );
   }

}
