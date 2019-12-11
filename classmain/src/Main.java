import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.ArrayList;

public class Main{
	JFrame frame;
	ArrayList<DrawObject> ListObject = new ArrayList<DrawObject>();

	public Main(){
		//MyShape poly= new MySquare(new Point[]{new Point(5,5), new Point(100,500)});
		MyShape poly= new MyCircle(50,50,50);
		ListObject.add(new DrawObject(poly));
		//system.out.println(ob);
		gui();
	}


	public static void main(String[] args){
		new Main();	
		
	}

	public void gui(){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				frame = new JFrame();
				frame.setLayout(new GridLayout());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new DrawPanel());
				frame.setSize(500,500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class DrawPanel extends JPanel{
		//TODO: Many shape need to be selected
		private DrawObject dragged;
		private Point offset;

		public DrawPanel(){

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (DrawObject ob: ListObject){
					if (ob.contains(e.getPoint())){
						System.out.println("Object is clicked");
						dragged = ob;
						Rectangle bounds = ob.getArea().getBounds();
						offset = new Point(bounds.x - e.getX(),bounds.y - e.getY());
						repaint();
						break;
					}	
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (dragged != null) {
					repaint();
				}
				dragged = null;
				offset = null;
			}
		    });

	    	addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragged != null && offset !=null){
					dragged.setLocation(offset, e.getPoint());
					repaint();
				}
			}
		    });
		}

		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			for (DrawObject ob : ListObject){
				ob.draw(g2d);
			}
			g2d.dispose();
			//ob.setSize();
			//ob.draw(g2d);
			//g2d.draw(new Rectangle(10,10,50,50));
		}
	}
}
