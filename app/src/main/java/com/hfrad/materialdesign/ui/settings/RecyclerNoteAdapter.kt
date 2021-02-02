package com.hfrad.materialdesign.ui.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hfrad.materialdesign.R
import kotlinx.android.synthetic.main.note_layout.view.*


 class RecyclerNoteAdapter(
        private var data: MutableList<String>

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
                var header = "$position-ая заметка "
                itemView.header.text = header
                itemView.note_description.text = data[position]
                itemView.deleteButton.setOnClickListener { removeItem() }
        }

        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }


     fun appendItem(note:String) {
         data.add(note)
         notifyItemInserted(itemCount - 1)
     }
     private fun generateItem() = "Добавленная заметка"




     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return  NoteViewHolder(inflater.inflate(R.layout.note_layout, parent, false) as View)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       holder as NoteViewHolder
        holder.bind(position)

    }
}