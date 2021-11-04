package A15;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileSystem;

class encrypt{
    public static void main(String[] args) {
        if(args.length != 2 && args.length != 0){
            System.out.println("Wrong amount of arguments");
            System.exit(-1);
        }

        long startTime = 0;
        long endTime = 0;

        //If the user has provided arguments
        if(args.length == 2) {
            startTime = System.nanoTime();

            String filename = args[0];
            String outputFileName = "Encrypted" + filename;

            int k = Integer.parseInt(args[1]);
            if (!test(k))
                System.exit(-1);

            try {
                Transform(filename, outputFileName, k);
            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
                System.exit(4);
            }

            endTime = System.nanoTime();
        //If the user hasn't provided arguments
        } else {

            try {
                File inputFile = selectFile();
                int k = Integer.parseInt(JOptionPane.showInputDialog("By how many bytes do you want the file to be encrypted?", 0));

                startTime = System.nanoTime();

                if(inputFile != null)
                    Transform(inputFile.getName(), "Encrypted" + inputFile.getName(), k);
                else {
                    System.err.println("File not provided");
                    System.exit(5);
                }

                endTime = System.nanoTime();

            } catch(Exception e) {
                System.err.println("Exception: " + e.getMessage());
                System.exit(6);
            }
        }

        long time = endTime - startTime;
        System.out.println("Time: " + time);
    }

    private static File selectFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file");

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filepath = file.getAbsolutePath();
            System.out.println("The path of the selected file is: " + filepath);

            return file;
        }

        return null;
    }

    private static void Transform(String inputFileName, String outputFileName, int inputk){
            File inputFile = new File(inputFileName);
            File outputFile = new File(outputFileName);
            Transform(inputFile, outputFile, inputk);
    }

    private static void Transform(File inputFile, File outputFile, int inputk){
            byte[] bFile = new byte[(int) inputFile.length()];

            try {
                FileInputStream in = new FileInputStream(inputFile); // Only works for the default file system.

                try {
                    in.read(bFile); //Read the file byte by byte and store the bytes in bFile[]
                    in.close();

                } catch(IOException e) {
                    System.err.println("Exception: " + e.getMessage());
                    System.exit(2);
                }

                try {
                    for (int i = 0; i < bFile.length; i++) {
                        bFile[i] += inputk; //Change the bytes by inputk
                    }

                    FileOutputStream out = new FileOutputStream(outputFile); // Only works for the default file system.
                    out.write(bFile);
                    out.close();

                } catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
                    System.exit(3);
                }

        } catch (FileNotFoundException e) {
            System.err.println("Exception: " + e.getMessage());
            System.exit(1);
        }
    }

    private static boolean test(int k){
        if(k <= 0) {
            System.err.println("Invalid argument");
            return false;
        }

        return true;
    }
}
