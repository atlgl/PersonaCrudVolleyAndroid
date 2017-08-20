package clienteservidor.angelus.ejemplopersona;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.Authenticator;

public class MainActivity_Mostrar extends AppCompatActivity {

    String url="http://10.0.2.2/inventariolabs/public/android/persona";
    private TextView txtid;
    private EditText txtnombre;
    private EditText txtapellido;
    private EditText edad;
    private EditText estadocivil;
    private Button btnguardar;
    private Button btnregresar;
    RequestQueue volley;
    String oper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__mostrar);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtid=(TextView) findViewById(R.id.txtId);
        txtnombre=(EditText) findViewById(R.id.txtnombre);
        txtapellido=(EditText) findViewById(R.id.txtape);
        edad=(EditText) findViewById(R.id.txtedad);
        estadocivil=(EditText) findViewById(R.id.txtestado);

        btnguardar=(Button) findViewById(R.id.btnguardar);
        btnregresar=(Button) findViewById(R.id.btnregresar);

        Intent intent=getIntent();
        oper=intent.getStringExtra("operacion");
        volley= Volley.newRequestQueue(getBaseContext());

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oper.equals("guardar"))
                {
                    setPersona();
                }
            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main_activity__mostrar, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
         //   return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    private void setPersona(){


        JSONObject object = new JSONObject();
        try {

            object.put("_token","XM3VAVfvmvZJS2b73aVXD1stBBaTbYOwFNMPFm5p");
            object.put("nombre", txtnombre.getText().toString());
            object.put("apellidos", txtapellido.getText().toString());
            object.put("estadocivil", estadocivil.getText().toString());
            object.put("fechanac", edad.getText().toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.POST,url,object,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        volley.add(objectRequest);

    }


}
