package com.example.medlemskort


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardBarcodeBinding
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.brand_card.view.*
import kotlinx.android.synthetic.main.fragment_create_card_input.*
import kotlinx.android.synthetic.main.fragment_view_card_barcode.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ViewCardBarcodeFragment : Fragment() {
    private lateinit var medNum:String
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentViewCardBarcodeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_barcode, container, false
        )

        val brand = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).brandName
        val cardname = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).cardName
        val cardnumber = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).cardNumber


        if(img(brand) == R.drawable.ic_settings)
        {
            binding.headerImageview.text = cardname
        }
        else
        {
            binding.headerImageview.setBackgroundResource(img(brand))
            binding.headerImageview.text = ""
        }
        binding.medlemsNummerTextView.text = cardnumber.toString()


        binding.NotesButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_barcode_to_fragment_view_card_notes)
        }

        medNum = getMedNumFromDatabase(cardname)
        try {
            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix = multiFormatWriter.encode(medNum, BarcodeFormat.CODABAR, 800, 400)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            binding.barcodeImageView.setImageBitmap(bitmap)
        } catch (e:Exception){
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG).show()
        }
        binding.medlemsNummerTextView.text = medNum


        return binding.root
        //TODO Set the Header text to the name of the Card
        //TODO Set the Barcode image and Barcode Text to be the data from the Card chosen
    }

    private fun getMedNumFromDatabase(cardname: String): String {
        database = FirebaseDatabase.getInstance().getReference("cards/${cardname}")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("GetCurrentCardBarcode",dataSnapshot.toString())
                val newcard = dataSnapshot.getValue(Card::class.java)
                Log.d("CardValues" , newcard?.cardname.toString())
                Log.d("CardValues" , newcard?.cardnumber.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Failed", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)


        return "1234"
    }

    private fun img(brand:String): Int {
        return when (brand) {
            "Matas" ->  R.drawable.matas_logo
            "Ikea" ->  R.drawable.ikea_logo
            "Bauhaus" ->  R.drawable.bauhaus_logo
            "Sportmaster"->  R.drawable.sportmaster_logo
            else ->  R.drawable.ic_settings
        }
    }


}

