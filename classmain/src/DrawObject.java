import java.awt.*; //Color
import java.util.*; //ArrayList, Stack, Queue, Random
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
	private DrawObject.BalanInverseArea area = null;
	private Color color = null;
	
	public DrawObject(MyShape shape){
		randomColor();
		this.arrShape.add(shape);
		this.func.add("0");
		this.area = new BalanInverseArea(func);	
	}

	public DrawObject(ArrayList<MyShape> arrShape, ArrayList<String> func){ 
		randomColor();	
		this.arrShape = arrShape;
		this.func = func;
		this.area = new BalanInverseArea(func);	
	}

	public void setColor(Color color){
		this.color = color;
	}

	public Color getColor(){
		return(color);
	}

	public void randomColor(){
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();

		this.color = new Color(r,g,b);

	}
	
    public void changeEndPoint(Point p){
        arrShape.get(0).changeEndPoint(p);
		this.area = new BalanInverseArea(func);
    };


	public class BalanInverseArea {
		private Area balan_area;
		public BalanInverseArea(ArrayList<String> Infix){
			if (Infix.size() > 1){
				ArrayList<String> postfix = changeToInverse(Infix);
				balan_area = createAreaFromPostfix(postfix);
			}
			else{
				balan_area = new Area(arrShape.get(Integer.parseInt(Infix.get(0))).getShape());
			}
		}

		public void update(){
			if (arrShape.size() > 1){
				ArrayList<String> postfix = changeToInverse(func);
				balan_area = createAreaFromPostfix(postfix);
			}
			else{
				balan_area = new Area(arrShape.get(Integer.parseInt(func.get(0))).getShape());
			}

		}

		private Area getArea(){
			return(balan_area);
		}

		private Area do_Math(Area a1, Area a2, String action){
			if (action == "^"){
				a1.intersect(a2);
			}
			else if (action == "-"){
				a1.subtract(a2);
			}
			else {
				a1.add(a2);
			}
			return(a1);

		}
		
		private Area createAreaFromPostfix(ArrayList<String> post){
			Stack<Area> stack = new Stack<Area>();
			//System.out.println(arrShape.size());
			//System.out.println(post);
			for (String s: post){
				if (isNumeric(s)){
					//Push shape to stack
					stack.push(new Area(arrShape.get(Integer.parseInt(s)).getShape()));
				}
				else{
					Area pop = stack.pop();
					stack.push(do_Math(pop, stack.pop(), s));
				}
			}
			return(stack.pop());
		}


		private ArrayList<String> changeToInverse(ArrayList<String> Infix){
			ArrayList<String> res =  new ArrayList<String>();
			Stack<String> stack = new Stack<String>(); 
			for (String s : Infix){
				if (isNumeric(s)){
					res.add(s);
				}
				else if (s != ")"){
					if (stack.empty()){
						stack.push(s);
					}
					else {
						if (checkImportant(s, stack.peek())){
							stack.push(s);
						}
						else{
							res.add(stack.pop());
							stack.push(s);
						}	
					}
				}
				else { //")"
					while (stack.peek() != "("){
						res.add(stack.pop());
					}
					stack.pop();
				}

			}
			if (stack.empty() == false) res.add(stack.pop());
			return(res);	
		}
		

		//check if s1 > s2
		private boolean checkImportant(String s1, String s2){
			if (s1 == "(" && s2 == "(") return false;
			if (s1 == "(") return true;
			return false;
		}
	}	
	
	public Area getArea(){
		return(area.getArea()); 
	};	

	public boolean contains(Point p){
		return(this.getArea().contains(p));
	}	

	public void fill(Graphics2D g){	
		g.setColor(color);
		for (MyShape shape: arrShape){
			g.fill(shape.getShape());
		}
	}
	public void draw(Graphics2D g, boolean is_filled){
		if (this.color==null) is_filled=false;
		if (is_filled) this.fill(g);
		g.draw(this.getArea());	
	}
	public void setLocation(Point startPoint, Point endPoint){
		for (MyShape shape: arrShape){
			shape.setLocation(startPoint, endPoint);
		}
		area.update();
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
		int new_index = -1;
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
	
	//TODO: +: union, -: subtract, ^: intersect
	public DrawObject do_math(DrawObject shape,String action){
		ArrayList<MyShape> arrShape_tmp = new ArrayList<MyShape>();
		arrShape_tmp.addAll(arrShape);
		arrShape_tmp.addAll(shape.getarr());

		//Set new function 
		ArrayList<String> func_tmp = new ArrayList<String>();
		func_tmp.add("(");
		func_tmp.addAll(func);
		func_tmp.add(")");
		func_tmp.add(action);
		func_tmp.add("(");
		func_tmp.addAll(shape.getfunc());
		func_tmp.add(")");
		func_tmp = mark_number_again(func_tmp);
		DrawObject tmp = new DrawObject(arrShape_tmp, func_tmp);
		while (tmp.getColor() == this.color || tmp.getColor() == shape.getColor()){
			tmp.randomColor();	
		}	
		return(tmp);
	}	
}
