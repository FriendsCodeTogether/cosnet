package cosnet.android;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import cosnet.android.Data.DAOs.CosplayDAO;
import cosnet.android.Data.DAOs.CosplayItemDAO;
import cosnet.android.Data.Relations.CosplayWithItems;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;

public class MainActivity extends AppCompatActivity {

  private CosnetDb db;
  private AppBarConfiguration mAppBarConfiguration;
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    db = CosnetDb.getInstance(this);

    new Thread(new Runnable() {
      @Override
      public void run() {

      }
    }).start();


    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    mAppBarConfiguration = new AppBarConfiguration.Builder(
      R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
      .setDrawerLayout(drawer)
      .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
      || super.onSupportNavigateUp();
  }

}
