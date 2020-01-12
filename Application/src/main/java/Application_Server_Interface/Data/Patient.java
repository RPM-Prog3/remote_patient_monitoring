package Application_Server_Interface.Data;

import java.io.Serializable;

public class Patient implements Serializable {

    String familyname, givenname, dofbirth, email, phonenumber;

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
        System.out.println(String.format("%s, %s, %s, %s, %s", familyname, givenname, dofbirth, email, phonenumber));
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
