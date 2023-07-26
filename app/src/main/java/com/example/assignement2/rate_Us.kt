package com.example.assignement2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class rate_Us : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rate__us, container, false)
        val txt12:TextView=view.findViewById(R.id.feedback_txt)
        val commit11:Button = view.findViewById(R.id.btnSubmitFeedback)


        commit11.setOnClickListener{view->
            txt12.text = "Thank you"
        }


return view
    }

}