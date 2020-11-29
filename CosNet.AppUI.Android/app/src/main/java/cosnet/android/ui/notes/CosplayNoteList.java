package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.NoteDAO;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.Note;
import cosnet.android.R;
import cosnet.android.adapters.NotesListAdapter;

public class CosplayNoteList extends AppCompatActivity {
  private static final String TAG = "CosplayNoteList";

  private ImageButton createNoteBTN;
  private List<Note> notesList;
  private ListView notesListView;
  private NotesListAdapter adapter;
  private Cosplay cosplay;
  private TextView toolbarTitle;
  private CosnetDb db;
  private NoteDAO noteDAO;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes_list);

    addDatabase();
    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

    addToolbar();
    initialiseWidgets();
    createList();
    setListeners();
  }

  private void createList() {
    notesList = noteDAO.getNotes();
    if (!notesList.isEmpty())
    {
      ArrayList<Note> list = new ArrayList<>();
      for (Note note : notesList)
      {
        if(note.cosplayId != null)
          {
            list.add(note);
          }
      }

      adapter = new NotesListAdapter(this, R.layout.note_list_item, list);
      notesListView.setAdapter(adapter);
    }
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
    noteDAO = db.getNoteDAO();
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setListeners() {
    createNoteBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, AddNoteToCosplay.class);
      intent.putExtra("cosplay", cosplay);
      startActivity(intent);
    });
  }

  private void initialiseWidgets() {
    createNoteBTN = findViewById(R.id.addNewNoteBTN);
    notesListView = findViewById(R.id.NotesList);
    toolbarTitle = findViewById(R.id.toolbarTitle);
    toolbarTitle.setText(cosplay.cosplayName);
  }
}
