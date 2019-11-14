package com.example.medlemskort


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardTemplateBinding
import kotlinx.android.synthetic.main.brand_card.view.*
import kotlinx.android.synthetic.main.fragment_create_card_template.*
import kotlinx.android.synthetic.main.fragment_create_card_template.view.*

/**
 * A simple [Fragment] subclass.
 */
class CreateCardTemplateFragment : Fragment() {

    lateinit var binding: FragmentCreateCardTemplateBinding
    lateinit var mainLinearLayout: LinearLayout
    var brands = listOf("Matas", "Ikea", "Bauhaus", "Sportmaster")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_template, container, false
        )

        mainLinearLayout = binding.mainScrollView.mainLinearLayout
        addBrandCards()

        return binding.root
    }

    fun addBrandCards(){
        for (brand in brands) {
            val cardView = layoutInflater.inflate(R.layout.brand_card, null, false)
            val space = layoutInflater.inflate(R.layout.spacer,null,false)
            val brandImg = img(brand)
            cardView.brandName.setText(brand)
            cardView.brandLogo.setImageResource(brandImg)
            cardView.setOnClickListener{ view: View ->
                    Navigation.findNavController(view)
                    .navigate(R.id.action_fragment_create_card_template_to_fragment_create_card_input)
            }
            mainLinearLayout!!.addView(cardView)
            mainLinearLayout!!.addView(space)
        }
    }
    fun img(brand:String): Int {
        when (brand) {
            "Matas" -> return R.drawable.matas_logo
            "Ikea" -> return R.drawable.ikea_logo
            "Bauhaus" -> return R.drawable.bauhaus_logo
            "Sportmaster"-> return R.drawable.sportmaster_logo
            else -> return R.drawable.ic_settings
        }
    }
}
