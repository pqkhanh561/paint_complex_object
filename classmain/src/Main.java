import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.Boolean.FALSE;

public class Main{
    JFrame frame;
    ArrayList<DrawObject> ListObject = new ArrayList<DrawObject>();
    DrawPanel dp = new DrawPanel();;


    public Main(){
        gui();
    }


    public static void main(String[] args){
        new Main();

    }

    public void gui(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                frame = new JFrame();
                frame.setLayout(new GridLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(dp);
                frame.setSize(1500,1500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class DrawPanel extends JPanel {
        //TODO: Many shape need to be selected

        //Define the operator
        static final String ADD = "+";
        static final String SUBTRACT = "-";
        static final String INTERSECT = "^";
        ControlPanel cp = new ControlPanel();
        PopClickListener popup = new PopClickListener();
        MouseAdapter do_draw = new DoDraw();
        private int shape_name;
        private DrawObject dragged;

        private ArrayList<Point> offset = new ArrayList<Point>();
        private ArrayList<DrawObject> selected = new ArrayList<DrawObject>();


        public DrawPanel(){
            addMouseListener(cp);
            addMouseMotionListener(cp);
            addMouseListener(popup);
        }

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
                        shape_name = 0;
                        dp.addMouseListener(do_draw);
                        dp.addMouseMotionListener(do_draw);
                    }
                });


                drawEllipse = new JMenuItem("Ellipse");
                draw.add(drawEllipse);
                drawEllipse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        shape_name = 1;
                        dp.addMouseListener(do_draw);
                        dp.addMouseMotionListener(do_draw);
                    }
                });

                subtractMenuItem = new JMenuItem("Subtract");
                clickItem.add(subtractMenuItem);
                subtractMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMathForDrawObject(SUBTRACT);
                    }
                });

                addMenuItem = new JMenuItem("Add");
                clickItem.add(addMenuItem);
                addMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMathForDrawObject(ADD);
                    }
                });

                intersectMenuItem = new JMenuItem("Intersect");
                clickItem.add(intersectMenuItem);
                intersectMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        doMathForDrawObject(INTERSECT);
                    }
                });

            }

            public void doMathForDrawObject(String func){
                DrawObject tmp = selected.get(0);
                for (DrawObject ob: selected) {
                    if (selected.indexOf(ob) != 0) {
                        tmp = tmp.do_math(ob, func);
                    }
                }
                ListObject.removeAll(selected);
                selected.clear();
                ListObject.add(tmp);
                dp.repaint();
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

            public void mousePressed(MouseEvent e){
                System.out.println("Do draw");
                this.start_x = e.getX();
                this.start_y = e.getY();
                if (shape_name == 1){
                    ListObject.add(new DrawObject(new MyEllipse(new Point(Math.min(start_x, e.getX()), Math.min(start_y, e.getY())) , new Point(e.getX(), e.getY())))); 
                }
                else {
                    ListObject.add(new DrawObject(new MyRect(new Point(Math.min(start_x, e.getX()), Math.min(start_y, e.getY())) , new Point(e.getX(), e.getY()))));
                }
            }
            public void mouseReleased(MouseEvent e){
                dp.removeMouseListener(do_draw);
                dp.removeMouseMotionListener(do_draw);
                dp.repaint();
            }

            public void mouseDragged(MouseEvent e){
                ListObject.get(ListObject.size()-1).changeEndPoint(e.getPoint());
                dp.repaint();
            }
        } 

        class ControlPanel extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount()==2){
                    for (DrawObject ob: ListObject){
                        if (ob.contains(e.getPoint()) && selected.contains(ob)==false){
                            selected.add(ob);
                            dp.repaint();
                            break;
                        }
                        else if (ob.contains(e.getPoint()) && selected.contains(ob)==true){
                            selected.remove(ob);
                            dp.repaint();
                            break;
                        }
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON2){
                    for (DrawObject ob: ListObject){
                        if (ob.contains(e.getPoint())){
                            dragged = ob;
                            for (int i =0; i< dragged.getarr().size();i++){
                                Point tmp =new Point();
                                Rectangle bounds = dragged.getarr().get(i).getShape().getBounds();
                                tmp.x = bounds.x - e.getX();
                                tmp.y = bounds.y - e.getY();
                                offset.add(tmp);
                            }
                            dp.repaint();
                        }
                }
                }
             //   dragged = null; //disable move
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragged != null) {
                    dp.repaint();

                }
                dragged = null;
           //     offset = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragged != null && offset !=null){
                    dragged.setLocation(offset,e.getPoint());
                    dp.repaint();
                }


            }

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
