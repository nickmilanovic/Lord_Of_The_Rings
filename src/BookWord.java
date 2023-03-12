// I, Nick Milanovic, 000292701 certify that this material is my original work.
//    No other person's work has been used without due acknowledgement

public class BookWord {

    private final String text;
    private int count;

    // Constructor for the BookWord which sets the word
    public BookWord(String text){
        this.text = text;
    }

    // Getter for the word
    public String getText(){
        return text;
    }

    // Getter for the current count of each word
    public int getCount(){
        return count;
    }

    // Increment the counter for each word
    public void incrementCount(){ count++; }

    // Overridden equals method to make sure the two words being compared are the same
    @Override
    public boolean equals(Object wordToCompare){
        if(wordToCompare!=null && wordToCompare.getClass().equals(this.getClass())){
            BookWord comparedWord = (BookWord)wordToCompare;
            if(this.text.equals(comparedWord.text))
            {
                return true;
            }
        }
        return false;
    }

    // algorithm retrieved from stack overflow
    // https://stackoverflow.com/questions/2624192/good-hash-function-for-strings
    @Override
    public int hashCode(){
        int hash = 7;
        for (int i = 0; i < text.length(); i++) {
            hash = hash*31 + text.charAt(i);
        }
        return hash;
    }

    // Overridden toString method that will return the book word and its respective count
    @Override
    public String toString(){
        return String.format("Bookword: %s, count: %2d", text, count);
    }
}
