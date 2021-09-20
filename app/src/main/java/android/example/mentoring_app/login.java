package android.example.mentoring_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity implements View.OnClickListener {
    private TextView createAcc,ForgotPswd;
    private EditText email,pswd;
    private Button login;
    private FirebaseAuth appAuth;
    private String name= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createAcc=(TextView)findViewById(R.id.create_tv);
        createAcc.setOnClickListener(this);
        ForgotPswd=(TextView)findViewById(R.id.forgot_tv);
        ForgotPswd.setOnClickListener(this);
        email=(EditText)findViewById(R.id.email_ment);
        pswd=(EditText)findViewById(R.id.password_et);
        login=(Button)findViewById(R.id.login_bt);
        login.setOnClickListener(this);

        if (getIntent().hasExtra("name")){
            name = getIntent().getStringExtra("name");
        }

        appAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_tv:
                Intent intent = new Intent(login.this,create_acc.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.login_bt:
                loginUser();
                break;
            case R.id.forgot_tv:
                startActivity(new Intent(login.this,forgot_pswd.class));
        }
    }

    private void loginUser() {
        String et_email = email.getText().toString().trim();
        String et_password = pswd.getText().toString().trim();
        if (et_email.isEmpty()) {
            email.setError("email is required!!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_email).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if (et_password.isEmpty()) {
            pswd.setError("password is required!!");
            pswd.requestFocus();
            return;
        }

        if (et_password.length() < 8) {
            pswd.setError(("Min password length must be 8 characters"));
            pswd.requestFocus();
            return;
        }
        appAuth.signInWithEmailAndPassword(et_email,et_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){

                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        String uid =task.getResult().getUser().getUid();
                        database.getReference().child("users").child(uid).child("userType_user").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int userType_user=snapshot.getValue(Integer.class);
                                if (userType_user==0){
                                    startActivity(new Intent(login.this,welcome_stud.class));

                                }
                                if(userType_user==1){
                                    startActivity(new Intent(login.this,welcome_cordd.class));

                                }
                                if(userType_user==2){
                                    startActivity(new Intent(login.this,welcome_ment_trial.class));

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull  DatabaseError error) {

                            }
                        });
                   }else{
                        user.sendEmailVerification();
                        Toast.makeText(login.this,"check your email to verify your account",Toast.LENGTH_LONG).show();
                    }


               }
                else{
                    Toast.makeText(login.this,"failed to login!Try again",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}


