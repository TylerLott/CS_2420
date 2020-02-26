import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WritePoetry {

    public String WritePoem(String file, String first, int length, boolean print) throws FileNotFoundException {

        // load file
        File poem = new File(file);
        Scanner sc = new Scanner(poem);

        // read all words in file and format
        ArrayList<String> wordsIn = new ArrayList<>();
        int i = 0;
        while (sc.hasNext()) {
            String word = sc.next();
            wordsIn.add(word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase());
            i++;
        }

        // initialize hash table
        HashTable<WordFreqInfo> table = new HashTable<WordFreqInfo>((int)(wordsIn.size() * 1.5));

        // insert all words into hash table
        for (int w=0; w < wordsIn.size(); w++) {
            // find if object is in table
            if (table.contains(wordsIn.get(w))) {
                WordFreqInfo insideAlready = table.find(wordsIn.get(w));
                insideAlready.occurCt++;

                // add followers
                if (w+1 < wordsIn.size()) {

                    for (WordFreqInfo.Freq followers : insideAlready.followList){
                        if (followers.toString().contains(wordsIn.get(w+1)+ " ")){
                            followers.followCt++;
                        }
                    }

                    if (!insideAlready.toString().contains(" " + wordsIn.get(w+1)+ " ")) {
                        WordFreqInfo.Freq newFollow = new WordFreqInfo.Freq(wordsIn.get(w + 1), 1);
                        insideAlready.followList.add(newFollow);
                    }

                } else {
                    WordFreqInfo.Freq newFollow = new WordFreqInfo.Freq(wordsIn.get(0), 1);
                    insideAlready.followList.add(newFollow);
                }
            }
            // if not found add to table
            else {
                WordFreqInfo wordObj = new WordFreqInfo(wordsIn.get(w), 1);
                table.insert(wordsIn.get(w), wordObj);

                // add followers
                WordFreqInfo insideAlready = table.find(wordsIn.get(w));
                if (w+1 < wordsIn.size()) {

                    for (WordFreqInfo.Freq followers : insideAlready.followList){
                        if (followers.toString().contains(wordsIn.get(w+1) + " ")){
                            followers.followCt++;
                        }
                    }

                    if (!insideAlready.toString().contains(" " + wordsIn.get(w+1) + " ")) {
                        WordFreqInfo.Freq newFollow = new WordFreqInfo.Freq(wordsIn.get(w + 1), 1);
                        insideAlready.followList.add(newFollow);
                    }
                } else {
                    WordFreqInfo.Freq newFollow = new WordFreqInfo.Freq(wordsIn.get(0), 1);
                    insideAlready.followList.add(newFollow);
                }
            }
        }

        if (print){ System.out.println(table.toString(1000)); }


        // actually write the poem
        String[] newPoem = new String[length];
        newPoem[0] = first;
        for (int len=0; len<length-1; len++){
            WordFreqInfo word = table.find(newPoem[len]);
            ArrayList<WordFreqInfo.Freq> possible = table.find(newPoem[len]).followList;

            double rand = Math.random();
            double probability = 0;

            if (possible.size() == 0){
                newPoem[len+1] = first;
            }

            for (WordFreqInfo.Freq pos : possible){
                probability += pos.followCt;
                double prob = probability / word.occurCt;
                if (rand <= prob){
                    newPoem[len+1] = pos.follow;
                    break;
                }
            }



        }

        String answer = "";
        for (String words : newPoem){
            answer+= words + " ";
        }
        return "Poem: " + answer;
    }
}
