import java.awt.*;

public class MySquare extends MyPolygon {

    public MySquare(Point[] point_arr) {
        super(point_arr);
        point_arr = addPoint(point_arr);
        super.setpoint(point_arr);

    }

    public Point[] addPoint(Point[] point_arr){
        Point[] newPoint = new Point[4];
        int base = Math.abs((int)point_arr[0].x - (int)point_arr[1].x);
        newPoint[0] = point_arr[0];
        newPoint[1] = new Point(point_arr[0].x +base, point_arr[0].y);
        newPoint[2] = new Point(point_arr[0].x +base, point_arr[0].y+base);
        newPoint[3] = new Point(point_arr[0].x, point_arr[0].y+base);
        return newPoint;
    }


}
