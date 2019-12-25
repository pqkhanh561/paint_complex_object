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
        return(new Rectangle2D.Double(Math.min(x,x2), Math.min(y,y2), Math.abs(x -x2), Math.abs(y-y2)));
        //return(new Rectangle2D.Double(50,50,50,50));
    }

    @Override
      public void setLocation(Point offset, Point endPoint){
        //Point offset = new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
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

