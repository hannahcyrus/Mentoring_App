package android.example.mentoring_app.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.mentoring_app.R;
import android.example.mentoring_app.SessionManagement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

private Button coordinator,mentor,student;
SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinator=(Button)findViewById(R.id.coordinator_bt);
        coordinator.setOnClickListener(this);
        mentor=(Button)findViewById(R.id.mentor_bt);
        mentor.setOnClickListener(this);
        student=(Button)findViewById(R.id.student_bt);
        student.setOnClickListener(this);

        sessionManagement = new SessionManagement(this);

        String usn = sessionManagement.getStudentUSN();

        if (usn.length()>0){
            DatabaseReference dref = FirebaseDatabase.getInstance().getReference("students");
            dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(sessionManagement.getStudentUID())){
                        FirebaseDatabase.getInstance().getReference("students").child(sessionManagement.getStudentUID()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);

        switch (v.getId()){
            case R.id.coordinator_bt:
                intent.putExtra("name", "coordinator");
                startActivity(intent);
                break;
            case R.id.student_bt:
                intent.putExtra("name", "student");
                startActivity(intent);
                break;

            case R.id.mentor_bt:
                intent.putExtra("name", "mentor");
                startActivity(intent);
            break;


        }
    }
}
//String name="aaaa";
// Intent intent=new Intent(Main_Activity.this,Other_Activity.class);
// intent.putExtra("name", name);
// startActivity(intent);