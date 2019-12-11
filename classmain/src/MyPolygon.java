import java.awt.*;

public class MyPolygon extends MyShape {
    private Point Position[] = {};
    public MyPolygon(Point[] point_arr){
        this.Position = point_arr;
    }

    public void setpoint(Point[] point_arr){
        this.Position = point_arr;
    }

    @Override
    public Shape getShape (){
        Polygon Pol = new Polygon();
        for (int i =0; i<Position.length;i++){
            Pol.addPoint((int)Position[i].getX(), (int)Position[i].getY());
        }
	    return(Pol);
    }

    @Override
    public void setLocation(Point startPoint, Point endPoint){
        for (int i =0 ; i<Position.length;i++){
            int dx = startPoint.x - endPoint.x;
            int dy = startPoint.y - endPoint.y;
            Position[i].translate(dx,dy);
        }

    }

}
