package com.ownproject.youtubepage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var layoutmanager:RecyclerView.LayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var str:String
    lateinit var adapter: vedioAdapter
    lateinit var itemlist:ArrayList<List<String>>
    lateinit var items:ArrayList<String>
    lateinit var search:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        str="mani"
        setContentView(R.layout.activity_main)
        search=findViewById(R.id.ev1)

        search.addTextChangedListener(
            object :TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    filter(p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            }
        )

    }
    fun filter(p:String)
    {
        if (p.length>=3){
            meth(p)
        }
    }
    fun meth(Search:String){

        val queue = Volley.newRequestQueue(this)
        itemlist= arrayListOf()
        recyclerView=findViewById(R.id.recycler_view)
        layoutmanager = LinearLayoutManager(this)
        val url="https://www.googleapis.com/youtube/v3/search?type=vedio&key=AIzaSyA9fQlSWw4-_a3oEMCZHbH2iXyy-ldk6hc&part=snippet&maxResults=50&q="+Search

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,null,
            Response.Listener {
                val data=it.getJSONArray("items")

                for (i in 0 until data.length()){
                    items= arrayListOf()
                    val datain=data.getJSONObject(i)
                    val snippet=datain.getJSONObject("snippet")
                    val thumb=snippet.getJSONObject("thumbnails")
                    items.add(thumb.getJSONObject("medium").getString("url"))
                    items.add(snippet.getString("title"))
                    items.add(snippet.getString("channelTitle"))
                    itemlist.add(items)
                }
                adapter= vedioAdapter(this,itemlist)
                recyclerView.adapter=adapter
                recyclerView.layoutManager=layoutmanager



            },Response.ErrorListener {

                Toast.makeText(this,"error Occured",Toast.LENGTH_SHORT).show()
            }){override fun getHeaders(): MutableMap<String, String> {
            val headers=HashMap<String,String>()

            headers["Content-type"]="application/json"
            headers["token"]="9bf534118365f1"

            return headers
        }
        }
        queue.add(jsonObjectRequest)

    }
}