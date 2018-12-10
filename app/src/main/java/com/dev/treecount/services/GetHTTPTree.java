package com.dev.treecount.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.dev.treecount.R;
import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Parcela;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class GetHTTPTree extends AsyncTask<Void, Void, String> {
    private Context httpContext;
    ProgressDialog progressDialog;

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = currentUser.getEmail();

    public GetHTTPTree(Context httpContext) {
        this.httpContext = httpContext;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, httpContext.getString(R.string.popMsgTitulo), httpContext.getString(R.string.popMsgMensaje));
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();
        TreeDBHelper db = new TreeDBHelper(httpContext);

        db.limpiaTabla("parcela");
        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("parcelas");
            for (int i=0; i<jsonArray.length();i++){

                float refLatitud  = 0;
                float refLongitud = 0;
                float p1Latitud = 0;
                float p1Longitud = 0;
                float p2Latitud = 0;
                float p2Longitud = 0;
                float p3Latitud = 0;
                float p3Longitud = 0;
                float p4Latitud = 0;
                float p4Longitud = 0;
                String latitud;
                String longitud;

                int idParcela = Integer.parseInt(jsonArray.getJSONObject(i).getString("idParcela"));
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                latitud = jsonArray.getJSONObject(i).getString("ref_latitud");
                if(latitud != null) refLatitud = Float.parseFloat(latitud);
                longitud = jsonArray.getJSONObject(i).getString("ref_longitud");
                if(longitud != null) refLongitud = Float.parseFloat(longitud);
                latitud = jsonArray.getJSONObject(i).getString("p1_latitud");
                if(latitud != null) p1Latitud = Float.parseFloat(latitud);
                longitud = jsonArray.getJSONObject(i).getString("p1_longitud");
                if(longitud != null) p1Longitud = Float.parseFloat(longitud);
                latitud = jsonArray.getJSONObject(i).getString("p2_latitud");
                if(latitud != null) p2Latitud = Float.parseFloat(latitud);
                longitud = jsonArray.getJSONObject(i).getString("p2_longitud");
                if(longitud != null) p2Longitud = Float.parseFloat(longitud);
                latitud = jsonArray.getJSONObject(i).getString("p3_latitud");
                if(latitud != null) p3Latitud = Float.parseFloat(latitud);
                longitud = jsonArray.getJSONObject(i).getString("p3_longitud");
                if(longitud != null) p3Longitud = Float.parseFloat(longitud);
                latitud = jsonArray.getJSONObject(i).getString("p4_latitud");
                if(latitud != null) p4Latitud = Float.parseFloat(latitud);
                longitud = jsonArray.getJSONObject(i).getString("p4_longitud");
                if(longitud != null) p4Longitud = Float.parseFloat(longitud);
                String departamento = jsonArray.getJSONObject(i).getString("departamento");
                int idBrigada = Integer.parseInt(jsonArray.getJSONObject(i).getString("idBrigada"));
                String brigadaNombre = jsonArray.getJSONObject(i).getString("brigadaNombre");

                db.saveParcela(new Parcela(idParcela, nombre, refLatitud, refLongitud, p1Latitud, p1Longitud, p2Latitud, p2Longitud, p3Latitud, p3Longitud, p4Latitud, p4Longitud, departamento, idBrigada, brigadaNombre));
            }

            String msg = String.valueOf(jsonArray.length()) + " registros de parcelas";
            Toast.makeText(httpContext, msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String wsURL = "http://demos.hypergis.pe/alumnos/cvalenzuela/wsParcela.php?user=" + userEmail + "&format=json";
            URL url = new URL(wsURL);
            HttpURLConnection urlCon = (HttpURLConnection)  url.openConnection();
            InputStream in = new BufferedInputStream(urlCon.getInputStream());
            result = inputStreamToString(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String inputStreamToString(InputStream in) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader rd = new BufferedReader(isr);
        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
}