package sg.edu.rp.c347.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class ContactDetail extends AppCompatActivity {

    int id;
    String firstname,lastname,mobile;

    private EditText etFirstName, etLastName, etMobile;
    private Button btnEdit,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Intent i = getIntent();
        id = i.getIntExtra("id",0);
        firstname = i.getStringExtra("firstname");
        lastname = i.getStringExtra("lastname");
        mobile = i.getStringExtra("mobile");


        etFirstName = (EditText)findViewById(R.id.dtFirstName);
        etLastName = (EditText)findViewById(R.id.dtLastName);
        etMobile = (EditText)findViewById(R.id.dtMobile);

        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        etFirstName.setText(firstname);
        etLastName.setText(lastname);
        etMobile.setText(mobile);



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                String url = "http://10.0.2.2/C302_CloudAddressBook/updateContact.php";

                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("id", id+"");
                request.addData("FirstName", etFirstName.getText().toString());
                request.addData("LastName", etLastName.getText().toString());
                request.addData("Mobile", etMobile.getText().toString());

                request.execute();
                finish();
                // Code for step 1 end
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                String url = "http://10.0.2.2/C302_CloudAddressBook/deleteContact.php";

                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("id", id+"");
                request.execute();
                finish();
                // Code for step 1 end
            }
        });




    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
