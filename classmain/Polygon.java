import java.awt.Shape;
import java.awt.Rectangle;




public class Polygon extends MyShape{
	public Polygon(){
		super();
	}
	@Override
	public Shape getShape(){
		return(new Rectangle(0,0,50,50));
	}
}
