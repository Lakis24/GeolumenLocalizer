package util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.neutro.geolumenlocalizer.R;

public class GestionePermessi extends Activity
{
    //Contesto
    private Context context;
    //Permessi posizione
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    //Permessi posizione
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    //Permessi lettura
    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    //Permessi scrittura
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    //Permessi garantiti
    private static final int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;
    //Permessi camera
    private static final String CAMERA = Manifest.permission.CAMERA;
    //Request code
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 3;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 5;
    private static final int PERMISSION_REQUEST_CODE=4;

    public GestionePermessi(Context context)
    {
        this.context = context;
    }


    //Controlla permessi
    public boolean checkPermession()
    {
        String[] permissions = {READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE, CAMERA,FINE_LOCATION,COARSE_LOCATION,};
        //Non uso for generalizzato per fare la differenza tra i tipi di permessi
        if ((ContextCompat.checkSelfPermission(context.getApplicationContext(),permissions[0])  == PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context.getApplicationContext(),permissions[1])  == PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context.getApplicationContext(),permissions[2])  == PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context.getApplicationContext(),permissions[3])  == PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(context.getApplicationContext(),permissions[4])  == PERMISSION_GRANTED))
        {
            //Permessi garantiti
            return true;
        }
        else
        {
            //Permessi non garantiti
            ActivityCompat.requestPermissions((Activity) context, permissions, PERMISSION_REQUEST_CODE);
            return false;
        }
    }

    //Crea il messagio PopUp
    public void buildPermissionMessage()
    {
        final String[] permissions = {READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE, CAMERA,FINE_LOCATION,COARSE_LOCATION,};
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.messaggio_permessi)
                .setTitle(R.string.attenzione)
                .setCancelable(false)
                .setPositiveButton(R.string.riprova, new DialogInterface.OnClickListener()
                {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                    {
                        ((Activity) context).requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                    }
                })
                .setNegativeButton(R.string.annulla, new DialogInterface.OnClickListener()
                {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                    {
                        dialog.cancel();
                        //chiudi l'app poichè non può essere utilizzata
                        ((Activity)context).finish();
                        System.exit(0);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(context.getColor(R.color.colorPrimary));
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(context.getColor(R.color.colorPrimary));
    }

}
