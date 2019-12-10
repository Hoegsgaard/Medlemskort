package com.example.medlemskort


import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardsBinding
import com.google.firebase.database.*
import android.view.Gravity
import kotlinx.android.synthetic.main.brand_card.view.*


/**
 * A simple [Fragment] subclass.
 */
class ViewCardsFragment : Fragment() {
    //TODO Show a loading animation when data is loading to show the user the app is not ready yet
    lateinit var binding: FragmentViewCardsBinding
    lateinit var list : MutableList<Card>
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_cards, container, false
        )

        generateOnClickListeners()

        setupDatabase()
//        val card1 = Card("Bauhaus" , brand = "Bauhaus")
//        val card2 = Card("Ikea" , brand = "Ikea")
//        val card3 = Card("Matas" , brand = "Matas")
//        val card4 = Card("Sportmaster" , brand = "Sportmaster")
//
         list = mutableListOf()


        return binding.root
    }

    private fun setupDatabase() {
        database = FirebaseDatabase.getInstance().getReference("cards")


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val newCard = postSnapshot.getValue(Card::class.java)
                    /*Log.d(
                        "NewCard",
                        postSnapshot.getValue(Card::class.java)?.cardname + postSnapshot.getValue(
                            Card::class.java
                        )?.cardnumber
                    )*/
                    list.add(newCard!!)

                }
                if (list.size < 1){
                    Log.d("size","Tom")
                    binding.textViewNoCards.visibility = View.VISIBLE;
                    binding.progressBar.visibility = View.GONE
                    binding.textViewLoading.visibility = View.GONE
                }else{
                    createCardsUI(list)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Failed", "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
    }

    private fun generateOnClickListeners()
    {
        binding.newCardFloatingActionButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(ViewCardsFragmentDirections.actionFragmentViewCardsToFragmentCreateCardTemplate())
        }
    }

    private fun createCardsUI(cards : List<Card>) {
        //TODO Create the correct ImageView View and inflate it to the layout
        // Repeat this process for all cards in the database
        // Should be called when receiving data in the OnDataChange function of PostListener
        val layout = binding.linearlayout
        for(i in 0 until(cards.size)){
            val cardView = layoutInflater.inflate(R.layout.brand_card, null, false)
            val space = layoutInflater.inflate(R.layout.spacer,null,false)
            val brandImg = img(cards[i].brand)
            cardView.brandName.text = cards[i].brand
            cardView.brandLogo.setImageResource(brandImg)
            cardView.setOnClickListener {
                    view: View ->
                Log.d("TestValues",cards[i].cardname)
                Log.d("TestValues",cards[i].brand)
                Log.d("TestValues",cards[i].cardnumber.toString())
                Log.d("TestValues",cards[i].note)

                Navigation.findNavController(view)
                    .navigate(ViewCardsFragmentDirections.actionFragmentViewCardsToFragmentViewCardBarcode(cards[i].cardname , cards[i].brand , cards[i].cardnumber , cards[i].note))
                //.navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
            }
            layout.addView(cardView)
            layout.addView(space)

        }
        binding.progressBar.visibility = View.GONE
        binding.textViewLoading.visibility = View.GONE
    }
    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

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


/*
private fun createCardsUI(cards : List<Card>) {
        //TODO Create the correct ImageView View and inflate it to the layout
        // Repeat this process for all cards in the database
        // Should be called when receiving data in the OnDataChange function of PostListener
        val layout = binding.linearlayout
        for(i in 0 until(cards.size))
        {
            val textView = TextView(context)
            val layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200)
            layoutParams.setMargins(5.toPx() , 5.toPx() , 5.toPx() , 5.toPx())
            textView.layoutParams = layoutParams
            textView.setBackgroundColor(Color.LTGRAY)
            textView.gravity = Gravity.CENTER
            textView.setOnClickListener {
                    view: View ->
                Log.d("hey",cards[i].cardname + " | " +cards[i].brand)
                Navigation.findNavController(view)
                    .navigate(ViewCardsFragmentDirections.actionFragmentViewCardsToFragmentViewCardBarcode(cards[i].cardname , cards[i].brand , cards[i].cardnumber))
                    //.navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
            }
            layout.addView(textView)
            textView.text = cards[i].cardname
        }
        binding.progressBar.visibility = View.GONE
        binding.textViewLoading.visibility = View.GONE
    }
*/
