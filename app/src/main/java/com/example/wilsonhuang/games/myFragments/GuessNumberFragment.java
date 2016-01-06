package com.example.wilsonhuang.games.myFragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wilsonhuang.games.Games;
import com.example.wilsonhuang.games.R;

public class GuessNumberFragment extends Fragment {
    private Context context;
    private EditText edit_MinNumber;
    private EditText edit_MaxNumber;
    private EditText edit_MyNumber;
    private Button bt_do_guess;
    private Button bt_do_cancel;
    private Button bt_checkNumber;
    private TextView txt_Result;

    private int maxNumber;
    private int minNumber;
    private int myNumber;
    private int answerNumber;

    public GuessNumberFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        int position = getArguments().getInt("position");
        String itemTitle = Games.gameNames[position];
        getActivity().setTitle(itemTitle);
        return inflater.inflate(R.layout.fragment_guess_number, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        processViews();
        processControllers();
    }

    private void processControllers() {
        bt_do_guess.setOnClickListener(new GuessNumberBtOnClickListener());
        bt_do_cancel.setOnClickListener(new GuessNumberBtOnClickListener());
        bt_checkNumber.setOnClickListener(new GuessNumberBtOnClickListener());
    }

    private void processViews() {
        edit_MaxNumber = (EditText) getView().findViewById(R.id.edit_maxNumber);
        edit_MinNumber = (EditText) getView().findViewById(R.id.edit_minNumber);
        edit_MyNumber = (EditText) getView().findViewById(R.id.edit_myNumber);
        bt_do_cancel = (Button) getView().findViewById(R.id.bt_do_cancel);
        bt_do_guess = (Button) getView().findViewById(R.id.bt_do_guess);
        bt_checkNumber = (Button) getView().findViewById(R.id.bt_checkNumber);
        txt_Result = (TextView) getView().findViewById(R.id.txt_result);
    }

    private class GuessNumberBtOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_checkNumber:
                    try {
                        maxNumber = Integer.parseInt(edit_MaxNumber.getText().toString());
                        minNumber = Integer.parseInt(edit_MinNumber.getText().toString());
                        if (maxNumber > minNumber) {
                            answerNumber = (int) (Math.random() * (maxNumber - minNumber + 1)) + minNumber;
                            edit_MaxNumber.setEnabled(false);
                            edit_MinNumber.setEnabled(false);
                            bt_checkNumber.setEnabled(false);
                            txt_Result.setText("請輸入要猜的數字...");
                        } else {
                            Toast.makeText(context, "最小值必須小於最大值！", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Log.i("debug", e.toString());
                        Toast.makeText(context, "數字範圍最大值或最小值尚未輸入！", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.bt_do_guess:
                    if (!bt_checkNumber.isEnabled()) {
                        try {
                            myNumber = Integer.parseInt(edit_MyNumber.getText().toString());
                            if (myNumber == answerNumber) {
                                txt_Result.setText("恭喜！\n猜中囉！");
                            } else if (myNumber > answerNumber) {
                                txt_Result.setText("猜得太大囉...");
                            } else if (myNumber < answerNumber) {
                                txt_Result.setText("猜得太小囉...");
                            }
                        } catch (Exception e) {
                            Toast.makeText(context, "要猜的數字尚未輸入！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "請先確認數字範圍...", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.bt_do_cancel:
                    edit_MaxNumber.setEnabled(true);
                    edit_MinNumber.setEnabled(true);
                    bt_checkNumber.setEnabled(true);

                    txt_Result.setText("請輸入要猜的數字範圍...");
                    break;

            }


        }
    }
}
