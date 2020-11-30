package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.NoteDAO;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.Note;
import cosnet.android.R;
import cosnet.android.adapters.NotesListAdapter;

public class CosplayNoteList extends AppCompatActivity {
  private static final String TAG = "CosplayNoteList";

  private static final int REQUEST_DELETE_NOTE = 1;
  private static final int REQUEST_ADD_NOTE = 2;

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
        if(Objects.equals(note.cosplayId, cosplay.cosplayId))
          {
            list.add(note);
          }
      }

      adapter = new NotesListAdapter(this, R.layout.note_list_item, list);
      notesListView.setAdapter(adapter);
    }
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
    notesListView.setOnItemClickListener((parent, v , position, id) ->{
      Note note = notesList.get(position);
      Intent intent = new Intent(this, ShowCosplayNote.class);
      intent.putExtra("note", note);
      startActivityForResult(intent, REQUEST_DELETE_NOTE);
    });
    createNoteBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, AddNoteToCosplay.class);
      intent.putExtra("cosplay", cosplay);
      startActivityForResult(intent, REQUEST_ADD_NOTE);
    });
  }

  private void initialiseWidgets() {
    createNoteBTN = findViewById(R.id.addNewNoteBTN);
    notesListView = findViewById(R.id.NotesList);
    toolbarTitle = findViewById(R.id.toolbarTitle);
    toolbarTitle.setText(cosplay.cosplayName);
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    //switch for the requests
    switch (requestCode) {
      case REQUEST_DELETE_NOTE:
        //switch for the results from add cosplay item
        switch (resultCode) {
          case RESULT_OK:
            String deletedNote = data.getStringExtra("deletedNote");
            Toast.makeText(this, deletedNote + " Deleted", Toast.LENGTH_SHORT).show();
            createList();
            break;
          case RESULT_CANCELED:
            Toast.makeText(this, "Cosplay Note Canceled", Toast.LENGTH_SHORT).show();
            break;
        }
        break;
      case REQUEST_ADD_NOTE:
        switch (resultCode) {
          case RESULT_OK:
            String addNote = data.getStringExtra("addedNote");
            Toast.makeText(this, addNote + " Added", Toast.LENGTH_SHORT).show();
            createList();
            break;
          case RESULT_CANCELED:
            createList();
            break;
        }
        break;
    }
  }
}
