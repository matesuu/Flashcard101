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
    
    public void createModifyGUI() {
        JFrame createFrame = new JFrame("Create and Modify");
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFrame.setSize(800, 600);
        createFrame.setResizable(true);
    
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        createFrame.add(mainPanel);
    
        JPanel textBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Select");
    
        textBoxPanel.add(textBoxLabel);
        textBoxPanel.add(textField);
        textBoxPanel.add(submitButton);
    
        JPanel flashcardPanel = reviewFlashcardUI();
    
        mainPanel.add(textBoxPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // spacing
        flashcardPanel.setVisible(false);
        mainPanel.add(flashcardPanel);

        submitButton.addActionListener(e -> {
            String filename = textField.getText().trim();
            if (!filename.isEmpty()) {
                set = new FlashcardSet(dir + filename + ".csv");
                System.out.println("Loaded flashcard set: " + filename);
                createFrame.revalidate();
                flashcardPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a filename: ");
            }
        });
    
        createFrame.setVisible(true);
    }
    
    public JPanel reviewFlashcardUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 240));
        
        // NEW: progress label at the top
        JLabel progressLabel = new JLabel("0 / 0", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Georgia", Font.PLAIN, 16));
        progressLabel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        mainPanel.add(progressLabel, BorderLayout.NORTH);

        JLabel cardLabel = new JLabel("Your Card Appears Here", SwingConstants.CENTER);
        cardLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        mainPanel.add(cardLabel, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel(new FlowLayout());
        Dimension buttonSize = new Dimension(120, 50);

        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.setPreferredSize(buttonSize);

        JButton prevButton = new JButton("Previous");
        prevButton.setPreferredSize(buttonSize);
    
        JButton flipButton = new JButton("Flip");
        flipButton.setPreferredSize(buttonSize);
    
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(buttonSize);
    
        buttonPanel.add(shuffleButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(flipButton);
        buttonPanel.add(nextButton);
    
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
     
        final int[] currentIndex = {0};
        final boolean[] showQuestion = {true};
    
        Runnable updateCard = () -> {
            if (set != null && !set.questions.isEmpty()) {
                QA current = set.questions.get(currentIndex[0]);
                cardLabel.setText(showQuestion[0] ? current.getQuestion() : current.getAnswer());
                //Update the progress label
                progressLabel.setText((currentIndex[0] + 1) + " / " + set.questions.size());
            } else {
             // cardLabel.setText("No cards available");
              progressLabel.setText("0 / 0");
            }
        };
    
        updateCard.run();
    
            shuffleButton.addActionListener(e -> {
        if (set != null && !set.questions.isEmpty()) {
            set.shuffle();
            currentIndex[0] = 0;
            showQuestion[0] = true;
            updateCard.run();
        }
        });
        
        prevButton.addActionListener(e -> {
            if (set != null && !set.questions.isEmpty()) {
                currentIndex[0] = (currentIndex[0] - 1 + set.questions.size()) % set.questions.size();
                showQuestion[0] = true; // show question when moving
                updateCard.run();
            }
        });
    
        nextButton.addActionListener(e -> {
            if (set != null && !set.questions.isEmpty()) {
                currentIndex[0] = (currentIndex[0] + 1) % set.questions.size();
                showQuestion[0] = true;
                updateCard.run();
            }
        });
    
        flipButton.addActionListener(e -> {
            if (set != null && !set.questions.isEmpty()) {
                showQuestion[0] = !showQuestion[0];
                updateCard.run();
            }
        });
        
        // Keyboard shortcuts: Left=Prev, Right=Next, Space=Flip
        InputMap im = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = mainPanel.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "prev");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "next");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "flip");

        am.put("prev", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (set != null && !set.questions.isEmpty()) {
                    currentIndex[0] = (currentIndex[0] - 1 + set.questions.size()) % set.questions.size();
                    showQuestion[0] = true;
                    updateCard.run();
                }
            }
        });
        am.put("next", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (set != null && !set.questions.isEmpty()) {
                    currentIndex[0] = (currentIndex[0] + 1) % set.questions.size();
                    showQuestion[0] = true;
                    updateCard.run();
                }
            }
        });
        am.put("flip", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                if (set != null && !set.questions.isEmpty()) {
                    showQuestion[0] = !showQuestion[0];
                    updateCard.run();
                }
            }
        }); 
        return mainPanel;
    } 

    public static void main(String[] args)
    {
        new GUI();
    }
}