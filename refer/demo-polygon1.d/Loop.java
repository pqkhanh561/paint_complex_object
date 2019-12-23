/*
 *  ==========================================================================
 *  Loop.java : Store list of nodes in a polygon loop ....
 *
 *  Written By : Mark Austin                                     November 2005
 *  ==========================================================================
 */

import java.lang.Math.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Loop {
    protected String sName;
    protected List nodeList  = new ArrayList();
    protected boolean clockwiseOrientation = true; 

    // Constructor methods ....

    public Loop() {}

    public Loop( String sName ) {
       this.sName = sName;
    }

    // Add nodes to loop description ...

    public void add ( Vertex ns ) {
       nodeList.add( ns );
    }

    // Print details of room ....

    public void print() {

        System.out.println("Loop: " + sName );
        System.out.println("==========================" );

        Iterator iterator1 = nodeList.iterator();
        while ( iterator1.hasNext() != false ) {
            Vertex ns = (Vertex) iterator1.next();
            System.out.println ( ns.toString() );
        }
    }

    // Exercise methods in loop class ....

    public static void main( String args[] ) {

       // Create a simple loop ....

       Loop loop1 = new Loop ("loop1");
       loop1.add ( new Vertex ( "n1",   0.0,   0.0 ) );
       loop1.add ( new Vertex ( "n2",   0.0, 100.0 ) );
       loop1.add ( new Vertex ( "n3", 100.0, 100.0 ) );
       loop1.add ( new Vertex ( "n4", 100.0,   0.0 ) );
       
       // Print details of loop ....

       loop1.print();
    }
}
