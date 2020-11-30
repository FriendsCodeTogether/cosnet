package cosnet.android.ui.material;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemMaterial;
import cosnet.android.R;
import cosnet.android.ui.cosplayItem.CosplayItemsList;
import cosnet.android.ui.cosplayItem.EditCosplayItem;
import cosnet.android.ui.cosplayItem.ShowCosplayItem;

public class ShowMaterial extends AppCompatActivity {

  private static final int REQUEST_EDIT_MATERIAL = 1;
  private CosplayItemMaterial material;
  private CosnetDb db;

  private TextView materialName;
  private TextView materialDescription;
  private TextView materialPrice;
  private TextView materialBuyLink;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_material);

    Intent incomingIntent = getIntent();
    material = (CosplayItemMaterial) incomingIntent.getSerializableExtra("material");
    Log.d("Test", "onCreate: "+ material);
    addToolbar();
    addDatabase();
    initialiseWidgets();
    setWidgets();
  }

  private void setWidgets() {
  materialName.setText(material.materialName);
  materialDescription.setText(material.description);
  materialPrice.setText("€ "+material.price);
  materialBuyLink.setText(material.buylink);
  }

  private void initialiseWidgets() {
  materialBuyLink = findViewById(R.id.MaterialShowBuyLink);
  materialDescription = findViewById(R.id.MaterialShowDescription);
  materialName = findViewById(R.id.MaterialShowName);
  materialPrice = findViewById(R.id.MaterialShowPrice);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.show_cosplayitem_menu, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      case R.id.showCosplayItemEditMenu:
        Intent intent = new Intent(this, EditMaterial.class);
        intent.putExtra("material", this.material);
        startActivityForResult(intent, REQUEST_EDIT_MATERIAL);
        return true;
      case R.id.showCosplayItemDeleteMenu:
        AlertDialog alertDialog = new AlertDialog.Builder(ShowMaterial.this).create();
        alertDialog.setTitle("Oh No");
        alertDialog.setMessage("Are you sure you want to delete this item: " + material.materialName + "?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", (dialog, which) -> {
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", (dialog, which) -> {
          db.getCosplayItemMaterialDAO().deleteItem(this.material);
          Intent intentDelete = new Intent(this, MaterialsList.class);
          intentDelete.putExtra("deletedMaterialName", this.material.materialName);
          setResult(RESULT_OK, intentDelete);
          finish();
        });
        alertDialog.show();
        return true;
    }
    return super.onOptionsItemSelected(item);
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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_EDIT_MATERIAL) {
      if (resultCode == RESULT_OK) {
        material = (CosplayItemMaterial) data.getSerializableExtra("editedMaterial");
        Toast.makeText(this, material.materialName + " Edited", Toast.LENGTH_SHORT).show();
        setWidgets();
      }
    }
  }
}
