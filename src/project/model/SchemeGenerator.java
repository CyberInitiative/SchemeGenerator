package project.model;

import java.util.ArrayList;

public class SchemeGenerator {
    ArrayList<Character> negativeInputs = new ArrayList<>();
    ArrayList<Character> positiveInputs = new ArrayList<>();
    ArrayList<Character> theFormula = new ArrayList<>();

    public ArrayList<Character> getNumberOfNegativeInputs(String expression) {
        //ArrayList<String> negativeOperand = new ArrayList<>();
        //ArrayList<Character> convertedString = new ArrayList<>(); //выражение превращенноё список
        for (Character character : expression.toCharArray()) {
            theFormula.add(character);
        }
        for (int i = 0; i < theFormula.size(); i++) {
            if (theFormula.get(i) == '!') {
                if (theFormula.get(i + 1) >= 'A' && theFormula.get(i + 1) <= 'Z') {
                    System.out.println("Got it");
                    //String result = Character.toString(convertedString.get(i));
                    //result += convertedString.get( i + 1);
                    negativeInputs.add(theFormula.get(i + 1));
                    theFormula.set(i, '#');
                    theFormula.set(i + 1, '#');
                }
            }
        }
        theFormula.removeIf(x -> x.equals('#'));
        return theFormula;
    }

    public void getNumberOfPositiveInputs() {
        if (!theFormula.isEmpty()) {
            for (int i = 0; i < theFormula.size(); i++) {
                if (theFormula.get(i) >= 'A' && theFormula.get(i) <= 'Z') {
                    positiveInputs.add(theFormula.get(i));
                }
            }
        } else {
            System.out.println("Error");
        }
    }

}
