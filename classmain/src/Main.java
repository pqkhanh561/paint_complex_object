import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import java.util.ArrayList;

public class Main{
	JFrame frame;
	ArrayList<DrawObject> ListObject = new ArrayList<DrawObject>();
    DrawPanel dp = new DrawPanel();

	public Main(){
	//	MyShape poly= new MySquare(new Point[]{new Point(5,5), new Point(100,500)});
	//	//MyShape cir = new MyPolygon(new Point[]{new Point(5,5), new Point(100,500), new Point(200,220)});
	//	////MyShape poly= new MyCircle(55,55,50);
	//	MyShape cir= new MyCircle(50,50,50);
	//	ListObject.add(new DrawObject(cir));

	//	ListObject.add(new DrawObject(poly));
	//	//system.out.println(ob);
		MyShape poly= new MyRect(new Point(5,5), new Point(100,500));
		MyShape cir = new MyEllipse(new Point(5,5), new Point(100,500));
		MyShape poly1 = new MyRect(new Point(20,20), new Point(150,200));
		ListObject.add(new DrawObject(cir));
		ListObject.add(new DrawObject(poly));
		ListObject.add(new DrawObject(poly1));
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
				frame.add(dp);
				frame.setSize(500,500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class DrawPanel extends JPanel {
		//TODO: Many shape need to be selected
		private DrawObject dragged;
		private Point offset;

		private ArrayList<DrawObject> selected = new ArrayList<DrawObject>();

		//Define the operator
		static final String ADD = "+";
		static final String SUBTRACT = "-";
		static final String INTERSECT = "^";

		public class PopUpDemo extends JPopupMenu{
			JMenu clickItem;

			JMenuItem subtractMenuItem;
			JMenuItem addMenuItem;
			JMenuItem intersectMenuItem;

			JMenu draw;
			JMenuItem drawRect;
			JMenuItem drawEllipse;

			public PopUpDemo(){
				clickItem = new JMenu("Operation");
				add(clickItem);

				draw = new JMenu("Draw");
				add(draw);

				drawRect = new JMenuItem("Rectangle");
				draw.add(drawRect);
				drawRect.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						MyShape Rect = new MyRect(new Point(20,20), new Point(150,200));
						ListObject.add(new DrawObject(Rect));
						dp.repaint();
					}
				});


				drawEllipse = new JMenuItem("Ellipse");
				draw.add(drawEllipse);
				drawEllipse.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
                        /*
                        DoDraw do_draw = new DoDraw("ELLIPSE");
                        dp.addMouseListener(do_draw);
                        dp.addMouseMotionListener(do_draw);
                        dp.removeMouseListener(do_draw);
                        */
					}
				});

				subtractMenuItem = new JMenuItem("Subtract");
				clickItem.add(subtractMenuItem);
				subtractMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DrawObject tmp = selected.get(1);
						for (DrawObject ob: selected) {
							if (selected.indexOf(ob) != 1) {
								tmp = tmp.do_math(ob, SUBTRACT);
							}
						}

						ArrayList<DrawObject> arr_tmp = (ArrayList<DrawObject>) ListObject.clone();
						for (DrawObject ob: ListObject){
							if (selected.contains(ob)) {
								arr_tmp.remove(ob);
							}
						}
						ListObject = arr_tmp;
						selected.clear();
						ListObject.add(tmp);
						dp.repaint();
					}
				});

				addMenuItem = new JMenuItem("Add");
				clickItem.add(addMenuItem);
				addMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DrawObject tmp = selected.get(1);
						for (DrawObject ob: selected) {
							if (selected.indexOf(ob) != 1) {
								tmp = tmp.do_math(ob, ADD);
							}
						}

						ArrayList<DrawObject> arr_tmp = (ArrayList<DrawObject>) ListObject.clone();
						for (DrawObject ob: ListObject){
							if (selected.contains(ob)) {
								arr_tmp.remove(ob);
							}
						}
						ListObject = arr_tmp;
						selected.clear();
						ListObject.add(tmp);
						dp.repaint();
					}
				});

				intersectMenuItem = new JMenuItem("Intersect");
				clickItem.add(intersectMenuItem);
				intersectMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DrawObject tmp = selected.get(1);
						for (DrawObject ob: selected) {
							if (selected.indexOf(ob) != 1) {
								tmp = tmp.do_math(ob, INTERSECT);
							}
						}

						ArrayList<DrawObject> arr_tmp = (ArrayList<DrawObject>) ListObject.clone();
						for (DrawObject ob: ListObject){
							if (selected.contains(ob)) {
								arr_tmp.remove(ob);
							}
						}
						ListObject = arr_tmp;
						selected.clear();
						ListObject.add(tmp);
						dp.repaint();
					}
				});

			}



		}


		class PopClickListener extends MouseAdapter {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					doPop(e);
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					doPop(e);
			}

			private void doPop(MouseEvent e) {
				PopUpDemo menu = new PopUpDemo();
				menu.show(e.getComponent(), e.getX(), e.getY());
			}
		}

        class ResizeHandler implements MouseWheelListener {

            @Override 
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                    System.out.println("wheel");
                    for (DrawObject ob: ListObject){
                        System.out.println(e.getX());
                        if (ob.contains(e.getPoint())) {
                            int amount = e.getWheelRotation() * 5;
                            System.out.println(amount);
                            ob.changeEndPoint(new Point(e.getX() + amount, e.getY() + amount));
                            dp.repaint();
                        }
                    }

                }
            }
        }
		class DoDraw extends MouseAdapter{
            private int start_x, start_y;
            private int shape_name;

            public DoDraw(String s){
                if (s == "ELLIPSE"){
                    this.shape_name = 1;
                }
            }

			public void mousePressed(MouseEvent e){
                System.out.println("Do draw");
                this.start_x = e.getX();
                this.start_y = e.getY();
                if (shape_name == 1){
                 ListObject.add(new DrawObject(new MyEllipse(new Point(Math.min(start_x, e.getX()), Math.min(start_y, e.getY())) , new Point(e.getX(), e.getY())))); 
                }
            }
			public void mouseReleased(MouseEvent e){
			}

			public void mouseDragged(MouseEvent e){
                ListObject.get(ListObject.size()-1).changeEndPoint(e.getPoint());
                dp.repaint();
		}
        }
/*
        public DrawPanel(){
            /*
            MyShape poly= new MySquare(new Point[]{new Point(5,5), new Point(100,500)});
            ////MyShape poly= new MyCircle(55,55,50);
            MyShape cir= new MyCircle(50,50,50);
            ListObject.add(new DrawObject(cir));
            ListObject.add(new DrawObject(poly));
            //TODO: Need to erase selected
            //DrawObject tmp = ListObject.get(0).do_math(ListObject.get(1),ADD);
            //System.out.println(tmp.getfunc());		
            //ListObject.clear();
            //ListObject.add(tmp);
            setBackground(Color.WHITE);		
            setVisible(true);
            DoDraw do_draw = new DoDraw();
            addMouseListener(do_draw);
            addMouseMotionListener(do_draw);
            addMouseWheelListener(new ResizeHandler());           
            addMouseListener(new PopClickListener());
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    //System.out.println("mouse click");
                    if (e.getClickCount()==2){
                        for (DrawObject ob: ListObject){
                            if (ob.contains(e.getPoint()) && selected.contains(ob)==false){
                                selected.add(ob);	
                                repaint();
                                break;
                            }
                            else if (ob.contains(e.getPoint()) && selected.contains(ob)==true){
                                selected.remove(ob);
                                repaint();
                                break;
                            }	
                        }
                    }
                    for (DrawObject ob: ListObject){
                        if (ob.contains(e.getPoint())){
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
                    ob.draw(g2d, true);
                    if (selected.contains(ob)){
                        g2d.setColor(Color.BLUE);
                        ob.draw(g2d, false);
                        g2d.setColor(Color.BLACK);
                    }
                }
                g2d.dispose();
            }
        }
    */
		public DrawPanel(){
		//addMouseListener(new PopClickListener());
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount()==2){
					for (DrawObject ob: ListObject){
						if (ob.contains(e.getPoint()) && selected.contains(ob)==false){
							selected.add(ob);
							repaint();
							break;
						}
						else if (ob.contains(e.getPoint()) && selected.contains(ob)==true){
							selected.remove(ob);
							repaint();
							break;
						}
					}
				}
				for (DrawObject ob: ListObject){
					if (ob.contains(e.getPoint())){
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
                    ob.draw(g2d, true);
                    if (selected.contains(ob)){
                        g2d.setColor(Color.BLUE);
                        ob.draw(g2d, false);
                        g2d.setColor(Color.BLACK);
                    }
                }
                g2d.dispose();
            }

	}
}
