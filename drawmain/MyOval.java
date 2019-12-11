import java.awt.*;
import java.awt.geom.Ellipse2D;


public class MyOval extends AbstractDrawable {
	public MyOval(State state) {
		super(state);
	}
	@Override
	public Shape getShape() {
		Rectangle bounds = getBounds();
		return new Ellipse2D.Float(bounds.x, bounds.y, bounds.width, bounds.height);
	}
}

