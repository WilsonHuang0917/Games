package com.example.wilsonhuang.games.myFragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilsonhuang.games.Games;
import com.example.wilsonhuang.games.R;

public class IndexFragment extends Fragment {
    public IndexFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int position = getArguments().getInt("position");
        String itemTitle = Games.gameNames[position];
        getActivity().setTitle(itemTitle);
        return inflater.inflate(R.layout.fragment_index, container, false);
    }


}
