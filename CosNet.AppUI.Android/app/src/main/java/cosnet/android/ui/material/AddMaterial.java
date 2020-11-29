package cosnet.android.ui.material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.CosplayItemMaterialDAO;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemMaterial;
import cosnet.android.R;
import me.abhinay.input.CurrencyEditText;

public class AddMaterial extends AppCompatActivity {

  private CosnetDb db;
  private CosplayItemMaterialDAO materialDAO;
  private CosplayItem cosplayItem;

  private TextInputLayout materialNameLayout;
  private TextInputLayout materialDescriptionLayout;
  private TextInputLayout materialBuyLinkLayout;
  private CurrencyEditText materialPriceEditText;
  private Button saveMaterialBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_material);

    addToolbar();
    getDatabase();
    initialiseWidgets();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplayItem = (CosplayItem) incomingIntent.getSerializableExtra("item");
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
    materialNameLayout = findViewById(R.id.AddMaterialNametextInputLayout);
    materialDescriptionLayout = findViewById(R.id.AddMaterialDescriptionTextInputLayout);
    materialBuyLinkLayout = findViewById(R.id.AddMaterialBuyLinkTextInputLayout);
    materialPriceEditText = findViewById(R.id.AddMaterialPriceEditText);
    saveMaterialBtn = findViewById(R.id.AddMaterialAddBtn);

    materialPriceEditText.setCurrency("€");
    materialPriceEditText.setSpacing(true);
  }

  private void setListeners() {
    saveMaterialBtn.setOnClickListener(v -> onClickAddButton());
  }

  private void onClickAddButton() {
    if (!validateMaterialName() | !validateMaterialDescrition() | !validateMaterialBuyLink()) {
      return;
    }

    CosplayItemMaterial newMaterial = new CosplayItemMaterial();
    newMaterial.itemId = cosplayItem.itemId;
    newMaterial.materialName = materialNameLayout.getEditText().getText().toString();
    newMaterial.description = materialDescriptionLayout.getEditText().getText().toString();
    newMaterial.price = materialPriceEditText.getText().toString().isEmpty() ? null : materialPriceEditText.getCleanDoubleValue();
    newMaterial.buylink = materialBuyLinkLayout.getEditText().getText().toString();
    materialDAO.insertItem(newMaterial);

    Intent intent = new Intent();
    intent.putExtra("addedMaterialName",newMaterial.materialName);
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
}
