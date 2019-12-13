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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: FragmentViewCardBarcodeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_card_barcode, container, false
        )

        val brand = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).brandName
        val cardname = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).cardName
        val cardnumber = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).cardNumber
        val note = ViewCardBarcodeFragmentArgs.fromBundle(arguments!!).note

        Log.d("TestValues","cardname: " + cardname)
        Log.d("TestValues","brand" + brand)
        Log.d("TestValues","cardnumber" + cardnumber.toString())
        Log.d("TestValues", "NOTE IS " + note)


        if(img(brand) == R.drawable.ic_settings) {
            binding.headerImageview.text = cardname
        }
        else {
            binding.headerImageview.setBackgroundResource(img(brand))
            binding.headerImageview.text = ""
        }
        binding.medlemsNummerTextView.text = cardnumber.toString()


        binding.NotesButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(ViewCardBarcodeFragmentDirections.actionFragmentViewCardBarcodeToFragmentViewCardNotes(brand, cardname, cardnumber , note))
        }
        try {
            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix = multiFormatWriter.encode(cardnumber.toString(), BarcodeFormat.CODABAR, 800, 400)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            binding.barcodeImageView.setImageBitmap(bitmap)
        } catch (e:Exception){
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG).show()
        }


        return binding.root
        //TODO Set the Header text to the name of the Card
        //TODO Set the Barcode image and Barcode Text to be the data from the Card chosen
    }

    private fun img(brand:String): Int {
        return when (brand) {
            "Matas" ->  R.drawable.matas_logo
            "Ikea" ->  R.drawable.ikea_logo
            "Bauhaus" ->  R.drawable.bauhaus_logo
            "Sportmaster"->  R.drawable.sportmaster_logo
            "Blockbuster" -> R.drawable.blockbuster_logo
            "H&M" -> R.drawable.hogm_logo
            "Imerco" -> R.drawable.imerco_logo
            "Jensens Bøfhus" -> R.drawable.jensen_boefhus_logo
            "Kop og kande" -> R.drawable.kop_og_kande_logo
            "Silvan" -> R.drawable.silvan_logo
            else ->  R.drawable.ic_settings
        }
    }


}

