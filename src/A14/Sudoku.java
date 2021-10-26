package A14;

import java.util.HashSet;
import java.util.Set;
import A14.BacktrackingAlgorithm.*;

class Sudoku{
    public static void main(String[] args) {

    }

    BacktrackingAlgorithm obj = new BacktrackingAlgorithm();

    private static boolean checkRowDuplicates(int[][] board, int i){
        for(int j = 0; j < board.length; j++){
            for(int k = j + 1; k < board.length; k++){
                if(board[i][j] == board[i][k] || board[i][k] < 0 || board[i][k] > 9){
                    System.out.println("Invalid row " + i);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkColumnDuplicates(int[][] board, int j){
        for(int i = 0; i < board.length; i++){
            for(int k = i + 1; k < board.length; k++){
                if(board[i][j] == board[k][j] || board[k][j] < 0 || board[k][j] > 9){
                    System.out.println("Invalid Column " + j);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkSquareDuplicates(int[][]board){
        int[] square = new int[3];
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < board.length; i += 3){
            for(int j = 0; j < board.length; j += 3){
                int count = 0;

                for(int k = i; k < i + 3; k++)
                    for(int l = j; l < j + 3; l++){
                        square[count] = board[k][l];
                        count++;
                    }
                }

                for(int number : square){
                    if(set.contains(number) || number < 0 || number > 9){
                        System.out.println("Invalid square");
                        return true;
                    } else {
                        set.add(number);
                    }
            }
        }

        return false;
    }

    public static boolean isValidBoard(int[][] board){
        if(checkSquareDuplicates(board))
            return false;

        for(int i = 0; i < board.length; i++){
            if(checkRowDuplicates(board,i) || checkColumnDuplicates(board,i))
                return false;
        }

        return true;
    }

    public boolean isSolvableBoard(int[][] board){
        return obj.solve(board);
    }
}