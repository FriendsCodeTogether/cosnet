package cosnet.android.ui.material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.CosplayItemMaterialDAO;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemMaterial;
import cosnet.android.R;
import me.abhinay.input.CurrencyEditText;

public class EditMaterial extends AppCompatActivity {

  private CosnetDb db;
  private CosplayItemMaterialDAO materialDAO;
  private CosplayItemMaterial material;

  private TextInputLayout materialNameLayout;
  private TextInputLayout materialDescriptionLayout;
  private TextInputLayout materialBuyLinkLayout;
  private CurrencyEditText materialPriceEditText;
  private Button saveMaterialBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_material);

    Intent incomingIntent = getIntent();
    material = (CosplayItemMaterial) incomingIntent.getSerializableExtra("material");

    addToolbar();
    getDatabase();
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

  private void getDatabase() {
    db = CosnetDb.getInstance(this);
    materialDAO = db.getCosplayItemMaterialDAO();
  }

  private void initialiseWidgets() {
    materialNameLayout = findViewById(R.id.EditMaterialNametextInputLayout);
    materialDescriptionLayout = findViewById(R.id.EditMaterialDescriptionTextInputLayout);
    materialBuyLinkLayout = findViewById(R.id.EditMaterialBuyLinkTextInputLayout);
    materialPriceEditText = findViewById(R.id.EditMaterialPriceEditText);
    saveMaterialBtn = findViewById(R.id.EditMaterialSaveBtn);

    materialPriceEditText.setCurrency("€");
    materialPriceEditText.setSpacing(true);
  }

  private void setWidgets(){
    materialNameLayout.getEditText().setText(material.materialName);
    materialDescriptionLayout.getEditText().setText(material.description);
    materialPriceEditText.setText(material.price != null ? material.price.toString() : "");
    materialBuyLinkLayout.getEditText().setText(material.buylink);
  }

  private void setListeners() {
    saveMaterialBtn.setOnClickListener(v -> onClickSaveButton());
  }

  private void onClickSaveButton() {
    if (!validateMaterialName() | !validateMaterialDescrition() | !validateMaterialBuyLink()) {
      return;
    }

    material.materialName = materialNameLayout.getEditText().getText().toString();
    material.description = materialDescriptionLayout.getEditText().getText().toString();
    material.price = materialPriceEditText.getText().toString().isEmpty() ? null : materialPriceEditText.getCleanDoubleValue();
    material.buylink = materialBuyLinkLayout.getEditText().getText().toString();
    materialDAO.updateItem(material);

    Intent intent = new Intent();
    intent.putExtra("editedMaterial",material);
    setResult(RESULT_OK,intent);
    finish();
  }

  private boolean validateMaterialBuyLink() {
    String buyLink = materialBuyLinkLayout.getEditText().getText().toString();
    if (buyLink.length() > 250) {
      materialBuyLinkLayout.setError(getApplicationContext().getString(R.string.max250Characters));
      return false;
    } else {
      materialBuyLinkLayout.setError(null);
      return true;
    }
  }

  private boolean validateMaterialDescrition() {
    String description = materialDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      materialDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      materialDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateMaterialName() {
    String itemName = materialNameLayout.getEditText().getText().toString();
    if (itemName.length() > 150) {
      materialNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(itemName.isEmpty()){
      materialNameLayout.setError(getApplicationContext().getString(R.string.characterNameErrorEmpty));
      return false;
    }
    else {
      materialNameLayout.setError(null);
      return true;
    }
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
