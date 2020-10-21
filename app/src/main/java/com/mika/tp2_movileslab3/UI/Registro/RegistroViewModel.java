package com.mika.tp2_movileslab3.UI.Registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mika.tp2_movileslab3.Model.Usuario;
import com.mika.tp2_movileslab3.Request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String> mensaje;
    private Context context;
    private ApiClient apiClient;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
        apiClient= new ApiClient();
    }

    public LiveData<Usuario> getUsuario() {
        if (usuario==null){
            usuario=new MutableLiveData<>();
        }
        return usuario;
    }

    public LiveData<String> getMensaje() {
        if (mensaje==null){
            mensaje= new MutableLiveData<>();
        }
        return mensaje;
    }

    public void registrar(String dni, String apellido, String nombre, String email, String pass){
        int dni1=Integer.parseInt(dni);
        Usuario u= new Usuario(dni1,apellido,nombre,email,pass);
        String msj = apiClient.guardar(context,u);
        mensaje.setValue(msj);
    }

    public void mostrar(Usuario u){
        if( u != null) {
            u = apiClient.leer(context);
            usuario.setValue(u);
        }
    }
}
