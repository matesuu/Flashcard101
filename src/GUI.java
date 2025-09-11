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
        frame.setSize(1200, 1200);
        frame.setResizable(true);
        frame.setVisible(true);

        JLabel title = new JLabel("Flashcard101: An Interactive Flashcard Application", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.PLAIN, 36));
        frame.add(title, BorderLayout.NORTH);

        JLabel credits = new JLabel("Created by Mateo Alado, Amreen Ahmed, Sanad Atia, Ohenewaa Ampem Darko, and Twinkle Johnson - Fall 2025", SwingConstants.CENTER);
        credits.setFont(new Font("Georgia", Font.PLAIN, 28));
        frame.add(credits, BorderLayout.SOUTH);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new FlowLayout());
        frame.add(reviewPanel, BorderLayout.EAST);

        JPanel createPanel = new JPanel();
        createPanel.setLayout(new FlowLayout());
        frame.add(createPanel, BorderLayout.WEST);

        JButton reviewButton = new JButton("Review Flashcards");
        reviewButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        reviewButton.setPreferredSize(new Dimension(600, 700));
        reviewPanel.add(reviewButton, BorderLayout.CENTER);
        
        JButton createButton = new JButton("Create and Modify");
        createButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        createButton.setPreferredSize(new Dimension(600, 700));
        createPanel.add(createButton, BorderLayout.CENTER);

        /*JPanel textBoxPanel = new JPanel();
        frame.add(textBoxPanel, BorderLayout. CENTER);

        JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
        JComboBox<String> inputField = new JComboBox<>();
        JButton submitButton = new JButton("Select");

        submitButton.addActionListener(e -> {

            
        });

        textBoxPanel.add(textBoxLabel);
        textBoxPanel.add(inputField);
        textBoxPanel.add(submitButton);

        set = new FlashcardSet(dir + filename + ".csv");

        */
    }

    public static void main(String[] args)
    {
        new GUI();
    }
}