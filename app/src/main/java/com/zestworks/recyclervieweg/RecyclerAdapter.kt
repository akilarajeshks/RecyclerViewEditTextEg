package com.zestworks.recyclervieweg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private var items: List<String>,
    val onItemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.editText.setText(items[position])
    }

    fun setList(list: List<String>) {
        val diffCallback = ListDiffUtil(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = list
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val editText: EditText = view.findViewById(R.id.edittext)

        init {
            editText.setOnFocusChangeListener { v, isFocused ->
                onItemClickListener.onItemClicked(adapterPosition)
            }
        }
    }
}


class ListDiffUtil(private val oldList: List<String>, private val newList: List<String>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.containsAll(newList)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val name = oldList[oldItemPosition]
        val name1 = newList[newItemPosition]

        return name == name1
    }
}


