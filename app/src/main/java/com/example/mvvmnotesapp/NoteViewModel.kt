package com.example.mvvmnotesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNodeDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // Using Co-routines in order to work in background thread , or in order to work with suspend function
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}