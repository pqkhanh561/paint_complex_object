import java.awt.*;


public class MyRect extends MyPolygon{

    public MyRect(Point[] point_arr) {
	//TODO: Add 2 more point in this array
        super(point_arr);
    }

    public Shape getShape(){
        Pol.addPoint((int)Position[0].getX(), (int)Position[0].getY());
        Pol.addPoint((int)Position[1].getX(),(int)Position[0].getY());
        Pol.addPoint((int)Position[1].getX(),(int)Position[1].getY());
        Pol.addPoint((int)Position[0].getX(),(int)Position[1].getY());
        return (Pol);
    }

}
