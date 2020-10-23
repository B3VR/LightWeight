package com.example.lightweight

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun editProfile(){
        var name: String = view?.findViewById<TextView>(R.id.editNameTxt).toString()
        var weight: Double = view?.findViewById<TextView>(R.id.editWeightTxt).toString().toDouble()
        var height: Double = view?.findViewById<TextView>(R.id.editHeightTxt).toString().toDouble()

        var ref = FirebaseDatabase.getInstance().getReference("User")
        var user: User = User(name, weight, height)
        val userId = ref.push().key

        ref.child(userId).setValue(user)



    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.editProfileBtn -> {
                if(editNameTxt.text.isEmpty()){
                    editNameTxt.error = "Wymagana nazwa użytkownika"
                    return
                }else if (editHeightTxt.text.isEmpty()){
                    editHeightTxt.error = "Wymagany wzrost"
                    return
                }else if (editWeightTxt.text.isEmpty()){
                    editHeightTxt.error = "Wymagana waga"
                    return
                }else

                    editProfile()
            }
        }

    }
}