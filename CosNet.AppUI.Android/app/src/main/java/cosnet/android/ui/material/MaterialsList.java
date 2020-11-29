package cosnet.android.ui.material;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemMaterial;
import cosnet.android.R;

public class MaterialsList extends AppCompatActivity {

  private final static int REQUEST_ADD_MATERIAL = 1;

  private ImageButton addMaterialBtn;
  //temp
  private ImageButton showButton;

  private CosplayItem cosplayItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_materials_list);

    Intent incomingIntent = getIntent();
    cosplayItem = (CosplayItem) incomingIntent.getSerializableExtra("item");

    addToolbar();
    initialiseWidgets();
    setListeners();
  }

  private void initialiseWidgets() {
    addMaterialBtn = findViewById(R.id.addNewMaterialBTN);
    showButton = findViewById(R.id.showBTN);
  }

  private void setListeners() {
    addMaterialBtn.setOnClickListener(v -> {
      Intent intent = new Intent(this, AddMaterial.class);
      intent.putExtra("item", cosplayItem);
      startActivityForResult(intent, REQUEST_ADD_MATERIAL);
    });

    //temp
    showButton.setOnClickListener(v ->{
      Intent intent = new Intent(this, ShowMaterial.class);
      startActivity(intent);
    });
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
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    //switch for the requests
    switch (requestCode) {
      case REQUEST_ADD_MATERIAL:
        //switch for the results from add cosplay item
        switch (resultCode) {
          case RESULT_OK:
            String addedMaterialName = data.getStringExtra("addedMaterialName");
            Toast.makeText(this, addedMaterialName + " Added", Toast.LENGTH_SHORT).show();
            //createList();
            break;
          case RESULT_CANCELED:
            Toast.makeText(this, "Material Canceled", Toast.LENGTH_SHORT).show();
            break;
        }
        break;
    }
  }
}
