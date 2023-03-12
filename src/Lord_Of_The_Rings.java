
import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
    I, Nick Milanovic, 000292701 certify that this material is my original work.
    No other person's work has been used without due acknowledgement

    DISCUSSION:

    The contains method in the simpleHashSet is definitely the quickest because the search is happening in only one
    bucket rather than the entire array, this means it will have a time complexity of O(1). The contains method
    in ArrayList is the slowest because it is a sequential search meaning the time complexity is O(n), and the binary
    search in ArrayList is faster than the contains method in ArrayList because it has a time complexity of O(log2(n)).

 */
public class Lord_Of_The_Rings {

    /**
     * The starting point of the application
     */
    public static void main(String[] args)
    {
        //PSEUDOLOGIC FROM CLASS


        SimpleHashSet<String> dictWordsHash = new SimpleHashSet<>();  // WORDS FROM DICTIONARY
        ArrayList<String> dictWordsArray = new ArrayList<>(); // WORDS FROM DICTIONARY
        ArrayList<BookWord> words = new ArrayList<>();  // WORDS FROM LOTR
        ArrayList<BookWord> lotrWords = new ArrayList<>(); // WORDS FROM LOTR AS THEY APPEAR

        int newCount = 0;
        // File is stored in a resources folder in the project
        final String filename = "src/TheLOrdOfTheRings.txt";
        int count = 0;
        try {
            Scanner fin = new Scanner(new File(filename));
            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
            while (fin.hasNext()) {
                // READ IN WORD FROM TEXT FILE
                String fileWord = fin.next().toLowerCase();
                if (fileWord.length() > 0) {
                    count++; // COUNTING HOW MANY WORDS IN THE FILE
                    BookWord word = new BookWord(fileWord);
                    lotrWords.add(word);
                    if (!words.contains(word)) {
                        words.add(word);
                        word.incrementCount();
                    } else {
                        words.get(words.indexOf(word)).incrementCount();
                    }
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // ADD other code after here
        final String dictFile = "src/US.txt";
        try {
            Scanner dict = new Scanner(new File(dictFile));
            while (dict.hasNext()) {
                String dictWord = dict.next().toLowerCase();
                if (dictWord.length() > 0) {
                    dictWordsArray.add(dictWord);
                    dictWordsHash.insert(dictWord);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Providing the number of words in the novel and the number of unique words
        System.out.println("Total number of words in the novel: " + count);
        System.out.println("Total number of unique words in the novel: " + words.size());

        // Sorting the unique words first by most frequent then in alphabetical order
        // using the Comparator interface
        words.sort(Comparator.comparingInt(BookWord::getCount).reversed()
                .thenComparing(BookWord::getText));

        Collections.sort(dictWordsArray, String::compareTo);

        // Providing a list of the 10 most frequent words
        System.out.println("List of the 10 most frequent words: ");
        for(BookWord word : words)
        {
            if(words.indexOf(word) < 10){
                System.out.println(word.toString());
            }
        }

        // Providing a list of the words that appear 64 times
        System.out.println("List of words that appear 64 times: ");
        for(BookWord word : words)
        {
            if(word.getCount() == 64){
                System.out.println(word.toString());
            }
        }
        int arrayListContainsCount = 0;
        int arrayListBinaryCount = 0;
        int hashContainsCount = 0;
        long startTime = System.nanoTime();
        // Utilizing the contains method of ArrayLists to provide the words that are in LOTR but not in the dictionary
        for(BookWord word : words)
        {
            if(!dictWordsArray.contains(word.getText()))
            {
                arrayListContainsCount++;
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Using arrayList contains: There are " + arrayListContainsCount +
                " words in LOTR that are not in the dictionary");
        System.out.println("Time elapsed: " + elapsedTime + " ns");

        // Utilizing the binarySearch method of ArrayLists to provide the words that are in LOTR but not in the dictionary
        startTime = System.nanoTime();
        for(BookWord word : words)
        {
            int indexSearch = Collections.binarySearch(dictWordsArray, word.getText(), String::compareTo);
            if(indexSearch < 0)
            {
                arrayListBinaryCount++;
            }
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Using arrayList binary search: There are " + arrayListBinaryCount +
                " words in LOTR that are not in the dictionary");
        System.out.println("Time elapsed: " + elapsedTime + " ns");

        // Utilizing the contains method of SimpleHashSet to provide the words that are in LOTR but not in the dictionary
        startTime = System.nanoTime();
        for(BookWord word : words)
        {
            if(!dictWordsHash.contains(word.getText()))
            {
                hashContainsCount++;
            }
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Using simpleHashSet contains search: There are " + hashContainsCount +
                " words in LOTR that are not in the dictionary");
        System.out.println("Time elapsed: " + elapsedTime + " ns");

        System.out.println("\n==========PART B============\n");

        startTime = System.nanoTime();
        ArrayList<Character> charList = new ArrayList<>();  // Assigning all the characters to the list

        for(int i=0; i< lotrWords.size(); i++)
        {
            int rings = 0;
            // Iterate over all the words in LOTR to find everytime a character is mentioned
            if(lotrWords.get(i).getText().equals("frodo") || lotrWords.get(i).getText().equals("sam") || lotrWords.get(i).getText().equals("bilbo") ||
                    lotrWords.get(i).getText().equals("gandalf") || lotrWords.get(i).getText().equals("boromir") || lotrWords.get(i).getText().equals("aragorn") ||
                    lotrWords.get(i).getText().equals("legolas") || lotrWords.get(i).getText().equals("gollum") || lotrWords.get(i).getText().equals("pippin") ||
                    lotrWords.get(i).getText().equals("merry") || lotrWords.get(i).getText().equals("gimli") || lotrWords.get(i).getText().equals("sauron") ||
                    lotrWords.get(i).getText().equals("saruman") || lotrWords.get(i).getText().equals("faramir") || lotrWords.get(i).getText().equals("denethor") ||
                    lotrWords.get(i).getText().equals("treebeard") || lotrWords.get(i).getText().equals("elrond") || lotrWords.get(i).getText().equals("galadriel"))
            {
                // Go 42 words back and 42 words forward from when one of the aforementioned characters are found
                // When it is found we will loop through to see if the word "ring" is there.
                int j = i - 42;
                int surroundingWordsLoop = 84;

                while(surroundingWordsLoop > 0)
                {
                    //System.out.println(rings);
                    surroundingWordsLoop--;
                    j++;

                    if(lotrWords.get(j).getText().equals("ring"))
                    {
                        rings++;
                    }
                }
                // Create a new character object that we will add to our characters list
                Character character = new Character(lotrWords.get(i).getText());
                // If the character is already in the list then we will increment the count of how often they show up
                // as well as adding to the proximity count, how many times we see the word "ring" within
                if(charList.contains(character))
                {
                    Character characterVariable = charList.get(charList.indexOf(character));
                    characterVariable.incrementCount();
                    charList.get(charList.indexOf(character)).addToProximityCount(rings);
                }
                else
                {
                    character.addToProximityCount(rings);
                    charList.add(character);
                }
                //If the characterList already contains the current character, then we increment the count.
            }
        }
        // Now that we have all of our characters in the list, we need to sort it based on our closeness attribute
        Collections.sort(charList, new Comparator<Character>() {
            public int compare(Character o1, Character o2) {
                return Double.compare(o2.getClosenessFactor(), o1.getClosenessFactor());
            }
        });

        for(Character character : charList)
        {
            System.out.printf("Name: " + character.getCharacterName() + " Count: " + character.getClosenessCount() +
                    " Proximity: " + character.getProximityCount() + " Closeness: %.4f" + "\n",character.getClosenessFactor());
            //System.out.printf("%.4f", character.getClosenessFactor());
        }
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Elapsed time: " + elapsedTime);

    }
}

