import java.awt.*;
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
	private ArrayList<String>func = new ArrayList<String>();
	
	public DrawObject(MyShape shape){
		arrShape.add(shape);
		func = " ";
	}

	public DrawObject(ArrayList<MyShape> arrShape, String func){ 
		this.arrShape = arrShape;
		this.func = func;
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
		else{
			
		}
	}
	public void setLocation(Point startPoint, Point endPoint){
		for(int i =0; i<arrShape.size();i++){
			arrShape.get(i).setLocation(startPoint, endPoint);
		}
	}

	public void joinShape(MyShape shape){
		arrShape.add(shape);
	}

	public ArrayList<MyShape> getarr(){
		return(arrShape);
	}

	public String getfunc(){
		return(func);
	}
	
	//TODO: 1: union
	public DrawObject union(DrawObject shape){
		ArrayList<MyShape> arrShape_tmp = new ArrayList<MyShape>();
		arrShape_tmp.addAll(arrShape);
		arrShape_tmp.addAll(shape.getarr());
		String func_tmp = "(" + func + ")+("+ shape.getfunc() + ")";
		DrawObject tmp = new DrawObject(arrShape_tmp, func_tmp);
		return(tmp);
	}	
}
