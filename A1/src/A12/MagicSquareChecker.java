package A12;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicSquareChecker
{
    public static void printArray(int[][] array)
    {
        for(int i = 0; i < array.length * 6; i++)
        {
            System.out.print("-");
        }
        System.out.print("-");
        System.out.println();

        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < array.length; j++)
            {
                System.out.printf("|%5d", array[i][j]);
            }
            System.out.print("|");
            System.out.println();
            for(int k = 0; k < array.length * 6; k++)
            {
                System.out.print("-");
            }
            System.out.print("-");
            System.out.println();
        }
    }

    public static int ReadInteger()
    {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private static int sumOfRow(int [][] s ,int k)
    {
        int sum = 0;
        for(int i = 0; i < s.length; i++)
        {
            sum += s[k][i];
        }
        return sum;
    }

    private  static int sumOfColumn(int [][] s, int k)
    {
        int sum = 0;
        for(int i = 0; i < s.length; i++)
        {
            sum += s[i][k];
        }
        return sum;
    }

    private  static int sumOfDiagonal1(int [][] s)
    {
        int sum = 0;
        for(int i = 0; i < s.length; i ++)
        {
            sum += s[i][i];
        }

        return sum;
    }

    private  static int sumOfDiagonal2(int [][] s)
    {
        int sum = 0;
        for(int i = s.length - 1; i >= 0; i--)
        {
            sum += s[i][i];
        }

        return sum;
    }

    private static boolean hasDuplicates(int[][] s)
    {
        try {
            Set<Integer> set = new HashSet<>();

            for (int[] n : s) {
                for (int number : n) { //if a number has not been found, put it in the hashset and if it is in the hashset return true for duplicate
                    if (set.contains(number))
                        return true;
                    else
                        set.add(number);
                }
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public static int getMagicNumber(int[][] s)
    {
        return sumOfDiagonal1(s); //Here we use the sumOfDiagonal1 to get the magic number as it is the most efficient out of all the  methods
    }

    public static boolean checkIsMagic(int[][] s)
    {
        int magicNumber = getMagicNumber(s);

        if(hasDuplicates(s))
        {
            System.out.println("The array has at least one duplicate number");
            return false;
        }
        for(int i = 0; i < s.length; i++)
        {
            if(sumOfRow(s,i) != magicNumber || sumOfColumn(s,i) != magicNumber)
            {
                System.out.println("Not all rows and columns have the same sum");
                return false;
            }
        }

        //We don't need to check for the magic number of the first diagonal because it is stored in the magic Number variable
        if(sumOfDiagonal2(s) != magicNumber)
        {
            System.out.println("The sum of the diagonal does not equal that of the rows and columns");
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        int n = 0;
        System.out.println("Type the size of the array (n)");
        while(n < 2 || n > 10)
        {
            n = ReadInteger();
            if(n < 2 || n > 10)
            {
                System.out.println("The number should be between 2 and 10, please try again");
            }
        }
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++) {
                System.out.println("Type a number for the " + i + "," + j + " cell");
                array[i][j] = ReadInteger();
            }
        }

        printArray(array);

        if(checkIsMagic(array))
            System.out.println("It is a magic square and the magic number is: " + getMagicNumber(array));
        else
            System.out.println("It is not a magic square");

    }
}

