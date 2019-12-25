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
        return(new Rectangle2D.Float(Math.min(x,x2), Math.min(y,y2), Math.abs(x -x2), Math.abs(y-y2)));
    }

    @Override
      public void setLocation(Point offset, Point endPoint){
        Rectangle rect = new Rectangle(Math.min(x,x2), Math.min(y,y2), Math.abs(x -x2), Math.abs(y-y2));
        int baseX = - rect.x + (endPoint.x + offset.x);
        int baseY = - rect.y + (endPoint.y + offset.y);
        rect.x += baseX;
        rect.y += baseY;
        this.x = rect.x;
        this.y = rect.y;
        this.x2 = x + rect.width;
        this.y2 = y + rect.height;
    }
        
    public void changeEndPoint(Point p){
        this.x2 = p.x;
        this.y2 = p.y;
    }
}

