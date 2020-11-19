package cosnet.android.ui.cosplayItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.CosplayItemDAO;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;
import cosnet.android.adapters.CosplayItemsExpandableListAdapter;

public class CosplayItemsList extends AppCompatActivity {

  private static final String TAG = "CosplayItemList";

  private ImageButton createCosplayItemBTN;
  private TextView character;
  private TextView series;
  private TextView status;
  private TextView dueDate;
  private List<CosplayItem> itemsList;
  private ExpandableListView cosplayItemsListView;
  private List<CosplayItem> madeCosplays;
  private List<CosplayItem> boughtCosplays;
  private CosplayItemsExpandableListAdapter adapter;
  private Cosplay cosplay;
  private CosnetDb db;
  private CosplayItemDAO cosplayItemDAO;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cosplay_items_list);

    addDatabase();
    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");
    itemsList = cosplayItemDAO.getCosplayWithItems(cosplay.cosplayId).cosplayItems;

    addToolbar();
    initialiseWidgets();
    createList();
    setListeners();

  }

  private void createList() {
    boughtCosplays = new ArrayList<>();
    madeCosplays = new ArrayList<>();

    for(CosplayItem cosplayItem : itemsList){
      if (cosplayItem.isMade == 0){
        boughtCosplays.add(cosplayItem);
      } else {
        madeCosplays.add(cosplayItem);
      }
    }

    List<String> itemTypes = new ArrayList<>();
    itemTypes.add("Made Items");
    itemTypes.add("Bought Items");

    HashMap<String, List<CosplayItem>> items = new HashMap<>();
    items.put(itemTypes.get(0),madeCosplays);
    items.put(itemTypes.get(1),boughtCosplays);

    adapter = new CosplayItemsExpandableListAdapter(this,itemTypes,items);
    cosplayItemsListView.setAdapter(adapter);
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
    cosplayItemDAO = db.getCosplayItemDAO();
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


  private void setListeners() {
    createCosplayItemBTN.setOnClickListener(v -> {
      Intent intent = new Intent(this, AddCosplayItem.class);
      intent.putExtra("cosplay", cosplay);
      startActivity(intent);
    });


    cosplayItemsListView.setOnChildClickListener((ExpandableListView parent, View v, int groupPosition, int childPosition, long id) -> {
      CosplayItem item = (CosplayItem) adapter.getChild(groupPosition,childPosition);
      Intent intent = new Intent(this, ShowCosplayItem.class);
      intent.putExtra("cosplayItem", item);
      startActivity(intent);
      return true;
    });

  }


  private void initialiseWidgets() {
    createCosplayItemBTN = findViewById(R.id.createCosplayItemBTN);
    cosplayItemsListView = findViewById(R.id.CosplayItemsExpListView);

    if (cosplay != null) {
      //get fields from view
      character = findViewById(R.id.CosplayItemListCharacterName);
      series = findViewById(R.id.CosplayItemListSeries);
      status = findViewById(R.id.CosplayItemListCosplayStatus);
      dueDate = findViewById(R.id.CosplayItemListCosplayDueDate);

      //Set fields according to value
      character.setText(cosplay.cosplayName);
      series.setText(cosplay.cosplaySeries);
      status.setText(cosplay.status);
      if (cosplay.dueDate == null || cosplay.dueDate.isEmpty()) {
        findViewById(R.id.CosplayItemListCosplayDueDateLbl).setVisibility(View.INVISIBLE);
        dueDate.setVisibility(View.INVISIBLE);
      } else {
        dueDate.setText(cosplay.dueDate);
      }
    }
  }
}
