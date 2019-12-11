import java.awt.*;

public class MyRectangle extends AbstractDrawable {
	public MyRectangle(State state) {
		super(state);
	}
	@Override
	public Shape getShape() {
		return getBounds();
	}
}
