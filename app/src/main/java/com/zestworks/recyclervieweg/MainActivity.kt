package com.zestworks.recyclervieweg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val listViewModel = ViewModelProviders.of(this)[ListViewModel::class.java]

        listViewModel.listLiveData.observe(this, Observer {
            when (recycler.adapter){
                null->{
                    recycler.apply {
                        adapter = RecyclerAdapter(it, object : RecyclerViewClickListener{
                            override fun onItemClicked(position: Int) {
                                listViewModel.onItemClicked(position)
                            }

                        })
                        layoutManager = LinearLayoutManager(this@MainActivity)

                    }
                }else->{
                (recycler.adapter as RecyclerAdapter).setList(it)
                (recycler.adapter as RecyclerAdapter).notifyDataSetChanged()
            }
            }
        })


    }
}

interface RecyclerViewClickListener{
    fun onItemClicked(position: Int)
}