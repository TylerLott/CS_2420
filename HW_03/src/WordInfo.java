public class WordInfo implements Comparable<WordInfo>{
    public String word;
    public int moves;
    public String history;
    public int priority;

    public WordInfo(String word, int moves, String history, int priority){
        this.word = word;
        this.moves = moves;
        this.history = history;
        this.priority = priority;
    }

    @Override
    public int compareTo(WordInfo b2){
        if (this.priority > b2.priority){
            return 1;
        } else if (this.priority < b2.priority){
            return -1;
        }
        return 0;
    }

    public String toString(){
        return "Word " + word    + " Moves " +moves  + " History ["+history +"]";
    }


}

