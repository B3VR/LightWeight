package com.example.lightweight.Fragments

import User
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.example.lightweight.models.Training
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController;
    private lateinit var auth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()

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

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }else{
            Toast.makeText(context, "Rejestracja nieudana", Toast.LENGTH_LONG).show()
        }
    }

    private fun register() {
        if(checkRegisterData()){
            auth.createUserWithEmailAndPassword(
                emailRegisterTxt.text.toString(),
                passwordRegisterTxt.text.toString()
            )
                .addOnSuccessListener {
                    val user = auth.currentUser
                    Toast.makeText(context, "Zarejestrowano", Toast.LENGTH_LONG).show()
                    updateUI(user)
                    addUser()
                    addUserTrainingsData()
                }
                .addOnFailureListener{
                    updateUI(null)
                }
        }
    }

    private fun addUser() {
        var name: String = userNameRegisterTxt.text.toString()
        var uid = auth.currentUser?.uid.toString()

        var user: User = User(uid, name, null, null, null, null)

        db.collection("Users").document(uid).set(user)
            .addOnSuccessListener {
               Log.d("REGISTER FRAGMENT", "UZYTKOWNIK DODANY")
            }.addOnFailureListener {
               Log.d("REGISTER FRAGMENT", "BŁĄ DODAWANIA UŻYTKOWNIKA")
            }
    }

    private fun addUserTrainingsData(){
        var currentTraining = Training()
        currentTraining.done = false
        db.collection("Users").document(auth.uid!!.toString())
            .collection("Current Training").document("Current Training")
            .set(currentTraining)
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