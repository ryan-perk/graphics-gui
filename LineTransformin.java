/**
 * LineTransformin.java
 *
 * @author ryanperkins
 */
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * LineTransformin.class
 */
public class LineTransformin extends JPanel {
    // lines
    private static ArrayList<Line> lines = new ArrayList<Line>();
    
    /** paintComponent method overrides the parent method to draw lines
     *
     * @param g graphics to be drawn
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.drawLine(0, 384, 1024, 384); // x-axis
        g.drawLine(512, 0, 512, 768); // y-axis
        g.setColor(Color.black);
        
        for(int i = 0; i < lines.size(); i++) {
            int x0 = (int)lines.get(i).getX0();
            int y0 = (int)lines.get(i).getY0();
            int x1 = (int)lines.get(i).getX1();
            int y1 = (int)lines.get(i).getY1();
            
            // first quadrant x0 and y0
            if(x0 >= 0 & y0 >= 0) {
                if(x0 == 0)
                    x0 = 512;
                else
                    x0 += 512;
                if(y0 == 0)
                    y0 = 384;
                else
                    y0 = 384 - y0;
            }
            
            // second quadrant x0 and y0
            else if(x0 <= 0 & y0 >= 0) {
                if(x0 == 0)
                    x0 = 512;
                else
                    x0 = 512 - Math.abs(x0);
                if(y0 == 0)
                    y0 = 384;
                else
                    y0 = 384 - y0;
            }
            
            // third quadrant x0 and y0
            else if(x0 <= 0 & y0 <= 0) {
                if(x0 == 0)
                    x0 = 512;
                else
                    x0 = 512 - Math.abs(x0);
                if(y0 == 0)
                    y0 = 384;
                else
                    y0 = Math.abs(y0) + 384;
            }
            
            // fourth quadrant x0 and y0
            else if(x0 >= 0 & y0 <= 0) {
                if(x0 == 0)
                    x0 = 512;
                else
                    x0 += 512;
                if(y0 == 0)
                    y0 = 384;
                else
                    y0 = Math.abs(y0) + 384;
            }
            
            // first quadrant x1 and y1
            if(x1 >= 0 & y1 >= 0) {
                if(x1 == 0)
                    x1 = 512;
                else
                    x1 += 512;
                if(y1 == 0)
                    y1 = 384;
                else
                    y1 = 384 - y1;
            }
            
            // second quadrant x1 and y1
            else if(x1 <= 0 & y1 >= 0) {
                if(x1 == 0)
                    x1 = 512;
                else
                    x1 = 512 - Math.abs(x1);
                if(y1 == 0)
                    y1 = 384;
                else
                    y1 = 384 - y1;
            }
            
            // third quadrant x1 and y1
            else if(x1 <= 0 & y1 <= 0) {
                if(x1 == 0)
                    x1 = 512;
                else
                    x1 = 512 - Math.abs(x1);
                if(y1 == 0)
                    y1 = 384;
                else
                    y1 = Math.abs(y1) + 384;
            }
            
            // fourth quadrant x1 and y1
            else if(x1 >= 0 & y1 <= 0) {
                if(x1 == 0)
                    x1 = 512;
                else
                    x1 += 512;
                if(y1 == 0)
                    y1 = 384;
                else
                    y1 = Math.abs(y1) + 384;
            }
            
            g.drawLine(x0, y0, x1, y1);
        }
    }
    
    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        // scanner initialized
        Scanner scan = new Scanner(System.in);
        
        // window is created
        JFrame f = new JFrame("Transformin' Lines");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(1024, 768));
        f.pack();
        
        // panel with all drawn lines is created and added
        LineTransformin canvas = new LineTransformin();
        f.add(canvas);
        
        // menu
        JMenuBar menuBar = new JMenuBar();
        
        /* draw menu */
        JMenu draw = new JMenu("draw");
        menuBar.add(draw);
        
        // draw lines
        JMenuItem displayPixels = new JMenuItem("displayPixels");
        displayPixels.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String num = JOptionPane.showInputDialog("# of lines: ", null);
                int n = Integer.parseInt(num);
                
                for(int i = 0; i < n; i++) {
                    String x0 = JOptionPane.showInputDialog("x0: ", null);
                    String y0 = JOptionPane.showInputDialog("y0: ", null);
                    String x1 = JOptionPane.showInputDialog("x1: ", null);
                    String y1 = JOptionPane.showInputDialog("y1: ", null);
                    lines.add(new Line(Integer.parseInt(x0), Integer.parseInt(y0), Integer.parseInt(x1), Integer.parseInt(y1)));
                    canvas.repaint();
                }
            }
        });
        draw.add(displayPixels);
        
        // clear
        JMenuItem clear = new JMenuItem("clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lines.clear();
                canvas.repaint();
            }
        });
        draw.add(clear);
        /* end draw menu */
        
        /* edit menu */
        JMenu edit = new JMenu("edit");
        menuBar.add(edit);
        
        // apply transformation
        JMenuItem applyTransform = new JMenuItem("applyTransformation");
        applyTransform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("fileName: ", null);
                File file = new File(fileName);
                
                double[][] trans = new double[3][3];
                trans[0][0] = Double.parseDouble(JOptionPane.showInputDialog("(0, 0): ", null));
                trans[0][1] = Double.parseDouble(JOptionPane.showInputDialog("(0, 1): ", null));
                trans[0][2] = Double.parseDouble(JOptionPane.showInputDialog("(0, 2): ", null));
                trans[1][0] = Double.parseDouble(JOptionPane.showInputDialog("(1, 0): ", null));
                trans[1][1] = Double.parseDouble(JOptionPane.showInputDialog("(1, 1): ", null));
                trans[1][2] = Double.parseDouble(JOptionPane.showInputDialog("(1, 2): ", null));
                trans[2][0] = Double.parseDouble(JOptionPane.showInputDialog("(2, 0): ", null));
                trans[2][1] = Double.parseDouble(JOptionPane.showInputDialog("(2, 1): ", null));
                trans[2][2] = Double.parseDouble(JOptionPane.showInputDialog("(2, 2): ", null));
                
                Line.applyTransformation(trans, file);
                canvas.repaint();
            }
        });
        edit.add(applyTransform);
        
        // inputLines
        JMenuItem inputLines = new JMenuItem("inputLines");
        inputLines.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("fileName: ", null);
                File file = new File(fileName);
                Line.inputLines(file, lines.size());
                canvas.repaint();
            }
        });
        edit.add(inputLines);
        
        // outputLines
        JMenuItem outputLines = new JMenuItem("outputLines");
        outputLines.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("fileName: ", null);
                File file = new File(fileName);
                Line.outputLines(file, lines.size());
                canvas.repaint();
            }
        });
        edit.add(outputLines);
        /* end edit menu */
        
        /* transform menu */
        JMenu transform = new JMenu("transform");
        menuBar.add(transform);
        
        // basic translate
        JMenuItem bTranslate = new JMenuItem("basicTranslate");
        bTranslate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Tx = JOptionPane.showInputDialog("Tx (horizontal translation): ", null);
                String Ty = JOptionPane.showInputDialog("Ty (vertical translation): ", null);
                
                Line.basicTranslate(Integer.parseInt(Tx), Integer.parseInt(Ty));
                canvas.repaint();
            }
        });
        transform.add(bTranslate);
        
        // basic scale
        JMenuItem bScale = new JMenuItem("basicScale");
        bScale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Sx = JOptionPane.showInputDialog("Sx (horizontal scaling factor): ", null);
                String Sy = JOptionPane.showInputDialog("Sy (vertical scaling factor): ", null);
                
                Line.basicScale(Integer.parseInt(Sx), Integer.parseInt(Sy));
                canvas.repaint();
            }
        });
        transform.add(bScale);
        
        // basic rotate
        JMenuItem bRotate = new JMenuItem("basicRotate");
        bRotate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String angle = JOptionPane.showInputDialog("angle: ", null);
                
                Line.basicRotate(Integer.parseInt(angle));
                canvas.repaint();
            }
        });
        transform.add(bRotate);
        
        // scale
        JMenuItem scale = new JMenuItem("scale");
        scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Sx = JOptionPane.showInputDialog("Sx (horizontal scaling factor): ", null);
                String Sy = JOptionPane.showInputDialog("Sy (vertical scaling factor): ", null);
                String Cx = JOptionPane.showInputDialog("Cx (pivot x coord.): ", null);
                String Cy = JOptionPane.showInputDialog("Cy (pivot y coord.): ", null);
                
                Line.scale(Integer.parseInt(Sx), Integer.parseInt(Sy), Integer.parseInt(Cx), Integer.parseInt(Cy));
                canvas.repaint();
            }
        });
        transform.add(scale);
        
        // rotate
        JMenuItem rotate = new JMenuItem("rotate");
        rotate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String angle = JOptionPane.showInputDialog("angle: ", null);
                String Cx = JOptionPane.showInputDialog("Cx (pivot x coord.): ", null);
                String Cy = JOptionPane.showInputDialog("Cy (pivot y coord.): ", null);
                
                Line.rotate(Integer.parseInt(angle), Integer.parseInt(Cx), Integer.parseInt(Cy));
                canvas.repaint();
            }
        });
        transform.add(rotate);
        /* end transform menu */
        
        f.setJMenuBar(menuBar);
        
        f.setVisible(true);
    }
    
    public static ArrayList<Line> getLines() {
        return lines;
    }
}





/**
 * Line.class
 */
 class Line {
     // x0 coordinate
     private double x0;
     // y0 coordinate
     private double y0;
     // x1 coordinate
     private double x1;
     // y1 coordinate
     private double y1;
     
     /**
      * line constructor
      *
      * @param x0 start x coordinate
      * @param y0 start y coordinate
      * @param x1 end x coordinate
      * @param y1 end y coordinate
      */
     public Line(double x0, double y0, double x1, double y1) {
         this.x0 = x0;
         this.y0 = y0;
         this.x1 = x1;
         this.y1 = y1;
     }
     
     /** basicTranslate method that translates a line
      *
      * @param Tx horizontal displacement
      * @param Ty vertical displacement
      */
     public static void basicTranslate(double Tx, double Ty) {
         // translation matrix
         double[][] trans = new double[3][3];
         trans[0][0] = 1;
         trans[0][1] = 0;
         trans[0][2] = 0;
         trans[1][0] = 0;
         trans[1][1] = 1;
         trans[1][2] = 0;
         trans[2][0] = Tx;
         trans[2][1] = Ty;
         trans[2][2] = 1;
         
         for(int i = 0; i < LineTransformin.getLines().size(); i++) {
             double tempX0 = LineTransformin.getLines().get(i).getX0();
             double tempX1 = LineTransformin.getLines().get(i).getX1();
             
             // set x0
             LineTransformin.getLines().get(i).setX0((trans[0][0] * LineTransformin.getLines().get(i).getX0()) + (trans[1][0] * LineTransformin.getLines().get(i).getY0()) + (trans[2][0]));
             // set y0
             LineTransformin.getLines().get(i).setY0((trans[0][1] * tempX0) + (trans[1][1] * LineTransformin.getLines().get(i).getY0()) + (trans[2][1]));
             // set x1
             LineTransformin.getLines().get(i).setX1((trans[0][0] * LineTransformin.getLines().get(i).getX1()) + (trans[1][0] * LineTransformin.getLines().get(i).getY1()) + (trans[2][0]));
             // set y1
             LineTransformin.getLines().get(i).setY1((trans[0][1] * tempX1) + (trans[1][1] * LineTransformin.getLines().get(i).getY1()) + (trans[2][1]));
         }
     }
     
     /** basicScale method that scales a line
      *
      * @param Sx horizontal scaling factor
      * @param Sy vertical scaling factor
      */
     public static void basicScale(double Sx, double Sy) {
         // scale matrix
         double[][] scale = new double[3][3];
         scale[0][0] = Sx;
         scale[0][1] = 0;
         scale[0][2] = 0;
         scale[1][0] = 0;
         scale[1][1] = Sy;
         scale[1][2] = 0;
         scale[2][0] = 0;
         scale[2][1] = 0;
         scale[2][2] = 1;
         
         for(int i = 0; i < LineTransformin.getLines().size(); i++) {
             double tempX0 = LineTransformin.getLines().get(i).getX0();
             double tempX1 = LineTransformin.getLines().get(i).getX1();
             
             // set x0
             LineTransformin.getLines().get(i).setX0((scale[0][0] * LineTransformin.getLines().get(i).getX0()) + (scale[1][0] * LineTransformin.getLines().get(i).getY0()) + (scale[2][0]));
             // set y0
             LineTransformin.getLines().get(i).setY0((scale[0][1] * tempX0) + (scale[1][1] * LineTransformin.getLines().get(i).getY0()) + (scale[2][1]));
             // set x1
             LineTransformin.getLines().get(i).setX1((scale[0][0] * LineTransformin.getLines().get(i).getX1()) + (scale[1][0] * LineTransformin.getLines().get(i).getY1()) + (scale[2][0]));
             // set y1
             LineTransformin.getLines().get(i).setY1((scale[0][1] * tempX1) + (scale[1][1] * LineTransformin.getLines().get(i).getY1()) + (scale[2][1]));
         }
     }
     
     /** basicRotate method that rotates a line
      *
      * @param angle degrees to rotate the line clockwise
      */
     public static void basicRotate(double angle) {
         // rotation matrix
         double[][] rotate = new double[3][3];
         rotate[0][0] = Math.cos(Math.toRadians(angle));
         rotate[0][1] = -Math.sin(Math.toRadians(angle));
         rotate[0][2] = 0;
         rotate[1][0] = Math.sin(Math.toRadians(angle));
         rotate[1][1] = Math.cos(Math.toRadians(angle));
         rotate[1][2] = 0;
         rotate[2][0] = 0;
         rotate[2][1] = 0;
         rotate[2][2] = 1;
         
         for(int i = 0; i < LineTransformin.getLines().size(); i++) {
             double tempX0 = LineTransformin.getLines().get(i).getX0();
             double tempX1 = LineTransformin.getLines().get(i).getX1();
             
             // set x0
             LineTransformin.getLines().get(i).setX0((rotate[0][0] * LineTransformin.getLines().get(i).getX0()) + (rotate[1][0] * LineTransformin.getLines().get(i).getY0()) + (rotate[2][0]));
             // set y0
             LineTransformin.getLines().get(i).setY0((rotate[0][1] * tempX0) + (rotate[1][1] * LineTransformin.getLines().get(i).getY0()) + (rotate[2][1]));
             // set x1
             LineTransformin.getLines().get(i).setX1((rotate[0][0] * LineTransformin.getLines().get(i).getX1()) + (rotate[1][0] * LineTransformin.getLines().get(i).getY1()) + (rotate[2][0]));
             // set y1
             LineTransformin.getLines().get(i).setY1((rotate[0][1] * tempX1) + (rotate[1][1] * LineTransformin.getLines().get(i).getY1()) + (rotate[2][1]));
         }
     }
     
     /** scale method that scales a line
      *
      * @param Sx horizontal scaling factor
      * @param Sy vertical scaling factor
      * @param Cx horizontal center of scale
      * @param Cy vertical center of scale
      */
     public static void scale(double Sx, double Sy, double Cx, double Cy) {
         double Tx = 0;
         double Ty = 0;
         
         // first quadrant
         if(Cx > 0 & Cy > 0) {
             Tx = -Cx;
             Ty = -Cy;
         }
         
         // second quadrant
         else if(Cx < 0 & Cy > 0) {
             Tx = Math.abs(Cx);
             Ty = -Cy;
         }
         
         // third quadrant
         else if(Cx < 0 & Cy < 0) {
             Tx = Math.abs(Cx);
             Ty = Math.abs(Cy);
         }
         
         // fourth quadrant
         else if(Cx > 0 & Cy < 0) {
             Tx = -Cx;
             Ty = Math.abs(Cy);
         }
         
         basicTranslate(Tx, Ty);
         basicScale(Sx, Sy);
         basicTranslate(-Tx, -Ty);
     }
     
     /** rotate method that rotates a line
      *
      * @param angle degrees to rotate the line clockwise
      * @param Cx horizontal center of rotation
      * @param Cy vertical center of rotation
      */
     public static void rotate(double angle, double Cx, double Cy) {
         double Tx = 0;
         double Ty = 0;
         
         // first quadrant
         if(Cx > 0 & Cy > 0) {
             Tx = -Cx;
             Ty = -Cy;
         }
         
         // second quadrant
         else if(Cx < 0 & Cy > 0) {
             Tx = Math.abs(Cx);
             Ty = -Cy;
         }
         
         // third quadrant
         else if(Cx < 0 & Cy < 0) {
             Tx = Math.abs(Cx);
             Ty = Math.abs(Cy);
         }
         
         // fourth quadrant
         else if(Cx > 0 & Cy < 0) {
             Tx = -Cx;
             Ty = Math.abs(Cy);
         }
         
         double[][] rotate = new double[3][3];
         // first translate matrix * rotate
         rotate[0][0] = Math.cos(Math.toRadians(angle));
         rotate[0][1] = -Math.sin(Math.toRadians(angle));
         rotate[0][2] = 0;
         rotate[1][0] = Math.sin(Math.toRadians(angle));
         rotate[1][1] = Math.cos(Math.toRadians(angle));
         rotate[1][2] = 0;
         rotate[2][0] = (Tx * rotate[0][0]) + (Ty * rotate[1][0]);
         rotate[2][1] = (Tx * rotate[0][1]) + (Ty * rotate[1][1]);
         rotate[2][2] = 1;
         
         // rotate * second translate
         rotate[2][0] = (rotate[2][0]) + (-Tx);
         rotate[2][1] = (rotate[2][1]) + (-Ty);
         
         for(int i = 0; i < LineTransformin.getLines().size(); i++) {
             double tempX0 = LineTransformin.getLines().get(i).getX0();
             double tempX1 = LineTransformin.getLines().get(i).getX1();
             
             // set x0
             LineTransformin.getLines().get(i).setX0((rotate[0][0] * LineTransformin.getLines().get(i).getX0()) + (rotate[1][0] * LineTransformin.getLines().get(i).getY0()) + (rotate[2][0]));
             // set y0
             LineTransformin.getLines().get(i).setY0((rotate[0][1] * tempX0) + (rotate[1][1] * LineTransformin.getLines().get(i).getY0()) + (rotate[2][1]));
             // set x1
             LineTransformin.getLines().get(i).setX1((rotate[0][0] * LineTransformin.getLines().get(i).getX1()) + (rotate[1][0] * LineTransformin.getLines().get(i).getY1()) + (rotate[2][0]));
             // set y1
             LineTransformin.getLines().get(i).setY1((rotate[0][1] * tempX1) + (rotate[1][1] * LineTransformin.getLines().get(i).getY1()) + (rotate[2][1]));
         }
     }
     
     /** applyTransformation method transforms lines based off data
      *
      * @param matrix transformation to preform
      * @param dataline file containing line data
      */
     public static void applyTransformation(double[][] matrix, File datalines) {
         inputLines(datalines, 0);
         
         for(int i = 0; i < LineTransformin.getLines().size(); i++) {
             double tempX0 = LineTransformin.getLines().get(i).getX0();
             double tempX1 = LineTransformin.getLines().get(i).getX1();
             
             // set x0
             LineTransformin.getLines().get(i).setX0((matrix[0][0] * LineTransformin.getLines().get(i).getX0()) + (matrix[1][0] * LineTransformin.getLines().get(i).getY0()) + (matrix[2][0]));
             // set y0
             LineTransformin.getLines().get(i).setY0((matrix[0][1] * tempX0) + (matrix[1][1] * LineTransformin.getLines().get(i).getY0()) + (matrix[2][1]));
             // set x1
             LineTransformin.getLines().get(i).setX1((matrix[0][0] * LineTransformin.getLines().get(i).getX1()) + (matrix[1][0] * LineTransformin.getLines().get(i).getY1()) + (matrix[2][0]));
             // set y1
             LineTransformin.getLines().get(i).setY1((matrix[0][1] * tempX1) + (matrix[1][1] * LineTransformin.getLines().get(i).getY1()) + (matrix[2][1]));
         }
     }
     
     /** inputLines method reads data from user provided file and returns
      * number of lines read from the file
      *
      * @param dataline file containing line data
      * @param num # of lines
      */
     public static int inputLines(File datalines, int num) {
         int lineNum = 0;
         
         // attempts to read the file
         try(BufferedReader in = new BufferedReader(new FileReader(datalines))) {

             // reads lines the file
             while(in.ready()) {
                 String line = in.readLine();
                 String[] tokens = line.split(" ");
                 
                 LineTransformin.getLines().add(new Line(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]), Integer.valueOf(tokens[3])));
                 
                 lineNum++;
             }
             
         } catch(IOException err) { } // exception handled
         
         return lineNum;
     }
     
     /** outputLines method outputs line data to a user provided file name
      *
      * @param dataline file containing line data
      * @param num # of lines
      */
     public static void outputLines(File datalines, int num) {
         try (PrintWriter out = new PrintWriter(datalines)) {
             // writes each line in the lines arraylist to a file of 'filename'
             for(int i = 0; i < num; i++) {
                 out.print(Integer.toString((int)(LineTransformin.getLines().get(i).getX0())) + " " + Integer.toString((int)(LineTransformin.getLines().get(i).getY0())) + " " + Integer.toString((int)(LineTransformin.getLines().get(i).getX1())) + " " + Integer.toString((int)(LineTransformin.getLines().get(i).getY1())) + "\n");
             }
             
             out.close();
             
         } catch(IOException err) {
             err.printStackTrace();
         }
     }
     
     /** x0 getter
      *
      * @return x0
      */
     public double getX0() {
         return this.x0;
     }
     
     /** y0 getter
      *
      * @return y0
      */
     public double getY0() {
         return this.y0;
     }
     
     /** x1 getter
      *
      * @return x1
      */
     public double getX1() {
         return this.x1;
     }
     
     /** y1 getter
      *
      * @return y1
      */
     public double getY1() {
         return this.y1;
     }
     
     /** x0 setter
      *
      * @param x0 new x0
      */
     public void setX0(double x0) {
        this.x0 = x0;
     }
     
     /** y0 setter
      *
      * @param y0 new y0
      */
     public void setY0(double y0) {
         this.y0 = y0;
     }
     
     /** x1 setter
      *
      * @param x1 new x1
      */
     public void setX1(double x1) {
         this.x1 = x1;
     }
     
     /** y1 setter
      *
      * @param y1 new y1
      */
     public void setY1(double y1) {
         this.y1 = y1;
     }
 }
