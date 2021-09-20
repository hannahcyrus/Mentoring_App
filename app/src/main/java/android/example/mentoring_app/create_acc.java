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
import com.google.firebase.database.FirebaseDatabase;

public class create_acc extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth appAuth ;
    private EditText fullName, email, password;
    private Button register;
    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        appAuth = FirebaseAuth.getInstance();

        fullName = (EditText) findViewById(R.id.fullName_et);
        email = (EditText) findViewById(R.id.emailReg_et);
        password = (EditText) findViewById(R.id.passwordReg_et);

        register = (Button) findViewById(R.id.register_bt);
        register.setOnClickListener(this);

        if (getIntent().hasExtra("name")){
            name = getIntent().getStringExtra("name");
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

        appAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullname,Email,Password,0);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {

                                    //You can add a separate field for semester whenever someone goes in with student
                                    //make that field invisible when not a student

                                    //If a student
                                    //Create a seperate Student model class which include the semester parameter as a String
                                    if (name.equals("student")) {

//                                    if (task.isSuccessful()){
//                                        FirebaseDatabase.getInstance().getReference("students")
//                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                .setValue(student).addOnCompleteListener(task1 -> {
//                                                    if(task.isSuccessful()){
//                                                        Toast.makeText(create_acc.this,"user has been registered successfully!",Toast.LENGTH_LONG).show();
//                                                        startActivity(new Intent(create_acc.this, login.class));
//
//                                                    }else{
//                                                        Toast.makeText(create_acc.this,"failed to register!Try again",Toast.LENGTH_LONG).show();
//
//                                                    }
//                                                });
//                                    }
                                    }
                                    else {

                                        //If not a student
                                        if (task.isSuccessful()) {
                                            Toast.makeText(create_acc.this, "user has been registered successfully!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(create_acc.this, login.class));

                                        } else {
                                            Toast.makeText(create_acc.this, "failed to register!Try again", Toast.LENGTH_LONG).show();

                                        }
                                    }

                                }
                            });

                        }
                    }
                });
    }
}


