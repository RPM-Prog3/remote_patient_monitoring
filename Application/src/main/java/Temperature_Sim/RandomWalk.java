package App.Temperature_Sim;

import javax.swing.*;
import java.util.Random;

public class RandomWalk extends JPanel
{
    final double   range   = 0.1;//max space to move in width or height
    final int   num     = 50000;//number of line-1 to draw
//    final float[] ax      = new float[num];
    final double[] ay      = new double[num];

    public RandomWalk()
    {

        final Random rand = new Random();//better to use Random. Easier to debug since you can set a seed

        // lets set first point in the middle
//        ax[0] = 0;
        ay[0] = 36.8;



        // generating random point
        for (int i=2;i<num; i=i+2)
        {
//                    ax[i] =  ax[i-2] + ((rand.nextFloat()) * range);
            ay[i] =  ay[0];

        }
        for (int i = 1;i <num;  i=i+2)
        {
            //make sure that we dont go outside the bound of the panel
//            ax[i] = (int) Math.max(0, Math.min(width, ax[i-1] + ((rand.nextFloat() - .5f) * range)));
//            ay[i] = (int) Math.max(0, Math.min(height, ay[i-1] + ((rand.nextFloat() - .5f) *range)));
//            ax[i] =  ax[i-1] + ((rand.nextFloat()) * range);
            ay[i] =  ay[i-1] + (Math.sin((4*rand.nextDouble())) * range);

        }

    }

    public double[] getPoints() {
        return ay;
    }

//    public void paintComponent(Graphics g)
//    {    g.drawLine(0,300,500,300);
//        g.drawLine(20,500,20,0);
//        g.drawString("Time t", 350, 320);
//        g.drawString("Body Temperature", 25, 200);
//        g.drawString("37.5 *C", 25, 245);
//        g.drawString("38 *C", 25, 220);
//        g.drawString("37 *C", 25, 270);
//        g.drawLine(18,220,22,220);
//        g.drawLine(18,270,22,270);
//        g.drawLine(18,245,22,245);
//
//        g.setColor(Color.black);
//        for (int i = 1; i < num; i++)
//            g.drawLine((int) ax[i - 1], (int) ay[i-1], (int) ax[i], (int) ay[i]);
//
//    }
//
//    public static void main(String[] args)
//    {
//        // Generates frame.
//        JFrame frame = new JFrame();
//
//        // Sets frame resolution and other parameters.
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        RandomWalk panel = new RandomWalk(frame.getWidth(), frame.getHeight());
//        frame.getContentPane().add(panel);
//        frame.setVisible(true);
//        frame.setResizable(true);
//
//    }
}