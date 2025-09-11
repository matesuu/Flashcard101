import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

        
        reviewButton.addActionListener(e -> {
            
            set = new FlashcardSet("Cards/cards.csv"); // test
            frame.dispose();
            review(set);

        });

        createButton.addActionListener(e -> {
            
            frame.dispose();
            createModify();
        });



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

    public void review(FlashcardSet set)
    {
        int counter = 1;
        JFrame reviewFrame = new JFrame("Review Flashcards");
        reviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reviewFrame.setLayout(new BorderLayout());
        reviewFrame.setSize(1200, 1200);
        reviewFrame.setResizable(true);
        reviewFrame.setVisible(true);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        reviewFrame.add(cardPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        reviewFrame.add(nextButton, BorderLayout.SOUTH);

        JButton counterButton = new JButton("Card " + counter + " of " + set.getSize());
        counterButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        reviewFrame.add(counterButton, BorderLayout.NORTH);

        JButton cardButton = new JButton("");
        cardButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        cardPanel.add(cardButton, BorderLayout.CENTER);

        
        if(set.getSize() <= 0)
        {
            throw new IllegalArgumentException("There are no terms in this set");
        }

        String term = set.getTerm(0);
        cardButton.setText(term);


        for(int i = 0; i < set.getSize(); i++)
        {

            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    cardButton.setText(set.getInfo(term));
                }
            });

            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    cardButton.setText(set.getInfo(term));
                }
            });
        }
    }

    public void createModify()
    {
        
    }

    public static void main(String[] args)
    {
        new GUI();
    }
}