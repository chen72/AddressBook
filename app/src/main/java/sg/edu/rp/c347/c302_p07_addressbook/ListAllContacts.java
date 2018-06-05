package sg.edu.rp.c347.c302_p07_addressbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAllContacts extends AppCompatActivity {

    ListView lv;
    ContactAdapter ca;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_contacts);



    }

    protected void onResume() {
        super.onResume();
//
//        lvCategories = (ListView) findViewById(R.id.listViewCategories);
//        aaCategories = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alCategories);
//        lvCategories.setAdapter(aaCategories);

        contacts = new ArrayList<Contact>();

        lv = (ListView)findViewById(R.id.lv) ;
        ca = new ContactAdapter(this, R.layout.row, contacts);
        lv.setAdapter(ca);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c = contacts.get(position);
                int cID = c.getId();
                String cFname = c.getFirstname();
                String cLname = c.getLastname();
                String cMobile = c.getMobile();

                Intent i = new Intent(ListAllContacts.this,
                        ContactDetail.class);
                i.putExtra("id", cID);
                i.putExtra("firstname", cFname);
                i.putExtra("lastname", cLname);
                i.putExtra("mobile", cMobile);
                Log.i("info", cID+"");
                startActivity(i);
            }
        });



        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_CloudAddressBook/getContacts.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int id = jsonObj.getInt("id");
                            String firstname = jsonObj.getString("firstname");
                            String lastname = jsonObj.getString("lastname");
                            String mobile = jsonObj.getString("mobile");
                            contacts.add(new Contact(id,firstname,lastname,mobile));
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    ca.notifyDataSetChanged();
                }
            };
    // Code for step 2 end
//Step 1
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //Step 2
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.CreateContact) {

            Intent intent =new Intent(getBaseContext(),MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
