import java.awt.*;


public class MyRect extends MyPolygon{

    public MyRect(Point[] point_arr) {
	//TODO: Add 2 more point in this array
        super(point_arr);
        addPoint(point_arr);
    }
    public Point[] addPoint(Point[] point_arr){
        point_arr[3] = point_arr[2];
        point_arr[2] = new Point(point_arr[3].x, point_arr[1].y);
        point_arr[4] = new Point(point_arr[1].x, point_arr[3].y);
        return point_arr;
    }

}
