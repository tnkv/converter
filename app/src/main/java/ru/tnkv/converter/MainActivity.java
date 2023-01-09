package ru.tnkv.converter;

import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ru.tnkv.converter.utils.*;

public class MainActivity extends AppCompatActivity {
    public static JSONObject valute_json;
    RequestQueue mRequestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);


        cbrfRequest("https://www.cbr-xml-daily.ru/daily_json.js"); // url центробанка
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
    public double currencyConvertation(double count, String currency, boolean isToConvertation) throws JSONException {

        Double course;
        Log.w("curConvert", "before if");

        if (currency.equals(getString(R.string.cur_aud))) {
            course = GetCourse.getCourse("AUD");
        } else if (currency.equals(getString(R.string.cur_azn))) {
            course = GetCourse.getCourse("AZN");
        } else if (currency.equals(getString(R.string.cur_gbp))) {
            course = GetCourse.getCourse("GBP");
        } else if (currency.equals(getString(R.string.cur_amd))) {
            course = GetCourse.getCourse("AMD");
        } else if (currency.equals(getString(R.string.cur_byn))) {
            course = GetCourse.getCourse("BYN");
        } else if (currency.equals(getString(R.string.cur_bgn))) {
            course = GetCourse.getCourse("USD");
        } else if (currency.equals(getString(R.string.cur_brl))) {
            course = GetCourse.getCourse("BRL");
        } else if (currency.equals(getString(R.string.cur_huf))) {
            course = GetCourse.getCourse("HUF");
        } else if (currency.equals(getString(R.string.cur_hkd))) {
            course = GetCourse.getCourse("HKD");
        } else if (currency.equals(getString(R.string.cur_dkk))) {
            course = GetCourse.getCourse("DKK");
        } else if (currency.equals(getString(R.string.cur_usd))) {
            course = GetCourse.getCourse("USD");
        } else if (currency.equals(getString(R.string.cur_eur))) {
            course = GetCourse.getCourse("EUR");
        } else if (currency.equals(getString(R.string.cur_inr))) {
            course = GetCourse.getCourse("INR");
        } else if (currency.equals(getString(R.string.cur_kzt))) {
            course = GetCourse.getCourse("KZT");
        } else if (currency.equals(getString(R.string.cur_cad))) {
            course = GetCourse.getCourse("CAD");
        } else if (currency.equals(getString(R.string.cur_kgs))) {
            course = GetCourse.getCourse("KGS");
        } else if (currency.equals(getString(R.string.cur_cny))) {
            course = GetCourse.getCourse("CNY");
        } else if (currency.equals(getString(R.string.cur_mdl))) {
            course = GetCourse.getCourse("MDL");
        } else if (currency.equals(getString(R.string.cur_nok))) {
            course = GetCourse.getCourse("NOK");
        } else if (currency.equals(getString(R.string.cur_pln))) {
            course = GetCourse.getCourse("PLN");
        } else if (currency.equals(getString(R.string.cur_ron))) {
            course = GetCourse.getCourse("RON");
        } else if (currency.equals(getString(R.string.cur_sgd))) {
            course = GetCourse.getCourse("SGD");
        } else if (currency.equals(getString(R.string.cur_tjs))) {
            course = GetCourse.getCourse("TJS");
        } else if (currency.equals(getString(R.string.cur_try))) {
            course = GetCourse.getCourse("TRY");
        } else if (currency.equals(getString(R.string.cur_tmt))) {
            course = GetCourse.getCourse("TMT");
        } else if (currency.equals(getString(R.string.cur_uzs))) {
            course = GetCourse.getCourse("UZS");
        } else if (currency.equals(getString(R.string.cur_uah))) {
            course = GetCourse.getCourse("UAH");
        } else if (currency.equals(getString(R.string.cur_czk))) {
            course = GetCourse.getCourse("CZK");
        } else if (currency.equals(getString(R.string.cur_sek))) {
            course = GetCourse.getCourse("SEK");
        } else if (currency.equals(getString(R.string.cur_chf))) {
            course = GetCourse.getCourse("CHF");
        } else if (currency.equals(getString(R.string.cur_zar))) {
            course = GetCourse.getCourse("ZAR");
        } else if (currency.equals(getString(R.string.cur_krw))) {
            course = GetCourse.getCourse("KRW");
        } else if (currency.equals(getString(R.string.cur_jpy))) {
            course = GetCourse.getCourse("JPY");
        } else course = 1.0;

        return (isToConvertation ? count * course : count / course);
    }

    public void solveCurrency(View view) throws JSONException {
        TextView result = (TextView) findViewById(R.id.result_text);
        Spinner fromCurSpin = (Spinner) findViewById(R.id.fromCurrency);
        String fromCurSpinText = fromCurSpin.getSelectedItem().toString();

        Spinner toCurSpin = (Spinner) findViewById(R.id.toCurrency);
        String toCurSpinText = toCurSpin.getSelectedItem().toString(), inEditText = ((EditText) findViewById(R.id.fromCurrencyNumber)).getText().toString();

        if (!inEditText.equals("")) {
            result.setText(String.format("%.2f", currencyConvertation(currencyConvertation(Double.parseDouble(inEditText), fromCurSpinText, true), toCurSpinText, false)));
        } else {
            result.setText(getString(R.string.not_empty));
        }
    }

    public void cbrfRequest(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    valute_json = response.getJSONObject("Valute"); //получаем JSON Валют
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}

