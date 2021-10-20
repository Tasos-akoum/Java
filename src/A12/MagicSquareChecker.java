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

    private  static boolean hasDuplicates(int[][] s)
    {
        int k,l;

        for(int i = 0; i < s.length; i++)
        {
            for(int j = 0; j < s.length; j++)
            {
                if(j == s.length - 1) {
                    k = i + 1;
                    l = 0;
                }
                else
                {
                    k = i;
                    l = j + 1;
                }

                while(k < s.length)
                {
                    if(s[i][j] == s[k][l])
                        return true;

                    if(l == s.length - 1)
                    {
                        l = 0;
                        k++;
                    }
                    else
                        l++;
                }
            }
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

        //We don't need to check for the magic number of the first diagonal because it is stored in the maginNumber variable
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

        if(checkIsMagic(array))
            System.out.println("It is a magic square");
        else
            System.out.println("It is not a magic square");

    }
}

