package com.example.todoapp;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.animation.Animator;



public class  Login extends AppCompatActivity {

    private Button LoginButton;
    private TextInputLayout Email;
    private TextInputEditText EmailEditText;
    private TextInputLayout Password;
    private TextInputEditText PasswordEditText;
    private ProgressBar Progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        LoginButton = (Button) findViewById(R.id.login_button);
        LoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (validateEmail() && validatePassword()) {
                    Progressbar = findViewById(R.id.progressbar);
                    ValueAnimator animator = ValueAnimator.ofInt(0, Progressbar.getMax());
                    animator.setDuration(3000);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Progressbar.setProgress((Integer) animation.getAnimatedValue());
                        }
                    });
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);

                            startActivity(new Intent(Login.this, Todo.class));
                            finish();
                        }
                    });
                    animator.start();


                }
                updateOkButtonState();
            }
        });

        Email = (TextInputLayout) findViewById(R.id.email);

        EmailEditText = findViewById(R.id.email_edit_text);
        EmailEditText.addTextChangedListener(emailTextWatcher);

        Password = (TextInputLayout) findViewById(R.id.password);

        PasswordEditText = findViewById(R.id.password_edit_text);
        PasswordEditText.addTextChangedListener(passwordTextWatcher);
    }

    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateEmail();
            updateOkButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // ignore
        }
    };

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // ignore
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validatePassword();
            updateOkButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // ignore
        }
    };

    private Boolean validatePassword(){
        String val=Password.getEditText().getText().toString();
        String passwordPattern = "[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]";
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty!");
            return false;
        } else if (!val.matches(passwordPattern)) {
            Password.setError("Password should be digits!");
            return false;
        } else {
            Password.setError(null);
            return true;
        }

    }


    private Boolean validateEmail() {
        String val = Email.getEditText().getText().toString();
        if(val.isEmpty()){
            Email.setError("Field cannot be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            Email.setError("Invalid email address");
            return false;
        }else{
            Email.setError(null);
            return true;
        }


    }
    private void updateOkButtonState() {
        boolean isEnabled = validateEmail() && validatePassword();
        LoginButton.setEnabled(isEnabled);
    }
}