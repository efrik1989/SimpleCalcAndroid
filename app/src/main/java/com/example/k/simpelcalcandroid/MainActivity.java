package com.example.k.simpelcalcandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс основной Активности
 */
public class MainActivity extends AppCompatActivity {
    private final static String TEMP_STATE = "tempstate";
    private final static String TEXT_FIELD_STATE = "textfield";
     
    static TextView sTextView;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    Button mButton7;
    Button mButton8;
    Button mButton9;
    Button mButton0;
    Button mButtonDivision;
    Button mButtonMultiplication;
    Button mButtonAddition;
    Button mButtonSubtraction;
    Button mButtonPoint;
    Button mButtonEqually;
    Button mCancelButton;
    Button mOpenBracketButton;
    Button mCloseBracketButton;
    Button mBackSpace;

    List<Button> buttonList = new ArrayList<>();
    ServiceCalc serviceCalc;

    /**
     * Создание активности
     * @param savedInstanceState сохраненное состояние
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewHandlers();
        serviceCalc = new ServiceCalc();
        setButtonListener(serviceCalc);

        restoreState(savedInstanceState);
    }

    /**
     * Привязка View элементов к коду
     */
    private void viewHandlers() {
        sTextView = findViewById(R.id.editText);
        mButton1 = findViewById(R.id.button1);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mButton5 = findViewById(R.id.button5);
        mButton6 = findViewById(R.id.button6);
        mButton7 = findViewById(R.id.button7);
        mButton8 = findViewById(R.id.button8);
        mButton9 = findViewById(R.id.button9);
        mButton0 = findViewById(R.id.button0);
        mButtonDivision = findViewById(R.id.buttonDivision);
        mButtonMultiplication = findViewById(R.id.buttonMultiplication);
        mButtonAddition = findViewById(R.id.buttonAddition);
        mButtonSubtraction = findViewById(R.id.buttonSubtraction);
        mButtonPoint = findViewById(R.id.buttonPoint);
        mButtonEqually = findViewById(R.id.buttonEqually);
        mCancelButton = findViewById(R.id.cancelButton);
        mOpenBracketButton = findViewById(R.id.openBracketButton);
        mCloseBracketButton = findViewById(R.id.closeBracketButton);
        mBackSpace = findViewById(R.id.backSpace);

        addButtonsToList();
    }

    /**
     *  Добавление кнопок в список
     */
    private void addButtonsToList() {
        buttonList.add(mButton1);
        buttonList.add(mButton2);
        buttonList.add(mButton3);
        buttonList.add(mButton4);
        buttonList.add(mButton5);
        buttonList.add(mButton5);
        buttonList.add(mButton6);
        buttonList.add(mButton7);
        buttonList.add(mButton8);
        buttonList.add(mButton9);
        buttonList.add(mButton0);
        buttonList.add(mButtonDivision);
        buttonList.add(mButtonMultiplication);
        buttonList.add(mButtonAddition);
        buttonList.add(mButtonSubtraction);
        buttonList.add(mButtonPoint);
        buttonList.add(mButtonEqually);
        buttonList.add(mCancelButton);
        buttonList.add(mOpenBracketButton);
        buttonList.add(mCloseBracketButton);
        buttonList.add(mBackSpace);
    }

    /**
     * Назнанчение слушателей на кнопки
     * @param serviceCalc переменная объекта ServiceCalc
     */
    private void setButtonListener(final ServiceCalc serviceCalc) {
        for (final Button button : buttonList) {
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    serviceCalc.textPrepare(button.getText().toString());
                }
            });
        }
    }

    public static TextView getTextView() {
        return sTextView;
    }

    /**
     * Сохранение данных при повороте экрана
     * @param outState сохраняемое состояние
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (serviceCalc.getTempText() != null && getTextView().getText() != null) {
            outState.putString(TEMP_STATE, serviceCalc.getTempText());
            outState.putString(TEXT_FIELD_STATE, getTextView().getText().toString());

            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Восстановление значение сохраненных переменных после поворота
     * @param savedInstanceState сохраенное ранее состояние
     */
    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            serviceCalc.setTempText(savedInstanceState.getString(TEMP_STATE));
            getTextView().setText(savedInstanceState.getString(TEXT_FIELD_STATE));
        }
    }
}

