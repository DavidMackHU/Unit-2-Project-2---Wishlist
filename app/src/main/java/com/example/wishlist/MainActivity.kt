package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var items: MutableList<WishlistItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        items = mutableListOf()
        val adapter = WishlistAdapter(items)
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        val itemNameEt = findViewById<EditText>(R.id.itemNameEt)
        val itemPriceEt = findViewById<EditText>(R.id.itemPriceEt)
        val itemUrlEt = findViewById<EditText>(R.id.itemUrlEt)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {
            val name = itemNameEt.text.toString()
            val price = itemPriceEt.text.toString()
            val url = itemUrlEt.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                val newItem = WishlistItem(name, price, url)
                items.add(newItem)
                adapter.notifyItemInserted(items.size - 1)

                // Clear input fields
                itemNameEt.text.clear()
                itemPriceEt.text.clear()
                itemUrlEt.text.clear()
            }
        }
    }
}
