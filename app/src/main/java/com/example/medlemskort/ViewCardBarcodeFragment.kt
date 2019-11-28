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
import kotlinx.android.synthetic.main.fragment_view_card_barcode.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ViewCardBarcodeFragment : Fragment() {
    private lateinit var medNum:String
    private lateinit var database: DatabaseReference
    private lateinit var brand : String
    private lateinit var cardname : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentViewCardBarcodeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_barcode, container, false
        )

        brand = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).brandName
        cardname = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).cardName


        binding.NotesButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_card_barcode_to_fragment_view_card_notes)
        }

        medNum = getMedNumFromDatabase()
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

    private fun getMedNumFromDatabase(): String {
        database = FirebaseDatabase.getInstance().getReference("cards/${cardname}")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("GetCurrentCardBarcode",dataSnapshot.toString())
                val newcard = dataSnapshot.getValue(Card::class.java)
                Log.d("CardValues" , newcard?.cardname.toString())
                Log.d("CardValues" , newcard?.cardnumber.toString())
                medlemsNummer_textView.text = newcard?.cardnumber.toString()
                header_imageview.text = newcard?.cardname

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Failed", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)


        return "1234"
    }


}

