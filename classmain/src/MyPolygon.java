import java.awt.*;

public class MyPolygon extends MyShape {
    Polygon Pol;
    Point Position[] = {};
    public MyPolygon(Point[] point_arr){
        this.Position = point_arr;
    }
    public void getShape (){
        Pol = new Polygon();
        for (int i =0; i<Position.length;i++){
            Pol.addPoint((int)Position[i].getX(), (int)Position[i].getY());
        }
    }

}
