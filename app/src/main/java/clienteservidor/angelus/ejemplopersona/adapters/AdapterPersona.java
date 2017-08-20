package clienteservidor.angelus.ejemplopersona.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import clienteservidor.angelus.ejemplopersona.R;
import clienteservidor.angelus.ejemplopersona.modelo.Persona;

/**
 * Created by Angelus on 19/08/2017.
 */

public class AdapterPersona extends ArrayAdapter<Persona> {

    private Context ctx;
    private List<Persona> personaList;
    private TextView txtid;
    private TextView txtnombre;
    private TextView txtapellidos;
    private TextView txtfechanac;
    private TextView txtestadocivil;



    public AdapterPersona(@NonNull Context context,List<Persona> personaList) {
        super(context, R.layout.item_persona,personaList);
        this.personaList=personaList;
        this.ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.item_persona,null,false);

        txtid=(TextView) view.findViewById(R.id.item_id);
        txtnombre=(TextView) view.findViewById(R.id.item_name);
        txtapellidos=(TextView) view.findViewById(R.id.itemapellidos);
        txtfechanac=(TextView) view.findViewById(R.id.itemfechanac);
        txtestadocivil=(TextView) view.findViewById(R.id.itemestadocivil);


        txtid.setText(String.valueOf(personaList.get(position).getId()));
        txtnombre.setText(personaList.get(position).getNombre());
        txtapellidos.setText(personaList.get(position).getApellidos());
        txtestadocivil.setText(personaList.get(position).getEstadocivil());
        txtfechanac.setText(personaList.get(position).getFechanac().toString());

        return view;
    }

}
