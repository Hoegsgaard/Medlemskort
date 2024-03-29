package com.example.medlemskort


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentCreateCardTemplateBinding
import kotlinx.android.synthetic.main.brand_card.view.*
import kotlinx.android.synthetic.main.fragment_create_card_template.view.*

/**
 * A simple [Fragment] subclass.
 */
class CreateCardTemplateFragment : Fragment() {

    lateinit var binding: FragmentCreateCardTemplateBinding
    lateinit var mainLinearLayout: LinearLayout
    var brands = listOf("Matas", "Ikea", "Bauhaus", "Sportmaster", "Blockbuster", "H&M", "Imerco", "Jensens Bøfhus", "Kop og kande", "Silvan")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_card_template, container, false
        )

        mainLinearLayout = binding.mainScrollView.mainLinearLayout
        addBrandCards()

        return binding.root
    }

    private fun addBrandCards(){

        val CustomCard = layoutInflater.inflate(R.layout.brand_card, null, false)
        val Space1 = layoutInflater.inflate(R.layout.spacer,null,false)
        CustomCard.brandName.text = "Make your own!"
        CustomCard.brandLogo.setImageResource(R.drawable.questionmark)
        CustomCard.setOnClickListener{ view: View ->
            Navigation.findNavController(view)
                //.navigate(R.id.action_fragment_create_card_template_to_fragment_create_card_input)
                .navigate(CreateCardTemplateFragmentDirections.actionFragmentCreateCardTemplateToFragmentCreateCardInput("" , ""))
        }
        mainLinearLayout.addView(CustomCard)
        mainLinearLayout.addView(Space1)


        for (brand in brands) {
            val cardView = layoutInflater.inflate(R.layout.brand_card, null, false)
            val space = layoutInflater.inflate(R.layout.spacer,null,false)
            val brandImg = img(brand)
            cardView.brandName.text = brand
            cardView.brandLogo.setImageResource(brandImg)
            cardView.setOnClickListener{ view: View ->
                    Navigation.findNavController(view)
                    //.navigate(R.id.action_fragment_create_card_template_to_fragment_create_card_input)
                    .navigate(CreateCardTemplateFragmentDirections.actionFragmentCreateCardTemplateToFragmentCreateCardInput(brand , brand))
            }

            mainLinearLayout.addView(cardView)
            mainLinearLayout.addView(space)
        }


    }
    private fun img(brand:String): Int {
        return when (brand) {
            "Matas" ->  R.drawable.matas_new_logo
            "Ikea" ->  R.drawable.ikea_new_logo
            "Bauhaus" ->  R.drawable.bauhaus_new_logo
            "Sportmaster"->  R.drawable.sportmaster_new_logo
            "Blockbuster" -> R.drawable.blockbuster_new_logo
            "H&M" -> R.drawable.hm_new_logo
            "Imerco" -> R.drawable.imerco_new_logo
            "Jensens Bøfhus" -> R.drawable.jensen_boefhus_new_logo
            "Kop og kande" -> R.drawable.kop_og_kande_logo
            "Silvan" -> R.drawable.silvan_new_logo
            else ->  R.drawable.ic_settings
        }
    }
}
