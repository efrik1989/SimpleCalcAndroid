package com.example.k.simpelcalcandroid;

import java.util.HashMap;

/**
 * Класс описывающий логику программы
 */
class Calc {
    private Stack numbersStack = new Stack();
    private Stack operatorsStack = new Stack();
    private String[] operatorsArray = {"/", "*", "-", "+"};

    Calc() {
    }

    /**
     * Создание карты приоритетов
     *
     * @return объект типа Map c приоритетами в виде значений
     */
    private HashMap<String, Integer> createPriorityMap() {
        HashMap<String, Integer> map = new HashMap<>();

        for (String operator : operatorsArray) {
            if (operator.equals("/") || operator.equals("*")) {
                map.put(operator, 2);
            } else if (operator.equals("-") || operator.equals("+")) {
                map.put(operator, 1);
            }
        }

        return map;
    }

    /**
     * Логика вычислений
     *
     * @param expression выражение
     * @return результат всего вычислений
     */
    String calculate(String expression) {
        TokensCreator tokensCreator = new TokensCreator(expression);
        HashMap<String, Integer> priorityMap = createPriorityMap();

        //Перебор токенов
        for (String token : tokensCreator.getTokenList()) {
            if (token.matches("[/*+-]")) {
                binaryOperatorsConditions(token, priorityMap);
            } else if (token.matches("[()]")) {
                bracketsConditions(token);
            } else numbersStack.addToStack(token);
        }

        while (!operatorsStack.IsEmpty()) {
            numbersStack.addToStack(doAction());
        }

        return checkNumbersAfterPoint((String) numbersStack.getTokenFromStack());

    }

    /**
     * Основная калькуляция
     */
    private String doAction() {
        double result = 0.0;
        String tempOperator = (String) operatorsStack.getTokenFromStack();
        double num1, num2;
        num2 = Double.parseDouble((String) numbersStack.getTokenFromStack());
        numbersStack.remove();
        num1 = Double.parseDouble((String) numbersStack.getTokenFromStack());
        numbersStack.remove();

        switch (tempOperator) {
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "+":
                result = num1 + num2;
                break;
        }
        operatorsStack.remove();

        return result + "";
    }

    /**
     * Условия совершения бинарных операций
     *
     * @param token список токенов
     * @param priorityMap карту приоритетов
     */
    private void binaryOperatorsConditions(String token, HashMap<String, Integer> priorityMap) {
        if (operatorsStack.IsEmpty()) {
            operatorsStack.addToStack(token);

        } else if (operatorsStack.getTokenFromStack().equals("(")
                || priorityMap.get(token) > priorityMap.get(operatorsStack.getTokenFromStack())) {
            operatorsStack.addToStack(token);

        } else if (priorityMap.get(token) <= priorityMap.get(operatorsStack.getTokenFromStack())) {

            while (!operatorsStack.IsEmpty()
                    && !operatorsStack.getTokenFromStack().equals("(")
                    && priorityMap.get(token)
                    <= priorityMap.get(operatorsStack.getTokenFromStack())) {
                numbersStack.addToStack(doAction());
            }
            operatorsStack.addToStack(token);

        }
    }

    /**
     * Действия при скобка
     */
    private void bracketsConditions(String token) {
        switch (token) {
            case "(":
                operatorsStack.addToStack(token);
                break;
            case ")":
                if (operatorsStack.getTokenFromStack().equals("(")) {
                    operatorsStack.remove();
                } else while (!operatorsStack.getTokenFromStack().equals("(")) {
                    numbersStack.addToStack(doAction());
                }
                operatorsStack.remove();
                break;
        }
    }

    /**
     * Проверка на знаки после плавающей точки и убирание плавающей точки, если число целое
     *
     * @return число после проверки
     */
    private String checkNumbersAfterPoint(String resultOfExpression) {
        int result;
        int countAfterPoint = 0;
        int indexOfPoint = 0;
        char symbol;
        boolean point = false;

        for (int i = 0; i < resultOfExpression.length(); i++) {
            symbol = resultOfExpression.charAt(i);
            if (symbol == '.') {
                indexOfPoint = i;
                point = true;
            }
            if (indexOfPoint != i && indexOfPoint < i && point) {
                countAfterPoint++;
            }
        }

        if (countAfterPoint == 1) {
            result = (int) Double.parseDouble(resultOfExpression);
        } else return resultOfExpression;

        return result + "";
    }
}



