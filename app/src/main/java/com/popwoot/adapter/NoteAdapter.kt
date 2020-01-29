package com.popwoot.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popwoot.R
import com.popwoot.model.Row
import com.popwoot.utils.loadImage
import kotlinx.android.synthetic.main.adapter_note_list.view.*


class NoteAdapter(var list: List<Row>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_note_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Row) {
            itemView.apply {
                if(model.imageHref==null){
                    thumbnail.visibility=View.GONE
                }else {
                    thumbnail.visibility=View.VISIBLE
                    thumbnail.loadImage(model.imageHref!!)
                }

                if(model.title==null){
                    tvTitle.visibility=View.GONE
                }else {
                    tvTitle.visibility=View.VISIBLE
                    tvTitle.text = model.title
                }

                if(model.description==null){
                    tvDescription.visibility=View.GONE
                }else {
                    tvDescription.visibility=View.VISIBLE
                    tvDescription.text = model.description
                }

                if(model.imageHref==null && model.title==null && model.description==null){
                    tvDescription.visibility=View.GONE
                }
            }
        }
    }

}
