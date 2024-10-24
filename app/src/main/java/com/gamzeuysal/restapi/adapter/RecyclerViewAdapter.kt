package com.gamzeuysal.restapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamzeuysal.restapi.R
import com.gamzeuysal.restapi.model.PhoneModel
import com.gamzeuysal.restapi.view.ListFragment
import kotlinx.android.synthetic.main.row_layout.view.*


class RecyclerViewAdapter(private val phoneList:ArrayList<PhoneModel>,private val listener: Listener, private  val context: Context):RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(phoneModel: PhoneModel)
    }
    class RowHolder (view :View):RecyclerView.ViewHolder(view){
      fun bind (phoneModel: PhoneModel,position: Int,listener:Listener,context: Context){
          itemView.brandTextView.text=phoneModel.brand
          itemView.modelTextView.text=phoneModel.model
          itemView.priceTextView.text=phoneModel.price.toString()
          //Glide
          Glide.with(context).load(phoneModel.imageUrl).into(itemView.imageViewPhone)

          itemView.setOnClickListener {
              listener.onItemClick(phoneModel)
          }

      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return  RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(phoneList[position],position,listener,context)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

}