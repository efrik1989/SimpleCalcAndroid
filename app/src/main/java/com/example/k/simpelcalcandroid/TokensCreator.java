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
    }

    /**
     * Получение токена
     */
    private void getTokens(char symbol, int index) {
        if (symbol != '/' && symbol != '*' && symbol != '+' && symbol != '-' && symbol != '(' && symbol != ')') {
            temp.append(symbol);
            if (index == expression.length() - 1) {
                tokenList.add(temp.toString());
                temp.setLength(0);
            }
        } else if (symbol == '-') {
            unoOperationsCheck(symbol, index);
        } else {

            if (temp.length() != 0) {
                tokenList.add(temp.toString());
                temp.setLength(0);
            }
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
}

