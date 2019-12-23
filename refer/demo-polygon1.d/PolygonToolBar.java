/*
 *  =================================================================
 *  PolygonToolBar.java: Create toolbar for polygon package ....
 *  =================================================================
 */

import java.lang.Math.*;
import java.applet.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.font.*;
import java.awt.image.*;
import java.awt.geom.*;   // Needed for affine transformation....
import java.net.URL;

public class PolygonToolBar extends JToolBar {
   DemoGraphicsScreen gs;
   Container   container;
   JPopupMenu popupMenu;
   JToolBar   toolBar;
   JMenuBar   menuBar;

   JDialog dialog = null;
   Color    color = null;

   JButton button1;
   JButton button2;
   JButton button3;
   JButton button4;

   // Constructor method for application version ....

   PolygonToolBar( DemoGraphicsScreen gs ) {
       this.gs = gs;

       // Create items for "file" pull-down menu ....

       JMenuItem openFile = new JMenuItem( "Open" );       
       openFile.addActionListener( new FileOpenListener() );

       JMenuItem saveFile = new JMenuItem( "Save" );       
       saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("*** Selected Save File!");
            }
       });

       JMenuItem saveAsFile = new JMenuItem( "Save As" );       
       saveAsFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("*** Selected Save As File!");
            }
       });

       // Build file menu ....

       JMenu fileMenu = new JMenu("File", true);
       fileMenu.add(new JMenuItem("New"));
       fileMenu.add( openFile );
       fileMenu.addSeparator();
       fileMenu.add( saveFile );
       fileMenu.add( saveAsFile );
                     
       // Add the Edit menu and its menu items.

       JMenu editMenu = new JMenu("Edit");
       editMenu.add(new JMenuItem("Undo"));
       editMenu.addSeparator();
       editMenu.add(new JMenuItem("Cut"));
       editMenu.add(new JMenuItem("Copy"));
       editMenu.add(new JMenuItem("Paste"));

       // Create "show/draw" menu items and action listeners.....

       JMenuItem draw1 = new JMenuItem("Show Nodes");
       draw1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("*** Selected Draw Nodes!");
            }
       });

       JMenuItem draw2 = new JMenuItem("Show Edges");
       draw2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.out.println("*** Selected Draw Edges!");
            }
       });

       // Create view submenus ....

       JMenu viewMenu = new JMenu("View");
       JMenuItem addZoomIn  = new JMenuItem( "Zoom In" );       
       JMenuItem addZoomOut = new JMenuItem( "Zoom Out" );       
       viewMenu.add( addZoomIn );
       viewMenu.add( addZoomOut );
       viewMenu.add( draw1 );
       viewMenu.add( draw2 );

       // Create menu for application layers .....

       JMenu layerMenu = new JMenu("Layers");
       JCheckBoxMenuItem layerItem;
       String[] lbLabels =  {"Cross-Section", "Reinforcing"};
       for (int i=0; i< lbLabels.length; i++) {
            layerItem = new JCheckBoxMenuItem( lbLabels[i] );
            layerMenu.add(layerItem);
       }

       // Create and add the Modify menu and submenus...

       JMenu modifyMenu          = new JMenu("Graphics");
       JMenu graphicsOptionsMenu = new JMenu("Options");
       JMenu gridsizeOptionsMenu = new JMenu("Grid Size");

       modifyMenu.add( graphicsOptionsMenu );
       modifyMenu.add( gridsizeOptionsMenu );

       JCheckBoxMenuItem cbItem;
       String[] cbLabels =  {"Snap to Grid", "Automatic Redraw"};
       for (int i=0; i<cbLabels.length; i++) {
            cbItem = new JCheckBoxMenuItem( cbLabels[i] );
            cbItem.addActionListener( new MenuListener() );
            graphicsOptionsMenu.add(cbItem);
       }

       JRadioButtonMenuItem rbItem;
       ButtonGroup group = new ButtonGroup();
       String[] gridLabels = { "5", "10", "20", "30", "40", "50" };
       for (int i=0; i<gridLabels.length; i++) {
            rbItem = new JRadioButtonMenuItem( gridLabels[i] );
            rbItem.addActionListener( new MenuListener() );
            gridsizeOptionsMenu.add(rbItem);
            group.add(rbItem);
       }

       // Create toolbar buttons ....

       Icon gridIcon    = new ImageIcon( "icons/grid.gif" );
       Icon moveIcon    = new ImageIcon( "icons/move.gif" );

       button1 = new JButton( moveIcon   );
       button1.setText("Move Node");
       button1.setBackground(Color.lightGray);
       button1.setToolTipText("Move Node");
       button1.addActionListener( new EditPolygonListener() );

       button2 = new JButton( gridIcon   );
       button2.setText("Draw Grid");
       button2.setBackground(Color.lightGray);
       button2.setToolTipText("Draw Grid");
       button2.addActionListener( new GridListener() );

       JPanel panel1 = new JPanel();
       panel1.add( button1 );
       panel1.add( button2 );

       // Add a toolbar to the top of the applet.

       menuBar = new JMenuBar();
       menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
       menuBar.setBorderPainted(true);

       menuBar.add( fileMenu );
       menuBar.add( editMenu );
       menuBar.add( layerMenu );
       menuBar.add( viewMenu );
       menuBar.add( modifyMenu );

       this.setLayout( new BorderLayout() );
       this.setMargin( new Insets(5,5,5,5));
       this.setBorder( new BevelBorder(BevelBorder.RAISED) );
       this.setBorderPainted(true);
       this.add( menuBar, BorderLayout.WEST );
       this.add(  panel1, BorderLayout.EAST );
   }     

   // Listener for "file open" class ....

   class FileOpenListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
          System.out.println("*** In FileOpen Listener!");

          // get name of xml file ....

          Component parentFrame = PolygonGUI.getPolygonGUIFrame();
          PolygonFileFilter filter = new PolygonFileFilter( "xml", "XML files");
          JFileChooser chooser = new JFileChooser();
          chooser.addChoosableFileFilter(filter);

          int returnVal = chooser.showOpenDialog(parentFrame);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
              File file = chooser.getSelectedFile();
              System.out.println("Opening: " + file.getPath());
              gs.loadPolygon( file.getPath() );
          }
       }
   }

   // Listener for "draw grid" class ....

   class GridListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Draw Grid")) {
                System.out.println("In Grid Listener: Draw Grid()");

                // Draw grid ....

                gs.drawGrid();

                // Raise/lower button bevels ....

                button1.setBorder( new BevelBorder(BevelBorder.LOWERED) );
                button1.setBorderPainted(false);
                button2.setBorder( new BevelBorder(BevelBorder.RAISED) );
                button2.setBorderPainted(true);

                // Set flags for graphics operations ...

                gs.moveEnabled = false;

            }
        }
   }

   // Listener for editing polygon shapes ...

   class EditPolygonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            System.out.println("In EditPolygonListener: button.setEnabled ( true) ");

            // Determine which button has been pushed, process flags....

            if (button.getToolTipText().equals("Move Node")) {
                button1.setBorder( new BevelBorder(BevelBorder.RAISED) );
                button1.setBorderPainted(true);
                button2.setBorder( new BevelBorder(BevelBorder.LOWERED) );
                button2.setBorderPainted(false);

                // Set flags for graphics operation ....

                gs.moveEnabled = true;
            }
        }
   }

   // Listener for handling menu selections ....

   class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JMenuItem item = (JMenuItem) e.getSource();
            System.out.println("In MenuListener():Label = " + item.getText());

            // Determine which menu item has been selected ....

            if (item.getText().equals("Snap to Grid")) {
                gs.grid.setGridSnap();
            }

            if (item.getText().equals("Automatic Redraw")) {
            }

            // Set grid size from radio buttons ....

            if ( item.getText().equals( "5") == true ||
                 item.getText().equals("10") == true ||
                 item.getText().equals("20") == true ||
                 item.getText().equals("30") == true ||
                 item.getText().equals("40") == true || 
                 item.getText().equals("50") == true ) { 
                    int iGridSize = Integer.valueOf( item.getText() ).intValue();
                    gs.grid.setGridSize( iGridSize );
                }
        }
   }     
}
