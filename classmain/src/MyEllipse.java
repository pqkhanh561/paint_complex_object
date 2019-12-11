import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape{
	private int x, y;
	private double height, width;

	//TODO: Set up to draw in paper
	public MyEllipse(int x, int y, double height, double width){
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
	public void setLocation(Point offset, Point end){
		end.x += offset.x;
		end.y += offset.y;
		//Rectangle bounds = this.getShape().getBounds();
		//bounds.setLocation(end.x, end.y);
		this.x = end.x;
		this.y = end.y;
	}
}
