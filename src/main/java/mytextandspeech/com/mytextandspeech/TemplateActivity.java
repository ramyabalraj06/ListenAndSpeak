package mytextandspeech.com.mytextandspeech;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TemplateActivity extends AppCompatActivity {
    // creating variables for our edittext, button and dbhandler

    private EditText titleId, titleName,titleDescription;
    private Button addTemplateBtn, clearTemplateBtn, deleteTemplateBtn;
    private DBHelper dbHandler;
    private TableLayout t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1 = findViewById(R.id.main_table);
        // initializing all our variables.
        titleId = findViewById(R.id.titleId);
        titleName = findViewById(R.id.titleName);
        titleDescription = findViewById(R.id.titleDescription);
        addTemplateBtn = findViewById(R.id.idBtnAdd);
        clearTemplateBtn = findViewById(R.id.idBtnClear);
        deleteTemplateBtn = findViewById(R.id.idBtnDelete);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHelper(TemplateActivity.this);

        // below line is to add on click listener for our add course button.
        addTemplateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String title = titleName.getText().toString();
                String id = titleId.getText().toString();
                String description = titleDescription.getText().toString();

                // validating if the text fields are empty or not.
                if (title.isEmpty() && description.isEmpty()) {
                    Toast.makeText(TemplateActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                if(id.isEmpty() || id == null || id =="" ){
                    dbHandler.addNewTemplate( title, description);
                    Toast.makeText(TemplateActivity.this, "Template has been added.", Toast.LENGTH_LONG).show();
                }
                else if(id != null && addTemplateBtn.getText().equals("UPDATE") ){
                    int nId = Integer.parseInt(id);
                    dbHandler.updateNewTemplate(nId, title, description);
                    Toast.makeText(TemplateActivity.this, "Template has been updated.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(TemplateActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // after adding the data we are displaying a toast message.


            }
        });
        BuildTable();
        TableRow row = new TableRow(this);
        row.setClickable(true);  //allows you to select a specific row
        int rowNumCount = t1.getChildCount();
        for(int count = 1; count < rowNumCount; count++) {
            View v = t1.getChildAt(count);
            if(v instanceof TableRow) {
                final TableRow clickRow = (TableRow) v;
                int rowCount = clickRow.getChildCount();
                if (rowCount > 0){
                    v.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            TableRow tablerow = (TableRow) v;
                            TextView id = (TextView) tablerow.getChildAt(0);
                            TextView title = (TextView) tablerow.getChildAt(1);
                            TextView description = (TextView) tablerow.getChildAt(2);
                            titleId.setText(id.getText().toString());
                            titleName.setText(title.getText().toString());
                            titleDescription.setText(description.getText().toString());
                            addTemplateBtn.setText("UPDATE");
                        }
                    });
                }
            }
        }
        deleteTemplateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = titleId.getText().toString();
                if(id != null && addTemplateBtn.getText().equals("UPDATE") ){
                    int nId = Integer.parseInt(id);
                    dbHandler.deleteTemplate(nId);
                }
                else{
                    Toast.makeText(TemplateActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        clearTemplateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                titleName.setText("");
                titleId.setText("");
                titleDescription.setText("");
                addTemplateBtn.setText("ADD");
                cleanTable(t1);
                BuildTable();
                clickableRow();


            }
        });

    }
//
//    public void refresh() {
//        if(t1.getChildCount() == 0) {
//            BuildTable();
//        }
//        else{
//            t1.removeViewsInLayout();
//        }
//    }
    public void BuildTable() {

        Cursor mCur = dbHandler.populateTable();
        int i =0;
        if (mCur.getCount() != 0) {
            if (mCur.moveToFirst()) {
                do {
                    int rows = mCur.getCount();
                    int cols = mCur.getColumnCount();

                    // outer for loop


                    TableRow row = new TableRow(this);
                    TableLayout.LayoutParams tableRowParams =
                            new TableLayout.LayoutParams
                                    (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                    t1.setStretchAllColumns(true);
                    row.setClickable(true);
                    row.setLayoutParams(tableRowParams);
                    row.setId(i);

                    // inner for loop
                    for (int j = 0; j < cols; j++) {

                        TextView tv = new TextView(this);

                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(14);
                        tv.setPadding(7,7,7,7);
                        tv.setId(j);
                        tv.setText(mCur.getString(j));
                        row.addView(tv);

                    }
                    t1.addView(row);
                    i++;
                } while (mCur.moveToNext());
            }
        }
        else{
            Toast.makeText(TemplateActivity.this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void cleanTable(TableLayout table) {

        int childCount = table.getChildCount();

        // Remove all rows except the first one
        if (childCount > 1) {
            table.removeViews(1, childCount - 1);
        }
    }

    public void clickableRow(){
        TableRow row = new TableRow(this);
        row.setClickable(true);  //allows you to select a specific row
        int rowNumCount = t1.getChildCount();
        for(int count = 1; count < rowNumCount; count++) {
            View v = t1.getChildAt(count);
            if(v instanceof TableRow) {
                final TableRow clickRow = (TableRow) v;
                int rowCount = clickRow.getChildCount();
                if (rowCount > 0){
                    v.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            TableRow tablerow = (TableRow) v;
                            TextView id = (TextView) tablerow.getChildAt(0);
                            TextView title = (TextView) tablerow.getChildAt(1);
                            TextView description = (TextView) tablerow.getChildAt(2);
                            titleId.setText(id.getText().toString());
                            titleName.setText(title.getText().toString());
                            titleDescription.setText(description.getText().toString());
                            addTemplateBtn.setText("UPDATE");
                        }
                    });
                }
            }
        }
    }
}
