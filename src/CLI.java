import java.util.Scanner;

public class CLI
{
    private FlashcardSet set;

    public CLI()
    {
        System.out.println("Flashcard101 CLI\n");

        set = new FlashcardSet("Cards/cards.csv");
        set.display();
        set.remove("sanad");
        set.saveData("Cards/cards.csv");
        
    }

    public static void main(String[] args)
    {
        new CLI();
    }
}