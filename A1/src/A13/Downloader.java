package A13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JOptionPane;

public class Downloader {

    static int[][] download(String address) {

        try {
            URL url = new URL(address);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            int lines = 0;
            String row;

            //Same logic as the readFile() method

            while (in.readLine() != null) {
                lines++;
            }
            in.close();

            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            int[][] array = new int[lines][lines];

            int i = 0;
            while((row = in.readLine()) != null)
            {
                String[] data = row.split(",");
                if(data.length != lines)
                {
                    System.err.println("This square is not of the type n*n");
                    System.exit(2);
                }

                try {
                    int j = 0;
                    for (String str : data) {
                        array[i][j] = Integer.parseInt(str);
                        j++;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    System.exit(3);
                }

                i++;
            }
            in.close();

            return array;

        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
         return null;
    }

    public static void main(String[] a) {

        String toDownload =
                JOptionPane.showInputDialog("Δώστε την διεύθυνση ", "");
        System.out.println(toDownload);

        download(toDownload);
    }
}