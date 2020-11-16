package cosnet.android.ui.cosplay;

import androidx.annotation.NonNull;
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
    setListeners();

  }

  private void setListeners() {
    cosplayItemListBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, CosplayItemsList.class);
      intent.putExtra("cosplay", (Serializable) cosplay);
      startActivity(intent);
    });
  }

  private void initialiseWidgets() {
    cosplayItemListBTN = findViewById(R.id.CosplayItemListBtn);

    if (cosplay != null) {
      //get fields from view
      character = (TextView) findViewById(R.id.ShowCosplayCharacterName);
      series = (TextView) findViewById(R.id.ShowCosplaySeries);
      status = (TextView) findViewById(R.id.ShowCosplayStatus);
      dueDate = (TextView) findViewById(R.id.ShowCosplayDueDate);

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

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        intent.putExtra("cosplay",(Serializable) cosplay);
        startActivity(intent);
        return true;
      case R.id.showCosplayFinishMenu:
        Toast.makeText(this, "finish selected", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.showCosplayDeleteMenu:
          AlertDialog alertDialog = new AlertDialog.Builder(ShowCosplay.this).create();
          alertDialog.setTitle("Oh No");
          alertDialog.setMessage("Are you sure you want to delete your " + cosplay.cosplayName+" cosplay?");
          alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", (dialog, which) -> {  });
          alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", (dialog, which) -> {
            db.getCosplayDAO().deleteCosplay(cosplay);
            Intent intentDelete = new Intent(this, MainActivity.class);
            startActivity(intentDelete);
            Toast.makeText(this, "deleted " + cosplay.cosplayName, Toast.LENGTH_SHORT).show();
          });
          alertDialog.show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
