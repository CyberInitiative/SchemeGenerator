package project.model;

import java.util.ArrayList;
import java.util.Arrays;

public class FormulaDecoder {
    //RPN_Interpreter interpreter = new RPN_Interpreter();

    public boolean isOperand(String operand) {
        return operand.matches("[A-Z]+");
    }

    public boolean isOperandAdvanced(String advOperand) {
        boolean check = false;
        String[] stringArray = advOperand.split("");
        for (int i = 0; i < stringArray.length; i++) {
            if (isOperand(stringArray[i])) {
                check = true;
            }
        }
        return check;
    }

    public ArrayList<String> decoding(String interpretedExpr) {
        String[] stringArray = interpretedExpr.split("");
        ArrayList<String> decodingList = new ArrayList<>();
        decodingList.addAll(Arrays.asList(stringArray));

        ArrayList<String> decodedOperations = new ArrayList<>();

        for (int i = 0; i < decodingList.size(); i++) {
            System.out.println("Кол-во элементов в массиве: " + decodingList.size());
            System.out.println("Массив: " + decodingList);
            //System.out.println(decodingList.get(i));

            if (decodingList.get(i).equals("!")) {
                String[] array = new String[decodingList.size()];
                array = decodingList.toArray(array);
                for (int j = i; j-- > 0; ) {
                    if (isOperandAdvanced(array[j])) {
                        String temp;
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                        if(array[i].length() > 1) {
                            array[j] =  array[j] + "(" + array[i] + ")";
                            array[i] = "#";
                            decodedOperations.remove(decodedOperations.size()-1);
                        }
                        else {
                            array[j] = array[j] + array[i];
                            array[i] = "#";
                        }
                        decodedOperations.add(array[j]);
                        break;
                    }
                }
                decodingList.clear();
                for (String string : array) {
                    decodingList.add(string);
                }
                for (int b = 0; b < array.length; b++) {
                    decodingList.removeIf(x -> x.equals("#"));
                }
                i = 0;
            }

            if (decodingList.get(i).equals("*") || decodingList.get(i).equals("+")) {
                String[] array = new String[decodingList.size()];
                array = decodingList.toArray(array);
                if (array.length > 2) {
                    if (isOperandAdvanced(array[i - 1])) {
                        if (isOperandAdvanced(array[i - 2])) {
                            String temp;
                            temp = array[i - 1];
                            array[i - 1] = array[i];
                            array[i] = temp;
                            array[i] = array[i - 2] + array[i - 1] + array[i];
                            array[i - 2] = "#";
                            array[i - 1] = "#";

                            decodedOperations.add(array[i]);
                        }
                    }
                }
                decodingList.clear();

                for (String string : array) {
                    decodingList.add(string);
                }

                for (int b = 0; b < array.length; b++) {
                    decodingList.removeIf(x -> x.equals("#"));
                }

                i = 0;
            }
        }

        return decodedOperations;
    }
}