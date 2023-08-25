package com.valesoft.conversordemoneda;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData <Double> mConversion;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Double> getMConversion(){
        if(mConversion == null){
            mConversion = new MutableLiveData<>();
        }
        return mConversion;
    }

    public void convertirMonto(String montoD, String montoE, Boolean rbD, Boolean rbE){
        Double cambio = (double) 0;

        if(rbD && montoD.length()>0){
            cambio = Double.parseDouble(montoD);
            cambio = cambio * 0.93;
        } else if(rbD && montoD.length()==0){
            Toast.makeText(context, "Ingrese una cantidad de Dolares para convertir", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rbE && montoE.length()>0){
            cambio = Double.parseDouble(montoE);
            cambio = cambio * 1.08;
        } else if(rbE && montoE.length()==0){
            Toast.makeText(context, "Ingrese una cantidad de Euros para convertir", Toast.LENGTH_SHORT).show();
            return;
        }

        mConversion.setValue(cambio);
    }
}
