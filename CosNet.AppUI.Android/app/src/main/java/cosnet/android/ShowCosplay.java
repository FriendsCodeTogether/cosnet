package cosnet.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import cosnet.android.Entities.Cosplay;

public class ShowCosplay extends AppCompatActivity {

  private TextView character;
  private TextView series;
  private TextView status;
  private TextView dueDate;
  private Cosplay cosplay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    //get cosplay from intent
    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

    if (cosplay != null) {
      //get fields from view
      character = (TextView) findViewById(R.id.Character);
      series = (TextView) findViewById(R.id.Series);
      status = (TextView) findViewById(R.id.Status);
      dueDate = (TextView) findViewById(R.id.DueDate);

      //Set fields according to value
      character.setText(cosplay.cosplayName);
      series.setText(cosplay.cosplaySeries);
      status.setText(cosplay.status);
      if (cosplay.dueDate == null || cosplay.dueDate.isEmpty()) {
        findViewById(R.id.DueDateLbl).setVisibility(View.INVISIBLE);
        dueDate.setVisibility(View.INVISIBLE);
      } else {
        dueDate.setText(cosplay.dueDate);
      }
    }
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
        Toast.makeText(this, "delete selected", Toast.LENGTH_SHORT).show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
