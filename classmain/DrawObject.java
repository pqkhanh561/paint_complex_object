import java.util.*;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.lang.String;

public class DrawObject{
	private  ArrayList<MyShape> arrShape = new ArrayList<MyShape>();
	private String func;
	
	public DrawObject(MyShape shape){
		arrShape.add(shape);
		func = "1";
	}

	public void setSize(){
	}


	public void draw(Graphics2D g){
		if (arrShape.size() == 1){
			System.out.println(arrShape.get(0).getShape());
			//g.draw(arrShape.get(0).getShape());
		}
	}

	public void joinShape(MyShape shape){
		arrShape.add(shape);
	}	

	
}
