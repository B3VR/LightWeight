package com.example.lightweight

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_edit_profile.*


class EditProfileFragment : Fragment(), View.OnClickListener {

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
    }

    private fun editProfile(){
        var name: String = view?.findViewById<TextView>(R.id.editNameTxt).toString()
        var weight: Double = view?.findViewById<TextView>(R.id.editWeightTxt).toString().toDouble()
        var height: Double = view?.findViewById<TextView>(R.id.editHeightTxt).toString().toDouble()

        var ref = FirebaseDatabase.getInstance().getReference("User")
        val userId = ref.push().key
        var user: User = User(userId!! ,name, weight, height)

        ref.child(userId!!).setValue(user).addOnCompleteListener{
            Toast.makeText(context, "Zapisano", Toast.LENGTH_LONG).show()
        }
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
                }else

                    editProfile()
            }
        }

    }
}