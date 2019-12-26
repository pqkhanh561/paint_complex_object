import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;


public class State {

	private final Color foreground, background;
	private final boolean gradient, filled, dashed;
	public final int lineWidth, dashLength;

 	//Add more 	
	public State(){
		this.foreground = Color.BLACK;
		this.background = Color.WHITE;
		this.gradient = false;
		this.filled = false;
		this.dashed = false;
		this.lineWidth = 10;
		this.dashLength = 5;

	}


	public State(Color foreground, Color background, boolean gradient, boolean filled, boolean dashed,
			int lineWidth, int dashLength) {
		this.foreground = foreground;
		this.background = background;
		this.gradient = gradient;
		this.filled = filled;
		this.dashed = dashed;
		this.lineWidth = lineWidth;
		this.dashLength = dashLength;
	}

	public Color getForeground() {
		return foreground;
	}

	public Color getBackground() {
		return background;
	}

	public boolean isGradient() {
		return gradient;
	}

	public boolean isFilled() {
		return filled;
	}

	public boolean isDashed() {
		return dashed;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public int getDashLength() {
		return dashLength;
	}
}


