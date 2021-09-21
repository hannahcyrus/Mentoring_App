package android.example.mentoring_app.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.example.mentoring_app.R;
import android.example.mentoring_app.databinding.ActivityWelcomeMentTrialBinding;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeMentTrialActivty extends AppCompatActivity {
    ActivityWelcomeMentTrialBinding binding;
    DatabaseReference reference;
    private static final int REQUEST_CALL =1;
    private TextView callText;
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWelcomeMentTrialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callText=findViewById(R.id.tv_mobile);
        call=findViewById(R.id.callBtn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallButton();
            }
        });
        binding.readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = binding.etusername.getText().toString();
                if(!usn.isEmpty()){
                    readData(usn);
                }else{
                    Toast.makeText(WelcomeMentTrialActivty.this,"enter the usn",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CallButton() {
        String number = callText.getText().toString();
        if(number.trim().length()>0) {
            if (ContextCompat.checkSelfPermission(WelcomeMentTrialActivty.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(WelcomeMentTrialActivty.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CallButton();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void readData(String usn) {
        reference= FirebaseDatabase.getInstance().getReference("mentor");
        reference.child(usn).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(WelcomeMentTrialActivty.this,"data fetched",Toast.LENGTH_SHORT).show();
                        DataSnapshot snapshot =task.getResult();
                        String mentName= String.valueOf(snapshot.child("mentName").getValue());
                        String studName= String.valueOf(snapshot.child("studName").getValue());
                        String usn= String.valueOf(snapshot.child("usn").getValue());
                        String batch= String.valueOf(snapshot.child("batch").getValue());
                        String bgroup= String.valueOf(snapshot.child("bgroup").getValue());
                        String fatherName= String.valueOf(snapshot.child("fatherName").getValue());
                        String guardianContact= String.valueOf(snapshot.child("guardianContact").getValue());
                        String permAdd= String.valueOf(snapshot.child("permAdd").getValue());
                        String currAdd= String.valueOf(snapshot.child("currAdd").getValue());
                        String aadhar= String.valueOf(snapshot.child("aadhar").getValue());
                        String mobile= String.valueOf(snapshot.child("mobile").getValue());
                        String fatherJob= String.valueOf(snapshot.child("fatherJob").getValue());
                        String  motherJob= String.valueOf(snapshot.child("motherJob").getValue());
                        String motherTong= String.valueOf(snapshot.child("motherTong").getValue());
                        String languages= String.valueOf(snapshot.child("languages").getValue());
                        String hobbie= String.valueOf(snapshot.child("hobbie").getValue());
                        String  strength= String.valueOf(snapshot.child("strength").getValue());
                        String aspirations= String.valueOf(snapshot.child("aspirations").getValue());
                        String res10= String.valueOf(snapshot.child("res10").getValue());
                        String res12= String.valueOf(snapshot.child("res12").getValue());
                        String resDip= String.valueOf(snapshot.child("resDip").getValue());
                        String  res1= String.valueOf(snapshot.child("res1").getValue());
                        String res2= String.valueOf(snapshot.child("res2").getValue());
                        String arr1= String.valueOf(snapshot.child("arr1").getValue());
                        String arr2= String.valueOf(snapshot.child("arr2").getValue());
                        binding.tvMentName.setText(mentName);
                        binding.tvStudName.setText(studName);
                        binding.tvUsn.setText(usn);
                        binding.tvBatch.setText(batch);
                        binding.tvBgroup.setText(bgroup);
                        binding.tvFname.setText(fatherName);
                        binding.tvGcont.setText(guardianContact);
                        binding.tvPadd.setText(permAdd);
                        binding.tvCadd.setText(currAdd);
                        binding.tvAadhar.setText(aadhar);
                        binding.tvMobile.setText(mobile);
                        binding.tvFjob.setText(fatherJob);
                        binding.tvMjob.setText(motherJob);
                        binding.tvMtong.setText(motherTong);
                        binding.tvLang.setText(languages);
                        binding.tvHobby.setText(hobbie);
                        binding.tvStrength.setText(strength);
                        binding.tvCareer.setText(aspirations);
                        binding.tv10.setText(res10);
                        binding.tv12.setText(res12);
                        binding.tvDip.setText(resDip);
                        binding.tv1.setText(res1);
                        binding.tv2.setText(res2);
                        binding.tvA1.setText(arr1);
                        binding.tvA2.setText(arr2);


                    }
                }

            }
        });
    }
}