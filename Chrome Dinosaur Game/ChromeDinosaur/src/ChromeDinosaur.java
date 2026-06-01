import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChromeDinosaur extends JPanel {
    private int boardWidth = 850;
    private int boardHeight = 250;

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
    }
}
