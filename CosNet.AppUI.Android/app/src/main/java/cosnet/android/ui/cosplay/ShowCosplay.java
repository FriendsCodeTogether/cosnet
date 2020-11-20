package cosnet.android.ui.cosplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.MainActivity;
import cosnet.android.R;
import cosnet.android.ui.cosplayItem.CosplayItemsList;

public class ShowCosplay extends AppCompatActivity {

  private static final int REQUEST_EDIT_COSPLAY = 1;

  private ImageButton cosplayItemListBTN;
  private TextView character;
  private TextView series;
  private TextView status;
  private TextView dueDate;
  private Cosplay cosplay;

  private CosnetDb db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay);

    //get cosplay from intent
    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

    addToolbar();
    addDatabase();
    initialiseWidgets();
    setWidgets();
    setListeners();

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

  private void initialiseWidgets() {
    cosplayItemListBTN = findViewById(R.id.CosplayItemListBtn);
    character = findViewById(R.id.ShowCosplayCharacterName);
    series = findViewById(R.id.ShowCosplaySeries);
    status = findViewById(R.id.ShowCosplayStatus);
    dueDate = findViewById(R.id.ShowCosplayDueDate);

  }

  private void setWidgets() {
    if (cosplay != null) {
      //Set fields according to value
      character.setText(cosplay.cosplayName);
      series.setText(cosplay.cosplaySeries);
      status.setText(cosplay.status);
      if (cosplay.dueDate == null || cosplay.dueDate.isEmpty()) {
        findViewById(R.id.ShowCosplayDueDateLbl).setVisibility(View.INVISIBLE);
        dueDate.setVisibility(View.INVISIBLE);
      } else {
        dueDate.setText(cosplay.dueDate);
      }
    }
  }

  private void setListeners() {
    cosplayItemListBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, CosplayItemsList.class);
      intent.putExtra("cosplay", cosplay);
      startActivity(intent);
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.showcosplay_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.showCosplayEditMenu:
        Intent intent = new Intent(this, EditCosplay.class);
        intent.putExtra("cosplay", cosplay);
        startActivityForResult(intent, REQUEST_EDIT_COSPLAY);
        return true;
      case R.id.showCosplayFinishMenu:
        Toast.makeText(this, "finish selected", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.showCosplayDeleteMenu:
        AlertDialog alertDialog = new AlertDialog.Builder(ShowCosplay.this).create();
        alertDialog.setTitle("Oh No");
        alertDialog.setMessage("Are you sure you want to delete your " + cosplay.cosplayName + " cosplay?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", (dialog, which) -> {
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", (dialog, which) -> {
          db.getCosplayDAO().deleteCosplay(cosplay);
          Intent intentDelete = new Intent(this, MainActivity.class);
          intentDelete.putExtra("deletedCosplayName", cosplay.cosplayName);
          setResult(RESULT_OK, intentDelete);
          finish();
        });
        alertDialog.show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_EDIT_COSPLAY) {
      if (resultCode == RESULT_OK) {
        cosplay = (Cosplay) data.getSerializableExtra("editedCosplay");
        Toast.makeText(this, cosplay.cosplayName + " Edited", Toast.LENGTH_SHORT).show();
        setWidgets();
      }
    }
  }
}
