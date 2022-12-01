package com.example.note.data

import androidx.room.*
import com.example.note.model.NoteModel

@Dao
interface NoteDao {
    @Query("SELECT * FROM notemodel ORDER BY title ASC")
    @PrimaryKey
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

}