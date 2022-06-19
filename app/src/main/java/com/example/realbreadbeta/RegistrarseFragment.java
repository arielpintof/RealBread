package com.example.realbreadbeta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseFragment extends Fragment implements View.OnClickListener {

    private EditText editNombre, editContraseña, editCelular, editCorreo;
    private Button registrarse;

    private String nombre;
    private String correo;
    private String celular;
    private String contraseña;

    FirebaseAuth mAuth;
    DatabaseReference unaBaseDeDatos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_regristarse,container, false);

        mAuth = FirebaseAuth.getInstance();
        unaBaseDeDatos = FirebaseDatabase.getInstance().getReference();

        editNombre =  root.findViewById(R.id.correo2);
        editContraseña =  root.findViewById(R.id.contraseña);
        editCelular =  root.findViewById(R.id.direccion);
        editCorreo =  root.findViewById(R.id.correo);
        registrarse =  root.findViewById(R.id.registrarse_boton);

        registrarse.setOnClickListener(this);
        return root;
    }

    public void registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("contraseña", contraseña);
                    map.put("celular", celular);


                    String id = mAuth.getCurrentUser().getUid();

                    unaBaseDeDatos.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()) {
                                Toast.makeText(getActivity(),"Te has registrado correctamente!",Toast.LENGTH_SHORT).show();;
                                Toast.makeText(getActivity(),"Ahora puedes iniciar sesión!",Toast.LENGTH_SHORT).show();;
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(),"Ha ocurrido un error.",Toast.LENGTH_SHORT);
                }
            }
        });


    }

    // este metodo verifica que cada editText no este nulo y que la contraseña no sea menor a 6 caracteres.
    @Override
    public void onClick(View v) {
        nombre = editNombre.getText().toString();
        contraseña = editContraseña.getText().toString();
        correo = editCorreo.getText().toString();
        celular = editCelular.getText().toString();

        if(!nombre.isEmpty() && !contraseña.isEmpty() && !correo.isEmpty() && !celular.isEmpty()){
            if(contraseña.length() >= 6 ){
                registrarUsuario();
            }
            else{
                Toast.makeText(getActivity(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
            }
        }

        else{
            Toast.makeText(getActivity(), "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
