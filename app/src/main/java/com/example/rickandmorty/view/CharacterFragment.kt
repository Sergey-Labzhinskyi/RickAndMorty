package com.example.rickandmorty.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Characterr
import com.squareup.picasso.Picasso


class
CharacterFragment : Fragment() {

    private val TAG: String = "CharacterFragment"
private lateinit var character: Characterr


   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          if (arguments != null && arguments!!.containsKey("Character")) {
              //character = Characterr()
                character = arguments!!.getSerializable("Characters") as Characterr
          } else{
              throw  IllegalArgumentException("Must be created through newInstance(...)")
          }
    }*/

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_character_item,
            container, false
        ).apply { tag = TAG }

        val imegeView = view.findViewById<ImageView>(R.id.imageView)
        val idTextView = view.findViewById<TextView>(R.id.char_id)
        val nameTextView = view.findViewById<TextView>(R.id.character_name)
        val statusTextView = view.findViewById<TextView>(R.id.status)
        val speciesTextView = view.findViewById<TextView>(R.id.species)
        val typeTextView = view.findViewById<TextView>(R.id.type)
        val genderTextView = view.findViewById<TextView>(R.id.gender)
        val originTextView = view.findViewById<TextView>(R.id.origin)
        val locationTextView = view.findViewById<TextView>(R.id.location)
        val createdTextView = view.findViewById<TextView>(R.id.created)
        val episodeTextView = view.findViewById<TextView>(R.id.episode)




            val ARG_PERMISSION = "Character"
            character = arguments?.getSerializable(ARG_PERMISSION) as Characterr

         //   var or: Origin? = Origin()
        //       or = character.mOrigin
       //    var string = or?.name
         //   Log.d(TAG, string)

                Picasso.get()
                .load(character.image)
                .into(imegeView)

            idTextView.text = character.id.toString()
            nameTextView.text = character.name
            statusTextView.text = character.status
            speciesTextView.text = character.species
            typeTextView.text = character.type
            genderTextView.text = character.gender
            originTextView.text = character.mOrigin?.name
            locationTextView.text = character.mLocation?.name
            createdTextView.text = character.created
            episodeTextView.text = character.episode?.size.toString()

        return view
    }




}