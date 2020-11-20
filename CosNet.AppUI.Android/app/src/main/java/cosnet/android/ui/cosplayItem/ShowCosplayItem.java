package cosnet.android.ui.cosplayItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;

public class ShowCosplayItem extends AppCompatActivity {

  private CosplayItem item;

  private ImageView cosplayItemPic;
  private TextView cosplayItemName;
  private TextView cosplayItemDescription;
  private TextView cosplayItemStatus;
  private TextView cosplayItemBudget;
  private TextView cosplayItemDueDate;
  private TextView cosplayItemBuylink;
  private TextView cosplayItemWorkTime;
  private TextView cosplayItemProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_cosplay_item);

    Intent incomingIntent = getIntent();
    item = (CosplayItem) incomingIntent.getSerializableExtra("cosplayItem");

    addToolbar();
    initialiseWidgets();
    setWidgets();
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

  private void setWidgets() {
    cosplayItemName.setText(item.itemName);

    cosplayItemDescription.setText(item.description);
    cosplayItemStatus.setText(item.status);
    cosplayItemBudget.setText("€" + item.price);
    cosplayItemDueDate.setText(item.dueDate);
    if (item.isMade == 0){
      cosplayItemBuylink.setText(item.buylink);
    } else {
      cosplayItemWorkTime.setText(item.worktimeHours +" H " + item.worktimeMinutes + " min");
      cosplayItemProgress.setText(item.progress + "%");
    }
  }

  private void initialiseWidgets() {
    cosplayItemName = findViewById(R.id.CosplayItemShowName);
    cosplayItemPic = findViewById(R.id.CosplayItemShowPic);
    cosplayItemDescription = findViewById(R.id.CosplayItemShowDescription);
    cosplayItemStatus = findViewById(R.id.CosplayItemShowStatus);
    cosplayItemBudget = findViewById(R.id.CosplayItemShowPrice);
    cosplayItemDueDate = findViewById(R.id.CosplayItemShowDueDate);
    cosplayItemBuylink = findViewById(R.id.CosplayItemShowBuyLink);
    cosplayItemWorkTime = findViewById(R.id.CosplayItemShowWorkTime);
    cosplayItemProgress = findViewById(R.id.CosplayItemShowProgress);

    if (item.isMade == 0){
      ConstraintLayout madeitemattributesLayout = findViewById(R.id.MadeItemAttributesLayout);
      madeitemattributesLayout.setVisibility(View.GONE);
    }else{
      ConstraintLayout boughtitemattributesLayout = findViewById(R.id.BoughtItemAttributesLayout);
      boughtitemattributesLayout.setVisibility(View.GONE);
    }
  }
}
