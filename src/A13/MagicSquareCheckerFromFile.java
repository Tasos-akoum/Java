package A13;

import javax.swing.*;
import java.io.*;
import java.lang.String;
import A13.Downloader;

import A12.MagicSquareChecker;

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
                    System.exit(3);
                }

                i++;
            }
            br.close();

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

    private static File getFolder()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(null);
        if(userSelection == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String filepath = file.getAbsolutePath();
            System.out.println("the path of the selected directory is: " + filepath);

            return file;
        }

        return null;
    }

    public static void main(String[] args) {

        File file;
        System.out.println("Do you want to choose a file from your computer(Type 0) or from a URL(Type 1)");
        int answer = MagicSquareChecker.ReadInteger();
        int[][] square = {};

        if(answer == 0)
        {
            file = getFile();
            if(file !=null && file.exists())
            {
                try {
                    String name = file.getName();
                    if (!name.substring(name.lastIndexOf(".")).equals(".txt"))
                    {
                        System.err.println("The file should be of the type .txt");
                        System.exit(0);
                    }
                } catch(Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            else {
                System.err.println("Failed to select file");
                System.exit(1);
            }

            square = readFile(file);
        }
        else if(answer == 1)
        {
            String address = JOptionPane.showInputDialog("Type the URL you want to use ","");
            square = Downloader.download(address);
        }
        else
        {
            System.err.println("Wrong answer...terminating program");
            System.exit(1);
        }


        MagicSquareChecker.printArray(square);

        if(MagicSquareChecker.checkIsMagic(square))
        {
            System.out.println("It is a magic square and the magic number is: " + MagicSquareChecker.getMagicNumber(square));
            File folder = getFolder();

            if(folder != null && folder.exists())
            {
                try {
                    File file2 = new File(folder + "/MagicSquareSavedFile.txt");
                    PrintWriter writer = new PrintWriter(file2);
                    for(int i = 0; i < square.length; i++)
                    {
                        for(int j = 0; j < square.length; j++)
                        {
                            writer.write(square[i][j] + ", ");
                        }
                        writer.write("\n");
                    }

                    writer.write("\nMagic square with magic number: " + MagicSquareChecker.getMagicNumber(square));
                    writer.close();
                } catch(FileNotFoundException e) {
                    System.err.println(e.getMessage());
                    System.exit(4);
                }
            }
        }
        else
            System.out.println("It is not a magic square");
    }
}