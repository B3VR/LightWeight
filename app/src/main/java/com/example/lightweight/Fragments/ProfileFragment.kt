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
            tvAgeData.text = "Brak"
        }else{
            tvAgeData.text = user.age.toString()
        }

        if(user?.weight == null){
            tvWeightData.text = "Brak"
        }else{
            tvWeightData.text = user.weight.toString()
        }

        if(user?.height == null){
            tvHeightData.text = "Brak"
        }else{
            tvHeightData.text = user.height.toString()
        }

        if(user?.name == null){
            tvUserName.text = "Brak"
        }else{
            tvUserName.text = user.name
        }

    }

    override fun onClick(v: View?){
        when(v!!.id){
            R.id.ivBackArrow -> navControler?.navigate(R.id.action_profileFragment_to_mainFragment)


        }
    }
}

