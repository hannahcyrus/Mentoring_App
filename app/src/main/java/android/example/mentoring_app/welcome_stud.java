package android.example.mentoring_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class welcome_stud extends AppCompatActivity  {
    private EditText ment_name,stud_name,Usn,Batch,bloodGroup,FatherName,GuardianContact,PermAdd,CurrAdd,Aadhar,Mobile,FatherJob,MotherJob,MotherTong,Languages,Hobbies,Strength,Aspirations,Res10,Res12,ResDip,Res1,Res2,Arr1,Arr2;
    private TextView arrear;
    private Button update;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_stud);
        ment_name=findViewById(R.id.mentName);
        stud_name=findViewById(R.id.studName);
        Usn=findViewById(R.id.usn_et);
        Batch=findViewById(R.id.batch_et);
        bloodGroup=findViewById(R.id.bGroup);
        FatherName=findViewById(R.id.Fname);
        GuardianContact=findViewById(R.id.Gcontact);
        PermAdd=findViewById(R.id.Padd);
        CurrAdd=findViewById(R.id.Cadd);
        Aadhar=findViewById(R.id.aadharNo);
        Mobile=findViewById(R.id.mob);
        FatherJob=findViewById(R.id.Fjob);
        MotherJob=findViewById(R.id.Mjob);
        MotherTong=findViewById(R.id.mtong);
        Languages=findViewById(R.id.lang);
        Hobbies=findViewById(R.id.hobb);
        Strength=findViewById(R.id.describe);
        Aspirations=findViewById(R.id.career);
        Res10=findViewById(R.id.res_10);
        Res12=findViewById(R.id.res_12);
        ResDip=findViewById(R.id.res_dip);
        Arr1=findViewById(R.id.arr_1);
        Arr2=findViewById(R.id.arr_2);
        Res1=findViewById(R.id.res_1);
        Res2=findViewById(R.id.res_2);
        arrear=findViewById(R.id.arr);

        update=findViewById(R.id.update);
        reference=FirebaseDatabase.getInstance().getReference().child("mentor");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudentData();
                AlertDialog.Builder builder=new AlertDialog.Builder(welcome_stud.this);
                builder.setTitle("Exit");
                builder.setMessage("You cannot edit once submitted");
                builder.setCancelable(false);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                }

            });
        }


    private void insertStudentData() {
        String mentName= ment_name.getText().toString();
        String studName = stud_name.getText().toString();
        String usn = Usn.getText().toString();
        String batch = Batch.getText().toString();
        String bgroup= bloodGroup.getText().toString();
        String fatherName =  FatherName.getText().toString();
        String guardianContact = GuardianContact.getText().toString();
        String permAdd  = PermAdd.getText().toString();
        String currAdd = CurrAdd.getText().toString();
        String aadhar = Aadhar.getText().toString();
        String mobile= Mobile.getText().toString();
        String fatherJob = FatherJob.getText().toString();
        String  motherJob = MotherJob.getText().toString();
        String motherTong = MotherTong.getText().toString();
        String languages = Languages.getText().toString();
        String hobbie = Hobbies.getText().toString();
        String  strength = Strength.getText().toString();
        String aspirations = Aspirations.getText().toString();
        String res10 = Res10.getText().toString();
        String res12 = Res12.getText().toString();
        String resDip = ResDip.getText().toString();
        String  res1 = Res1.getText().toString();
        String res2 = Res2.getText().toString();
        String arr1 = Arr1.getText().toString();
        String arr2 = Arr2.getText().toString();

        Students students = new Students(mentName,studName,usn,batch,bgroup,fatherName,guardianContact,permAdd,currAdd,aadhar,mobile,fatherJob,motherJob,motherTong,languages,hobbie,strength,aspirations,res10,res12,resDip,res1,res2,arr1,arr2);
        reference.child(usn).setValue(students);
        Toast.makeText(this,"updated successfully",Toast.LENGTH_SHORT).show();

    }


}