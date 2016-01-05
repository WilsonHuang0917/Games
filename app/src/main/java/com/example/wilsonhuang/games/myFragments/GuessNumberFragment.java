package com.example.wilsonhuang.games.myFragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilsonhuang.games.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuessNumberFragment extends Fragment {


    public GuessNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guess_number, container, false);
    }


}
