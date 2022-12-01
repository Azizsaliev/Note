package com.example.note.ui.fragment.board

import android.net.wifi.WifiManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.databinding.BoardItemBinding


class BoardAdapter(private val listener: StartListener)
    :RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {
    
 val titleList = listOf("Заметки","Контакты","Конец")
    val desList = listOf("Добавлять заметки","Доступ ко всем контактам","Это все что есть")
    val imgList = listOf(R.drawable.board_first,R.drawable.board_second,R.drawable.board_third)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding =
            BoardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
     holder.onBind(position)
     holder.binding.boardStart.setOnClickListener{
      listener.start()
      }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    inner class BoardViewHolder(val binding:  BoardItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(position: Int) {
           binding.imgBoard.setBackgroundResource(imgList[position])
           binding.tvBoardItem.text = titleList[position]
           binding.tvItem.text = desList[position]

            if (position != 2) {
                binding.boardStart.isVisible = false
            } else {
                binding.boardStart.isGone = false
            }
        }
    }
    interface StartListener {
        fun start()
    }
}