import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyEllipse extends MyShape{
	private int x, y, x2, y2;

	//TODO: Set up to draw in paper
	public MyEllipse(Point start, Point end){
		this.x = start.x;
		this.y = start.y;
		this.x2 = end.x;
		this.y2 = end.y;	
	}

	@Override
	public Shape getShape(){
		return(new Ellipse2D.Double(Math.min(x, x2), Math.min(y,y2), Math.abs(x-x2), Math.abs(y-y2)));
	}

	@Override
	public void setLocation(Point offset, Point end){
		end.x += offset.x;
		end.y += offset.y;
		this.x = end.x;
		this.y = end.y;
	}

    public void changeEndPoint(Point p){
        this.x2 = p.x;
        this.y2 = p.y;
    }


}
