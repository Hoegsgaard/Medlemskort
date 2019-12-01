package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentEditNotesBinding
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class EditNotesFragment : Fragment() {
    lateinit var brand:String
    lateinit var cardname:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentEditNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_notes, container, false
        )
        brand = EditNotesFragmentArgs.fromBundle(arguments!!).brandName
        cardname = EditNotesFragmentArgs.fromBundle(arguments!!).cardName
        val cardnumber = EditNotesFragmentArgs.fromBundle(arguments!!).cardNumber
        val prevNotes = EditNotesFragmentArgs.fromBundle(arguments!!).note

        binding.noteEdittext.setText(prevNotes)

        binding.cancelImageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(EditNotesFragmentDirections.actionFragmentEditNotesToFragmentViewCardNotes(brand, cardname, cardnumber , prevNotes))
        }
        binding.cancelTextview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(EditNotesFragmentDirections.actionFragmentEditNotesToFragmentViewCardNotes(brand, cardname, cardnumber , prevNotes))
        }
        binding.acceptImageview.setOnClickListener { view: View ->
            val newNote = binding.noteEdittext.text.toString()
            addCardToDatabase(newNote)
            Navigation.findNavController(view)
                .navigate(EditNotesFragmentDirections.actionFragmentEditNotesToFragmentViewCardNotes(brand, cardname, cardnumber , newNote))
            //TODO Update the cards notes if you click on the 'Done' button both locally and remotely (In the database)
        }
        binding.acceptTextview.setOnClickListener { view: View -> //HVAD ER DET HER DANIEL?
            val newNote = binding.noteEdittext.text.toString()
            addCardToDatabase(newNote)
            Navigation.findNavController(view)
                .navigate(EditNotesFragmentDirections.actionFragmentEditNotesToFragmentViewCardNotes(brand, cardname, cardnumber , newNote))
            //TODO Update the cards notes if you click on the 'Done' button both locally and remotely (In the database)
        }
        return binding.root
    }

    private fun addCardToDatabase(newNote: String)
    {
        // Write a message to the database
        //TODO First child of the new card should be a unique ID and not the name of the card
        val database = FirebaseDatabase.getInstance()
        val cardRef = database.getReference("cards/" + cardname + "/" + "note")
        cardRef.setValue(newNote)
    }

}
