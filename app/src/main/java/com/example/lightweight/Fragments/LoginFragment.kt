package com.example.lightweight.Fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController;
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.registerTxt).setOnClickListener(this)
        view.findViewById<Button>(R.id.loginBtn).setOnClickListener(this)

        //Stworzenie instancji autoryzacji
        auth = Firebase.auth
        //Stworzenie kontrolera nawigacji przy stworeniu pierwszego widoku
        navController = Navigation.findNavController(view)
    }

    public override fun onStart() {
        super.onStart()
        auth.signOut()
        // Check if user is signed in (non-null) and update UI accordingly.
        /*val currentUser = auth.currentUser
        updateUI(currentUser)*/
    }

    private fun login() {
        if (checkLoginData()){
            auth.signInWithEmailAndPassword(emailLoginTxt.text.toString(), passwordLoginTxt.text.toString())
                .addOnSuccessListener {
                    val user = auth.currentUser
                    Toast.makeText(context, "Zalogowano", Toast.LENGTH_LONG).show()
                    updateUI(user)
                    Log.d("LOGIN FRAGMENT", "UZYTKOWNIK ZALOGOWANY")
                }
                .addOnFailureListener {
                    updateUI(null)
                    Log.d("LOGIN FRAGMENT", "BŁĄD LOGOWANIA")
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            navController.navigate(R.id.action_loginFragment_to_mainFragment)
        }else{
            Toast.makeText(context, "Logowanie nieudane", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLoginData(): Boolean {
        if(emailLoginTxt.text.isEmpty()){
            emailLoginTxt.error = "Nie podano adresu e-mail"
            emailLoginTxt.requestFocus()
            return false

        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailLoginTxt.text.toString()).matches()) {
            emailLoginTxt.error = "Nie podano adresu e-mail"
            emailLoginTxt.requestFocus()
            return false

        }else if(passwordLoginTxt.text.isEmpty()) {
            passwordLoginTxt.error = "Nie podano adresu hasła"
            passwordLoginTxt.requestFocus()
            return false

        }else if(passwordLoginTxt.text.length <= 6) {
            passwordLoginTxt.error = "Hasło musi zawierać 6 lub więcej znaków"
            passwordLoginTxt.requestFocus()
            return false

        }else
            return true
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.loginBtn ->{
                login()
            }
            R.id.registerTxt ->{
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

}