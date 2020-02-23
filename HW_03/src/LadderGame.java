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
        ArrayList list;
        ArrayList<String> l = allList[a.length()];
        list = (ArrayList) l.clone();
        System.out.println("Seeking a solution from " + a + " ->" + b + " Size of List " + list.size());
        
        // Solve the word ladder problem

        // check to see both words are in dictionary
        if (!list.contains(a))
            System.out.println("Word " + a + " was not found in the dictionary.");
        if (!list.contains(b))
            System.out.println("Word " + b + " was not found in the dictionary.");

        // initialize the linked list
        WordInfo beginning = new WordInfo(a, 0, "", 0);
        AVLTree<WordInfo> q  = new AVLTree<WordInfo>();
        q.insert(beginning);

        // create iterating and answer variables
        boolean done = false;
        WordInfo answer = null;
        int enqueues = 0;
        int numOfWords = 0;
        boolean exists = false;

        // actual logic and iteration of the program
        while (!q.isEmpty() && !done) {

            // get the smallest(closest to being done) of priority queue
            WordInfo start = q.findMin();

            // deletes that smallest node off of the AVL tree
            q.deleteMin();

            // iterate through the words in the list
            for (Object word : list) {

                // iterate total number of words read from the dictionary
                numOfWords++;

                // check that the word is one letter off the word info object that is being checked
                // also check that the word is not part of already used words
                if (offByOne(start.word, (String) word)) {

                    // display all words one away from the node word
//                    System.out.println(word);

                    // calculates the priority of the word that is one off
                    int pri = start.moves * 2 + (int)Math.pow(numberOffBy((String) word, b), 2);

                    // if the word is the final word then end
                    if (b.equals(word)) {
                        String hist = start.history + " " + start.word;
                        int moves = start.moves + 1;
                        WordInfo step = new WordInfo((String) word, moves, hist, pri);
                        done = true;
                        answer = step;
                        exists = true;
                        break;
                    } else {
                        // add to the enqueues and add a new word info object to the queue

                        if (!start.history.contains((String) word)) {
                            enqueues++;
                            String hist = start.history + " " + start.word;
                            int moves = start.moves + 1;
                            WordInfo step = new WordInfo((String) word, moves, hist, pri);
                            q.insert(step);
//                            System.out.println(q.findMin().toString());
//                            q.printTree("tree");
                        }

                        // display current tree
//                        q.printTree("tree");

                    }
                }
            }
        }

        // output the information from the program
        if (exists) {
            String output = a + "->" + b + "  " + answer.moves + " Moves ["
                    + answer.history + " " + answer.word
                    + "] \ntotal enqueues " + enqueues + "  \nTotal number of words read: " + numOfWords + "\n";
            System.out.println(output);
        } else {
            System.out.println("No ladder connecting " + a + " to " + b + " exists. \nTotal number of words read: " + numOfWords + "\n");
        }
    }

    public void play(int len) {
       if (len >= MaxWordSize) return;
        ArrayList<String> list = allList[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play(a, b);

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

    public int numberOffBy(String a, String b){
        int count = 0;
        for (int i=0; i<a.length();i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public void listWords(int numWords, int numLetters){
        ArrayList list;
        ArrayList<String> l = allList[numLetters];
        list = (ArrayList) l.clone();
        for (int i=0;i<numWords;i++) {
            System.out.println(list.get(i));
        }
    }

}
