package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemNote;
import cosnet.android.R;
import cosnet.android.ui.cosplayItem.ShowCosplayItem;

public class AddNote extends AppCompatActivity {

  private static final String TAG = "Add Note";

  private CosnetDb db;
  private CosplayItem cosplayItem;
  private Button addNoteButton;
  private EditText noteTitleEditText;
  private EditText noteDescriptionEditText;
  private EditText noteTypeEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_item_main);

    addToolbar();
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplayItem = (CosplayItem) incomingIntent.getSerializableExtra("cosplayItem");
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void getItemsBound() {
  }

  private void initializeItems() {
  //addNoteButton = findViewById(R.id.NoteAddButton);
  //noteTitleEditText = findViewById(R.id.NoteTitleEditText);
  //noteDescriptionEditText = findViewById(R.id.NoteDescriptionEditText);
  //noteTypeEditText = findViewById(R.id.NoteTypeEditText);
  }

  private void setListeners() {
    addNoteButton.setOnClickListener(i -> onClickAddButton());
  }

  private void onClickAddButton() {
    CosplayItemNote newNote = new CosplayItemNote();
    newNote.cosplayItemId = cosplayItem.itemId;
    //newNote.title =
    //newNote.description =
    //newNote.type =
    db.getCosplayItemNoteDAO().insertItem(newNote);

    Intent intent = new Intent(this, ShowCosplayItem.class);
    intent.putExtra("cosplayItem", cosplayItem);
    startActivity(intent);
  }
}

