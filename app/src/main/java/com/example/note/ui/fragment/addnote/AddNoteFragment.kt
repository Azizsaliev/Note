package com.example.note.ui.fragment.addnote


import com.example.note.base.BaseFragment
import com.example.note.databinding.FragmentAddNoteBinding
import com.example.note.model.NoteModel
import com.example.note.ui.App
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    override fun setupUi() {
        if (arguments != null) {
            val title = arguments?.getString("title")
            val desc = arguments?.getString("desc")
            val id = arguments?.getInt("id")
            val date = getCurrentDataTime()
            val dateInString = date.toString("yyyy/mm/dd HH:mm:ss")

            binding.etTitle.setText(title.toString())
            binding.etDes.setText(desc.toString())
            binding.btnSave.setOnClickListener {
                App.db.noteDao()!!.addNote(NoteModel(id,binding.etTitle.text.toString(),binding.etDes.text.toString(),
                date = dateInString))
                controller.navigateUp()
            }
        } else {
            binding.btnSave.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val description = binding.etDes.text.toString()
                val date = getCurrentDataTime()
                val dateInString = date.toString("yyyy/mm/dd HH:mm:ss")
                App.db.noteDao()!!.addNote(
                    NoteModel(
                        title = title,
                        description = description,
                        date = dateInString
                    )
                )
                controller.navigateUp()
                DateTimeFormatter.ISO_DATE_TIME
            }
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDataTime(): Date {
        return Calendar.getInstance().time
    }
}
