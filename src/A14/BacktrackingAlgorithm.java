package A14;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BacktrackingAlgorithm {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    private static final int NO_VALUE = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private static int[][] board = new int[9][9];

    public static void main(String[] args) {
        BacktrackingAlgorithm solver = new BacktrackingAlgorithm();
        Sudoku sudoku = new Sudoku();

        System.out.println("Type how many board you want to create");
        int N = readInt();
        System.out.println("Type how many cells you want to be empty");
        int x = readInt();

        int count = 1;
        int invalid_count = 0;
        int unsolvable_count = 0;

        long start = System.currentTimeMillis();
        while(count <= N){
            board = Sudoku.createBoard(x);
            if(Sudoku.isValidBoard(board)){
                if(sudoku.isSolvableBoard(board)) {
                    System.out.println("Board #" + count);
                    solver.printBoard();

                    System.out.println("Solution of the Board #" + count);
                    solver.solve(board);
                    solver.printBoard();

                    count++;
                } else {
                    unsolvable_count++;
                }
            } else {
                invalid_count++;
            }
        }

        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;

        System.out.printf("%-25s:%-5d%n","Empty cells per board",x);
        System.out.printf("%-25s:%-5d%n","Valid boards created",N);
        System.out.printf("%-25s:%-5d%n","Invalid boards created",invalid_count);
        System.out.printf("%-25s:%-5d%n","Unsolvable boards created",unsolvable_count);
        System.out.printf("%-25s:%-3.3f%n","Elapsed time in seconds", sec);

    }

    private static int readInt(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    private void printBoard() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public boolean solve(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] board, int row, int column) {
        return rowConstraint(board, row) &&
                columnConstraint(board, column) &&
                subsectionConstraint(board, row, column);
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}