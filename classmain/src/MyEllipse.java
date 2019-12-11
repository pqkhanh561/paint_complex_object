import java.awt.geom.Ellipse2D;
import java.awt.Shape;

public class MyEllipse extends MyShape{
	private double x, y, height, width;

	//TODO: Set up to draw in paper
	public MyEllipse(double x, double y, double height, double width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;	
	}

	@Override
	public Shape getShape(){
		return(new Ellipse2D.Double(x, y, width, height));
	}
}
