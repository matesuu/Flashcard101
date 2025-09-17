import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            
             // test
            frame.dispose();
            reviewGUI();

        });

        createButton.addActionListener(e -> {
            
            frame.dispose();
            createModifyGUI();
        });

    }

    public void reviewGUI()
    {
        JFrame reviewFrame = new JFrame("Review Flashcards");
        reviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reviewFrame.setLayout(new BorderLayout());
        reviewFrame.setSize(1200, 1200);
        reviewFrame.setResizable(true);
        reviewFrame.setVisible(true);

        JPanel textBoxPanel = new JPanel();
        reviewFrame.add(textBoxPanel, BorderLayout.CENTER);

        JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Select");

        textBoxPanel.add(textBoxLabel);
        textBoxPanel.add(textField);
        textBoxPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e)
        {
            filename = textField.getText().trim();
        
            if(!filename.isEmpty()) 
            {
                set = new FlashcardSet(dir + filename + ".csv");
                System.out.println("loaded flashcard set: " + filename);

                textBoxPanel.remove(textBoxLabel);
                textBoxPanel.remove(textField);
                textBoxPanel.remove(submitButton);
                reviewFrame.revalidate();

                return ;
            } 

            else 
            {
                JOptionPane.showMessageDialog(null, "Please enter a filename: ");
            }
        }
            
        });
        
        
         JPanel reviewPanel = new JPanel(); 
         reviewFrame.add(reviewPanel, BorderLayout.CENTER);

         JButton cardButton = new JButton("Start");
         cardButton.setFont(new Font("Georgia", Font.PLAIN, 24));
         reviewPanel.add(cardButton);

        
        cardButton.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e)
        {
          //  for(int i = 0; i < )
        }
         
    });
    }

    public void createModifyGUI()
    {
        JFrame createFrame = new JFrame("Create and Modify");
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFrame.setLayout(new BorderLayout());
        createFrame.setSize(1200, 1200);
        createFrame.setResizable(true);
        createFrame.setVisible(true);

        JPanel textBoxPanel = new JPanel();
        createFrame.add(textBoxPanel, BorderLayout.CENTER);

        JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Select");

        textBoxPanel.add(textBoxLabel);
        textBoxPanel.add(textField);
        textBoxPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e)
        {
            filename = textField.getText().trim();
        
            if(!filename.isEmpty()) 
            {
                set = new FlashcardSet(dir + filename + ".csv");
                System.out.println("loaded flashcard set: " + filename);

                textBoxPanel.remove(textBoxLabel);
                textBoxPanel.remove(textField);
                textBoxPanel.remove(submitButton);
                createFrame.revalidate();

                return ;
            } 

            else 
            {
                JOptionPane.showMessageDialog(null, "Please enter a filename: ");
            }
        }
            
            });

    }

    public static void main(String[] args)
    {
        new GUI();
    }
}