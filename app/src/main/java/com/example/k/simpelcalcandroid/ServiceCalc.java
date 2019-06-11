package com.example.k.simpelcalcandroid;

/**
 * Класс отвечающий за взаимодействие с логикой просчетов и поведением кнопок
 */
class ServiceCalc {
    private StringBuilder mTempText;

    ServiceCalc(){
    }

    /**
     * Подготовка поступающих символов и Создание объекта Calc + логика кнопок
     */
    void textPrepare(String symbolOfExpression) {
        if (symbolOfExpression != null) {
            if (mTempText == null) {
                mTempText = new StringBuilder();
            }else checkIncorrectTeg();
            if (!symbolOfExpression.equals("=")
                    && !symbolOfExpression.equals("C")
                    && !symbolOfExpression.equals("B")) {
                checkPointPlacement(symbolOfExpression);
                checkOperator(symbolOfExpression);
                mTempText.append(symbolOfExpression);
                checkBracketsNeighbour();
                MainActivity.getTextView().setText(mTempText.toString());
            } else if (symbolOfExpression.equals("C")) {
                cancel();
            } else if (symbolOfExpression.equals("B")) {
                backSpaceButtonLogic();
            }
            else {
                if (mTempText.length() != 0) {
                    Calc calc = new Calc();
                    String result = calc.calculate(mTempText.toString());
                    MainActivity.getTextView().setText(result);
                    setTempText(MainActivity.getTextView().getText().toString());
                }
            }
        }
        System.out.println("String mTempText = " + mTempText);
    }

    /**
     * Очистка поля вывода и временых переменных
     */
    private void cancel(){
        mTempText.setLength(0);
        MainActivity.getTextView().setText(mTempText);
    }

    /**
     * Действия при нажатии backSpace ("B")
     */
    private void backSpaceButtonLogic() {
        if (mTempText.length() > 0) {
            mTempText.deleteCharAt(mTempText.length() - 1);
            MainActivity.getTextView().setText(mTempText);
        }
    }

    public String getTempText() {
        if (mTempText != null) {
            return mTempText.toString();
        }
        return "0";
    }

    public void setTempText(String tempText) {
        mTempText = new StringBuilder(tempText);
    }

    /**
     * Проверка на то, чтоб открывающая и закрывающая скобки рядом не стояли
     */
    private void checkBracketsNeighbour() {
        if (mTempText.charAt(mTempText.length() - 1) == ')') {
            if (mTempText.length() != 1) {
                if (mTempText.charAt(mTempText.length() - 2) == '(') {
                    mTempText.setLength(mTempText.length() - 2);
                }
            }else {
                mTempText.setLength(mTempText.length() - 1);
            }
        }
    }

    /**
     * Проверка чтоб знаки операции друг за другом не стояли
     * @param symbolOfExpression поступающий символ
     */
    private void checkOperator(String symbolOfExpression) {
        if (mTempText.length() != 0) {
            if (symbolOfExpression.matches("[/*+-]")) {
                if (mTempText.charAt(mTempText.length() - 1) == '/'
                        || mTempText.charAt(mTempText.length() - 1) == '*'
                        || mTempText.charAt(mTempText.length() - 1) == '-'
                        || mTempText.charAt(mTempText.length() - 1) == '+') {

                    mTempText.setLength(mTempText.length() - 1);
                }
            }
        }
    }

    /**
     * Проверка на повторяющуюся точку ('.')
     * @param symbolOfExpression поступающий символ
     */
    private void checkPointPlacement(String symbolOfExpression) {
        if (mTempText.length() != 0) {
            if (symbolOfExpression.matches(".")) {
                if (mTempText.charAt(mTempText.length() - 1) == '.') {

                    mTempText.setLength(mTempText.length() - 1);
                }
            }
        }
    }

    /**
     * Убирание фразы о неверном выражении из текстового поля
     */
    private void checkIncorrectTeg() {
        if (MainActivity.getTextView().getText().equals("Неверно введено выражение")) {
            cancel();
        }
    }
}

