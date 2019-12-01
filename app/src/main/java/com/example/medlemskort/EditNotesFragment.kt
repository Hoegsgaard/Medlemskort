package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentEditNotesBinding

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

        binding.cancelImageview.setOnClickListener { view: View ->
            navigateToNotes(view)
        }
        binding.cancelTextview.setOnClickListener { view: View ->
            navigateToNotes(view)
        }
        binding.acceptImageview.setOnClickListener { view: View ->
            navigateToNotes(view)
            //TODO Update the cards notes if you click on the 'Done' button both locally and remotely (In the database)
        }
        binding.acceptTextview.setOnClickListener { view: View -> //HVAD ER DET HER DANIEL?
            navigateToNotes(view)
            //TODO Update the cards notes if you click on the 'Done' button both locally and remotely (In the database)
        }
        return binding.root
    }

    fun navigateToNotes(view: View){
        Navigation.findNavController(view)
            .navigate(EditNotesFragmentDirections.actionFragmentEditNotesToFragmentViewCardNotes(brand, cardname, EditNotesFragmentArgs.fromBundle(arguments!!).cardNumber))
    }
}
