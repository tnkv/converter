package ru.tnkv.converter;

import androidx.appcompat.app.AppCompatActivity;


import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    double value = 1, cur;
    int nominal = 1;
    RequestQueue mRequestQueue; // очередь запросов

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        String cur_rub = getString(R.string.cur_rub), cur_aud = getString(R.string.cur_aud),
                cur_azn = getString(R.string.cur_azn), cur_gbp = getString(R.string.cur_gbp),
                cur_amd = getString(R.string.cur_amd), cur_byn = getString(R.string.cur_byn),
                cur_bgn = getString(R.string.cur_bgn), cur_brl = getString(R.string.cur_brl),
                cur_huf = getString(R.string.cur_huf), cur_hkd = getString(R.string.cur_hkd),
                cur_dkk = getString(R.string.cur_dkk), cur_usd = getString(R.string.cur_usd),
                cur_eur = getString(R.string.cur_eur), cur_inr = getString(R.string.cur_inr),
                cur_kzt = getString(R.string.cur_kzt), cur_cad = getString(R.string.cur_cad),
                cur_kgs = getString(R.string.cur_kgs), cur_cny = getString(R.string.cur_cny),
                cur_mdl = getString(R.string.cur_mdl), cur_nok = getString(R.string.cur_nok),
                cur_pln = getString(R.string.cur_pln), cur_ron = getString(R.string.cur_ron),
                cur_sgd = getString(R.string.cur_sgd), cur_tjs = getString(R.string.cur_tjs),
                cur_try = getString(R.string.cur_try), cur_tmt = getString(R.string.cur_tmt),
                cur_uzs = getString(R.string.cur_uzs), cur_uah = getString(R.string.cur_uah),
                cur_czk = getString(R.string.cur_czk), cur_sek = getString(R.string.cur_sek),
                cur_chf = getString(R.string.cur_chf), cur_zar = getString(R.string.cur_zar),
                cur_krw = getString(R.string.cur_krw), cur_jpy = getString(R.string.cur_jpy);


        String[] currency = {cur_rub, cur_aud, cur_azn, cur_gbp,
                cur_amd, cur_byn, cur_bgn, cur_brl,
                cur_huf, cur_hkd, cur_dkk, cur_usd,
                cur_eur, cur_inr, cur_kzt, cur_cad,
                cur_kgs, cur_cny, cur_mdl, cur_nok,
                cur_pln, cur_ron, cur_sgd, cur_tjs,
                cur_try, cur_tmt, cur_uzs, cur_uah,
                cur_czk, cur_sek, cur_chf, cur_zar,
                cur_krw, cur_jpy};

        Spinner fromCurrency = findViewById(R.id.fromCurrency);
        Spinner toCurrency = findViewById(R.id.toCurrency);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        fromCurrency.setOnItemSelectedListener(itemSelectedListener);


    }

    public double toRubleCurrency(double count, String currency) throws JSONException {
        Log.w("toRubbleCur", "count = " + count + " cur = " + currency);
        if (currency.equals(getString(R.string.cur_rub))) {
            return count * 1;
        } else if (currency.equals(getString(R.string.cur_usd))) {
            getCourse("USD");
            return count * cur;
        } else if (currency.equals(getString(R.string.cur_try))) {
            getCourse("TRY");
            return count * cur;
        } else return count;
    }

    public double fromRubleCurrency(double count, String currency) {
        Log.w("toRubbleCur", "count = " + count + " cur = " + currency);
        if (currency.equals(getString(R.string.cur_rub))) {
            return count / 1;
        } else if (currency.equals(getString(R.string.cur_usd))) {
            getCourse("USD");
            return count / cur;
        } else if (currency.equals(getString(R.string.cur_try))) {
            getCourse("TRY");
            return count / cur;
        } else return count;
    }


    public void solveCurrency(View view) throws JSONException {
        TextView result = (TextView) findViewById(R.id.result_text);
        Spinner fromCurSpin = (Spinner) findViewById(R.id.fromCurrency);
        String fromCurSpinText = fromCurSpin.getSelectedItem().toString();

        Spinner toCurSpin = (Spinner) findViewById(R.id.toCurrency);
        String toCurSpinText = toCurSpin.getSelectedItem().toString(), inEditText = ((EditText) findViewById(R.id.fromCurrencyNumber)).getText().toString();


        if (!inEditText.equals("")) {
            //  result.setText(String.format("%.2f", ));
            result.setText(String.format("%.2f", fromRubleCurrency(toRubleCurrency(Double.parseDouble(inEditText), fromCurSpinText), toCurSpinText)));
        } else {
            result.setText(getString(R.string.not_empty));
        }
    }

    public String getCourse(String valute) {
        final String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject valute_json = response.getJSONObject("Valute").getJSONObject(valute); //получаем JSON-обьекты main и wind (в фигурных скобках - объекты, в квадратных - массивы (JSONArray).
                    value = valute_json.getDouble("Value");
                    nominal = valute_json.getInt("Nominal");
                    cur = value / nominal;
                    Log.w("Currency near sv", "Before return: value = " + value + " nom = " + nominal);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { // в случае возникновеня ошибки
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
        return null;
    }

}
