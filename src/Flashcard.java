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
        setTerm(t);
        setInfo(i);
    }

    public void setTerm(String t)
    {
        this.term = t;
    }

    public void setInfo(String i)
    {
        this.info = i;
    }

    public String getTerm()
    {
        return this.term;
    }

    public String getInfo()
    {
        return this.info;
    }
}