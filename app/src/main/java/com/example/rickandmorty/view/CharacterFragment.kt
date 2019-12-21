package com.example.rickandmorty.view

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterItemBinding
import com.example.rickandmorty.model.Characterr
import com.squareup.picasso.Picasso


class
CharacterFragment : Fragment() {

    private val TAG = "CharacterFragment"
    private lateinit var character: Characterr


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_character_item,
            container, false
        ).apply { tag = TAG }

        val imageView = view.findViewById<ImageView>(R.id.imageView)
/*        val idTextView = view.findViewById<TextView>(R.id.char_id)
        val nameTextView = view.findViewById<TextView>(R.id.character_name)
        val statusTextView = view.findViewById<TextView>(R.id.status)
        val speciesTextView = view.findViewById<TextView>(R.id.species)
        val typeTextView = view.findViewById<TextView>(R.id.type)
        val genderTextView = view.findViewById<TextView>(R.id.gender)*/
        val originTextView = view.findViewById<TextView>(R.id.origin)
        val locationTextView = view.findViewById<TextView>(R.id.location)
        //val createdTextView = view.findViewById<TextView>(R.id.created)
        val episodeTextView = view.findViewById<TextView>(R.id.episode)


        val ARG_PERMISSION = "Character"
        character = arguments?.getSerializable(ARG_PERMISSION) as Characterr


        /*val binding1: FragmentCharacterItemBinding = DataBindingUtil.setContentView(
            activity, R.layout.fragment_character_item)
*/
        //   binding1.characterr = character


        /*    val binding: FragmentCharacterItemBinding = FragmentCharacterItemBinding.inflate(
                layoutInflater
            )
            binding.characterr = character
*/



        val listItemBinding = DataBindingUtil.inflate<FragmentCharacterItemBinding>(
            layoutInflater,
            R.layout.fragment_character_item,
            container,
            false
        )


        listItemBinding.character = character
        Log.d(TAG, character.name)



        Picasso.get()
            .load(character.image)
            .into(imageView)

        /*  idTextView.text = character.id.toString()
          nameTextView.text = character.name
          statusTextView.text = character.status
          speciesTextView.text = character.species
          typeTextView.text = character.type
          genderTextView.text = character.gender*/
        originTextView.text = character.mOrigin?.name
        locationTextView.text = character.mLocation?.name
        // createdTextView.text = character.created
        episodeTextView.text = character.episode?.size.toString()

        return view
    }


}