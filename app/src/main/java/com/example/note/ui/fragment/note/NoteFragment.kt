package com.example.note.ui.fragment.note


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.base.BaseFragment
import com.example.note.databinding.FragmentNoteBinding
import com.example.note.model.NoteModel
import com.example.note.ui.App


class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),NoteAdapter.NoteClickImterface {
  private lateinit var adapter: NoteAdapter

    override fun setupUi() {
       adapter = NoteAdapter(this)
        binding.rvNote.adapter = adapter
        adapter.addNote(App.db.noteDao()!!.getAllNote())
    }

    override fun setupObserver() {
        super.setupObserver()
        deleteNote()
        binding.btnNote.setOnClickListener{
            controller.navigate(R.id.addNoteFragment)
        }
    }
    private fun deleteNote(){
        val simpleCallBack
        = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Удалить заметку")
                    .setNegativeButton("Нет") { _: DialogInterface?, _: Int ->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setNegativeButton("Да") {_: DialogInterface?, _: Int ->
                        adapter.deleteNote(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
            val itemTouchHelper = ItemTouchHelper(simpleCallBack)
            itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    override fun noteClick(model: NoteModel) {
        val bundle = Bundle()
        bundle.putString("title",model.title)
        bundle.putString("des",model.description)
        model.id?.let { bundle.putInt("id",it) }
        controller.navigate(R.id.addNoteFragment,bundle)
    }
}