import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

class Ticktacktoe extends JComponent {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_0 = 100;
    int[][] field;
    boolean isTurn;

    public Ticktacktoe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        startGame();
    }

    public void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        isTurn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            if (field[i][j] == FIELD_EMPTY) {
                field[i][j] = isTurn ? FIELD_X : FIELD_0;
                isTurn = !isTurn;
                repaint();
                int cS = checkState();
                if (cS != 0){
                    if (cS == FIELD_X * 3) {
                        JOptionPane.showMessageDialog(this, "X won!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (cS == FIELD_0 * 3){
                        JOptionPane.showMessageDialog(this, "0 won!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Draw!", "Draw!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    startGame();
                    repaint();
                }
            }
        }
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLUE);
        int gridw = getWidth() / 3;
        int gridh = getHeight() / 3;
        int x = i * gridw;
        int y = j * gridh;
        graphics.drawLine(x, y, x + gridw, y + gridh);
        graphics.drawLine(x, y + gridh, x + gridw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.RED);
        int gridw = getWidth() / 3;
        int gridh = getHeight() / 3;
        int x = i * gridw;
        int y = j * gridh;
        graphics.drawOval(x + 5 * gridw / 100, y, gridw * 9 / 10, gridh);
    }

    void drawX0(Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == FIELD_0) {
                    draw0(i, j, graphics);
                }
            }
        }
    }

    int checkState() {
        int cross = 0;
        int cross1 = 0;
        for (int i = 0; i < 3; i++) {
            cross += field[i][i];
            cross1 += field[i][2 - i];
        }
        if (cross == FIELD_0 * 3 || cross == FIELD_X * 3) {
            return cross;
        }
        if (cross1 == FIELD_0 * 3 || cross1 == FIELD_X * 3) {
            return cross1;
        }
        int checki, checkj;
        boolean empty = false;
        for (int i = 0; i < 3; i++){
            checki = 0;
            checkj = 0;
            for (int j = 0; j < 3; j++){
                if (field[i][j] == 0){
                    empty = true;
                }
                checki += field[i][j];
                checkj += field[j][i];
            }
            if (checki == FIELD_X * 3 || checki == FIELD_0 * 3){
                return checki;
            }
            if (checkj == FIELD_X * 3 || checkj == FIELD_0 * 3){
                return checkj;
            }
        }
        if (empty){
            return 0;
        }
        else{
            return -1;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.clearRect(0,0,getWidth(),getHeight());
        drawGrid(graphics);
        drawX0(graphics);
    }
    void drawGrid(Graphics graphics){
        int width = getWidth();
        int height = getHeight();
        int gridw = width/3;
        int gridh = height/3;
        graphics.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++){
            graphics.drawLine(0, gridh * i, width, gridh * i);
            graphics.drawLine(gridw * i, 0, gridw * i, height);
        }
    }
}

public class TictactoeMain {
    public static void main(String args[]) {
        JFrame window = new JFrame("TicTacToe");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Ticktacktoe game = new Ticktacktoe();
        window.add(game);
    }
}