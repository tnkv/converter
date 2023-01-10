package ru.tnkv.converter.utils;

import android.content.Context;
import org.json.JSONException;

import ru.tnkv.converter.R;

public class Convert {
    static Context context;

    public Convert(Context context){
        this.context = context;
    }


    public static double currencyConvertation(double count, String currency, boolean isToConvertation) throws JSONException {

        Double course;

        if (currency.equals(context.getString(R.string.cur_aud))) {
            course = GetCourse.getCourse("AUD");
        } else if (currency.equals(context.getString(R.string.cur_azn))) {
            course = GetCourse.getCourse("AZN");
        } else if (currency.equals(context.getString(R.string.cur_gbp))) {
            course = GetCourse.getCourse("GBP");
        } else if (currency.equals(context.getString(R.string.cur_amd))) {
            course = GetCourse.getCourse("AMD");
        } else if (currency.equals(context.getString(R.string.cur_byn))) {
            course = GetCourse.getCourse("BYN");
        } else if (currency.equals(context.getString(R.string.cur_bgn))) {
            course = GetCourse.getCourse("USD");
        } else if (currency.equals(context.getString(R.string.cur_brl))) {
            course = GetCourse.getCourse("BRL");
        } else if (currency.equals(context.getString(R.string.cur_huf))) {
            course = GetCourse.getCourse("HUF");
        } else if (currency.equals(context.getString(R.string.cur_hkd))) {
            course = GetCourse.getCourse("HKD");
        } else if (currency.equals(context.getString(R.string.cur_dkk))) {
            course = GetCourse.getCourse("DKK");
        } else if (currency.equals(context.getString(R.string.cur_usd))) {
            course = GetCourse.getCourse("USD");
        } else if (currency.equals(context.getString(R.string.cur_eur))) {
            course = GetCourse.getCourse("EUR");
        } else if (currency.equals(context.getString(R.string.cur_inr))) {
            course = GetCourse.getCourse("INR");
        } else if (currency.equals(context.getString(R.string.cur_kzt))) {
            course = GetCourse.getCourse("KZT");
        } else if (currency.equals(context.getString(R.string.cur_cad))) {
            course = GetCourse.getCourse("CAD");
        } else if (currency.equals(context.getString(R.string.cur_kgs))) {
            course = GetCourse.getCourse("KGS");
        } else if (currency.equals(context.getString(R.string.cur_cny))) {
            course = GetCourse.getCourse("CNY");
        } else if (currency.equals(context.getString(R.string.cur_mdl))) {
            course = GetCourse.getCourse("MDL");
        } else if (currency.equals(context.getString(R.string.cur_nok))) {
            course = GetCourse.getCourse("NOK");
        } else if (currency.equals(context.getString(R.string.cur_pln))) {
            course = GetCourse.getCourse("PLN");
        } else if (currency.equals(context.getString(R.string.cur_ron))) {
            course = GetCourse.getCourse("RON");
        } else if (currency.equals(context.getString(R.string.cur_sgd))) {
            course = GetCourse.getCourse("SGD");
        } else if (currency.equals(context.getString(R.string.cur_tjs))) {
            course = GetCourse.getCourse("TJS");
        } else if (currency.equals(context.getString(R.string.cur_try))) {
            course = GetCourse.getCourse("TRY");
        } else if (currency.equals(context.getString(R.string.cur_tmt))) {
            course = GetCourse.getCourse("TMT");
        } else if (currency.equals(context.getString(R.string.cur_uzs))) {
            course = GetCourse.getCourse("UZS");
        } else if (currency.equals(context.getString(R.string.cur_uah))) {
            course = GetCourse.getCourse("UAH");
        } else if (currency.equals(context.getString(R.string.cur_czk))) {
            course = GetCourse.getCourse("CZK");
        } else if (currency.equals(context.getString(R.string.cur_sek))) {
            course = GetCourse.getCourse("SEK");
        } else if (currency.equals(context.getString(R.string.cur_chf))) {
            course = GetCourse.getCourse("CHF");
        } else if (currency.equals(context.getString(R.string.cur_zar))) {
            course = GetCourse.getCourse("ZAR");
        } else if (currency.equals(context.getString(R.string.cur_krw))) {
            course = GetCourse.getCourse("KRW");
        } else if (currency.equals(context.getString(R.string.cur_jpy))) {
            course = GetCourse.getCourse("JPY");
        } else course = 1.0;

        return (isToConvertation ? count * course : count / course);
    }
}
