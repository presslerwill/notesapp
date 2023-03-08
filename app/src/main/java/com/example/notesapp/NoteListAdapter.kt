package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteListAdapter(private val dataSet: List<Note>): RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblNoteTitle: TextView
        val lblNoteText: TextView
        val lblNoteDatEdited: TextView
        val lblId: TextView

        init {
            // Define click listener for the ViewHolder's View.
            lblNoteTitle = view.findViewById(R.id.noteTitle)
            lblNoteText = view.findViewById(R.id.noteText)
            lblNoteDatEdited = view.findViewById(R.id.noteDateEdited)
            lblId = view.findViewById<TextView>(R.id.listId)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.note_row_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.lblNoteTitle.text = dataSet[position].title
        viewHolder.lblNoteText.text = dataSet[position].contents
        viewHolder.lblNoteDatEdited.text = dataSet[position].dateEdited
        viewHolder.lblId.text = "${dataSet[position].uid}"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}