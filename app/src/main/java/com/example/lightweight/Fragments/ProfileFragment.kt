package com.example.lightweight.Fragments

import User
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import java.math.BigDecimal
import java.math.RoundingMode

class ProfileFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null
    private lateinit var auth: FirebaseAuth
    private var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        navControler = Navigation.findNavController(view)

        view.findViewById<ImageView>(R.id.ivBackArrow).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tvEditProfile).setOnClickListener(this)



        getUserData()

    }

    private fun getUserData(){

        db.collection("Users").document(auth.currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                var user = it.toObject(User::class.java)
                displayUserData(user)

            }.addOnFailureListener {

            }
    }

    private fun displayUserData(user: User?){
        if(user?.age == null){
            tvAgeData.text = "-"
        }else{
            tvAgeData.text = BigDecimal(user.age!!).setScale(0,RoundingMode.HALF_EVEN).toString()
        }

        if(user?.weight == null){
            tvWeightData.text = "-"
        }else{
            tvWeightData.text = BigDecimal(user.weight!!).setScale(0,RoundingMode.HALF_EVEN).toString() + " kg"
        }

        if(user?.height == null){
            tvHeightData.text = "-"
        }else{
            tvHeightData.text = BigDecimal(user.height!!).setScale(0,RoundingMode.HALF_EVEN).toString() + " cm"
        }

        if(user?.name == null){
            tvUserName.text = "-"
        }else{
            tvUserName.text = user.name
        }

        if (user?.getBMI() == null){
            tvBMIData.text = "-"
            tvBMI.setError("Uzupełnij profil by uzyskać więcej informacji")
        }else {
            tvBMIData.text = BigDecimal(user?.getBMI()!!).setScale(0, RoundingMode.HALF_EVEN).toString()
        }

        if (user?.getPPM() == null){
            tvPPMData.text = "-"
        }else{
            tvPPMData.text = BigDecimal(user?.getPPM()!!).setScale(0, RoundingMode.HALF_EVEN).toString() + " kcal"
        }

        if (user?.getBMR() == null){
            tvBMRData.text = "null"
        }else{
            tvBMRData.text = BigDecimal(user.getBMR()!!).setScale(0, RoundingMode.HALF_EVEN).toString() + " kcal"
        }


    }

    override fun onClick(v: View?){
        when(v!!.id){
            R.id.ivBackArrow -> navControler?.navigate(R.id.action_profileFragment_to_mainFragment)

            R.id.tvEditProfile -> navControler?.navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }
}

