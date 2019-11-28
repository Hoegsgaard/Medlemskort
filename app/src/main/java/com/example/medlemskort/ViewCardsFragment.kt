package com.example.medlemskort


import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
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


/**
 * A simple [Fragment] subclass.
 */
class ViewCardsFragment : Fragment() {
    //TODO Show a loading animation when data is loading to show the user the app is not ready yet
    lateinit var binding: FragmentViewCardsBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_cards, container, false
        )

        generateOnClickListeners()

        setupDatabase()
        val card1 = Card()
        val card2 = Card()
        val card3 = Card()
        val card4 = Card()

        val list = listOf(card1 , card2 , card3 , card4)
        createCardsUI(list)

        return binding.root
    }

    private fun setupDatabase() {
        database = FirebaseDatabase.getInstance().getReference("cards")


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    Log.d(
                        "NewCard",
                        postSnapshot.getValue(Card::class.java)?.cardname + postSnapshot.getValue(
                            Card::class.java
                        )?.cardnumber
                    )
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
                .navigate(R.id.action_fragment_view_cards_to_fragment_create_card_template)
        }
    }

    fun createCardsUI(cards : List<Card>)
    {
        //TODO Create the correct ImageView View and inflate it to the layout
        // Repeat this process for all cards in the database
        // Should be called when receiving data in the OnDataChange function of PostListener
        val layout = binding.linearlayout
        for(i in 0 .. cards.size)
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
                Navigation.findNavController(view)
                    .navigate(ViewCardsFragmentDirections.actionFragmentViewCardsToFragmentViewCardBarcode(cards[i].cardname , cards[i].brand))
                    //.navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
            }
            layout.addView(textView)
            textView.text = "Card number " + i
        }
    }

    fun Int.toDp(): Int =(this / Resources.getSystem().displayMetrics.density).toInt()

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


}
