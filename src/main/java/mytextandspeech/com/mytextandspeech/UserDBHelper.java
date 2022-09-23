package mytextandspeech.com.mytextandspeech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class UserDBHelper  extends SQLiteOpenHelper {
    private Context context;
    SQLiteDatabase DB;

    public UserDBHelper(@Nullable Context context) {

        super(context, "TextandSpeech2.db", null, 1);
        this.context = context;
       // onCreate(DB);
     //   onUpgrade(DB, 0,2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String createQuery = "CREATE TABLE user (userId INTEGER PRIMARY KEY AUTOINCREMENT,  userName TEXT, gender TEXT, address TEXT, mobileNo TEXT, DOB TEXT, guardianName TEXT, guardianPhone TEXT);";
        DB.execSQL(createQuery);
        System.out.println(createQuery);
          data();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            String DROP_TABLE = "DROP TABLE IF EXISTS user";
            db.execSQL(DROP_TABLE);
            onCreate(db);
    }


    public void data() {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("userName","Admin");
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

    public Cursor populateTable(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // String[] columns = {"id", "name"};
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery("SELECT * FROM user WHERE id="+id+";", null);
        }
        return cursor;
    }

    public void updateNewTemplate(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values
        if(name != null) {
            values.put("userName", name);
        }

        db.update("user", values, "id="+id, null);
        db.close();
    }

    public void deleteTemplate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user", "id="+id, null);
        db.close();
    }
}