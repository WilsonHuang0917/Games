package com.example.wilsonhuang.games.myFragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wilsonhuang.games.Games;
import com.example.wilsonhuang.games.R;

public class GuessNumberFragment extends Fragment {
    private Context context;
    private EditText edit_MinNumber;
    private EditText edit_MaxNumber;
    private EditText edit_MyNumber;
    private Button bt_do_guess;
    private Button bt_do_cancel;
    private TextView txt_Result;

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

    }
}
