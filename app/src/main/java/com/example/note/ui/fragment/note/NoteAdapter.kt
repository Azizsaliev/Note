package com.example.note.ui.fragment.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemNoteBinding
import com.example.note.model.NoteModel
import com.example.note.ui.App

class NoteAdapter(private val noteClickInterface: NoteClickImterface): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    private var list: List<NoteModel> = arrayListOf()

    fun addNote(list: List<NoteModel>){
        this.list = list

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
     val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return  NoteViewHolder(binding)
    }
    fun deleteNote(position: Int){
        App.db.noteDao()!!.deleteNote(list[position])
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            noteClickInterface.noteClick(list[position])
        }

    }

    override fun getItemCount(): Int = list.size


    class NoteViewHolder(private var binding:ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(noteModel: NoteModel) {
            binding.tvDes.text = noteModel.description
            binding.tvTitle.text = noteModel.title
        }

    }
    interface NoteClickImterface{
        fun noteClick(model: NoteModel)
    }
}