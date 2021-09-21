package android.example.mentoring_app.models;

public class StudentSem {

    private String name;
    private String sem;

    public StudentSem(String name, String sem) {
        this.name = name;
        this.sem = sem;
    }

    public String getName() {
        return name;
    }

    public String getSem() {
        return sem;
    }
}
