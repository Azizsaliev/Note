package com.example.note.ui.fragment.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemContactBinding
import com.example.note.model.ContactModel

class ContactAdapter(private val listener: ShareContactInterface): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    private var list = listOf<ContactModel>()

    fun setList(list: List<ContactModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
    val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
     return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.binding.btnWhatsapamg.setOnClickListener{
            listener.share(list[position].contact,true)
        }
        holder.binding.imgShareDialer.setOnClickListener {
            listener.share(list[position].contact,false)
        }
    }

    override fun getItemCount() = list.size

    class ContactViewHolder(public val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ContactModel) {
            binding.ctName.text = model.name
            binding.ctNumber.text = model.contact

        }

    }

    interface ShareContactInterface {
       fun share(number:String,shareSwitch: Boolean)
    }
}