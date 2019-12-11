import java.util.*;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Area;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.String;


public class DrawObject{
	private  ArrayList<MyShape> arrShape = new ArrayList<MyShape>();
	private String func;
	
	public DrawObject(MyShape shape){
		arrShape.add(shape);
		func = "1";
	}
	
	public Area getArea(){
		return new Area(arrShape.get(0).getShape());
	};	

	public boolean contains(Point p){
		//TODO: Need getArea()
		return(this.getArea().contains(p));
	}	

	public void draw(Graphics2D g){
		if (arrShape.size() == 1){
//			System.out.println(arrShape.get(0).getShape());
			g.draw(arrShape.get(0).getShape());
		}
	}
}
