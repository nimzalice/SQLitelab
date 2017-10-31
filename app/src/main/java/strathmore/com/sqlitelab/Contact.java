package strathmore.com.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class Contact extends AppCompatActivity {

    //private variables
    int _id;
    String _name;
    String _phone_number;

    //Empty constructor
    public Contact() {

    }

    //constructor
    public Contact(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;

    }
    //constructor

    public Contact(String name, String _phone_number) {
        this._name = name;
        this._phone_number = _phone_number;
    }
    //getting ID

    public int getID() {
        return this._id;
    }
    //getting name

    public String getName() {
        return this._name;
    }
    //getting phonenumber

    public String getPhoneNumber() {
        return this._phone_number;
    }

    //SETTERS
    //setting id

    public void setID(int _id) {
        this._id = _id;
    }

    //setting name

    public void setName(String _name) {
        this._name = _name;
    }

    //setting phone


    public void setPhoneNumber(String _phone_number) {
        this._phone_number = _phone_number;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        DatabaseHandler db = new DatabaseHandler(this);

        //inserting contacts
        Log.d("Insert:", "Inserting....");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9133333333"));

        //reading all contacts
        Log.d("Reading:", "Reading all contacts...");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id:" + cn.getID() + ", Name:" + cn.getName() + ",Phone:"+cn.getPhoneNumber();
            //writing contacts to log
            Log.d("Name:", log);
        }
    }
}

