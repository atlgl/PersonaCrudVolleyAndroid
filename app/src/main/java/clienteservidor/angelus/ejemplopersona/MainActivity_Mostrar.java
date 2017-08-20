package clienteservidor.angelus.ejemplopersona;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import clienteservidor.angelus.ejemplopersona.modelo.Persona;

public class MainActivity_Mostrar extends AppCompatActivity {

    String url="http://www.legionx.com.mx/inventariolabs/public/android/persona";
    private TextView txtid;
    private EditText txtnombre;
    private EditText txtapellido;
    private EditText edad;
    private Spinner estadocivil;
    private Button btnguardar;
    private Button btnregresar;

    private RequestQueue volley;
    private String oper;

    private String opcSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__mostrar);


        txtid=(TextView) findViewById(R.id.txtid);
        txtnombre=(EditText) findViewById(R.id.txtnombre);
        txtapellido=(EditText) findViewById(R.id.txtape);
        edad=(EditText) findViewById(R.id.txtedad);
        estadocivil=(Spinner) findViewById(R.id.txtestado);

        btnguardar=(Button) findViewById(R.id.btnguardar);
        btnregresar=(Button) findViewById(R.id.btnregresar);

        estadocivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                opcSpinner=(String)estadocivil.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent intent=getIntent();
        oper=intent.getStringExtra("operacion");
        volley= Volley.newRequestQueue(getBaseContext());

        if(oper.equals("editar")){
            Persona persona=intent.getParcelableExtra("persona");
            txtid.setText(String.valueOf(persona.getId()));
            txtnombre.setText(persona.getNombre());
            txtapellido.setText(persona.getApellidos());
            edad.setText(persona.getFechanac().toString());
            estadocivil.setSelection(1);
        }


        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oper.equals("guardar"))
                {
                    savePersona(1);
                }
                else if(oper.equals("editar")){

                    savePersona(2);

                }
            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity_Mostrar.this);
                alerta.setTitle("Aviso");
                alerta.setCancelable(true);
                alerta.setMessage("Esta Seguro de eliminar el elemento");
                alerta.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        savePersona(3);
                    }
                });
                alerta.show();

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

    private void savePersona(int opc){


        JSONObject object = new JSONObject();
        try {

            if (opc==2){
                object.put("id", txtid.getText().toString());
                url=url+"/"+txtid.getText().toString();
            }
            if(opc==3){
                url=url+"/"+txtid.getText().toString();
            }

            object.put("nombre", txtnombre.getText().toString());
            object.put("apellidos", txtapellido.getText().toString());
            object.put("estadocivil", opcSpinner);
            object.put("fechanac", edad.getText().toString());
        }catch (Exception ex){
            ex.printStackTrace();
        }



        JsonObjectRequest objectRequest=new JsonObjectRequest(opc,url,object,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(getBaseContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        volley.add(objectRequest);

    }






}
