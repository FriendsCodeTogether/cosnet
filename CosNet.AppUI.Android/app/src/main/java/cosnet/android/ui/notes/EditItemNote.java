package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Note;
import cosnet.android.R;

public class EditItemNote extends AppCompatActivity {

  private static final String TAG = "Edit Note";

  private CosnetDb db;
  private Note note;
  private Button saveNoteButton;
  private TextInputLayout noteNameLayout;
  private TextInputLayout noteDescriptionLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.edit_note);
    getWindow().setSoftInputMode(
      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    Intent incomingIntent = getIntent();
    note = (Note) incomingIntent.getSerializableExtra("note");

    addToolbar();
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void getItemsBound() {
    saveNoteButton = findViewById(R.id.editNoteAddButton);
    noteNameLayout = findViewById(R.id.editNoteNametextInput);
    noteDescriptionLayout = findViewById(R.id.editNoteDescriptionTextInput);
  }

  private void initializeItems() {
    noteNameLayout.getEditText().setText(note.title);
    noteDescriptionLayout.getEditText().setText(note.description);
  }

  private void setListeners() {
    saveNoteButton.setOnClickListener(v -> onClickAddButton());
  }

  private boolean validateItemNoteDescrition() {
    String description = noteDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      noteDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      noteDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateItemNoteName() {
    String noteName = noteNameLayout.getEditText().getText().toString();
    if (noteName.length() > 150) {
      noteNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(noteName.isEmpty()){
      noteNameLayout.setError(getApplicationContext().getString(R.string.requiredFieldErrorEmpty));
      return false;
    }
    else {
      noteNameLayout.setError(null);
      return true;
    }
  }

  private void onClickAddButton() {
    if (!validateItemNoteName() | !validateItemNoteDescrition()) {
      return;
    }

    note.title = noteNameLayout.getEditText().getText().toString();
    note.description= noteDescriptionLayout.getEditText().getText().toString();

    db.getNoteDAO().updateItem(note);

    Intent intent = new Intent();
    intent.putExtra("editedNote",note);
    setResult(RESULT_OK,intent);
    finish();
  }
}
