package clienteservidor.angelus.ejemplopersona;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import clienteservidor.angelus.ejemplopersona.adapters.AdapterPersona;
import clienteservidor.angelus.ejemplopersona.modelo.Persona;

public class ListaPersonasActivity extends AppCompatActivity {
    private ListView listView;

    private Button btnbuscar;


    private List<Persona> personaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Agregar una nueva Persona", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i=new Intent(getBaseContext(),MainActivity_Mostrar.class);
                i.putExtra("operacion","guardar");
                startActivity(i);
            }
        });

        listView=(ListView) findViewById(R.id.listviewpersona);

        btnbuscar=(Button) findViewById(R.id.btnactualizar);
        getPersona();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(personaList!=null){
                  Intent intent=new Intent(getBaseContext(),MainActivity_Mostrar.class);
                    intent.putExtra("operacion","editar");
                    intent.putExtra("persona",personaList.get(position));
                    startActivity(intent);
                }
               // startActivity(new Intent(getBaseContext(),MainActivity_Mostrar.class));
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPersona();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getPersona();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPersona();
    }

    private void getPersona(){

        String url="http://www.legionx.com.mx/inventariolabs/public/android/persona";
        RequestQueue volleyCola= Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                personaList=new ArrayList<>();
                try{
                    for (int i=0;i<response.length();i++){
                        JSONObject object=response.getJSONObject(i);
                        Persona p=new Persona();
                        p.setId(object.getInt("id"));
                        p.setNombre(object.getString("nombre"));
                        p.setApellidos(object.getString("apellidos"));
                        p.setEstadocivil(object.getString("estadocivil"));
                        p.setFechanac(object.getString("fechanac"));
                        personaList.add(p);
                    }}catch (Exception ex){
                    ex.printStackTrace();
                }
                //Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_LONG).show();
                listView.setAdapter(new AdapterPersona(getBaseContext(),personaList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volleyCola.add(jsonArrayRequest);
    }









}
