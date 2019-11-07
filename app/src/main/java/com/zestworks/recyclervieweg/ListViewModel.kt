package com.zestworks.recyclervieweg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val list = mutableListOf("first","second","third","fourth")
    val listLiveData = MutableLiveData<List<String>>().apply { postValue(list) }

    fun onItemClicked(position:Int){
        if (position>0) {
            val s = list[position]
            list.removeAt(position)
            list.add(0, s)
            listLiveData.postValue(list)
        }
     }
}