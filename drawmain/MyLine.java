import java.awt.*;
import java.awt.geom.Line2D;

public class MyLine extends AbstractDrawable {
	public MyLine(State state) {
		super(state);
	}
	@Override
	public Shape getShape() {
		Rectangle bounds = getBounds();
		return new Line2D.Float(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
	}
}

