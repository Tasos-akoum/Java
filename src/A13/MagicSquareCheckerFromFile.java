package A13;

import javax.swing.JFileChooser;
import java.io.*;
import java.lang.String;

class MagicSquareCheckerFromFile{
    private static int[][] readFile(File file)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String row;
            int lines = 0;

            while (br.readLine() != null) {
                lines++;
            }

            br.close();
            br = new BufferedReader((new FileReader(file)));
            int[][] array = new int[lines][lines];

            int i = 0;
            while((row = br.readLine()) != null)
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
                }

                i++;
            }

            return array;

        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }

        return null;
    }

    private static File getFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file");
        int userSelection = fileChooser.showSaveDialog(null);
        if(userSelection == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String filepath = file.getAbsolutePath();
            System.out.println("the path of the selected file is: " + filepath);

            return file;
        }

        return null;

    }


    public static void main(String[] args) {

        File file = getFile();
        if(file !=null && file.exists())
        {
            try {
                String name = file.getName();
                if (!name.substring(name.lastIndexOf(".")).equals(".txt"))
                {
                    System.err.println("The file should be of the type .txt");
                    System.exit(1);
                }
            } catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            System.err.println("Failed to select file");
            System.exit(1);
        }

        int[][] array = readFile(file);
    }
}