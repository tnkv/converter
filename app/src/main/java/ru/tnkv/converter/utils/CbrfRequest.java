package ru.tnkv.converter.utils;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import ru.tnkv.converter.MainActivity;


public class CbrfRequest {

    public static void request(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                MainActivity.valute_json = response.getJSONObject("Valute"); //получаем JSON Валют
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, Throwable::printStackTrace);
        MainActivity.mRequestQueue.add(request);
    }

}
