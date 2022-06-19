package com.example.realbreadbeta;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesionFragment extends Fragment {

    private EditText correo, contraseña;
    private Button iniciarSesion;
    private String correoDelUsuario;
    private String contraseñaDelUsuario;

    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_iniciar_sesion,container, false);

        correo = root.findViewById(R.id.correo2);
        contraseña = root.findViewById(R.id.contraseña);
        iniciarSesion = root.findViewById(R.id.iniciar_sesion_boton);
        mAuth = FirebaseAuth.getInstance();


        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //correo.setText("ejemplo@gmail.com");
                //contraseña.setText("123456");

                correoDelUsuario = correo.getText().toString().trim();
                contraseñaDelUsuario = contraseña.getText().toString().trim();

                if(!correoDelUsuario.isEmpty() && !contraseñaDelUsuario.isEmpty()){
                    if(contraseña.length() >= 6 ){
                        mAuth.signInWithEmailAndPassword(correoDelUsuario,contraseñaDelUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid = user.getUid();
                                    Toast.makeText(getActivity(),"Has iniciado sesión correctamente",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("id",uid);
                                    getActivity().startActivity(intent);
                                    getActivity().finish();
                                }
                                else{
                                    Toast.makeText(getActivity(),"Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                    else{
                        Toast.makeText(getActivity(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                }

                else{
                    Toast.makeText(getActivity(), "Ambos campos son obligatorios", Toast.LENGTH_LONG).show();
                }

            }
        });

        return root;
    }
}

