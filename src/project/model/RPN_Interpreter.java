package project.model;

import java.util.ArrayList;

public class RPN_Interpreter {
    public ArrayList<Character> operationStack = new ArrayList<>(); //массив операций
    int openBracketCount = 0;
    int closeBracketCount = 0;

    public String parse(String expression) {
        ArrayList<Character> output = new ArrayList<>(); //строка вывода
        ArrayList<Character> convertedString = new ArrayList<>(); //выражение превращенноё список
        for (Character character : expression.toCharArray()) {
            convertedString.add(character);
        }

        for (int i = 0; i < convertedString.size(); i++) {
            //System.out.println("Итерация №" + i + ", Массив опреаций: " + operationStack);
            //System.out.println("Элемент формулы на итерации: " + i + " " + convertedString.get(i));
            if ((convertedString.get(i) >= 'A' && convertedString.get(i) <= 'Z')) {
                output.add(convertedString.get(i));
            }

            if (convertedString.get(i) == '+') {
                if (!operationStack.isEmpty()) {

                    if (operationStack.get(operationStack.size() - 1) == '*') {

                        Character[] array = new Character[operationStack.size()];
                        array = operationStack.toArray(array);
                        output.add(array[array.length - 1]);
                        array[array.length - 1] = '#';
                        if (array.length > 1) {
                            if (array[array.length - 2] == '+') {
                                output.add(array[array.length - 2]);
                                array[array.length - 2] = '#';
                            }
                        }

                        operationStack.clear();
                        for (Character ch : array) {
                            operationStack.add(ch);
                        }
                        for (int b = 0; b < operationStack.size(); b++) {
                            operationStack.removeIf(x -> x.equals('#'));
                        }
                        operationStack.add(operationStack.size(), convertedString.get(i));

                    } else if (operationStack.get(operationStack.size() - 1) == '!') {
                        Character[] array = new Character[operationStack.size()];
                        array = operationStack.toArray(array);
                        output.add(array[array.length - 1]);
                        array[array.length - 1] = '#';
                        if (array.length > 1) {
                            if (array[array.length - 2] == '*') {
                                output.add(array[array.length - 2]);
                                array[array.length - 2] = '#';
                                if (array.length > 2) {
                                    if (array[array.length - 3] == '+') {
                                        output.add(array[array.length - 3]);
                                        array[array.length - 3] = '#';
                                    }
                                }
                            }
                            if (array[array.length - 2] == '+') {
                                output.add(array[array.length - 2]);
                                array[array.length - 2] = '#';
                            }
                        }

                        operationStack.clear();
                        for (Character ch : array) {
                            operationStack.add(ch);
                        }
                        for (int b = 0; b < operationStack.size(); b++) {
                            operationStack.removeIf(x -> x.equals('#'));
                        }
                        operationStack.add(operationStack.size(), convertedString.get(i));

                    } else if (operationStack.get(operationStack.size() - 1) == '+') {
                        output.add(operationStack.get(operationStack.size() - 1));
                        operationStack.remove(operationStack.get(operationStack.size() - 1));
                        operationStack.add(convertedString.get(i));
                    } else {
                        operationStack.add(convertedString.get(i));
                    }
                } else {
                    operationStack.add(convertedString.get(i));
                }
            }

            if (convertedString.get(i) == '*') {
                if (!operationStack.isEmpty()) {
                    if (operationStack.get(operationStack.size() - 1) == '!') {
                        Character[] array = new Character[operationStack.size()];
                        array = operationStack.toArray(array);
                        output.add(array[array.length - 1]);
                        array[array.length - 1] = '#';
                        if (array.length > 1) {
                            if (array[array.length - 2] == '*') {
                                output.add(array[array.length - 2]);
                                array[array.length - 2] = '#';
                            }
                        }

                        operationStack.clear();
                        for (Character ch : array) {
                            operationStack.add(ch);
                        }
                        for (int b = 0; b < operationStack.size(); b++) {
                            operationStack.removeIf(x -> x.equals('#'));
                        }
                        operationStack.add(operationStack.size(), convertedString.get(i));
                    } else if (operationStack.get(operationStack.size() - 1) == '*') {
                        output.add(operationStack.get(operationStack.size() - 1));
                        operationStack.remove(operationStack.get(operationStack.size() - 1));
                        operationStack.add(convertedString.get(i));
                    } else {
                        operationStack.add(convertedString.get(i));
                    }
                } else {
                    operationStack.add(convertedString.get(i));
                }
            }

            if (convertedString.get(i) == '!') {
                if (convertedString.get(0) != '!') {
                    if (((convertedString.get(i - 1)) >= 'A' && (convertedString.get(i - 1)) <= 'Z') && ((convertedString.get(i + 1)) >= 'A' && (convertedString.get(i + 1)) <= 'Z')) {
                        if (operationStack.get(operationStack.size() - 1) == '*') {
                            output.add(operationStack.get(operationStack.size() - 1));
                            operationStack.remove(operationStack.size() - 1);
                            operationStack.add(0, '!');
                            operationStack.add(0, '*');
                        } else {
                            operationStack.add('*');
                            operationStack.add('!');
                        }
                    }
                }
                if (!operationStack.isEmpty()) {
                    if (operationStack.get(operationStack.size() - 1) == '!') {
                        output.add(operationStack.get(0));
                        operationStack.remove(0);
                        operationStack.add(0, convertedString.get(i));
                    } else {
                        operationStack.add(convertedString.get(i));
                    }
                } else {
                    operationStack.add(convertedString.get(i));
                }
            }

            if (convertedString.get(i) == '(') {
                operationStack.add(convertedString.get(i));
            }

            if (convertedString.get(i) == ')') {
                Character[] array = new Character[operationStack.size()];
                array = operationStack.toArray(array);
                for (int j = array.length; j-- > 0; ) {
                    if (array[j] == '(') {
                        array[j] = '#';
                        break;
                    } else {
                        output.add(array[j]);
                        array[j] = '#';
                    }
                }
                operationStack.clear();
                for (Character ch : array) {
                    operationStack.add(ch);
                }
                for (int b = 0; b < operationStack.size(); b++) {
                    operationStack.removeIf(x -> x.equals('#'));
                }
            }

            //если достигнут конец функции, выводим все оставшиеся операции из массива в выходную строку
            if (i + 1 == convertedString.size()) {
                for (int w = operationStack.size() - 1; w >= 0; w--) {
                    output.add(operationStack.get(w));
                }
                for (int c = 0; c < operationStack.size(); c++) {
                    operationStack.remove(operationStack.get(c));
                }
            }
        }

        if (openBracketCount != closeBracketCount) {
            System.out.println("Вы ввели неверное количество скобок");
        }


        StringBuilder result = new StringBuilder(output.size());
        for (Character c : output) {
            result.append(c);
        }
        return result.toString();
    }
}