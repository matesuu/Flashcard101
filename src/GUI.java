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

        
        reviewButton.addActionListener(e -> {
            
            set = new FlashcardSet("Cards/cards.csv"); // test
            frame.dispose();
            review();

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
        JFrame reviewFrame = new JFrame("Review Flashcards");
        reviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reviewFrame.setLayout(new BorderLayout());
        reviewFrame.setSize(1200, 1200);
        reviewFrame.setResizable(true);
        reviewFrame.setVisible(true);

        boolean CLICK_FLAG = false;
        boolean NEXT_FLAG = false;
        int index = 0;

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        reviewFrame.add(cardPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        cardPanel.add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            
            

        });

        while(index < set.size());
        {
            FLIP_FLAG = false;
            NEXT_FLAG = false;
            String term = set.getTerm(index);
            String info = set.getInfo(term);

            if(term == null)
            {
                throw new IllegalArgumentException("term does not exist");
            }

            if(info == null)
            {
                throw new IllegalArgumentException("info does not exist");
            }
            
            JButton termButton = new JButton(term);
            termButton.setFont(new Font("Georgia", Font.PLAIN, 36));
            reviewFrame.add(termButton, BorderLayout.CENTER);

            nextButton.addActionListener(e -> {
            
                FLIP_FLAG = true;

            });

            while(!FLIP_FLAG)
            {
                termButton.addActionListener(e-> {
                    
                    

                })
            }
            
            index++;
        }

        JButton termButton = new JButton()
        
    }

    public void createModify()
    {
        
    }

    public static void main(String[] args)
    {
        new GUI();
    }
}