package com.dev.treecount.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dev.treecount.R;
import com.dev.treecount.adapter.ParcelaAdapter;
import com.dev.treecount.model.Parcela;

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
import java.util.List;

public class GetHTTPParcelas extends AsyncTask<Void, Void, String> {
    private List<Parcela> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    ProgressDialog progressDialog;

    public GetHTTPParcelas(List<Parcela> httpList, RecyclerView httpRecycler, RecyclerView.Adapter httpAdapter, Context httpContext) {
        this.httpList = httpList;
        this.httpRecycler = httpRecycler;
        this.httpAdapter = httpAdapter;
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

        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("parcelas");
            for (int i=0; i<jsonArray.length();i++){
                int idParcela = Integer.parseInt(jsonArray.getJSONObject(i).getString("idParcela"));
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String departamento = jsonArray.getJSONObject(i).getString("departamento");
                int idBrigada = Integer.parseInt(jsonArray.getJSONObject(i).getString("idBrigada"));
                String brigadaNombre = jsonArray.getJSONObject(i).getString("brigadaNombre");
                float refLatitud = Float.parseFloat(jsonArray.getJSONObject(i).getString("ref_latitud"));
                float refLongitud = Float.parseFloat(jsonArray.getJSONObject(i).getString("ref_longitud"));

                this.httpList.add(new Parcela(idParcela, nombre, refLatitud, refLongitud, 0, 0, 0, 0, 0, 0, 0, 0));

            }
            httpAdapter = new ParcelaAdapter(this.httpList);
            httpRecycler.setAdapter(this.httpAdapter);

            String msg = String.valueOf(httpAdapter.getItemCount()) + " registros";
            Toast.makeText(httpContext, msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        String  result = null;
        try {
            String wsURL = "http://demos.hypergis.pe/alumnos/cvalenzuela/wsParcela.php?user=cvalenzuela@upc.edu.pe&format=json";
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