package com.example.medlemskort


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.medlemskort.databinding.FragmentViewCardsBinding
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class ViewCardsFragment : Fragment() {
    lateinit var binding: FragmentViewCardsBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_cards, container, false
        )
        generateOnClickListeners()

        setupDatabase()

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
        binding.card1Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card2Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card3Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        binding.card4Imageview.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

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
        val layout = binding.linearLayoutMain
        val imageView = layoutInflater.inflate(R.layout.card, layout, false)
        imageView.setOnClickListener {
                view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_fragment_view_cards_to_fragment_view_card_barcode)
        }

        layout.addView(imageView)
    }


}
