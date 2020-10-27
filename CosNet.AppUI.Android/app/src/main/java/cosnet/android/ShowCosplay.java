package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import cosnet.android.Entities.Cosplay;

public class ShowCosplay extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay);

    Intent intent = getIntent();
    Cosplay cosplay = (Cosplay) intent.getSerializableExtra("cosplay");
    Log.d("ShowCosplay", "onCreate: " + cosplay.cosplayName + " " + cosplay.cosplaySeries);

    TextView character = (TextView) findViewById(R.id.Character);
    character.setText(cosplay.cosplayName);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.showcosplay_menu,menu);
    return true;
  }
}
