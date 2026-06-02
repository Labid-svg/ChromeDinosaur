import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 850;
        int boardHeight = 350;

        JFrame frame = new JFrame("Chrome Dinosaur");
        frame.setSize(boardWidth, boardHeight);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
