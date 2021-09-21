package android.example.mentoring_app.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.mentoring_app.R;
import android.example.mentoring_app.SessionManagement;
import android.example.mentoring_app.models.Students;
import android.example.mentoring_app.models.User;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth appAuth;
    private EditText fullName, email, password, semester;
    private Button register;
    private EditText tv;
    private String name;
    SessionManagement sessionManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        appAuth = FirebaseAuth.getInstance();

        sessionManagement = new SessionManagement(this);

        fullName = (EditText) findViewById(R.id.fullName_et);
        email = (EditText) findViewById(R.id.emailReg_et);
        password = (EditText) findViewById(R.id.passwordReg_et);

        register = (Button) findViewById(R.id.register_bt);
        register.setOnClickListener(this);
        tv = findViewById(R.id.tv1);
        semester = findViewById(R.id.sem);

        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");

        }

        if (name.equals("coordinator") || name.equals("mentor")) {
            tv.setVisibility(View.INVISIBLE);
        }
        if (name.equals("coordinator") || name.equals("mentor")) {
            semester.setVisibility(View.INVISIBLE);
        }
        if (name.equals("student")) {
            email.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_bt:
                register();
        }

    }

    private void register() {
        String Email = email.getText().toString().trim();
        String fullname = fullName.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (fullname.isEmpty()) {
            fullName.setError("fullname is required!!");
            password.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            email.setError("email is required!!");
            email.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            password.setError("password is required!!");
            password.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if (password.length() < 8) {
            password.setError(("Min password length must be 8 characters"));
            password.requestFocus();
            return;
        }
        if (name.equals("coordinator") || name.equals("mentor")) {
            appAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(fullname, Email, Password, 0);
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(CreateAccountActivity.this, "user has been registered successfully!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(CreateAccountActivity.this, LoginActivity.class));

                                        } else {
                                            Toast.makeText(CreateAccountActivity.this, "failed to register!Try again", Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });

                            }
                        }
                    });
        }
        else{

            String key = FirebaseDatabase.getInstance().getReference("students").push().getKey();

//            Students user = new Students(key,"", add the rest of the data);
            Students user = new Students();

            FirebaseDatabase.getInstance().getReference("students").child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        // Cache the data
                        sessionManagement.setStudentUID(key);
                        sessionManagement.setStudentUSN(user.getUsn());



                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Failed to register!Try again", Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }
}