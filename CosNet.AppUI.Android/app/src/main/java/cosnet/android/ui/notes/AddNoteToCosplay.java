package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.Note;
import cosnet.android.R;

public class AddNoteToCosplay extends AppCompatActivity {

  private static final String TAG = "Add Note";

  private CosnetDb db;
  private Cosplay cosplay;
  private Button addNoteButton;
  private TextInputLayout noteNameLayout;
  private TextInputLayout noteDescriptionLayout;
  private String noteType;
  private String date;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_note);

    addToolbar();
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");
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
    addNoteButton = (Button) findViewById(R.id.NoteAddButton);
    noteNameLayout = (TextInputLayout) findViewById(R.id.noteNametextInput);
    noteDescriptionLayout = (TextInputLayout) findViewById(R.id.noteDescriptionTextInput);

  }

  private void initializeItems() {
    noteType="cosplay";
    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    date = df.format(c);
  }

  private void setListeners() {
    addNoteButton.setOnClickListener(v -> onClickAddButton());
  }

  private boolean validateItemDescrition() {
    String description = noteDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      noteDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      noteDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateItemName() {
    String itemName = noteNameLayout.getEditText().getText().toString();
    if (itemName.length() > 150) {
      noteNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(itemName.isEmpty()){
      noteNameLayout.setError(getApplicationContext().getString(R.string.characterNameErrorEmpty));
      return false;
    }
    else {
      noteNameLayout.setError(null);
      return true;
    }
  }

  private void onClickAddButton() {
    if (!validateItemName() | !validateItemDescrition()) {
      return;
    }
    Note newNote = new Note();

    newNote.cosplayId=cosplay.cosplayId;
    newNote.itemId = "";
    newNote.title = noteNameLayout.getEditText().getText().toString();
    newNote.description= noteDescriptionLayout.getEditText().getText().toString();
    newNote.type=noteType;
    newNote.createdDate=date;

    db.getNoteDAO().insertItem(newNote);

    Intent intent = new Intent();
    intent.putExtra("addedNote",newNote.title);
    setResult(RESULT_OK,intent);
    finish();
  }
}

