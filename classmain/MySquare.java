import java.awt.*;
import java.security.PublicKey;

public class MySquare extends MyPolygon {

    public MySquare(Point[] point_arr) {
            super(point_arr);

    }

    public void getShape(){
        Pol.addPoint((int)Position[0].getX(),(int)Position[0].getY());
        Pol.addPoint((int)Position[0].getX()+ Math.abs((int)Position[0].getX()- (int)Position[1].getX()),(int)Position[0].getY());
        Pol.addPoint((int)Position[0].getX()+ Math.abs((int)Position[0].getX()- (int)Position[1].getX()),(int)Position[0].getY()+Math.abs((int)Position[0].getX()- (int)Position[1].getX()));
        Pol.addPoint((int)Position[0].getX(),(int)Position[0].getY()+Math.abs((int)Position[0].getX()- (int)Position[1].getX()));
        
}
}
