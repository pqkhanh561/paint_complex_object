/*
 *  ============================================================================
 *  GraphicsOperation.java: Definition and Management of the graphics operations
 *  and functionality, e.g., drawing lines and rectangles, snapping to grid...
 *
 *  Written By : Mark Austin                                       November 2005
 *  ============================================================================
 */

public class GraphicsOperation {

    // Boolean variables for drawing shapes ...

    public boolean drawLine = false;
    public boolean drawRect = false;

    // Set boolean variables for supporting graphical operations ...

    public boolean grid = false; 
    public boolean snap = false;

    void setDrawLine() {
       drawLine = true;
       drawRect = false;
    }

    void setDrawRectangle() {
       System.out.println("*** In GraphicsOperation().setDrawRectangle()");
       drawLine = false;
       drawRect = true;
    }

    public boolean getDrawLine()      { return drawLine; }
    public boolean getDrawRectangle() { return drawRect; }

    // Variables for supporting graphical operations ...

    void setGridOn()  { grid = true; }
    void setGridOff() { grid = false; }
    void setSnapOn()  { snap = true; }
    void setSnapOff() { snap = false; }
}
