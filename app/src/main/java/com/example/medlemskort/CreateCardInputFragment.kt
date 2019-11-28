package com.example.medlemskort


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardInputBinding
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.brand_card.*
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.fragment_create_card_input.*


/**
 * A simple [Fragment] subclass.
 */
class CreateCardInputFragment : Fragment() {


    lateinit var binding: FragmentCreateCardInputBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_input, container, false
        )

        val brand = CreateCardInputFragmentArgs.fromBundle(arguments!!).brand
        val cardname = CreateCardInputFragmentArgs.fromBundle(arguments!!).cardname
        val logo = getLogoByBrand(brand)
        /*
        Create card and navigate to View Barcode Fragment
         */
        binding.scanImageView.setOnClickListener{
            Toast.makeText(requireContext(),"Funktion under udvikling", Toast.LENGTH_LONG).show()
        }
        binding.addCardButton.setOnClickListener { view: View ->
            addCardToDatabase()
            val cardnumber = card_number_edittext.text.toString().toLong()
            Navigation.findNavController(view)

                .navigate(CreateCardInputFragmentDirections.actionFragmentCreateCardInputToFragmentViewCardBarcode(cardname , brand , cardnumber))
        }
        binding.addCardButton.isEnabled = false
        binding.addCardButton.isClickable = false


        binding.storeNameEdittext.setText(cardname)
        binding.headerImageview.setImageResource(logo)
        binding.circleImageview.setImageResource(logo)
        binding.cardNumberEdittext.doOnTextChanged { text, start, count, after ->
            if(text!!.isNotEmpty())
            {
                binding.addCardButton.isEnabled = true
                binding.addCardButton.isClickable = true
            }
            else
            {
                binding.addCardButton.isEnabled = false
                binding.addCardButton.isClickable = false
            }

        }

        return binding.root
    }

    private fun addCardToDatabase()
    {
        // Write a message to the database
        //TODO First child of the new card should be a unique ID and not the name of the card
        val database = FirebaseDatabase.getInstance()
        val cardRef = database.getReference("cards")
        val newCard = Card(binding.storeNameEdittext.text.toString(), binding.cardNumberEdittext.text.toString().toLong())
        cardRef.child(binding.storeNameEdittext.text.toString()).setValue(newCard)
    }

   private  fun getLogoByBrand(brand:String): Int {
        return when (brand) {
            "Matas" -> R.drawable.matas_logo
            "Ikea" ->  R.drawable.ikea_logo
            "Bauhaus" -> R.drawable.bauhaus_logo
            "Sportmaster"-> R.drawable.sportmaster_logo
            else -> R.drawable.ic_settings
        }
    }


}
