package A11;
 //comment
import javax.swing.JOptionPane;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.io.File;
import java.util.Scanner;

class DrawF
{
    static public void drawF(int L)
    {
        for (int j = 0; j < L; j++) {
            System.out.print("*");
        }
        System.out.println(" ");

        for (int i = 1; i < L; i++)
        {
            if (i == L/2)
            {
                for(int k = 0; k <= L/2; k++)
                {
                    System.out.print("*");
                }

                System.out.println(" ");
            }
            else
            {
                System.out.println("*");
            }
        }

        System.out.print("\n");
    }

     static void DrawHgraphics(int L)
     {
         Frame f  = new Frame("Drawing F"){
             public void paint(Graphics g)
             {
                 Graphics2D g2 = (Graphics2D) g;
                 g2.draw(new Line2D.Double(50, 50, 50, 50 + L*50));
                 QuadCurve2D q = new QuadCurve2D.Float();
                 q.setCurve(50,50,L * 50,L + 50,L * 15,40 + L* 15);
                 g2.draw(q);
                 g2.draw(new Line2D.Double(50 - L,50 + L*25, L * 25,50 + L*25));
             }
         };

         f.setSize(400,400);
         f.setVisible(true);

     }

     static int ReadInt()
     {
         int L;
         System.out.println("Type a new integer");
         Scanner in = new Scanner(System.in);
         L = in.nextInt();
         return L;
     }



    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("Wrong number of arguments");
            System.exit(0);
        }
        String M = args[0];
        int L = Integer.parseInt(args[1]);

        if(!M.equals("c") && !M.equals("w") && !M.equals("f") && !M.equals("g"))
        {
            System.err.println("Wrong type of argument, please type a valid letter");
            System.exit(0);
        }
        if(L < 3 || L > 20)
        {
            System.err.println(("Invalid argument, please stay within the range of 3-20"));
            System.exit(0);
        }

        switch (M)
        {
            case "c":
                drawF(L);
                break;
            case "w":
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(baos);
                    PrintStream old = System.out;
                    System.setOut(ps);
                    drawF(L);
                    System.out.flush();
                    System.setOut(old);
                    JOptionPane.showMessageDialog(null, baos.toString(), "exit window", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    System.err.println("Exception: " + e);
                    System.exit(2);
                }
                break;
            case "f":
                try
                {
                    File file = new File("f" + L + ".html");
                    PrintWriter writer = new PrintWriter(file);
                    writer.write("<!DOCTYPE html>\n");
                    writer.write("<html>\n");
                    writer.write("<head>\n");
                    writer.write("<meta http-equiv= \"content-type\" content=\"text/html;charset=utf-8\"/>\n");
                    writer.write("</head>\n");
                    writer.write("<body><font size=\"" + L + "\">F with font size =" + L +"</font></body>\n");
                    writer.write("</html>\n");
                    writer.close();
                }
                catch(Exception e)
                {
                    System.err.println("Exception: " + e);
                    System.exit(1);
                }
                break;
            case "g":
                DrawHgraphics(L);
                break;
        }

        if(L > 3)
        {
            L = L - 1;
            main(new String[] {M, Integer.toString(L) });
        }
        else
        {
            if(!M.equals("w"))
            {
                L = ReadInt();
            }
            else
            {
                L = Integer.parseInt(JOptionPane.showInputDialog("Give me a new integer", 4));
            }
            main(new String[] {M, Integer.toString(L) });
        }
    }
}
