package cosnet.android.ui.cosplayItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;
import cosnet.android.CosnetDb;
import cosnet.android.ui.notes.ItemNoteList;

public class ShowCosplayItem extends AppCompatActivity {

  private static final int REQUEST_EDIT_COSPLAYITEM = 1;
  private CosplayItem item;

  private ImageButton itemNotesBTN;
  private ImageView cosplayItemPic;
  private TextView cosplayItemName;
  private TextView cosplayItemDescription;
  private TextView cosplayItemStatus;
  private TextView cosplayItemBudget;
  private TextView cosplayItemDueDate;
  private TextView cosplayItemBuylink;
  private TextView cosplayItemWorkTime;
  private TextView cosplayItemProgress;
  private ConstraintLayout madeitemattributesLayout;
  private ConstraintLayout boughtitemattributesLayout;

  private CosnetDb db;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay_item);

    Intent incomingIntent = getIntent();
    item = (CosplayItem) incomingIntent.getSerializableExtra("cosplayItem");

    addToolbar();
    addDatabase();
    initialiseWidgets();
    setWidgets();
    togglelayout();
    setListeners();
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        Intent intent = new Intent(this, EditCosplayItem.class);
        intent.putExtra("cosplayItem", this.item);
        startActivityForResult(intent, REQUEST_EDIT_COSPLAYITEM);
        return true;
      case R.id.showCosplayItemDeleteMenu:
        AlertDialog alertDialog = new AlertDialog.Builder(ShowCosplayItem.this).create();
        alertDialog.setTitle("Oh No");
        alertDialog.setMessage("Are you sure you want to delete this item: " + this.item.itemName + "?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", (dialog, which) -> {
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", (dialog, which) -> {
          db.getCosplayItemDAO().deleteItem(this.item);
          Intent intentDelete = new Intent(this, CosplayItemsList.class);
          intentDelete.putExtra("deletedCosplayItemName", this.item.itemName);
          setResult(RESULT_OK, intentDelete);
          finish();
        });
        alertDialog.show();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void setWidgets() {
    cosplayItemName.setText(item.itemName);

    cosplayItemDescription.setText(item.description);
    cosplayItemStatus.setText(item.status);
    cosplayItemBudget.setText("€" + item.price);
    cosplayItemDueDate.setText(item.dueDate);
    if (item.isMade == 0) {
      cosplayItemBuylink.setText(item.buylink);
    } else {
      cosplayItemWorkTime.setText(item.worktimeHours + " H " + item.worktimeMinutes + " min");
      cosplayItemProgress.setText(item.progress + "%");
    }
  }

  private void initialiseWidgets() {
    itemNotesBTN = findViewById(R.id.ItemNoteButton);
    cosplayItemName = findViewById(R.id.CosplayItemShowName);
    cosplayItemPic = findViewById(R.id.CosplayItemShowPic);
    cosplayItemDescription = findViewById(R.id.CosplayItemShowDescription);
    cosplayItemStatus = findViewById(R.id.CosplayItemShowStatus);
    cosplayItemBudget = findViewById(R.id.CosplayItemShowPrice);
    cosplayItemDueDate = findViewById(R.id.CosplayItemShowDueDate);
    cosplayItemBuylink = findViewById(R.id.CosplayItemShowBuyLink);
    cosplayItemWorkTime = findViewById(R.id.CosplayItemShowWorkTime);
    cosplayItemProgress = findViewById(R.id.CosplayItemShowProgress);
    madeitemattributesLayout = findViewById(R.id.MadeItemAttributesLayout);
    boughtitemattributesLayout = findViewById(R.id.BoughtItemAttributesLayout);
  }

  private void setListeners() {
    itemNotesBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, ItemNoteList.class);
      intent.putExtra("cosplayItem", item);
      startActivity(intent);
    });
  }
  private void togglelayout() {
    if (item.isMade == 0) {
      madeitemattributesLayout.setVisibility(View.GONE);
      boughtitemattributesLayout.setVisibility(View.VISIBLE);

    } else {
      boughtitemattributesLayout.setVisibility(View.GONE);
      madeitemattributesLayout.setVisibility(View.VISIBLE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_EDIT_COSPLAYITEM) {
      if (resultCode == RESULT_OK) {
        item = (CosplayItem) data.getSerializableExtra("editedCosplayItem");
        Toast.makeText(this, item.itemName + " Edited", Toast.LENGTH_SHORT).show();
        initialiseWidgets();
        setWidgets();
        togglelayout();
      }
    }
  }
}
