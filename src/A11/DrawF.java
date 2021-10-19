package A11;

import javax.swing.JOptionPane;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.File;

class DrawF
{
    static public void drawF(int L)
    {
    	//commend2
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

        if(M.equals("c"))
        {
            drawF(L);
        }
        else if(M.equals("w"))
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            drawF(L);
            System.out.flush();
            System.setOut(old);
            JOptionPane.showMessageDialog(null, baos.toString(), "exit window", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(M.equals("f"))
        {
            try
            {
                File file = new File("f.html");
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
        }
    }

}
