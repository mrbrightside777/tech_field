package com.example.halcyonjuly7.weekend1project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.halcyonjuly7.weekend1project.Adapters.CalcGridAdapter;
import com.example.halcyonjuly7.weekend1project.Misc.Calculator;

import java.util.EmptyStackException;

public class CalculatorFragment extends Fragment implements View.OnClickListener {

    String[] buttons = new String[] {
            "clear", "c", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "(", ")", "0", "="
    };

    EditText expression_text;
    TextView expression_answer;
    Calculator calculator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        CalcGridAdapter grid_adapter = new CalcGridAdapter(this.getContext(), buttons);

        View view = inflater.inflate(R.layout.calc_frag_layout, container, false);
        expression_text = view.findViewById(R.id.expression_text);
        expression_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });

        expression_answer = view.findViewById(R.id.expression_answer);
        calculator = new Calculator();
        GridView grid_view = view.findViewById(R.id.calc_grid);
        grid_view.setAdapter(grid_adapter);
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (buttons[i]) {
                    case "c":
                        int pos = expression_text.getSelectionStart();
                        if (pos > 0)
                            expression_text.getText().delete(pos - 1, pos).toString();
                        break;
                    case "=":
                        try {
                            Integer answer = calculator.solve(expression_text.getText().toString());
                            expression_answer.setText(Integer.toString(answer));
                        } catch (EmptyStackException e) {
                            expression_answer.setText("E");
                        }

                        break;
                    case "clear":
                        expression_text.setText("");
                        break;

                    default:
                        expression_text.getText().insert(expression_text.getSelectionStart(), buttons[i]);
                        break;
                }

            }
        });
        return view;
    }


    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "c":
                int pos = expression_text.getSelectionStart();
                if (pos > 0)
                    expression_text.getText().delete(pos - 1, pos).toString();
                break;
            case "=":
                try {
                    Integer answer = calculator.solve(expression_text.getText().toString());
                    expression_answer.setText(Integer.toString(answer));
                } catch (EmptyStackException e) {
                    expression_answer.setText("E");
                }

                break;
            case "clear":
                expression_text.setText("");
                break;

            default:
                expression_text.getText().insert(expression_text.getSelectionStart(), button.getText().toString());
                break;
        }
    }

}
