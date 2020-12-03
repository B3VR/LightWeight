package com.example.lightweight.Fragments

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_edit_profile.*


class EditProfileFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.setBtn).setOnClickListener(this)

        navControler = Navigation.findNavController(view)
    }

    private fun editProfile(){

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.setBtn -> {
                if(editNameTxt.text.isEmpty()){
                    editNameTxt.error = "Podaj nazwę użytkownika"
                    return
                }else if (editHeightTxt.text.isEmpty()){
                    editHeightTxt.error = "Podaj wzrost"
                    return
                }else if (editWeightTxt.text.isEmpty()){
                    editHeightTxt.error = "Podaj wagę"
                    return
                }else {
                    editProfile()
                    navControler!!.navigate(R.id.action_editProfileFragment_to_mainFragment)
                }
            }
        }

    }
}