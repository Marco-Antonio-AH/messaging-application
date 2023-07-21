package com.example.ccln2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private Button buttonLogin, buttonRegister;

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        usersRef = FirebaseDatabase.getInstance().getReference("users");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                loginWithPhoneNumberAndPassword(phoneNumber, password);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                registerUser(phoneNumber, password);
            }
        });
    }





    private void registerUser(String phoneNumber, String password) {
        // Aquí puedes realizar las validaciones necesarias antes de registrar al usuario

        // Obtén una referencia a la colección "users" en la base de datos
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Genera un nuevo ID para el usuario
        String userId = usersRef.push().getKey();

        // Crea un objeto User con los datos del nuevo usuario
        User user = new User(phoneNumber, password);

        // Guarda el objeto User en la base de datos con el ID generado
        usersRef.child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Registro exitoso
                        Toast.makeText(LoginActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error en el registro
                        Toast.makeText(LoginActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void loginWithPhoneNumberAndPassword(final String phoneNumber, final String password) {
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoggedIn = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null && user.phoneNumber.equals(phoneNumber) && user.password.equals(password)) {
                        isLoggedIn = true;
                        break;
                    }
                }

                if (isLoggedIn) {
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("clave1", phoneNumber);  // Reemplaza "clave1" por un nombre descriptivo y "valor1" por la primera variable
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Número de teléfono o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error al leer los datos de la base de datos
                Toast.makeText(LoginActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}