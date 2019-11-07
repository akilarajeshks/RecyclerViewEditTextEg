package com.zestworks.recyclervieweg

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private var items: List<String>,
    val onItemClickListener: RecyclerViewClickListener
):RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.editText.setText(items[position])
    }

    fun setList(list:List<String>){
        items = list
    }

    inner class ItemViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener, View.OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean
        {
            onItemClickListener.onItemClicked(adapterPosition)
            return false
        }

        val editText=view.findViewById<EditText>(R.id.edittext)
        init {
            editText.setOnClickListener(this)
            editText.setOnTouchListener(this)
        }
        override fun onClick(v: View?) {
            onItemClickListener.onItemClicked(adapterPosition)
        }

    }
}


