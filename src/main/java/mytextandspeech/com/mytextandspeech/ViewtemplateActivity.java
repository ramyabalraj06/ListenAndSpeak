package mytextandspeech.com.mytextandspeech;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewtemplateActivity extends AppCompatActivity {

    private Button viewTemplateBtn;
    private DBHelper dbHandler;
    public TableLayout t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtemplate);
        getSupportActionBar().setTitle("Information");
    }
}
