public class Flashcard
{
    private String term; // term name on flashcard
    private String info; // information about a term

    public Flashcard() // constructor for default case
    {
        this.term = "";
        this.info = "";
    }

    public Flashcard(String t, String i) // constructor that sets term and info
    {
        setTerm(t);
        setInfo(i);
    }

    public void setTerm(String t) // sets term
    {
        this.term = t;
    }

    public void setInfo(String i) // sets info
    {
        this.info = i;
    }

    public String getTerm() // returns term as string
    {
        return this.term;
    }

    public String getInfo() // returns info as string
    {
        return this.info;
    }
}