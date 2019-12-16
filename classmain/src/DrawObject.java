import java.awt.*;
import java.util.*;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Area;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.String;
import java.lang.Integer;


public class DrawObject{
	private  ArrayList<MyShape> arrShape = new ArrayList<MyShape>();
	private ArrayList<String>func = new ArrayList<String>();
	
	public DrawObject(MyShape shape){
		arrShape.add(shape);
		func.add("1");
	}

	public DrawObject(ArrayList<MyShape> arrShape, ArrayList<String> func){ 
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

	public ArrayList<String> getfunc(){
		return(func);
	}

	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
 	
	private ArrayList<String> mark_number_again(ArrayList<String> list){
		int new_index = 0;
		for (int i =0; i<list.size(); i++){
			if (isNumeric(list.get(i))){
				new_index+=1;
				//list.replace(str,Integer.toString(new_index));
				list.set(i, Integer.toString(new_index));
				//str = Integer.toString(new_index);
			}
		}
		return(list);
	}
	
	//TODO: 1: union
	public DrawObject union(DrawObject shape){
		ArrayList<MyShape> arrShape_tmp = new ArrayList<MyShape>();
		arrShape_tmp.addAll(arrShape);
		arrShape_tmp.addAll(shape.getarr());

		//Set new function 
		ArrayList<String> func_tmp = new ArrayList<String>();
		func_tmp.add("(");
		func_tmp.addAll(func);
		func_tmp.add(")");
		func_tmp.add("+");
		func_tmp.add("(");
		func_tmp.addAll(shape.getfunc());
		func_tmp.add(")");
		func_tmp = mark_number_again(func_tmp);
		DrawObject tmp = new DrawObject(arrShape_tmp, func_tmp);
		return(tmp);
	}	
}
