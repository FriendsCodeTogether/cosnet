package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.NoteDAO;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.Note;
import cosnet.android.R;
import cosnet.android.adapters.NotesListAdapter;
import cosnet.android.ui.cosplayItem.AddCosplayItem;
import cosnet.android.ui.cosplayItem.ShowCosplayItem;

public class NoteList extends AppCompatActivity {
  private static final String TAG = "CosplayItemList";

  private ImageButton createNoteBTN;
  private List<Note> notesList;
  private ListView notesListView;
  private NotesListAdapter adapter;
  private CosplayItem cosplayItem;
  private TextView toolbarTitle;
  private CosnetDb db;
  private NoteDAO noteDAO;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notes_list);

    addDatabase();
    Intent incomingIntent = getIntent();
    cosplayItem = (CosplayItem) incomingIntent.getSerializableExtra("cosplayItem");

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
      list.addAll(notesList);

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
    createNoteBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, AddNoteToItem.class);
      intent.putExtra("cosplayItem", cosplayItem);
      startActivity(intent);
    });
  }

  private void initialiseWidgets() {
    createNoteBTN = findViewById(R.id.addNewNoteBTN);
    notesListView = findViewById(R.id.NotesList);
    toolbarTitle = findViewById(R.id.toolbarTitle);
    toolbarTitle.setText(cosplayItem.itemName);
  }
}
