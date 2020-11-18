package cosnet.android.ui.cosplayItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Data.DAOs.CosplayItemDAO;
import cosnet.android.Data.Relations.CosplayWithItems;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;
import cosnet.android.adapters.CosplayItemsExpandableListAdapter;
import cosnet.android.adapters.CosplayItemsListAdapter;
import cosnet.android.adapters.CosplayListAdapter;

public class CosplayItemsList extends AppCompatActivity {

  private static final String TAG = "CosplayItemList";

  private ImageButton createCosplayItemBTN;
/*  private ImageButton madeItemsListCollapseBtn;
  private ImageButton boughtItemsListCollapseBtn;*/
  private TextView character;
  private TextView series;
  private TextView status;
  private TextView dueDate;
  private List<CosplayItem> itemsList;
  private ExpandableListView cosplayItemsListView;
/*  private ListView cosplayMadeItemList;
  private ListView cosplayBoughtItemList;*/
  private List<CosplayItem> madeCosplays;
  private List<CosplayItem> boughtCosplays;
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
    setListeners();


    for (CosplayItem item : itemsList) {
      Log.d(TAG, "onCreate: " + item.itemName);
    }

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

    /*cosplayBoughtItemList.setOnItemClickListener((parent, view, position, id) -> {

    });

    cosplayMadeItemList.setOnItemClickListener((parent, view, position, id) -> {

    });

    madeItemsListCollapseBtn.setOnClickListener(v -> toggleListView(cosplayMadeItemList));

    boughtItemsListCollapseBtn.setOnClickListener(v -> toggleListView(cosplayBoughtItemList) );*/
  }

  /*private void toggleListView(ListView listView){
    if (listView.getVisibility() == View.VISIBLE){
      listView.setVisibility(View.GONE);
    } else {
      listView.setVisibility(View.VISIBLE);
    }
  }*/

  private void initialiseWidgets() {
    createCosplayItemBTN = findViewById(R.id.createCosplayItemBTN);
    cosplayItemsListView = findViewById(R.id.CosplayItemsExpListView);
    /*madeItemsListCollapseBtn = (ImageButton) findViewById(R.id.MadeItemsListCollapseBtn);
    boughtItemsListCollapseBtn = (ImageButton) findViewById(R.id.BoughtItemsListCollapseBtn);
    cosplayMadeItemList = (ListView) findViewById(R.id.CosplayMadeItemsListView);
    cosplayBoughtItemList = (ListView) findViewById(R.id.CosplayBoughtItemsListView);*/
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


    CosplayItemsExpandableListAdapter adapter = new CosplayItemsExpandableListAdapter(this,itemTypes,items);
    cosplayItemsListView.setAdapter(adapter);
    /*CosplayItemsListAdapter boughtListAdapter = new CosplayItemsListAdapter(this, R.layout.cosplay_item_list_item, boughtCosplays);
    cosplayBoughtItemList.setAdapter(boughtListAdapter);
    CosplayItemsListAdapter madeListAapter = new CosplayItemsListAdapter(this, R.layout.cosplay_item_list_item, madeCosplays);
    cosplayMadeItemList.setAdapter(madeListAapter);*/

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
