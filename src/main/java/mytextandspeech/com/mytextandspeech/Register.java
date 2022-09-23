package mytextandspeech.com.mytextandspeech;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button register;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    private DBHelper dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        register= (Button)findViewById(R.id.idBtnRegister);
        ed1 = (EditText)findViewById(R.id.idEdtUserName);
        ed2 = (EditText)findViewById(R.id.idEdtPassword);
        ed3 = (EditText)findViewById(R.id.idEdtDOB);
        ed4 = (EditText)findViewById(R.id.idEdtAddress);
        ed5 = (EditText)findViewById(R.id.idEdtGender);
        ed6 = (EditText)findViewById(R.id.idEdtGuardianName);
        ed7 = (EditText)findViewById(R.id.idEdtGuardianPhone);
        dbHandler = new DBHelper(Register.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                String phone = ed2.getText().toString();
                String dob = ed3.getText().toString();
                String address = ed4.getText().toString();
                String gender = ed5.getText().toString();
                String gName = ed6.getText().toString();
                String gPhone = ed7.getText().toString();

                // validating if the text fields are empty or not.
                if (name.isEmpty() || phone.isEmpty() || dob.isEmpty() || address.isEmpty() || gender.isEmpty() || gName.isEmpty() || gPhone.isEmpty()) {
                    Toast.makeText(Register.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    dbHandler.addNewUser(name, gender, address, phone, dob, gName, gPhone);
                    Toast.makeText(Register.this, "Register Successfully pls login again!!!.", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this, LoginActivity.class);
                    //   Intent i = new Intent(LoginActivity.this, Menu.class);
                    startActivity(i);
                    finish();
                }

            }
            });

    }

}
