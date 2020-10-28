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

import cosnet.android.DAOs.CosplayDAO;
import cosnet.android.DAOs.CosplayItemDAO;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayWithItems;

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

          //import DAO's
          CosplayDAO cosplayDAO = db.getCosplayDAO();
          CosplayItemDAO CosplayItemDAO = db.getCosplayItemDAO();

          Cosplay cosplay = new Cosplay();
          cosplay.cosplayName = "testname";
          cosplay.cosplaySeries = "testSeries";
          cosplay.startDate = "19/10/2020";
          cosplay.dueDate = "20/10/2020";
          cosplay.budget = 25.00;
          cosplay.status = "In progress";
          cosplayDAO.insertCosplay(cosplay);

          CosplayItem cosplayItem = new CosplayItem();
          cosplayItem.cosplayId = cosplayDAO.getCosplayById(2).cosplayId;
          cosplayItem.buylink = "www.something.com";
          cosplayItem.description = "Epic sword of doom";
          cosplayItem.dueDate = "10/15/2020";
          cosplayItem.price  =150.69;
          cosplayItem.status = "Being Delivered";
          cosplayItem.itemName = "Magic wand";
          CosplayItemDAO.insertItem(cosplayItem);
          Log.d("Add Item", "added item: "+ cosplayItem);

          CosplayItem cosplayItem2 = new CosplayItem();
          cosplayItem2.cosplayId = cosplayDAO.getCosplayById(1).cosplayId;
          cosplayItem2.buylink = "www.something.com";
          cosplayItem2.description = "Epic sword of doom";
          cosplayItem2.dueDate = "10/15/2020";
          cosplayItem2.price  =150.69;
          cosplayItem2.status = "Being Delivered";
          cosplayItem2.itemName = "Okarina of time";
          CosplayItemDAO.insertItem(cosplayItem2);
          Log.d("Add Item", "added item: "+ cosplayItem2);
          /*
          cosplayItem = CosplayItemDAO.getCosplayItemById(1);
          Log.d("Add Item", "added item: "+ cosplayItem.itemName);*/

          List<CosplayWithItems> list = CosplayItemDAO.getCosplayWithItems();
          for (CosplayWithItems c:list){
            Log.d("COSPLAY", "run: "+ c.cosplayItems);
          }





          //////////////////////////////////
/*          Cosplay cosplay = dao.getCosplayById(1);
          Log.d("COSPLAY", "run: "+ cosplay.cosplay_name);
          cosplay.cosplay_name = "nieuwename";
          dao.updateCosplay(cosplay);
          Cosplay uc = dao.getCosplayById(1);
          Log.d("COSPLAY", "run: "+ uc.cosplay_name);

          List<Cosplay> list = dao.getAllCosplays();
          for (Cosplay c:list){
            Log.d("COSPLAY", "run: "+ c.cosplay_name);
          }
          Cosplay cosplay = dao.getCosplayById(1);
          dao.deleteCosplay(cosplay);
          Log.d("COSPLAY", "run:Cosplay deleted");
          list = dao.getAllCosplays();
          for (Cosplay c:list){
            Log.d("COSPLAY", "run: "+ c.cosplay_name);
          }*/
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
