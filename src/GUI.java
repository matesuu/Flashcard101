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
import javax.swing.border.EmptyBorder;

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
        // frame.setSize(1200, 1200);
        frame.setResizable(true);

        JLabel title = new JLabel("Flashcard101: An Interactive Flashcard Application", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.PLAIN, 36));
        frame.add(title, BorderLayout.NORTH);

        JLabel credits = new JLabel(
        "Created by Mateo Alado, Amreen Ahmed, Sanad Atia, Ohenewaa Ampem Darko, and Twinkle Johnson - Fall 2025",
        SwingConstants.CENTER);
        credits.setFont(new Font("Georgia", Font.PLAIN, 28));
        frame.add(credits, BorderLayout.SOUTH);

        JButton reviewButton = new JButton("Review Flashcards");
        reviewButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        reviewButton.setPreferredSize(new Dimension(400, 240));
        reviewButton.setFocusPainted(false);

        JButton createButton = new JButton("Create and Modify");
        createButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        createButton.setPreferredSize(new Dimension(400, 240));
        createButton.setFocusPainted(false);

        // ✅ Make them exactly the same size
        Dimension sameSize = reviewButton.getPreferredSize();
        createButton.setPreferredSize(sameSize);

        // ✅ Place them side by side, centered
        JPanel mainButtons = new JPanel(new GridLayout(1, 2, 16, 16));
        mainButtons.add(reviewButton);
        mainButtons.add(createButton);
        mainButtons.setBorder(new EmptyBorder(24, 24, 24, 24)); // add padding
        frame.add(mainButtons, BorderLayout.CENTER);
    
        reviewButton.addActionListener(e -> {
            
             // test
            frame.dispose();
            reviewGUI();

        });

        createButton.addActionListener(e -> {
            
            frame.dispose();
            createModifyGUI();
        });
        frame.pack(); // size window based on its contents
        frame.setLocationRelativeTo(null); // center the window
        frame.setVisible(true);  
    }
    private java.io.File resolveSetFile(String baseName) {
    java.io.File f1 = new java.io.File("Cards/" + baseName + ".csv");   // run from project root
    if (f1.exists()) return f1;
    java.io.File f2 = new java.io.File("../Cards/" + baseName + ".csv"); // run from src/
    if (f2.exists()) return f2;
    return null;
    }   
    public void reviewGUI() {
    JFrame reviewFrame = new JFrame("Review Flashcards");
    reviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    reviewFrame.setLayout(new BorderLayout());
    reviewFrame.setResizable(true);

    // --- top: load a set ---
    JPanel textBoxPanel = new JPanel();
    JLabel textBoxLabel = new JLabel("Enter a Flashcard Set: ");
    JTextField textField = new JTextField(20);
    JButton submitButton = new JButton("Select");
    textBoxPanel.add(textBoxLabel);
    textBoxPanel.add(textField);
    textBoxPanel.add(submitButton);
    reviewFrame.add(textBoxPanel, BorderLayout.NORTH);

    // --- center: Start button ---
    JPanel reviewPanel = new JPanel();
    JButton cardButton = new JButton("Start");
    cardButton.setFont(new Font("Georgia", Font.PLAIN, 24));
    cardButton.setEnabled(false);              // disabled until a valid set loads
    reviewPanel.add(cardButton);
    reviewFrame.add(reviewPanel, BorderLayout.CENTER);

    reviewFrame.pack();
    reviewFrame.setLocationRelativeTo(null);
    reviewFrame.setVisible(true);

    // Try to resolve file from project root or from src/ (see helper below)
    submitButton.addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
            String name = textField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(reviewFrame, "Please enter a filename.");
                return;
            }
            java.io.File f = resolveSetFile(name);          // <— uses helper below
            System.out.println("user.dir = " + System.getProperty("user.dir"));
            if (f == null) {
                JOptionPane.showMessageDialog(
                    reviewFrame,
                    "File not found:\n" +
                    "• Cards/" + name + ".csv\n" +
                    "• ../Cards/" + name + ".csv"
                );
                return;
            }

            FlashcardSet loaded = new FlashcardSet(f.getPath());
            if (loaded.getSize() <= 0) {
                JOptionPane.showMessageDialog(reviewFrame, "That file has no cards.");
                return;
            }

            filename = name;
            set = loaded;
            System.out.println("Loaded flashcard set: " + f.getPath());

            textBoxPanel.remove(textBoxLabel);
            textBoxPanel.remove(textField);
            textBoxPanel.remove(submitButton);
            reviewFrame.revalidate();
            reviewFrame.repaint();

            cardButton.setEnabled(true);
        }
    });

    cardButton.addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
            if (set == null || set.questions.isEmpty()) {
                JOptionPane.showMessageDialog(reviewFrame, "Load a flashcard set first.");
                return;
            }
            JFrame reviewWindow = new JFrame("Review: " + filename);
            reviewWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            reviewWindow.setLayout(new BorderLayout());
            reviewWindow.add(reviewFlashcardUI(), BorderLayout.CENTER);
            reviewWindow.pack();
            reviewWindow.setLocationRelativeTo(null);
            reviewWindow.setVisible(true);
        }
    });
}

    
    public void createModifyGUI() {
        JFrame createFrame = new JFrame("Create and Modify");
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // createFrame.setSize(800, 600);
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

        createFrame.pack();                     
        createFrame.setLocationRelativeTo(null);
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
            try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception ignore) {}
        new GUI();
    }
}