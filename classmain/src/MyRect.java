import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;


public class MyRect extends MyShape{
    int x, y, x2, y2;

    public MyRect(Point start, Point end) {
        this.x = start.x;
        this.y = start.y;
        this.x2 = end.x;
        this.y2 = end.y;
    }

    @Override
    public Shape getShape (){
        //return(new Rectangle2D.Double(Math.min(x,x2), Math.min(y,y2), Math.abs(x -x2), Math.abs(y-y2)));
        return(new Rectangle2D.Double(50,50,50,50));
    }

    @Override
    public void setLocation(Point offset, Point end){
        end.x += offset.x;
		end.y += offset.y;
        Point diganol = new Point(x2 - x, y2-y);
		this.x = end.x;
		this.y = end.y;
        this.x2 = x + diganol.x;
        this.y2 = y + diganol.y;
    }
        
    public void changeEndPoint(Point p){
        this.x2 = p.x;
        this.y2 = p.y;
    }
}

