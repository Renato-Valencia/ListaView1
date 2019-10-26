package com.example.listaview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.clase.AdaptadorNoticias;
import com.clase.Noticias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    public Noticias[] noticias;

    //public Noticias[] noticias =
    //      new Noticias[]{
    //            new Noticias("Noticia 1", "SubNoticia Contenido Contenido Contenido Contenido 1"),
    //          new Noticias("Noticia 2", "SubNoticia Contenido Contenido Contenido Contenido 2"),
    //        new Noticias("Noticia 3", "SubNoticia Contenido Contenido Contenido Contenido 3"),
    //      new Noticias("Noticia 4", "SubNoticia Contenido Contenido Contenido Contenido 4")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    ListView lstOpciones;

    public void ejecutarWebService(View dt) {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://api.androidhive.info/contacts/", datos,
                MainActivity.this, MainActivity.this);
        ws.execute("");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish", result);
        lstOpciones = (ListView) findViewById(R.id.listanoticias);
        parsearJSON(result);
    }

    public void parsearJSON(String jsonStr) throws JSONException {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(jsonStr);
        JSONArray contacts = jsonObj.getJSONArray("contacts");

        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);
            String id = c.getString("id");
            String name = c.getString("name");
            String email = c.getString("email");
            String address = c.getString("address");
            String gender = c.getString("gender");

            JSONObject phone = c.getJSONObject("phone");
            String mobile = phone.getString("mobile");
            String home = phone.getString("home");
            String office = phone.getString("office");

            HashMap<String, String> contact = new HashMap<>();
            contact.put("id", id);
            contact.put("name", name);
            contact.put("email", email);
            contact.put("mobile", mobile);
            contactList.add(contact);
        }
        String info = "";
        for (int x = 0; x < contactList.size(); x++) {
            HashMap<String, String> dato = contactList.get(x);
            for (Map.Entry<String, String> z : dato.entrySet()) {
                info = info + "" + z.getKey() + ": " + z.getValue() + "\n";
            }

            noticias = new Noticias[]{new Noticias("", info)
            // info=info+"\n";
        };
            // txtresultado.setText(info);
        }
        View header = getLayoutInflater().inflate(R.layout.cabecera, null);
        lstOpciones.addHeaderView(header);

        AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);
        lstOpciones.setAdapter(adaptadornoticias);
    }
}
