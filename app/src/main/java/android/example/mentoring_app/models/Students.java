package android.example.mentoring_app.models;

public class Students {
    public Students(String mentName, String studName, String usn, String batch, String bgroup, String fatherName, String guardianContact, String permAdd, String currAdd, String aadhar, String mobile, String fatherJob, String motherJob, String motherTong, String languages, String hobbie, String strength, String aspirations, String res10, String res12, String resDip, String res1, String res2, String arr1, String arr2) {
    }

    public String getUsn() {
        return usn;
    }

    public String getBatch() {
        return batch;
    }

    public String getBgroup() {
        return bgroup;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public String getPermAdd() {
        return permAdd;
    }

    public String getCurrAdd() {
        return currAdd;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getMobile() {
        return mobile;
    }

    public String getFatherJob() {
        return fatherJob;
    }

    public String getMotherJob() {
        return motherJob;
    }

    public String getMotherTong() {
        return motherTong;
    }

    public String getLanguages() {
        return languages;
    }

    public String getHobbie() {
        return hobbie;
    }

    public String getStrength() {
        return strength;
    }

    public String getAspirations() {
        return aspirations;
    }

    public String getRes10() {
        return res10;
    }

    public String getRes12() {
        return res12;
    }

    public String getResDip() {
        return resDip;
    }

    public String getArr1() {
        return arr1;
    }

    public String getArr2() {
        return arr2;
    }

    public String getRes1() {
        return res1;
    }

    public String getRes2() {
        return res2;
    }

    public Students(String studentUid, String sem, String mentName, String studName, String usn, String batch, String bgroup, String fatherName, String guardianContact, String permAdd, String currAdd, String aadhar, String mobile, String fatherJob, String motherJob, String motherTong, String languages, String hobbie, String strength, String aspirations, String res10, String res12, String resDip, String res1, String res2,String arr1, String arr2) {
        this.mentName = mentName;
        this.studName = studName;
        this.usn = usn;
        this.batch = batch;
        this.bgroup = bgroup;
        this.fatherName = fatherName;
        this.guardianContact = guardianContact;
        this.permAdd = permAdd;
        this.currAdd = currAdd;
        this.aadhar = aadhar;
        this.mobile = mobile;
        this.fatherJob = fatherJob;
        this.motherJob = motherJob;
        this.motherTong = motherTong;
        this.languages = languages;
        this.hobbie = hobbie;
        this.strength = strength;
        this.aspirations = aspirations;
        this.res10 = res10;
        this.res12 = res12;
        this.resDip = resDip;
        this.res1 = res1;
        this.res2 = res2;
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.sem = sem;
        this.studentUid = studentUid;
    }


    public String getMentName() {
        return mentName;
    }

    public String getStudName() {
        return studName;
    }

    public String getSem() {
        return sem;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public Students() {
    }

    String mentName;
    String studName;
    String usn;
    String batch;
    String bgroup;
    String fatherName;
    String guardianContact;
    String permAdd;
    String currAdd;
    String aadhar;
    String mobile;
    String fatherJob;
    String  motherJob;
    String motherTong;
    String languages;
    String hobbie;
    String  strength;
    String aspirations;
    String res10;
    String res12;
    String resDip;
    String arr1;
    String arr2;
    String  res1;
    String res2;
    String sem;
    String studentUid;


}
