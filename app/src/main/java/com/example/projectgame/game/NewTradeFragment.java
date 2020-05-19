package com.example.projectgame.game;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.projectgame.MediaPlayerInterface;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;
import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

public class NewTradeFragment extends DialogFragment implements View.OnClickListener{
    View view;
    EditText eT1, eT2;
    Button btnTrade;
    Spinner spinner, spinner2;
    GameFragment gameFragment;
    MediaPlayer mP;

    NewTradeFragment(GameFragment gameFragment){
        this.gameFragment = gameFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_new_trade,
                new LinearLayout(getActivity()), false);
        initSpinner();
        initViews();
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.
                graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        ((MediaPlayerInterface) getActivity()).pauseMediaPlayer();
        mP = MediaPlayer.create(getContext(), R.raw.opened_door);
        mP.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mP.start();
        return builder;
    }

    public void initViews(){
        eT1 = view.findViewById(R.id.num);
        eT2 = view.findViewById(R.id.num2);
        btnTrade = view.findViewById(R.id.btnNewTrade); //пренеси все материал листы в отдельный класс
        btnTrade.setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void initSpinner(){
        String[] materials = {"wood", "stone", "iron", "metal"};
        spinner = (Spinner) view.findViewById(R.id.materials);
        spinner2 = (Spinner) view.findViewById(R.id.materials2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, materials);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, materials);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {
        String num1 = eT1.getText().toString();
        String num2 = eT2.getText().toString();
        if (!num1.isEmpty() && !num2.isEmpty()){
            RetrofitProcesses retrofit = new RetrofitProcesses(getActivity());
            SharedPreferences shared = getActivity().
                    getSharedPreferences("loginSettings", Context.MODE_PRIVATE);
            String user_id = shared.getString("USER_ID", "1");

            retrofit.getResources(user_id, new RetrofitProcesses.ResourceCallbacks() {
                @Override
                public void onGetResources(Map<String, Integer> reses) {
                    String res1 = spinner.getSelectedItem().toString();
                    String res2 = spinner2.getSelectedItem().toString();
                    if ((reses.get(res1) >= Integer.parseInt(num1)) &&
                            (Integer.parseInt(num1) >= Integer.parseInt(num2))){ //1 текст. поле - то, что мы отдаем
                        retrofit.changeResource(user_id, res1, -Integer.parseInt(num1));
                        retrofit.changeResource(user_id, res2, Integer.parseInt(num2));
                        gameFragment.setOneResource(res1, -Integer.parseInt(num1));
                        gameFragment.setOneResource(res2, Integer.parseInt(num2));
                    } else {
                        gameFragment.makeSnackbar();
                    }
                }
            });
            onDismiss(getDialog());
        } else {
            Snackbar.make(view, "You can't trade empty string!!", Snackbar.LENGTH_SHORT)
                    .show();
        }

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        ((MediaPlayerInterface) getActivity()).resumeMediaPlayer();
    }
}
