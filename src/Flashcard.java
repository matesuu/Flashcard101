public class Flashcard
{
    private String term; // term name on flashcard
    private String info; // information about a term

    public Flashcard()
    {
        this.term = "";
        this.info = "";
    }

    public Flashcard(String t, String i)
    {
        this.term = t;
        this.info = i;
    }
}