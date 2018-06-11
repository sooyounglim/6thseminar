package com.stellar1198.a6thseminar

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class CollectionViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var collectionNum : TextView = itemView!!.findViewById(R.id.collection_item_number) as TextView
    var collectionName : TextView = itemView!!.findViewById(R.id.collection_item_name) as TextView
    var collectionType : TextView = itemView!!.findViewById(R.id.collection_item_type) as TextView
}