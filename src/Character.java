// I, Nick Milanovic, 000292701 certify that this material is my original work.
//    No other person's work has been used without due acknowledgement

public class Character {
    private String characterName;
    private int closenessCount;
    private int proximityCount;
    private double closenessFactor = 0;

    // Character constructor, setting the character name and closeness count
    public Character(String characterName){this.characterName = characterName; closenessCount++;}

    // Getter for the character name
    public String getCharacterName(){return characterName;}

    // Getter for the closeness count
    public int getClosenessCount(){return closenessCount;}

    // Increments the closeness count, and updates the closeness factor
    public void incrementCount(){closenessCount++; updateCloseness();}

    // Increments the proximity count
    public void addToProximityCount(int closenessCount){
        proximityCount += closenessCount;
        updateCloseness();
    }

    // Getter for the proximity count
    public int getProximityCount(){return proximityCount;}

    // Getter for the closeness factor
    public double getClosenessFactor(){return closenessFactor;}

    // Updates the closeness factor
    public void updateCloseness(){
        closenessFactor = (double)proximityCount / (double)closenessCount;
    }

    // Overridden equals method to compare hash codes rather than strings
    @Override
    public boolean equals(Object wordToCompare){
        return characterName.hashCode() == wordToCompare.hashCode();
    }

    // algorithm retrieved from youtube
    // https://www.youtube.com/watch?v=jtMwp0FqEcg&t
    // had to change the hash code from my bookWord class due to contains not being able to notice that
    // the character has already been added to the charList in the main file, this hash ended up working
    @Override
    public int hashCode(){
        int g = 31;
        int hash = 0;
        for (int i = 0; i < characterName.length(); i++) {
            hash = g * hash + characterName.charAt(i);
        }
        return hash;
    }
}
