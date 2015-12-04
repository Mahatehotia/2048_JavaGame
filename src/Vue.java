import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

import static java.lang.StrictMath.sqrt;

/**
 * Created by evanzyker on 20/11/15.
 */
public class Vue extends JFrame {

    public JLabel[][] grid;
    protected Model model;
    protected int row;
    protected int col;


    public Vue(Model model){
        this.model = model;
        initAttributes(this.model.getGridSize());
        createWidget(this.model.getGridSize());
        placeWidget();
        refresh();
        setSize(new Dimension(400, 400));
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void placeWidget() {
        getContentPane().removeAll();

        JPanel masterPanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(model.getGridSize(), model.getGridSize()));
        for (int r = 0; r < row; r++){
            for (int c = 0; c < col; c++){
                gridPanel.add(grid[r][c]);
            }
        }

        masterPanel.add(gridPanel, BorderLayout.CENTER);
        setContentPane(masterPanel);
        getContentPane().doLayout();
        update(getGraphics());
    }

    private void createWidget(int gridSize) {
        //Initialisation de la génération de la grille
        row = gridSize;
        col = gridSize;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++) {
                grid[r][c] = new JLabel("", SwingConstants.CENTER);
                grid[r][c].setBorder(new LineBorder(Color.BLACK));
            }
        }
    }

    private void initAttributes(int gridSize) {
        grid = new JLabel[gridSize][gridSize];
        //On insère un JLabel dans chaque case du tableau dynamiquement
        for(int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                grid[i][j] = new JLabel(String.valueOf(model.getCase(i, j)), SwingConstants.CENTER);
            }
        }
    }

    public void setControlKey(ControlKey controlkey) {
        this.addKeyListener(controlkey);
    }

    public void refresh(){
        for (int r = 0; r < row; r++){
            for (int c = 0; c < col; c++) {
                grid[r][c].setForeground(Color.BLACK);
                if (model.getCase(r, c) == 0) {
                    grid[r][c].setText("");
                    grid[r][c].setBackground(Color.WHITE);
                } else {
                    grid[r][c].setText(String.valueOf(model.getCase(r, c)));
                }
                if (!grid[r][c].getText().equals("")){
                    switch(Integer.parseInt(grid[r][c].getText())){
                        case 2:
                            grid[r][c].setBackground(Color.WHITE);
                            break;
                        case 4:
                            grid[r][c].setBackground(Color.GRAY);
                            break;
                        case 8:
                            grid[r][c].setBackground(Color.ORANGE);
                            break;
                        case 16:
                            grid[r][c].setBackground(Color.ORANGE.darker());
                            break;
                        case 32:
                            grid[r][c].setBackground(Color.RED);
                            break;
                        case 64:
                            grid[r][c].setBackground(Color.RED.darker());
                            break;
                        case 128:
                            grid[r][c].setBackground(Color.YELLOW);
                            break;
                        case 256:
                            grid[r][c].setBackground(Color.YELLOW.brighter());
                            break;
                        case 512:
                            grid[r][c].setBackground(Color.YELLOW.brighter().brighter());
                            break;
                        case 1024:
                            grid[r][c].setBackground(Color.BLACK.brighter());
                            break;
                        case 2048:
                            grid[r][c].setBackground(Color.BLACK);
                            break;
                        case 4096:
                            grid[r][c].setBackground(Color.BLACK.darker());
                            break;
                        case 8192:
                            grid[r][c].setBackground(Color.BLUE);
                            break;
                    }
                }
                grid[r][c].setOpaque(true);
            }
        }
        if (! model.isPlayable()){
            if (model.hasWon()){
                JOptionPane.showMessageDialog( this, "Vous avez gagné !", "VICTOIRE",
                        JOptionPane.WARNING_MESSAGE );
            }else{
                JOptionPane.showMessageDialog( this, "Vous avez perdu !", "DEFAITE",
                        JOptionPane.WARNING_MESSAGE );
            }
        }
    }

}
