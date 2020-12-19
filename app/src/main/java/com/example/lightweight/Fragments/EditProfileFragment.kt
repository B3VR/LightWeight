package com.example.lightweight.Fragments

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.math.BigDecimal
import java.math.RoundingMode


class EditProfileFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null
    private var db = FirebaseFirestore.getInstance()
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        view.findViewById<TextView>(R.id.tvSaveProfile).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.ivBackArrow2).setOnClickListener(this)

        displayUserData()
    }

    private fun displayUserData()
    {
        db.collection("Users")
            .document(auth.currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                var user = it.toObject(User::class.java)

                if (user!!.weight != null){
                    etWeight.append(BigDecimal(user.weight!!).setScale(0, RoundingMode.HALF_EVEN).toString())
                }

                if (user!!.age != null){
                    etAge.append(BigDecimal(user.age!!).setScale(0,RoundingMode.HALF_EVEN).toString())
                }

                if (user!!.height != null){
                    etHeight.append(BigDecimal(user.height!!).setScale(0,RoundingMode.HALF_EVEN).toString())
                }

                if(user!!.sex != null && user!!.sex == "Kobieta"){
                    rgSex.check(R.id.rbFemale)

                }else if(user!!.sex != null && user!!.sex == "Mężczyzna"){
                    rgSex.check(R.id.rbMale)
                }


            }
    }

    private fun editProfile(){

        if(etAge.text.isEmpty()){
            etAge.error = "Nie podano wieku"
        }else if(etWeight.text.isEmpty()){
            etWeight.error = "Nie podano wagi"
        }else if(etHeight.text.isEmpty()){
            etHeight.error = "nie podano wzrostu"

        }else {

            var weight: Double? = etWeight.text.toString().toDouble()
            var height: Double? = etHeight.text.toString().toDouble()
            var age: Double? = etAge.text.toString().toDouble()

            var sex: String? = null
            if (rbFemale.isChecked) {
                sex = "Kobieta"

            } else if (rbMale.isChecked) {

                sex = "Mężczyzna"
            }

            db.collection("Users")
                .document(auth.currentUser?.uid.toString())
                .update(
                    mapOf(
                        "weight" to weight,
                        "height" to height,
                        "age" to age,
                        "sex" to sex

                    )
                ).addOnSuccessListener {
                    Toast.makeText(context, "Zapisano", Toast.LENGTH_LONG).show()

                }.addOnFailureListener {
                    Toast.makeText(context, "Nie udało się zapisać danych", Toast.LENGTH_LONG)
                        .show()
                }

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tvSaveProfile ->
            {
                editProfile()
            }

            R.id.ivBackArrow2 -> navControler?.navigate(R.id.action_editProfileFragment_to_profileFragment)

        }

    }
}