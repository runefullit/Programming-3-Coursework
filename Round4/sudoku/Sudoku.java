/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

import java.util.Arrays;

/**
 *
 * @author Olli
 */
public class Sudoku {
    private char[][] grid;
    
    public Sudoku() {
        this.grid = new char[9][9];
        for (char[] j : this.grid) {
            Arrays.fill(j, ' ');
        }
    }
    
    
    /**
     * Set an element on grid to either an empty space or a digit.
     * @param i
     * @param j
     * @param c 
     */
    public void set(int i, int j, char c) {
        try {
            // This ugly piece tries to kick off the Index excetion before
            // the if-else control structure.
            this.grid[i][j] = this.grid[i][j];
            if ( c == ' ' | Character.isDigit(c)){
                this.grid[i][j] = c;
            }
            else {
                System.out.format("Trying to set illegal character %s to (%d, %d)!%n", c, i, j);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.format("Trying to access illegal cell (%d, %d)!%n", i, j);
        }
    }
    
    /**
     * Check the legality of the sudoku. Diagonals are not checked in this version.
     * @return 
     */
    public boolean check(){
        
        // Check rows
        int i = 0;
        for (char[] row : this.grid) {
            char b = checkUnique(row);
            if (b != ' '){
                System.out.format("Row %d has multiple %s's!%n",i,b);
                return false;
            }
            i++;
        }
        
        // Check columns
        for (i = 0; i < 9; i++){
            char b = checkUnique(getCol(i));
            if (b != ' '){
                System.out.format("Column %d has multiple %s's!%n",i,b);
                return false;
            }
        }
        
        // Check blocks
        for (i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                char b = checkUnique(getBlock(3*i,3*j));
                if (b != ' '){
                    System.out.format("Block at (%d, %d) has multiple %s's!%n",3*i,3*j,b);
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Draw the sudoku in the console at 37x37 characters.
     */
    public void print() {
        //TODO: Implement printing.
        System.out.println(new String(new char[9*4+1]).replace("\0", "#"));
        int count = 1;
        for (char[] row : this.grid) {
            // This ugly piece is supposed to be temporary. Seeing it is a testament to my failure.
            System.out.format("# %s | %s | %s # %s | %s | %s # %s | %s | %s #%n",
                    row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8]);
            
            if (count % 3 != 0){
                System.out.println("#---+---+---#---+---+---#---+---+---#");
            }
            else {
                System.out.println(new String(new char[9*4+1]).replace("\0", "#"));
            }
            count++;
        }
    }
    
    private char[] getCol(int index) {
        int i = 0;
        char[] col = new char[9];
        for (char[] row: this.grid){
            col[i] = row[index];
            i++;
        }
        return col;
    }
    
    private char[] getBlock(int row, int col) {
        char[] block =  new char[9];
        // Performing floor division to inputs to make sure that they're the
        // block's top left corner.
        row -= row%3;
        col -= col%3;
        
        int index = 0;
        for (int i = row; i < row+3;i++){
            for (int j = col; j < col+3; j++){
                block[index] = this.grid[i][j];
                index++;
            }
        }
        
        return block;
    }
    
    /**
     * Check if all elements of the array are either unique or empty.
     * @param sArray
     * @return 
     */
    private char checkUnique(char[] sArray) {
        char[] sortedCopy = Arrays.copyOf(sArray, sArray.length);
        Arrays.sort(sortedCopy);
        char prev = ' ';
        for (char s : sortedCopy) {
            if (s != ' ' & s == prev) {
                return s;
            }
            prev = s;
        }
        return ' ';
    }
}
