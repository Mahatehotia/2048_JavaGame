import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by evanzyker on 24/11/15.
 */
public class Model {

    private int[][] grid;
    private int gridSize;
    private int[] valuesCase = {0, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192};
    private boolean playable;
    private Hashtable<Integer, Integer> toWin;



    public Model(int gridSize){
        toWin = new Hashtable<>();
        toWin.put(4, 2048);
        toWin.put(5, 4096);
        toWin.put(6, 8192);
        this.gridSize = gridSize;
        grid = new int[this.gridSize][this.gridSize];
        for (int i = 0; i < this.gridSize; i++){
            for (int j = 0; j < this.gridSize; j++){
                grid[i][j] = 0;
            }
        }
        addOne();
    }

    public int getGridSize() {
        return gridSize;
    }

    public int[] getValuesCase() {
        return valuesCase;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCase(int i, int j, int val){
        grid[i][j] = val;
    }

    public int getCase(int i, int j){
        return grid[i][j];
    }

    public void move(String direction) {
        switch (direction){
            case "up":
                if (moveUp()){
                    addOne();
                }
                break;
            case "down":
                if (moveDown()){
                    addOne();
                }
                break;
            case "right":
                if (moveRight()){
                addOne();
            }
                break;
            case "left":
                if (moveLeft()){
                    addOne();
                }
                break;
        }
    }

    private void addOne() {
        Random rng = new Random();
        int i, j;
        boolean add = false;
        while (!add){
            i = rng.nextInt(gridSize);
            j = rng.nextInt(gridSize);
            if (grid[i][j] == 0){
                int random = rng.nextInt(100);
                if (random < 90){
                    grid[i][j] = 2;
                    add = true;
                }else{
                    grid[i][j] = 4;
                    add = true;
                }
            }
        }
    }

    private boolean moveLeft() {
        boolean still;
        boolean mustAdd = false;
        do{
            still = false;
            for (int r = 0; r < gridSize; r++){
                for (int c = 1; c < gridSize; c++){
                    if (grid[r][c] != 0){
                        if ((grid[r][c - 1] == grid[r][c])|| grid[r][c - 1] == 0){
                            grid[r][c - 1] += grid[r][c];
                            grid[r][c] = 0;
                            still = true;
                            mustAdd = true;
                        }
                    }
                }
            }
        }while(still);
        return mustAdd;
    }

    private boolean moveRight() {
        boolean still;
        boolean mustAdd = false;
        do{
            still = false;
            for (int r = 0; r < gridSize; r++){
                for (int c = 0; c < gridSize - 1; c++){
                    if (grid[r][c] != 0){
                        if ((grid[r][c + 1] == grid[r][c])|| grid[r][c + 1] == 0){
                            grid[r][c + 1] += grid[r][c];
                            grid[r][c] = 0;
                            still = true;
                            mustAdd = true;
                        }
                    }
                }
            }
        }while(still);
        return mustAdd;
    }

    private boolean moveUp() {
        boolean still;
        boolean mustAdd = false;
        do{
            still = false;
            for (int r = 1; r < gridSize; r++){
                for (int c = 0; c < gridSize; c++){
                    if (grid[r][c] != 0){
                        if ((grid[r - 1][c] == grid[r][c])|| grid[r - 1][c] == 0){
                            grid[r - 1][c] += grid[r][c];
                            grid[r][c] = 0;
                            still = true;
                            mustAdd = true;
                        }
                    }
                }
            }
        }while(still);
        return mustAdd;
    }

    private boolean moveDown(){
        boolean still;
        boolean mustAdd = false;
        do{
            still = false;
            for (int r = 0; r < gridSize - 1; r++){
                for (int c = 0; c < gridSize; c++){
                    if (grid[r][c] != 0){
                        if ((grid[r + 1][c] == grid[r][c])|| grid[r + 1][c] == 0){
                            grid[r + 1][c] += grid[r][c];
                            grid[r][c] = 0;
                            still = true;
                            mustAdd = true;
                        }
                    }
                }
            }
        }while (still);
        return mustAdd;
    }

    private boolean checkGrid(){
        for (int r = 0; r < gridSize; r++){
            for (int c = 0; c < gridSize; c++){
                if (grid[r][c] == 0){
                    return false;
                }else if (r - 1 > 0 && grid[r - 1][c] == grid[r][c]){
                    return false;
                }else if (r + 1 < gridSize && grid[r + 1][c] == grid[r][c]){
                    return false;
                }else if (c - 1 > 0 && grid[r][c - 1] == grid[r][c]) {
                    return false;
                }else if (c + 1 < gridSize && grid[r][c + 1] == grid[r][c]){
                    return false;
                }
            }
        }
        return true;
    }

    private void autoSetPlayable(){
        playable = checkGrid();
    }

    public boolean isPlayable(){
        return playable;
    }

    public int getHighest(){
        int highest = 0;
        for (int r = 0; r < gridSize; r++){
            for (int c = 0; c < gridSize; c++){
                if (grid[r][c] > highest){
                    highest = grid[r][c];
                }
            }
        }
        return highest;
    }

    public boolean hasWon(){
        int best = getHighest();
            if (toWin.containsKey(gridSize)){
                while (toWin.keys().hasMoreElements()){
                    int taille = toWin.keys().nextElement();
                    if (gridSize == taille){
                        if (best == toWin.get(taille)){
                            return true;
                        }
                    }
                }
            }
        return false;
    }
}
