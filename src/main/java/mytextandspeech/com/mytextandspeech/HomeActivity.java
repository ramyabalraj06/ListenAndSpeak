package mytextandspeech.com.mytextandspeech;

import android.content.Intent;
import android.database.Cursor;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class HomeActivity extends AppCompatActivity {
    EditText Text;
    Button btnText, templateBtnText;
    TextToSpeech textToSpeech;
    public ImageView iv_mic;
    TextView tv_Speech_to_text, autoCompleteText;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    AutoCompleteTextView atext;
    private DBHelper dbHandler;
    String[] languages={"Android ","java","IOS","SQL","JDBC","Web services"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Listen And Speak");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Text = findViewById(R.id.Text);
        btnText = findViewById(R.id.btnText);
        autoCompleteText = findViewById(R.id.autoCompleteText);
        templateBtnText = findViewById(R.id.btnTemplateText);
        atext = (AutoCompleteTextView) findViewById(R.id.autoTextViewTemplate);
        String []autoTemplateList = getTemplate();
        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,autoTemplateList);
        atext.setAdapter(adapter);
        atext.setThreshold(1);
//        atext.showDropDown();
        // create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        // Adding OnClickListener
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(Text.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        // Adding OnClickListener
        templateBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoCompleteText.getText() != null) {
                    textToSpeech.speak(autoCompleteText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        atext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String autoText=(String)adapterView.getItemAtPosition(i);
                autoCompleteText.setText(autoText);
            }
        });

        iv_mic = findViewById(R.id.iv_mic);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e) {
                    Toast
                            .makeText(HomeActivity.this, " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                tv_Speech_to_text.setText(
                        (result).get(0));
            }
        }
    }

    private String[]  getTemplate(){
        dbHandler = new DBHelper(HomeActivity.this);
        Cursor mCur = dbHandler.populateTable();
        ArrayList<String> template = new ArrayList<String>();
        String []templateArray = null;

        if(mCur != null){
            while(mCur.moveToNext()){
                template.add(mCur.getString(mCur.getColumnIndex("description")));
            }
        }
        if(template.size() > 0){
            templateArray = new String[template.size()];
            template.toArray(templateArray);
        }
        return templateArray;
    }
}
