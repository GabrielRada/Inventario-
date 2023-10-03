package com.example.ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseApp.initializeApp(this)
        val analytic = FirebaseAnalytics.getInstance(this)
        val bundle =Bundle()
        bundle.putString("message" , "la conecion es exitosa")
        analytic.logEvent("InitedScreen", bundle)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val signUpButton = findViewById<Button>(R.id.logOutBotton)
        val passwordEditText = findViewById<EditText>(R.id.providerTextView)
        val loginButton: Button = findViewById(R.id.loginButton)


        //Setup

        fun setup() {
            title = "Autenticacion"

            signUpButton.setOnClickListener{
                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailEditText.text.toString(),
                            passwordEditText.text.toString()).addOnCompleteListener(this) {

                            if (it.isSuccessful){
                                Toast.makeText(this, "El usuario ha sido registrado con exito", Toast.LENGTH_SHORT).show()

                            }else{
                                showAlerttwo()
                            }
                        }

                }

            }
            loginButton.setOnClickListener{
                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(emailEditText.text.toString(),
                            passwordEditText.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent: Intent = Intent(this,entrada::class.java)
                                startActivity(intent)
                            }else{
                                showAlert()
                            }
                        }
                }
            }
        }
        setup()
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Digite correctamente su usuario")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlerttwo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Un usuario ya esta registado con ese nombre ")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

//    private fun showHome(email: String, provider: ProviderType){
//        val homeIntent = Intent(this,HomeActivity::class.java).apply {
//            putExtra("email", email)
//            putExtra("provider", provider.name)
//        }
//        startActivity(homeIntent)
//    }




//        // Configurar el botón de registro
//        logOutBotton.setOnClickListener {
//            if (!(emailEditText.text.isNullOrEmpty() && providerTextView.text.isNullOrEmpty())) {
//                FirebaseAuth.getInstance()
//                    .createUserWithEmailAndPassword(emailEditText.text.toString(),
//                        providerTextView.text.toString()).addOnCompleteListener(this) {
//                        if (it.isSuccessful) {
//                            // El registro fue exitoso, puedes redirigir al usuario a la siguiente actividad
//                            val panelUsuario = Intent(this, entrada::class.java)
//                            startActivity(panelUsuario)
//                        } else {
//                            // Hubo un error durante el registro, muestra un mensaje de error
//                            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            } else {
//                Toast.makeText(this, "El usuario y la contraseña no pueden ir vacíos", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

