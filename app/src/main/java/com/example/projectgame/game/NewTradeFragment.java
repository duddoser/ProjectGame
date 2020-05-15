package com.example.projectgame.game;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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

import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;

public class NewTradeFragment extends DialogFragment implements View.OnClickListener{
    View view;
    EditText eT1, eT2;
    Button btnTrade;
    Spinner spinner, spinner2;

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
        onDismiss(getDialog());
    }
}
