package com.example.k.simpelcalcandroid;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс пересчета строки на токены
 */
class TokensCreator {
    private String expression;
    private StringBuilder temp = new StringBuilder();
    private List<String> tokenList = new ArrayList<>();

    TokensCreator(String stringExpression) {
        this.expression = stringExpression;
        makeTokens(expression);
    }

    /**
     * Переведение строки в список токенов
     *
     */
    private void makeTokens(String expression) {
        char symbol;
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            getTokens(symbol, i);
        }
        for (String token : tokenList) {
            System.out.println(token);
        }
    }

    /**
     * Получение токена
     */
    private void getTokens(char symbol, int index) {
        if (symbol != '/'
                && symbol != '*'
                && symbol != '+'
                && symbol != '-'
                && symbol != '('
                && symbol != ')') {
            temp.append(symbol);
            if (index == expression.length() - 1) {
                tokenList.add(temp.toString());
                temp.setLength(0);
            }
        } else if (symbol == '-') {
            unoOperationsCheck(symbol, index);
        } else if (symbol == '(' || symbol == ')') {
            checkTempFullness();
            checkForHiddenOperations(symbol, index);
        }else {

            checkTempFullness();
            String s = "" + symbol;
            tokenList.add(s);

        }
    }

    List<String> getTokenList() {
        return tokenList;
    }

    /**
     * Проверка на унарные операции (-)
     */
    private void unoOperationsCheck(char symbol, int index) {
        if (index == 0
                || expression.charAt(index - 1) == '/'
                || expression.charAt(index - 1) == '*'
                || expression.charAt(index - 1) == '+'
                || expression.charAt(index - 1) == '(') {
            temp.append(symbol);
        } else {
            tokenList.add(temp.toString());
            temp.setLength(0);
            tokenList.add(temp.append(symbol).toString());
            temp.setLength(0);
        }
    }

    /**
     * Проверка на скрытые операции (*)
     */
    private void checkForHiddenOperations(char symbol, int index) {
        if (index != 0 && index != expression.length() - 1) {

            if (symbol == '(' && expression.charAt(index - 1) != '/'
                    && expression.charAt(index - 1) != '*'
                    && expression.charAt(index - 1) != '+'
                    && expression.charAt(index - 1) != '-'
                    && expression.charAt(index - 1) != ')'
                    && expression.charAt(index - 1) != '(') {
                tokenList.add("*");
                temp.append(symbol);
                tokenList.add(temp.toString());
                temp.setLength(0);

            } else if (symbol == ')' && expression.charAt(index + 1) != '/'
                    && expression.charAt(index + 1) != '*'
                    && expression.charAt(index + 1) != '+'
                    && expression.charAt(index + 1) != '-'
                    && expression.charAt(index + 1) != ')') {
                temp.append(symbol);
                tokenList.add(temp.toString());
                temp.setLength(0);
                tokenList.add("*");

            } else {temp.append(symbol);
                tokenList.add(temp.toString());
                temp.setLength(0);

            }
        } else {
            temp.append(symbol);
            tokenList.add(temp.toString());
            temp.setLength(0);

        }
    }

    private void checkTempFullness() {
        if (temp.length() != 0){
            tokenList.add(temp.toString());
            temp.setLength(0);
        }
    }
}

