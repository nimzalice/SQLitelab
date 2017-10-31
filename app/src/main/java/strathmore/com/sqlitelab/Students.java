package strathmore.com.sqlitelab;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by alice nimu on 24/10/2017.
 */

public class Students extends AppCompatActivity {


    //private variables
    int student_id;
    String student_name;
    String student_faculty;
    String student_course;


    //Empty constructor
    public Students(int i, String string, String cursorString) {

    }

    //constructor

    public Students(int student_id, String student_name, String student_faculty, String student_course) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_faculty = student_faculty;
        this.student_course = student_course;
    }

    public Students(String student_name, String student_faculty, String student_course) {
        this.student_name = student_name;
        this.student_faculty = student_faculty;
        this.student_course = student_course;
    }

    public Students() {

    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_faculty() {
        return student_faculty;
    }

    public void setStudent_faculty(String student_faculty) {
        this.student_faculty = student_faculty;
    }

    public String getStudent_course() {
        return student_course;
    }

    public void setStudent_course(String student_course) {
        this.student_course = student_course;
    }
}
