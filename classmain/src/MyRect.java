import java.awt.*;
import java.util.Arrays;


public class MyRect extends MyPolygon{

    public MyRect(Point[] point_arr) {
	//TODO: Add 2 more point in this array
        super(point_arr);
        point_arr = addPoint(point_arr);
        super.setpoint(point_arr);
    }

    public Point[] addPoint(Point[] point_arr){
        Point[] newPoint = new Point[4];
        newPoint[0] = point_arr[0];
        newPoint[1] = new Point(point_arr[1].x, point_arr[0].y);
        newPoint[2] = point_arr[1];
        newPoint[3] = new Point(point_arr[0].x, point_arr[1].y);
        return newPoint;
    }

}

