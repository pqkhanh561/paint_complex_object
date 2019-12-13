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
    public void setLocation(Point offset, Point end){
        int baseX = - Position[0].x + (end.x + offset.x);
        int baseY = - Position[0].y + (end.y + offset.y);
        for (int i =0; i<Position.length;i++){
            this.Position[i].x += baseX;
            this.Position[i].y += baseY;
        }
    }

}
