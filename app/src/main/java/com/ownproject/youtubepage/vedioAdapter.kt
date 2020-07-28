package com.ownproject.youtubepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.zip.Inflater


class vedioAdapter(val context: Context, var itemList:ArrayList<List<String>>): RecyclerView.Adapter<vedioAdapter.VedioViewHolder>() {
    class VedioViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val Vedioweb:ImageView=view.findViewById(R.id.webview)
        val tv1:TextView=view.findViewById(R.id.Vtitle)
        val tv2:TextView=view.findViewById(R.id.Ctitle)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): vedioAdapter.VedioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vedio_view, parent, false)
        return VedioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: vedioAdapter.VedioViewHolder, position: Int) {
        val item=itemList[position]
        Picasso.get().load(item[0]).error(R.drawable.ic_launcher_background).into(holder.Vedioweb)
        holder.tv1.text=item[1]
        holder.tv2.text=item[2]
    }

}