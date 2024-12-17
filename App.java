import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    public JButton[][] buttons = new JButton[3][3];
    public boolean xTurn = true;
    int drawCount = 0;

    public App() {
        setTitle("Hopefully this works");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLayout(new GridLayout(3,3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(this);
                Font font = buttons[i][j].getFont();
                buttons[i][j].setFont(new Font(font.getName(), font.getStyle(), 90));
                
                add(buttons[i][j]);


            }
        }
        setVisible(true);
        
    }

    public void winLabel(String x) {
        getContentPane().removeAll();
        setLayout(new GridLayout(1,1)); 
        JLabel winMessage;
        if (x.equals("draw")) {
            winMessage = new JLabel("Its A Draw");
        } else {
            winMessage = new JLabel(x + " Wins!");
        }
        winMessage.setFont(winMessage.getFont().deriveFont(100f));
        winMessage.setVerticalAlignment(SwingConstants.CENTER);
        winMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(winMessage);
        revalidate();
        repaint();
    }
    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                return true;
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[0][0].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[0][2].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            if (xTurn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            xTurn = !xTurn;
            drawCount++;
            if (drawCount == 9) {
                winLabel("draw");
            }
        } else {
            return;
        }
        if (checkWin()) {
            if (xTurn) {
                winLabel("O");
            } else {
                winLabel("X");
            }
        }

    }   


    public static void main(String[] args) {
        new App();
    }
}
    
    