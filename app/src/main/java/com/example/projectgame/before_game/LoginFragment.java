package com.example.projectgame.before_game;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.projectgame.Consts;
import com.example.projectgame.NavigationHost;
import com.example.projectgame.OnBackPressedListener;
import com.example.projectgame.R;
import com.example.projectgame.RetrofitProcesses;


/* Fragment where new user can be added, or sign in*/
public class LoginFragment extends Fragment implements View.OnClickListener, OnBackPressedListener {
    private View view;
    private Button btnLogin, btnCancel, btnSignin;
    private EditText username, password, email;
    private SharedPreferences sharedPreferences;
    public Consts consts;
    public RetrofitProcesses retrofitProcesses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.login_button);
        btnCancel = view.findViewById(R.id.cancel_button);
        btnSignin = view.findViewById(R.id.btnAuth);

        retrofitProcesses = new RetrofitProcesses(getActivity());
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSignin.setOnClickListener(this);

        username = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel){
            onBackPressed();
        } else if (v == btnLogin){
            String name = username.getText().toString();
            String pasw = password.getText().toString();
            String mail = email.getText().toString();
            if (!name.isEmpty() && !pasw.isEmpty() && !mail.isEmpty()){
               retrofitProcesses.loginProcess(name, pasw, mail, view);
            }
        } else if (v == btnSignin){
            ((NavigationHost) getActivity()).navigateTo(new Sign_inFragment(), true);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }
}
