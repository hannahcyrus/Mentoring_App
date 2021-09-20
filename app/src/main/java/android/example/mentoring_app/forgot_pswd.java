package android.example.mentoring_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_pswd extends AppCompatActivity {
    private EditText emailFrgt;
    private Button reset;
    FirebaseAuth appAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pswd);
        emailFrgt=(EditText)findViewById(R.id.emailForgot);
        reset=(Button)findViewById(R.id.reset_bt);
        appAuth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {
        String Email=emailFrgt.getText().toString().trim();
        if (Email.isEmpty()) {
            emailFrgt.setError("email is required!!");
            emailFrgt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            emailFrgt.setError("Please provide valid email");
            emailFrgt.requestFocus();
            return;
        }
        appAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgot_pswd.this,"Check your email to reset the password",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(forgot_pswd.this,"Try again!!Something went wrong!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}