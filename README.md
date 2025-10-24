# Flashcard 101 (Group 1)
# Group 1's Project Codebase


To run our project from the Command Line:

1. Download and Install the latest version of Java from the Oracle website

2. use git clone to clone our project repo into a desired folder:

    git clone https://github.com/matesuu/Flashcard101.git

3. Type the following into the Command Line while in the /src folder:

    javac Flashcard.java FlashcardSet.java GUI.java

or alternatively if you run macOS:

    chmod +x make.sh
    ./make.sh


4. Type the following into the command line to start the execution of the applicaton:

    java GUI

5. Once the GUI opens:
- In the text box labeled **"Enter a Flashcard Set"**, you can type either **Cards** or **ComputerBasics**
- Click **Select** to load the chosen flashcard set.
- Then click **Start** to begin studying your flashcards.

Tip: You can add new CSV files to the `src/Cards` folder to create your own custom flashcard sets!
