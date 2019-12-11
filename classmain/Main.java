import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class Main{
	JFrame frame;
	DrawObject ob;

	public Main(){
		//MyShape poly= new MyRect(new Point[]{new Point(0,0), new Point(10,50)});
		MyShape poly= new MyCircle(50,50,500); 
		ob = new DrawObject(poly);
		//System.out.println(ob);
		Gui();
	}


	public static void main(String[] args){
		Main m = new Main();	
		
	}

	public void Gui(){
		frame = new JFrame();
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new DrawPanel());
		frame.setSize(500,500);
		frame.setVisible(true);
	}

	public class DrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D) g.create();
			ob.draw(g2d);
			//g2d.draw(new Rectangle(10,10,50,50));
		}
	}
}
