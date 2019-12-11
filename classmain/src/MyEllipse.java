import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Point;

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

	@Override
	public void setLocation(Point start, Point end){
		Point point = this.getShape().getBounds().getLocation();
		point.translate(- start.x + end.x, - start.y + end.y);
		this.x = point.x;
		this.y = point.y;
	}
}
