import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class FlashcardSet
{
    private HashMap<String, String> cards = new HashMap<>(); // hashmap to store each term with its associated info
    private ArrayList<String> terms = new ArrayList<>(); // arraylist for storing names of terms as strings for convinience
    private int size = 0; // size of set
    ArrayList<QA> questions = new ArrayList<>(); // Stores only terms (questions)

    public FlashcardSet(String filename) // constructor that reads data from a file
    {
        loadData(filename);
    }

    public int getSize() // returns size
    {
        return terms.size();
    }

    public void display()
    {
        if(size == 0)
        {
            return ;
        }

        else
        {
            for(int i = 0; i < terms.size(); i++)
            {
                System.out.println(terms.get(i));
            }
        }
    }
    
    public void loadData(String filename) // uses a filereader to read data
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = ""; // string to store current line in file

            while((line = reader.readLine()) != null) // if line is not "", then continue loop
            {
                String[] parts = line.split(","); // split the string into term and info (comma acts a delimiter)
                
                add(parts[0].trim(), parts[1].trim()); // NOTE!!!: add(parts[0].trim(), parts[1].trim()) also worked
                size++; // increment size
            }
        }

        catch (IOException e) // if it fails
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
            questions.add(new QA(term, info));
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
        if(cards.containsKey(term))
        {
            cards.put(term, newInfo);
        }
    }

    public String getTerm(int index)
    {
        if(index < 0 || index >= size)
        {
            return null;
        }

        else
        {
            return terms.get(index);
        }
    }

    public String getInfo(String term)
    {
        if(cards.containsKey(term))
        {
            return cards.get(term);
        }

        else
        {
            return null;
        }
    }

    public void shuffle() 
    {
        // Shuffle the order of terms
        Collections.shuffle(terms);
        // Rebuild questions list in the same order
        questions.clear();
        for (String t : terms) 
        {
            String info = cards.get(t);
            questions.add(new QA(t, info));
        }
    }

    public String search(String term) 
    {
        // Simple exact match
        if (cards.containsKey(term)) 
        {
            return cards.get(term);
        }
        // Or partial match: find any terms that contain the query
        StringBuilder results = new StringBuilder();
        for (String t : terms) 
        {
            if (t.toLowerCase().contains(term.toLowerCase())) 
            {
                results.append(t)
                       .append(": ")
                       .append(cards.get(t))
                       .append("\n");
            }
        }
        if (results.length() > 0) 
        {
            return results.toString();
        }
        return null;
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
