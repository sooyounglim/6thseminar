package com.stellar1198.a6thseminar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CollectionAdapter(var collectionItems : ArrayList<PokmonVO>) : RecyclerView.Adapter<CollectionViewHolder>() {
    private lateinit var onItemClick : View.OnClickListener

    fun setOnItemClickListner(l : View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val mainView : View = LayoutInflater.from(parent.context).inflate(R.layout.collection_item, parent, false)
        mainView.setOnClickListener(onItemClick)
        return CollectionViewHolder(mainView)
    }

    override fun getItemCount(): Int = collectionItems.size

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.collectionName.text = collectionItems[position]!!.name
        holder.collectionNum.text = collectionItems[position]!!.num.toString()
        holder.collectionType.text = collectionItems[position]!!.type
    }
}