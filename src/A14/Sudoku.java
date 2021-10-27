package A14;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Sudoku{

    BacktrackingAlgorithm obj = new BacktrackingAlgorithm();

    private static boolean checkRowDuplicates(int[][] board, int i){
        for(int j = 0; j < board.length; j++){
            for(int k = j + 1; k < board.length; k++){
                if(board[i][j] != 0 && board[i][k] != 0) {
                    if (board[i][j] == board[i][k] || board[i][k] < 0 || board[i][k] > 9) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkColumnDuplicates(int[][] board, int j){
        for(int i = 0; i < board.length; i++){
            for(int k = i + 1; k < board.length; k++){
                if(board[i][j] != 0 && board[k][j] != 0) {
                    if (board[i][j] == board[k][j] || board[k][j] < 0 || board[k][j] > 9) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkSquareDuplicates(int[][]board){
        int[] square = new int[9];
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3) {
                try {
                    int count = 0;

                    for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            square[count] = board[k][l];
                            count++;
                        }
                    }

                    for (int number : square) {
                        if (set.contains(number) && number != 0 || number < 0 || number > 9) {
                            return true;
                        } else {
                            set.add(number);
                        }
                    }

                    set.clear();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
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
        int[][] _board = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                _board[i][j] = board[i][j];
            }
        }

        return obj.solve(_board);
    }

    public static int[][] createBoard(int x){
        int[][] board = new int[9][9];

        Random rng = new Random();

        for(int i = 0; i < 81 - x; i++){
            int row = rng.nextInt(9);
            int column = rng.nextInt(9);

            while(board[row][column] != 0){
                row = rng.nextInt(9);
                column = rng.nextInt(9);
            }

            board[row][column] = rng.nextInt(9) + 1;
        }

        return board;
    }
}