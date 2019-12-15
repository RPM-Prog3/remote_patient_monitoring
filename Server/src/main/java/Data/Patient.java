package Data;

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
}
