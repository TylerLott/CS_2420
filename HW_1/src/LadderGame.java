import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int MaxWordSize = 15;
    ArrayList<String>[] allList;  // Array of ArrayLists of words of each length.
    Random random;

    public LadderGame(String file) {
        random = new Random();
        allList = new ArrayList[MaxWordSize];
        for (int i = 0; i < MaxWordSize; i++)
            allList[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length()< MaxWordSize) allList[word.length()].add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void play(String a, String b) {

        if (a.length() != b.length()){
            System.out.println("Words are not the same length");
            return;
         }
        if (a.length()  >= MaxWordSize) return;
        ArrayList list = new ArrayList();
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());
        
        // Solve the word ladder problem

        // initialize the linked list
        WordInfo beginning = new WordInfo(a, 0, "");
        LinkedList q  = new LinkedList(beginning);

        // create an arraylist to contain all of the used words
        ArrayList<String> usedWords = new ArrayList<>();

        // create iterating and answer variables
        boolean done = false;
        WordInfo answer = null;
        int enqueues = 0;

        // actual logic and iteration of the program
        while (!done) {

            // get the top of linked list queue
            WordInfo start = (WordInfo) q.dequeue().getData();

            // iterate through the words in the list
            for (Object word : list) {

                // check that the word is one letter off the word info object that is being checked
                // also check that the word is not part of already used words
                if (!usedWords.contains(word) && offByOne(start.word, (String) word)) {

                    // if the word is the final word then end
                    if (b.equals(word)){
                        String hist = start.history + " " + start.word;
                        int moves = start.moves + 1;
                        WordInfo step = new WordInfo((String) word, moves, hist);
                        done = true;
                        answer = step;
                        System.out.println(step);
                        break;
                    } else {
                        // add to the enqueues and add a new word info object to the queue
                        enqueues++;
                        usedWords.add((String) word);
                        String hist =  start.history + " " + start.word;
                        int moves = start.moves + 1;
                        WordInfo step = new WordInfo((String) word, moves, hist);
                        q.add(step);
                    }
                }
            }
        }

        // output the information from the program
        String output = a + "->" + b + "  " + answer.moves + " Moves [" + answer.history + "] total enqueues " + enqueues;
        System.out.println(output);

    }

    private boolean offByOne(String a, String b){
        // count the number of characters in the word and compare them, if off by one then return true
        int count = 0;
        for (int i=0; i<a.length();i++){
            if(a.charAt(i) == b.charAt(i)){
                count++;
            }
        }
        return count == (a.length() - 1);
    }

    public void play(int len) {
       if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play(a, b);

    }

}
