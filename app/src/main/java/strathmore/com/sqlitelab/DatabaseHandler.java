package strathmore.com.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alice nimu on 17/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    //All static variables
    //Database version

    private static final int DATABASE_VERSION=1;

    //Db name
    private static final String DATABASE_NAME="contactsManager";

    private static final String TABLE_CONTACTS="contacts";
    private static final String TABLE_STUDENTS="students";

    //contacts table column names
    private static final String KEY_ID="id";
    private static final String KEY_NAME="id";
    private static final String KEY_PH_NO="phone_number";
    private static final String STUD_NAME="student_name";
    private static final String STUD_ID="student_id";
    private static final String STUD_FACULTY="student_faculty";
    private static final String STUD_COURSE="student_course";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    //creating db
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE="CREATE TABLE"+TABLE_CONTACTS+"("+KEY_ID+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT,"+KEY_PH_NO+"TEXT,"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_STUDENTS_TABLE="CREATE TABLE"+TABLE_STUDENTS+"("+STUD_ID+"INTEGER PRIMARY KEY,"+STUD_NAME+"TEXT,"+STUD_FACULTY+"TEXT,"+STUD_COURSE+"TEXT ,"+")";
        db.execSQL(CREATE_STUDENTS_TABLE);

    }

    @Override
    //upgrading db
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_CONTACTS+TABLE_STUDENTS);

        //create tables again
        onCreate(db);
    }
  //adding a new contact
    public void addContact(Contact contact){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME, contact.getName()); //Contact Name
        values.put(KEY_PH_NO,contact.getPhoneNumber());//Contact phone

        //inserting row
        db.insert(TABLE_CONTACTS,null, values);
        db.close();//Closing db connection
    }

    //getting tables single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return contact
        return contact;
    }

    //getting all contacts
    public List<Contact>getAllContacts(){
        List<Contact>contactList=new ArrayList<Contact>();

        //select all query
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //adding contact to list
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }

    //getting contacts count
    public int getContactsCount(){
        String countQuery= "SELECT * FROM"+TABLE_CONTACTS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //updating a single contact
    public int updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID +"=?", new String[]{String.valueOf(contact.getID())});

    }

    //deleting a single contact
    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID+"=?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////STUDENTS TABLE////////////////////////////////////////////////
    //adding a new contact
    public void addStudent(Students students){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(STUD_NAME, students.getStudent_name()); //Student Name
        values.put(STUD_ID,students.getStudent_id());//Student phone
        values.put(STUD_FACULTY, students.getStudent_faculty()); //Student faculty
        values.put(STUD_COURSE,students.getStudent_course());//Student course

        //inserting row
        db.insert(TABLE_STUDENTS,null, values);
        db.close();//Closing db connection
    }

    //getting tables single student
    public Students getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{STUD_ID, STUD_NAME, STUD_FACULTY, STUD_COURSE}, STUD_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Students students = new Students(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        //return students
        return students;
    }

    //getting all students
    public List<String> getAllStudents(){
        List<String >studentsList= new ArrayList<>();

        //select all query
        String selectQuery="SELECT * FROM "+TABLE_STUDENTS;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do {
                Students students = new Students();
                students.setStudent_id(Integer.parseInt(cursor.getString(0)));
                students.setStudent_name(cursor.getString(1));
                students.setStudent_course(cursor.getString(2));
                students.setStudent_faculty(cursor.getString(3));

                //adding contact to list
                studentsList.add(String.valueOf(students));
            }while (cursor.moveToNext());
        }
        return studentsList;
    }

    //getting contacts count
    public int getStudentsCount(){
        String countQuery= "SELECT * FROM"+TABLE_STUDENTS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //updating a single contact
    public int updateStudent(Students students){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(STUD_ID, students.getStudent_id());
        values.put(STUD_NAME, students.getStudent_name());
        values.put(STUD_ID, students.getStudent_id());
        values.put(STUD_NAME, students.getStudent_name());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID +"=?", new String[]{String.valueOf(students.getStudent_id())});

    }

    //deleting a single contact
    public void deleteStudents(Students students){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, STUD_ID+"=?", new String[]{String.valueOf(students.getStudent_id())});
        db.close();
    }


}
