import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class FlashcardSet
{
    private HashMap<String, String> cards = new HashMap<>();
    private ArrayList<String> terms = new ArrayList<>();

    public FlashcardSet(String filename)
    {
        loadData(filename);
    }

    public void loadData(String filename)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = "";

            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                
                if(!cards.containsKey(parts[0].trim()))
                {
                    cards.put(parts[0].trim(), parts[1].trim());
                    terms.add(parts[0].trim());
                }
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
    }
    
    public void add(String term, String info)
    {
        if(!cards.containsKey(term))
        {
            cards.put(term, info);
            terms.add(term);
        }
    }

    public void remove(String term)
    {
        if(cards.containsKey(term))
        {
            cards.remove(term);
            terms.remove(term);
        }
    }

    public void edit(String term, String newInfo)
    {
        if(cards.containsKey(terms))
        {
            cards.put(term, newInfo);
        }
    }

    public void display()
    {
        for(int i = 0; i < terms.size(); i++)
        {
            System.out.println(terms.get(i) + ": " + cards.get(terms.get(i)));
        }

        System.out.print("\n");
    }

    public void saveData(String filename)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for(int i = 0; i < terms.size(); i++)
            {
                String line = terms.get(i) + "," + cards.get(terms.get(i)) + "\n";
                writer.write(line);
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
    }
}
