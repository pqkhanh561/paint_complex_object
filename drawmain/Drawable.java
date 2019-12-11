import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;


public interface Drawable {
	public void paint(JComponent parent, Graphics2D g2d);

	public void setLocation(Point location);

	public void setSize(Dimension size);

	public State getState();

	public Rectangle getBounds();
}

