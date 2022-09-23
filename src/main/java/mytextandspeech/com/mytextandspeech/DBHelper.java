package mytextandspeech.com.mytextandspeech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    UserSession session;
    public DBHelper(@Nullable Context context) {

        super(context, "TextandSpeech.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String createQuery2 = "CREATE TABLE user (userId INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, gender TEXT, address TEXT, mobileNo TEXT, DOB TEXT, guardianName TEXT, guardianPhone TEXT );";
        DB.execSQL(createQuery2);
        String createQuery= "CREATE TABLE template (id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, description TEXT, fkUserId INTEGER, FOREIGN KEY(fkUserId) REFERENCES user(userId));";
        DB.execSQL(createQuery);

        dataUser(DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE2 = "DROP TABLE IF EXISTS user" ;
        db.execSQL(DROP_TABLE2);
        String DROP_TABLE = "DROP TABLE IF EXISTS template" ;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    public void data() {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues c = new ContentValues();
     //   c.put("id",1);
        c.put("name","Greetings");
        c.put("description","Hello");
     //   c.put("id",2);
        c.put("name","Help");
        c.put("description","Please help");
       long result = DB.insert("template", null, c);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
        }

    // we have created a new method for reading all the courses.
    public ArrayList<ListenAndSpeakModal> readTemplates() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor templateCourses = db.rawQuery("SELECT * FROM " + "template", null);

        // on below line we are creating a new array list.
        ArrayList<ListenAndSpeakModal> listenAndSpeaklArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (templateCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                listenAndSpeaklArrayList.add(new ListenAndSpeakModal(templateCourses.getInt(1),
                        templateCourses.getString(2),
                        templateCourses.getString(3)));
            } while (templateCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        templateCourses.close();
        return listenAndSpeaklArrayList;
    }


    // this method is use to add new course to our sqlite database.
    public void addNewTemplate(String templateName, String templateDescription) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        session = new UserSession(context);
        int fId = Integer.parseInt(session.getUserId());
        if(fId > 0) {
            values.put("fkUserId", fId);
        }
        else{
            values.put("fkUserId", 1);
        }
        values.put("name", templateName);
        values.put("description", templateDescription);


        // after adding all values we are passing
        // content values to our table.

        long result =  db.insert("template", null, values);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public Cursor populateTable(){
        SQLiteDatabase db = this.getReadableDatabase();
       // String[] columns = {"id", "name"};
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery("SELECT * FROM template;", null);
        }
        return cursor;
    }

    public void updateNewTemplate(int id, String title, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       //values
        if(title != null) {
            values.put("name", title);
        }
        if(desc != null) {
            values.put("description", desc);
        }
        db.update("template", values, "id="+id, null);
        db.close();
    }

    public void deleteTemplate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("template", "id="+id, null);
        db.close();
    }

    public void dataUser(SQLiteDatabase DB) {

        ContentValues c = new ContentValues();
        c.put("userName","admin");
        c.put("gender","female");
        c.put("address","vivekanandar street, xxx");
        c.put("mobileNo","123456789");
        c.put("DOB","06/01/1972");
        c.put("guardianName","Deen");
        c.put("guardianPhone","098765432");
        long result = DB.insert("user", null, c);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }

    // this method is use to add new course to our sqlite database.
    public void addNewUser(String name, String gender, String address, String mobile, String DOB, String gName, String gPhone) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues c = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        // values.put("id", id);
        c.put("userName", name);
        c.put("gender", gender);
        c.put("address", address);
        c.put("mobileNo", mobile);
        c.put("DOB", DOB);
        c.put("guardianName", gName);
        c.put("guardianPhone", gPhone);


        // after adding all values we are passing
        // content values to our table.

        long result =  db.insert("user", null, c);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public Cursor populateUserUsingId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] columns = {"id", "name"};
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery("SELECT * FROM user WHERE userId="+id+";", null);
        }
        return cursor;
    }

    public Cursor populateUser(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] columns = {"id", "name"};
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery("SELECT * FROM user WHERE userName="+'"'+name+'"'+";", null);
        }
        return cursor;
    }

    public void updateUser(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values
        if(name != null) {
            values.put("userName", name);
        }

        db.update("user", values, "id="+id, null);
        db.close();
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user", "id="+id, null);
        db.close();
    }
}
