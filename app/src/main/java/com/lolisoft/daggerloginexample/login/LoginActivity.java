package com.lolisoft.daggerloginexample.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lolisoft.daggerloginexample.R;
import com.lolisoft.daggerloginexample.root.App;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    @Inject
    public LoginActivityMVP.Presenter presenter;

    private EditText edtName;
    private EditText edtLastName;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App)getApplication()).getComponent().inject(this);

        edtName = findViewById(R.id.edt_name);
        edtLastName = findViewById(R.id.edt_last_name);
        btnSignIn = findViewById(R.id.btn_sing_in);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               presenter.onLoginButtonClick();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getName() {
        return this.edtName.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return this.edtLastName.getText().toString().trim();
    }

    @Override
    public void showUserNotValid() {
        Toast.makeText(this, R.string.msg_invalid_user, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyField() {
        Toast.makeText(this, R.string.msg_empty_field, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, R.string.msg_user_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setName(String name) {
        this.edtName.setText(name);
    }

    @Override
    public void setLastName(String lastName) {
        this.edtLastName.setText(lastName);
    }
}
