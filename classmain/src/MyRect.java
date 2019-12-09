import java.awt.*;
import java.lang.*;


public class MyRect extends MyPolygon{

    public MyRect(Point[] point_arr) {
        super(point_arr);
    }

    public void getShape(){
        Pol.addPoint((int)Position[0].getX(), (int)Position[0].getY());
        Pol.addPoint((int)Position[1].getX(),(int)Position[0].getY());
        Pol.addPoint((int)Position[1].getX(),(int)Position[1].getY());
        Pol.addPoint((int)Position[0].getX(),(int)Position[1].getY());
    }
}
