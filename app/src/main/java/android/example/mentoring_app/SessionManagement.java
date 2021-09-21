package android.example.mentoring_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.mentoring_app.activties.CreateAccountActivity;

public class SessionManagement {

    SharedPreferences pref;
    public static SharedPreferences.Editor editor;
    Context context;

    public static final String USN = "studentUSN";
    public static final String UID = "studentUID";

    public SessionManagement(Context context) {
        this.context = context;
    }

    public void setStudentUSN(String value){
        editor.putString(USN, value);
        editor.commit();
    }

    public String getStudentUSN() {
        return pref.getString(USN, "");
    }

    public void setStudentUID(String value){
        editor.putString(UID, value);
        editor.commit();
    }

    public String getStudentUID() {
        return pref.getString(UID, "");
    }

}
