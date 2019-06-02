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
            }
            if (!symbolOfExpression.equals("=")
                    && !symbolOfExpression.equals("C")
                    && !symbolOfExpression.equals("B")) {
                mTempText.append(symbolOfExpression);
                MainActivity.getTextView().setText(mTempText.toString());
            } else if (symbolOfExpression.equals("C")) {
                cancel();
            } else if (symbolOfExpression.equals("B")) {
                backSpaceButtonLogic();
            }
            else {
                Calc calc = new Calc();
                MainActivity.getTextView().setText(calc.calculate(mTempText.toString()));
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
}
