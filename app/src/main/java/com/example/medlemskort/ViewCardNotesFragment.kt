package com.example.medlemskort


import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardNotesBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewCardNotesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentViewCardNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_notes, container, false
        )

        binding.buttonEditNotes.setOnClickListener { view: View ->
            navigateToNotes(view)
        }
        binding.notesTextTextView.setOnClickListener { view: View ->
            navigateToNotes(view)
        }
        binding.notesLableTextView.setOnClickListener { view: View ->
            navigateToNotes(view)
        }



        binding.buttonBarcode.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_notes_to_fragment_view_card_barcode)
        }

        binding.imageViewFront.setOnClickListener{
            Toast.makeText(requireContext(),"Funktion under udvikling", Toast.LENGTH_LONG).show()
        }
        binding.imageViewBack.setOnClickListener{
            Toast.makeText(requireContext(),"Funktion under udvikling", Toast.LENGTH_LONG).show()
        }

        return binding.root

        //TODO Pull the Note of a card from the backend or cache and display it in the Note text box.

        //TODO In a later version
            //TODO add camera function
                //TODO Step 1. Ask for permission to use Camera Roll or Camera
                //TODO Step 2. Use the image provided by the user, upload it to the database storage and show a preview in the app
                //TODO Step 3. If the user has a picture chosen make sure that when you click it a full scren view of the picture is shown with an option to edit or delete it

    }

    fun navigateToNotes(view: View){
        Navigation.findNavController(view)
            .navigate(R.id.action_fragment_view_card_notes_to_fragment_edit_notes)
    }
}
