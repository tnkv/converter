package ru.tnkv.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import ru.tnkv.converter.utils.*;

public class MainActivity extends AppCompatActivity {
    public static JSONObject valute_json;
    public static RequestQueue mRequestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = Volley.newRequestQueue(this);

        Convert convert = new Convert(this);
        CbrfRequest.request("https://www.cbr-xml-daily.ru/daily_json.js"); // URL Центробанка

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

    public void solveCurrency(View view) throws JSONException {
        TextView result = findViewById(R.id.result_text);
        Spinner fromCurSpin = findViewById(R.id.fromCurrency);
        String fromCurSpinText = fromCurSpin.getSelectedItem().toString();

        Spinner toCurSpin = findViewById(R.id.toCurrency);
        String toCurSpinText = toCurSpin.getSelectedItem().toString(), inEditText = ((EditText) findViewById(R.id.fromCurrencyNumber)).getText().toString();

        if (!inEditText.equals("")) {
            result.setText(String.format(Locale.US, "%.2f", Convert.currencyConvertation(Convert.currencyConvertation(Double.parseDouble(inEditText), fromCurSpinText, true), toCurSpinText, false)));
        } else {
            result.setText(getString(R.string.not_empty));
        }
    }
}

