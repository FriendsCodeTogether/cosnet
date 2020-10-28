package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import cosnet.android.Entities.Cosplay;

public class ShowCosplay extends AppCompatActivity {

  private TextView character;
  private TextView series;
  private TextView status;
  private TextView dueDate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay);

    //get cosplay from intent
    Intent incomingIntent = getIntent();
    Cosplay cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

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
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.showcosplay_menu, menu);
    return true;
  }
}
