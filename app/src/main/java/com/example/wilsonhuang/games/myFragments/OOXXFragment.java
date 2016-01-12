package com.example.wilsonhuang.games.myFragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.wilsonhuang.games.Games;
import com.example.wilsonhuang.games.OOXXquestions;
import com.example.wilsonhuang.games.Question;
import com.example.wilsonhuang.games.R;

public class OOXXFragment extends Fragment {
    private ImageButton ibt_1, ibt_2, ibt_3, ibt_4, ibt_5, ibt_6, ibt_7, ibt_8, ibt_9;
    private int iBtFlag_1, iBtFlag_2, iBtFlag_3, iBtFlag_4, iBtFlag_5, iBtFlag_6, iBtFlag_7, iBtFlag_8, iBtFlag_9;
    private Button bt_restart;

    private Context context;
    private View view;

    private boolean userAnswer;
    private Question question;
    private int questionsCount;

    private MediaPlayer mediaPlayer;

    public OOXXFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        int position = getArguments().getInt("position");
        String itemTitle = Games.gameNames[position];
        getActivity().setTitle(itemTitle);
        return inflater.inflate(R.layout.fragment_ooxx, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        view = getView();
        initFlag();
        processViews();
        processControllers();
    }

    private void processControllers() {
        ibt_1.setOnClickListener(new OOXXonClickListener());
        ibt_2.setOnClickListener(new OOXXonClickListener());
        ibt_3.setOnClickListener(new OOXXonClickListener());
        ibt_4.setOnClickListener(new OOXXonClickListener());
        ibt_5.setOnClickListener(new OOXXonClickListener());
        ibt_6.setOnClickListener(new OOXXonClickListener());
        ibt_7.setOnClickListener(new OOXXonClickListener());
        ibt_8.setOnClickListener(new OOXXonClickListener());
        ibt_9.setOnClickListener(new OOXXonClickListener());
        bt_restart.setOnClickListener(new OOXXonClickListener());
    }

    private void processViews() {
        ibt_1 = (ImageButton) view.findViewById(R.id.ibt_1);
        ibt_2 = (ImageButton) view.findViewById(R.id.ibt_2);
        ibt_3 = (ImageButton) view.findViewById(R.id.ibt_3);
        ibt_4 = (ImageButton) view.findViewById(R.id.ibt_4);
        ibt_5 = (ImageButton) view.findViewById(R.id.ibt_5);
        ibt_6 = (ImageButton) view.findViewById(R.id.ibt_6);
        ibt_7 = (ImageButton) view.findViewById(R.id.ibt_7);
        ibt_8 = (ImageButton) view.findViewById(R.id.ibt_8);
        ibt_9 = (ImageButton) view.findViewById(R.id.ibt_9);
        bt_restart = (Button) view.findViewById(R.id.bt_restart);
    }

    private class OOXXonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int viewID = v.getId();
            if (viewID == R.id.bt_restart) {
                restart();
            } else {
                showDialog(v.getId());
            }

        }
    }

    private void restart() {
        ibt_1.setEnabled(true);
        ibt_1.setImageResource(R.drawable.question_mark);
        ibt_2.setEnabled(true);
        ibt_2.setImageResource(R.drawable.question_mark);
        ibt_3.setEnabled(true);
        ibt_3.setImageResource(R.drawable.question_mark);
        ibt_4.setEnabled(true);
        ibt_4.setImageResource(R.drawable.question_mark);
        ibt_5.setEnabled(true);
        ibt_5.setImageResource(R.drawable.question_mark);
        ibt_6.setEnabled(true);
        ibt_6.setImageResource(R.drawable.question_mark);
        ibt_7.setEnabled(true);
        ibt_7.setImageResource(R.drawable.question_mark);
        ibt_8.setEnabled(true);
        ibt_8.setImageResource(R.drawable.question_mark);
        ibt_9.setEnabled(true);
        ibt_9.setImageResource(R.drawable.question_mark);
        initFlag();
    }

    private void initFlag() {
        iBtFlag_1 = 0;
        iBtFlag_2 = 0;
        iBtFlag_3 = 0;
        iBtFlag_4 = 0;
        iBtFlag_5 = 0;
        iBtFlag_6 = 0;
        iBtFlag_7 = 0;
        iBtFlag_8 = 0;
        iBtFlag_9 = 0;
    }

    private void showDialog(final int viewID) {
        questionsCount = OOXXquestions.questionsList.size();
        question = OOXXquestions.questionsList.get((int) (Math.random() * questionsCount));

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(question.getQuestionContent());

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userAnswer = true;
                changImage(viewID);
                winOrLose();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userAnswer = false;
                changImage(viewID);
                winOrLose();
            }
        });

        builder.setCancelable(false);
        builder.create().show();
    }

    private void changImage(int viewID) {
        ImageButton iBt = (ImageButton) view.findViewById(viewID);
        if (question.isAnswer() == userAnswer) {
            iBt.setImageResource(R.drawable.round);
            setFlag(viewID, 1);
        } else {
            iBt.setImageResource(R.drawable.skewer);
            setFlag(viewID, -1);
        }
        iBt.setEnabled(false);
    }

    private void setFlag(int viewID, int flag) {
        switch (viewID) {
            case R.id.ibt_1:
                iBtFlag_1 = flag;
                break;
            case R.id.ibt_2:
                iBtFlag_2 = flag;
                break;
            case R.id.ibt_3:
                iBtFlag_3 = flag;
                break;
            case R.id.ibt_4:
                iBtFlag_4 = flag;
                break;
            case R.id.ibt_5:
                iBtFlag_5 = flag;
                break;
            case R.id.ibt_6:
                iBtFlag_6 = flag;
                break;
            case R.id.ibt_7:
                iBtFlag_7 = flag;
                break;
            case R.id.ibt_8:
                iBtFlag_8 = flag;
                break;
            case R.id.ibt_9:
                iBtFlag_9 = flag;
                break;
        }
    }

    private void winOrLose() {
        int count1 = iBtFlag_1 + iBtFlag_2 + iBtFlag_3;
        int count2 = iBtFlag_4 + iBtFlag_5 + iBtFlag_6;
        int count3 = iBtFlag_7 + iBtFlag_8 + iBtFlag_9;
        int count4 = iBtFlag_1 + iBtFlag_4 + iBtFlag_7;
        int count5 = iBtFlag_2 + iBtFlag_5 + iBtFlag_8;
        int count6 = iBtFlag_3 + iBtFlag_6 + iBtFlag_9;
        int count7 = iBtFlag_1 + iBtFlag_5 + iBtFlag_9;
        int count8 = iBtFlag_3 + iBtFlag_5 + iBtFlag_7;

        if (count1 == 3 || count2 == 3 || count3 == 3 || count4 == 3 || count5 == 3 || count6 == 3 || count7 == 3 || count8 == 3) {
            gameOver("您真是中肯之人！！");
            myMediaPlayer(R.raw.applause);
        } else if (count1 == -3 || count2 == -3 || count3 == -3 || count4 == -3 || count5 == -3 || count6 == -3 || count7 == -3 || count8 == -3) {
            gameOver("痾....");
            myMediaPlayer(R.raw.guffaw);
        }
    }

    private void myMediaPlayer(int id) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(context, R.raw.applause);
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gameOver(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restart();
            }
        });
        builder.create().show();
    }

    @Override
    public void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onPause();
    }
}
