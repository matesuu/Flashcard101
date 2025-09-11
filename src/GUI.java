import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GUI
{
    private String filepath = "Cards/cards.txt";
    private FlashcardSet set;

    public GUI()
    {
        JFrame frame = new JFrame("Flashcard101");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setResizable(false);
        frame.setVisible(true);

        set = new FlashcardSet(filepath);

        
    }

    public static void main(String[] args)
    {
        new GUI();
    }
}