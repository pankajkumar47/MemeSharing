package com.example.memesharing

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
        var sex= "gf"
    }

    fun loadMeme(){
        var currentImageUrl:String
        pregresbar.visibility=View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jSOnObjectRequest = JsonObjectRequest(
            url,null,
            Response.Listener { response ->
                currentImageUrl= response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object:RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pregresbar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pregresbar.visibility=View.GONE
                        return false
                    }

                }).into(imageView)
            },
            Response.ErrorListener {})

// Add the request to the RequestQueue.
        queue.add(jSOnObjectRequest)

    }
    fun nextMeme(view: View) {
        loadMeme()

    }
    fun shareMeme(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, checkout this cool meme i got from reddit ")
        val chooser= Intent.createChooser(intent,"share this meme using...")
        intent.type="text/plain"
        startActivity(intent)
    }
}