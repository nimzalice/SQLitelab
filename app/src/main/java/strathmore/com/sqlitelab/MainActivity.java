package strathmore.com.sqlitelab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

/**
 * Created by alice nimu on 01/11/2017.
 */

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        DatabaseHandler db = new DatabaseHandler(this);

        //inserting contacts'
        Log.d("Insert:", "Inserting....");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9133333333"));

        Log.d("Reading:", "Reading all contacts...");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id:" + cn.getID() + ", Name:" + cn.getName() + ",Phone:" + cn.getPhoneNumber();
            //writing contacts to log
            Log.d("Name:", log);
        }


        //INSERTING STUDENTS
        Log.d("Insert:", "Inserting....");
        db.addStudent(new Students("Alicia", "BBIT","FIT"));
        db.addStudent(new Students("Timothy","BCOM", "SMC"));
        db.addStudent(new Students("Janet","CPA", "SOA"));
        db.addStudent(new Students("Kenny","BIF","FIT"));

        //reading all contacts

        //reading students

        Log.d("Reading:", "Reading all students...");
        List<Students> students=db.getAllStudents();


        for (Students st : students) {
                String log = "Id:" + st.getStudent_id() + ", Name:" + st.getStudent_name() + ",Course:" + st.getStudent_course()+ ",Faculty:" + st.getStudent_faculty();
                //writing contacts to log
                Log.d("Name:", log);
        }
    }
}
