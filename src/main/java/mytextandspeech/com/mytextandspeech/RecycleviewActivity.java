package mytextandspeech.com.mytextandspeech;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class RecycleviewActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    DBHelper dbHandler;

    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 //       setContentView(R.layout.activity_recycleview);

//        getSupportActionBar().setTitle("Recyclerview");
//
//        recyclerview = findViewById(R.id.recyclerView);
//        getData();
//        ListenAndSpeakModal l = new ListenAndSpeakModal(1, "Greetings", "Hello World!!!");
//        template.add(l);
//
//        customAdapter = new CustomAdapter(RecycleviewActivity.this, template);
//        recyclerview.setLayoutManager(new LinearLayoutManager(RecycleviewActivity.this));
//        recyclerview.setAdapter(customAdapter);




    }


    void getData(){
        dbHandler = new DBHelper(RecycleviewActivity.this);
        Cursor mCur = dbHandler.populateTable();
        ArrayList<ListenAndSpeakModal> template = new ArrayList<ListenAndSpeakModal>();


        if(mCur != null){
            while(mCur.moveToNext()){
                ListenAndSpeakModal listenAndSpeakModal = new  ListenAndSpeakModal();

                listenAndSpeakModal.setId(mCur.getInt(mCur.getColumnIndex("id")));
                listenAndSpeakModal.setTemplateName(mCur.getString(mCur.getColumnIndex("name")));
                listenAndSpeakModal.setTemplateDescription(mCur.getString(mCur.getColumnIndex("description")));
                template.add(listenAndSpeakModal);
            }
        }
        //        if (mCur.getCount() != 0) {
//            if (mCur.moveToFirst()) {
//                do {
//
//                listenAndSpeakModal.setId(mCur.getInt(mCur.getColumnIndex("id")));
//                listenAndSpeakModal.setTemplateName(mCur.getString(mCur.getColumnIndex("name")));
//                listenAndSpeakModal.setTemplateDescription(mCur.getString(mCur.getColumnIndex("description")));
//                template.add(listenAndSpeakModal);
//            }  while (mCur.moveToNext());
//        }}
//        if (mCur.getCount() != 0) {
//            int rows = mCur.getCount();
//            int cols = mCur.getColumnCount();
//            for(int i=0; i< rows; i++){
//                listenAndSpeakModal = new ListenAndSpeakModal(mCur.getInt(mCur.getColumnIndex("id")),
//                        mCur.getString(mCur.getColumnIndex("name")),
//                        mCur.getString(mCur.getColumnIndex("description")) );
//                template.add(listenAndSpeakModal);
//            }
//            if(template.size() != 0){
//                Toast.makeText(RecycleviewActivity.this, template.get(0).templateName, Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(RecycleviewActivity.this, "No data.", Toast.LENGTH_SHORT).show();
//            }
//        }
        else{
            Toast.makeText(RecycleviewActivity.this, "No data.", Toast.LENGTH_SHORT).show();
        }

        customAdapter = new CustomAdapter(RecycleviewActivity.this, template);
        recyclerview.setLayoutManager(new LinearLayoutManager(RecycleviewActivity.this));
        recyclerview.setAdapter(customAdapter);


    }
}
