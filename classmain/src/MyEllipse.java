import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
		return(new Ellipse2D.Float(Math.min(x, x2), Math.min(y,y2), Math.abs(x-x2), Math.abs(y-y2)));
		//return(new Ellipse2D.Double(50,50,150,150));
	}

	@Override
	/*
	public void setLocation(Point offset, Point end){
        end.x += offset.x;
		end.y += offset.y;
        Point diganol = new Point(x2 - x, y2-y);
		this.x = end.x;
		this.y = end.y;
        this.x2 = x + diganol.x;
        this.y2 = y + diganol.y;

	}
*/
	public void setLocation(Point offset, Point endPoint){
	//	Point offset = new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
		int baseX = - this.x + (endPoint.x + offset.x);
		int baseY = - this.y + (endPoint.y + offset.y);
		this.x += baseX;
		this.y += baseY;
		this.x2 += baseX;
		this.y2 += baseY;
	}

    public void changeEndPoint(Point p){
        this.x2 = p.x;
        this.y2 = p.y;
    }


}
