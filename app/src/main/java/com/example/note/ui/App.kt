package com.example.note.ui

import android.app.Application
import android.content.SharedPreferences
import com.example.note.data.NoteDatabase
import com.example.note.ui.utiols.Prefs


class App : Application() {
   private lateinit var preferences: SharedPreferences
    companion object {
        lateinit var db: NoteDatabase
        lateinit var prefs :Prefs
    }

    override fun onCreate() {
        super.onCreate()
        db = NoteDatabase.invoke(this)
        preferences = this.applicationContext.getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}