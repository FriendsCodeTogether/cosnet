package cosnet.android.ui.notes;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Note;
import cosnet.android.R;
import cosnet.android.ui.cosplayItem.ShowCosplayItem;

public class ShowItemNote extends AppCompatActivity {

  private static final int REQUEST_EDIT_NOTE = 1;

  private TextView noteName;
  private TextView noteDescription;
  private TextView noteDate;
  private Note note;
  private CosnetDb db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_note);

    Intent incomingIntent = getIntent();
    note = (Note) incomingIntent.getSerializableExtra("note");

    addToolbar();
    addDatabase();
    initialiseWidgets();
    setWidgets();
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

  private void initialiseWidgets(){
    noteName = findViewById(R.id.NoteTitleLabel);
    noteDescription = findViewById(R.id.NoteDescriptionLabel);
    noteDate = findViewById(R.id.NoteStampLabel);
  }

  private void setWidgets(){
    if (note != null){
      noteName.setText(note.title);
      noteDescription.setText(note.description);
      noteDate.setText(note.createdDate);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.shownote_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.showNoteEditMenu:
        Intent intent = new Intent(this, EditItemNote.class);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_EDIT_NOTE);
        return true;
      case R.id.showNoteDeleteMenu:
        AlertDialog alertDialog = new AlertDialog.Builder(ShowItemNote.this).create();
        alertDialog.setTitle("Oh No");
        alertDialog.setMessage("Are you sure you want to delete your " + note.title + " note?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", (dialog, which) -> { });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", (dialog, which) -> {
          db.getNoteDAO().deleteItem(note);
          Intent intentDelete = new Intent(this, ShowCosplayItem.class);
          intentDelete.putExtra("deletedNote", note.title);
          setResult(RESULT_OK, intentDelete);
          finish();
        });
        alertDialog.show();
        return true;
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_EDIT_NOTE) {
      if (resultCode == RESULT_OK) {
        note = (Note) data.getSerializableExtra("editNote");
        Toast.makeText(this, note.title + " Edited", Toast.LENGTH_SHORT).show();
        setWidgets();
      }
    }
  }
}
