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
    /*public void setLocation(Point offset, Point end){
        Point ofset = new Point(end.x - offset.x, end.y + offset.y);
        this.x =  this.x + ofset.x;
        this.y = this.y - ofset.y;

        end.x += offset.x;
		end.y += offset.y;

        Point diganol = new Point(x2 - x, y2-y);
        this.x2 = diganol.x + x;
        this.y2 = y + diganol.y;
    }


    public void setLocation(Point offset, Point end){
        int baseX = offset.x - end.x;
        int baseY = offset.y - end.y;
        /*
        for (int i =0; i<Position.length;i++){
            this.Position[i].x += baseX;
            this.Position[i].y += baseY;
        }
        this.x += baseX;
        this.y += baseY;
        this.x2 += baseX;
        this.y2 -= baseY;
    }
*/
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

