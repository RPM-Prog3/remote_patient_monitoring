package Application_Server_Interface.Data;

import java.io.Serializable;

public class Patient implements Serializable {

    private String patient_id, familyname, givenname, dofbirth, email, phonenumber;

    public Patient(String patient_id, String familyname, String givenname,
                   String dofbirth, String email,
                   String phonenumber){
        this.patient_id = patient_id;
        this.familyname = familyname;
        this.givenname = givenname;
        this.dofbirth = dofbirth;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Patient(String familyname, String givenname,
                   String dofbirth, String email,
                   String phonenumber){
        this.familyname = familyname;
        this.givenname = givenname;
        this.dofbirth = dofbirth;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public void print_patient_details(){
        System.out.println(String.format("%s, %s, %s, %s, %s, %s", patient_id, familyname, givenname, dofbirth, email, phonenumber));
    }

    public String get_patient_id() {
        return patient_id;
    }

    public String get_familyname(){
        return familyname;
    }

    public String get_givenname(){
        return givenname;
    }

    public String get_dofbirth(){
        return dofbirth;
    }

    public String get_email(){
        return email;
    }

    public String get_phonenumber(){
        return phonenumber;
    }
}
