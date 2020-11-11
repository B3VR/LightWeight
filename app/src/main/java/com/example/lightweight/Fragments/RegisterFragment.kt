package com.example.lightweight.Fragments

import android.os.Bundle
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


class RegisterFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController;
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.registerBtn).setOnClickListener(this)

        //Stworzenie instancji autoryzacji
        auth = Firebase.auth
        //Stworzenie kontrolera nawigacji przy stworeniu pierwszego widoku
        navController = Navigation.findNavController(view)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }else{
            Toast.makeText(context, "Rejestracja nieudana", Toast.LENGTH_LONG).show()
        }
    }

    private fun addUser() {
        TODO("Not yet implemented")
    }

    private fun register() {
        if(checkRegisterData()){
            auth.createUserWithEmailAndPassword(emailRegisterTxt.text.toString(), passwordRegisterTxt.text.toString())
                .addOnSuccessListener {
                    val user = auth.currentUser
                    Toast.makeText(context, "Zarejestrowano", Toast.LENGTH_LONG).show()
                    updateUI(user)
                }
                .addOnFailureListener{
                    updateUI(null)
                }
        }
    }

    private fun checkRegisterData(): Boolean {
        if(userNameRegisterTxt.text.isEmpty()){
            userNameRegisterTxt.error = "Nie podano nazwy użytkownika"
            userNameRegisterTxt.requestFocus()
            return false

        }else if(emailRegisterTxt.text.isEmpty()) {
            emailRegisterTxt.error = "Nie podano adresu e-mail"
            emailRegisterTxt.requestFocus()
            return false

        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailRegisterTxt.text.toString()).matches()){
            emailRegisterTxt.error = "Nieprawidłowy adres e-mail"
            emailRegisterTxt.requestFocus()
            return false

        }else if (passwordRegisterTxt.text.isEmpty()){
            passwordRegisterTxt.error = "Nie podano hasła"
            passwordRegisterTxt.requestFocus()
            return false

        }else if(passwordRegisterTxt.text.length <= 6) {
            passwordRegisterTxt.error = "Hasło musi zawierać 6 lub więcej znaków"
            passwordRegisterTxt.requestFocus()
            return false

        }else if (passwordRegisterTxt.text.toString() != passwordRegistertxt2.text.toString()){
            passwordRegistertxt2.error = "Hasła nie są takie same"
            passwordRegistertxt2.requestFocus()
            return false

        }else

            return true
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.registerBtn -> {
                register()
            }
        }
    }
}