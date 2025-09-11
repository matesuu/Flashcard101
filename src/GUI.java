import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.io.File;

public class GUI
{
    private final String dir = "Cards/";
    private String filename = "";
    private FlashcardSet set;

    public GUI()
    {
        JFrame frame = new JFrame("Flashcard101");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setResizable(true);
        frame.setVisible(true);

        JLabel title = new JLabel("Flashcard101", SwingConstants.CENTER);
        frame.add(title, BorderLayout.NORTH);

        JPanel textBoxPanel = new JPanel();
        frame.add(textBoxPanel, BorderLayout. CENTER);

        JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
        JComboBox<String> inputField = new JComboBox<>();
        JButton submitButton = new JButton("Select");

        submitButton.addActionListener(e -> {

            
        });

        textBoxPanel.add(textBoxLabel);
        textBoxPanel.add(inputField);
        textBoxPanel.add(submitButton);

        set = new FlashcardSet(dir + filename);
    }

    public static void main(String[] args)
    {
        new GUI();
    }
}