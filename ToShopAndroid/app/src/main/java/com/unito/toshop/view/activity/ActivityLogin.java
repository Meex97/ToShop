package com.unito.toshop.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.unito.toshop.Application;
import com.unito.toshop.R;

public class ActivityLogin extends AppCompatActivity {

    private EditText email, password;
    private Button login, logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initComponents();
        this.setActions();
    }

    private void initComponents() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);
        logout = findViewById(R.id.buttonLogout);
    }

    private void setActions() {
        login.setOnClickListener(Application.getInstance().getLoginControl().getActionLogin());
        logout.setOnClickListener(Application.getInstance().getLoginControl().getActionLogout());
    }

    @Override
    public void onBackPressed() {
        showActivityHome();
    }

    public void showActivityHome() {
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);
        finish();
    }

    public String getEmail() {
        return email.getText().toString();
    }

    public String getPassword() {
        return password.getText().toString();
    }

    public void setErrorEmail() {
        email.setError("Email not valid!");
    }

    public void setErrorPassword() {
        password.setError("Password must be at least 3 characters long!");
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
