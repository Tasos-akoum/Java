package A13;

import javax.swing.JFileChooser;
import java.io.*;
import java.lang.String;

class MagicSquareCheckerFromFile{
    private static void readFile(File file)
    {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String row;

            while ((row = br.readLine()) != null) {
                String[] data = row.split(",");
                for(String str : data)
                {
                    System.out.println(str);
                }
            }


        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }


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

        readFile(file);



    }
}