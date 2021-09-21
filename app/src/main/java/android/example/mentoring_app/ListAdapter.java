package android.example.mentoring_app;

import android.app.Activity;
import android.example.mentoring_app.models.Students;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Students> studentsList;
    public ListAdapter(Activity mContext,List<Students>studentsList){
        super(mContext,R.layout.list_view,studentsList);
        this.mContext= mContext;
        this.studentsList=studentsList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=mContext.getLayoutInflater();
        View listItemView=inflater.inflate(R.layout.list_view,null,true);
        TextView mName =listItemView.findViewById(R.id.tv_mentName);
        TextView sName = listItemView.findViewById(R.id.tv_studName);
        TextView usn = listItemView.findViewById(R.id.tv_usn);
        TextView batch = listItemView.findViewById(R.id.tv_batch);
        TextView bgroup= listItemView.findViewById(R.id.tv_bgroup);
        TextView FatherName=listItemView.findViewById(R.id.tv_fname);
        TextView GuardianContact=listItemView.findViewById(R.id.tv_gcont);
        TextView PermAdd=listItemView.findViewById(R.id.tv_padd);
        TextView        Aadhar=listItemView.findViewById(R.id.tv_aadhar);
        TextView Mobile=listItemView.findViewById(R.id.tv_mobile);
        TextView FatherJob=listItemView.findViewById(R.id.tv_fjob);
        TextView MotherJob=listItemView.findViewById(R.id.tv_mjob);
        TextView MotherTong=listItemView.findViewById(R.id.tv_mtong);
        TextView Languages=listItemView.findViewById(R.id.tv_lang);
        TextView Hobbies=listItemView.findViewById(R.id.tv_hobby);
        TextView Strength=listItemView.findViewById(R.id.tv_strength);
        TextView Aspirations=listItemView.findViewById(R.id.tv_career);
        TextView Res10=listItemView.findViewById(R.id.tv_10);
        TextView Res12=listItemView.findViewById(R.id.tv_12);
        TextView ResDip=listItemView.findViewById(R.id.tv_dip);
        TextView  Res1=listItemView.findViewById(R.id.tv_1);
        TextView Res2=listItemView.findViewById(R.id.tv_2);
        TextView Arr1=listItemView.findViewById(R.id.tv_a1);
        TextView Arr2=listItemView.findViewById(R.id.tv_a2);

        Students students=studentsList.get(position);

        mName.setText(students.getMentName());
        sName.setText(students.getStudName());
        usn.setText(students.getUsn());
         batch .setText(students.getBatch());
         bgroup.setText(students.getBgroup());
         FatherName.setText(students.getFatherName());
         GuardianContact.setText(students.getGuardianContact());
         PermAdd.setText(students.getPermAdd());
               Aadhar.setText(students.getAadhar());
         Mobile.setText(students.getMobile());
         FatherJob.setText(students.getFatherJob());
         MotherJob.setText(students.getMotherJob());
         MotherTong.setText(students.getMotherTong());
         Languages.setText(students.getLanguages());
        Hobbies.setText(students.getHobbie());
         Strength.setText(students.getStrength());
         Aspirations.setText(students.getAspirations());
         Res10.setText(students.getRes10());
        Res12.setText(students.getRes12());
         ResDip.setText(students.getResDip());
         Res1.setText(students.getRes1());
         Res2.setText(students.getRes2());
         Arr1.setText(students.getArr1());
         Arr2.setText(students.getArr2());

        return listItemView;


    }
}
