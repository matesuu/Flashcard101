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
    private int size = 0;

    public FlashcardSet(String filename)
    {
        loadData(filename);
    }

    public int getSize()
    {
        return size;
    }
    
    public void loadData(String filename)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = "";

            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                
                add(parts[0].trim(), parts[1].trim());
                size++;
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

        size++;
    }

    public void remove(String term)
    {
        if(cards.containsKey(term))
        {
            cards.remove(term);
            terms.remove(term);
        }

        size--;
    }

    public void edit(String term, String newInfo)
    {
        if(cards.containsKey(terms))
        {
            cards.put(term, newInfo);
        }
    }

    public String displayTerm(int index)
    {
        if(index < 0 || index >= size)
        {
            return null;
        }

        else
        {
            System.out.println(terms.get(index));
            return terms.get(index);
        }
    }

    public void displayInfo(String term)
    {
        if(cards.containsKey(term))
        {
            System.out.println(cards.get(term));
        }
    }

    public void randomize()
    {
        // implement later
    }

    public void search(String term)
    {
        // implement later
    }

    public void saveData(String filename)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for(int i = 0; i < terms.size(); i++)
            {
                if(i < terms.size() - 1)
                {
                    String line = terms.get(i) + "," + cards.get(terms.get(i)) + "\n";
                    writer.write(line);
                }

                else
                {
                    String line = terms.get(i) + "," + cards.get(terms.get(i));
                    writer.write(line);
                }
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return ;
        }
    }
}
