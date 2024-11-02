import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    JFrame frame = new JFrame("TicTacToe");
    JLabel header = new JLabel();
    JButton[][] cells = new JButton[3][3];
    JPanel board = new JPanel();

    String playerX = "X";
    String playerY = "Y";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int usedCells = 0;

    TicTacToe(){

        frame.setSize(600,650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        header.setText("TicTacToe");
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial",Font.BOLD,24));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setOpaque(true);
        frame.add(header,BorderLayout.NORTH);

        board.setLayout(new GridLayout(3,3));
        board.setBackground(Color.BLACK);
        frame.add(board,BorderLayout.CENTER);
        fillCells(cells);

        frame.setVisible(true);
    }

    void fillCells(JButton[][] array){
        for(int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                JButton cell = new JButton();
                array[r][c] = cell;
                board.add(cell);
                cell.setBackground(Color.BLACK);
                cell.setForeground(Color.WHITE);
                cell.setFont(new Font("Arial",Font.BOLD,100));
                cell.setFocusable(false);

                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(cell.getText().equals("")){
                            if(gameOver) return;
                            cell.setText(currentPlayer);
                            usedCells++;
                            checkWinner();
                            if(!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerY : playerX;
                                header.setText(currentPlayer + "'s turn");
                            }
                        }

                    }
                });
            }
        }
    }

    void checkWinner(){
        //horizontally
        for (int r = 0; r < 3; r++){
            if(cells[r][0].getText() == cells[r][1].getText() &&
                    cells[r][1].getText() == cells[r][2].getText() &&
                    cells[r][0].getText() != ""){
                for(int c = 0; c < 3; c++) {
                    setWinner(cells[r][c]);
                }
                header.setText(currentPlayer+ " won");
                gameOver = true;
                return;
            }
        }

        //vertically
        for(int c = 0; c < 3; c++){
            if(cells[0][c].getText() == cells[1][c].getText() &&
                    cells[1][c].getText() == cells[2][c].getText() &&
                    cells[0][c].getText() != ""){

                for (int r = 0; r < 3; r++){
                    setWinner(cells[r][c]);
                }

                header.setText(currentPlayer+ " won");
                gameOver = true;
                return;
            }
        }

        //diagonally
        if(cells[0][0].getText() == cells[1][1].getText() &&
                cells[1][1].getText() == cells[2][2].getText() &&
                cells[0][0].getText() != ""){

            for(int i = 0; i < 3; i++){
                setWinner(cells[i][i]);
            }

            header.setText(currentPlayer+ " won");
            gameOver = true;
            return;
        }

        //reverse diagonally
        if(cells[0][2].getText() == cells[1][1].getText() &&
                cells[1][1].getText() == cells[2][0].getText() &&
                cells[0][2].getText() != ""){

            setWinner(cells[0][2]);
            setWinner(cells[1][1]);
            setWinner(cells[2][0]);

            header.setText(currentPlayer+ " won");
            gameOver = true;
            return;
        }

        if(usedCells == 9){
            for (JButton[] row:cells) {
                for (JButton cell:row) {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.DARK_GRAY);
                }
            }

            header.setText("Stalemate");
            gameOver = true;
        }
    }

    void setWinner(JButton cell){
        cell.setBackground(Color.YELLOW);
        cell.setForeground(Color.BLACK);
    }

}
