package A12;

import java.util.Scanner;

class MagicSquareChecker
{
    public static int ReadInteger()
    {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        return a;
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

    public static boolean checkIsMagic(int[][] s)
    {
        for(int i = 1; i < s.length; i++)
        {
            if(sumOfRow(s,i) != sumOfRow(s,0))
                return false;
            else if(sumOfColumn(s,i) != sumOfColumn(s,0))
                return false;
        }

        if(sumOfColumn(s,0) != sumOfRow(s,0))
            return false;
        if(sumOfDiagonal2(s) != sumOfDiagonal1(s) || sumOfDiagonal2(s) != sumOfRow(s,0))
            return false;

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


    }
}

