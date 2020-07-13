package util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences
{
    private Context applicationContext;
    private final static String KEY ="GeolumenLocalizer";

    //Costruttore
    public MySharedPreferences(Context applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    //Salva una chiave booleana
    public void salvaDatoBoolean(String key, boolean bool)
    {
        SharedPreferences preferences = applicationContext.getSharedPreferences(KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, bool);
        editor.commit();
    }

    //Recupera una chiave booleana
    public boolean recuperaDatoBoolean(String key)
    {
        SharedPreferences preferences = applicationContext.getSharedPreferences(KEY, MODE_PRIVATE);
        return  preferences.getBoolean(key,false);
    }

    //Salva una chiava stringa
    public void salvaDatoStringa(String key, String string)
    {
        SharedPreferences preferences = applicationContext.getSharedPreferences(KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,string);
        editor.commit();
    }

    //Recupera una chiava stringa
    public String recuperaDatoStringa(String key)
    {
        SharedPreferences preferences = applicationContext.getSharedPreferences(KEY, MODE_PRIVATE);
        return preferences.getString(key, null);
    }
}
