package ru.tnkv.converter.utils;

import org.json.JSONException;
import org.json.JSONObject;

import ru.tnkv.converter.MainActivity;

public class GetCourse {
    public static double getCourse(String valute) throws JSONException {
        JSONObject selectedJson = MainActivity.valute_json.getJSONObject(valute);
        return selectedJson.getDouble("Value") / selectedJson.getDouble("Nominal");
    }
}
