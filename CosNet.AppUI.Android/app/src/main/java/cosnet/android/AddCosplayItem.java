package cosnet.android;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;
import com.shawnlin.numberpicker.*;

import cosnet.android.Entities.Cosplay;
import me.abhinay.input.CurrencyEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosnet.android.Entities.CosplayItem;

public class AddCosplayItem extends AppCompatActivity {

  private static final String TAG = "Add Item";

  private CosnetDb db;
  private CosplayItem cosplayItem;
  private ConstraintLayout madeItemLayout;
  private ConstraintLayout boughtItemLayout;
  private EditText cosplayItemNameEditText;
  private EditText cosplayItemDescriptionEditText;
  private CurrencyEditText cosplayItemPriceEditText;
  private EditText cosplayItemDueDateEditText;
  private ToggleSwitch cosplayItemTypeSwitch;
  private Spinner boughtStatusSpinner;
  private EditText cosplayItemBuyLinkEditText;
  private Spinner madeStatusSpinner;
  private EditText cosplayItemProgressEditText;
  private EditText cosplayItemWorkTimeEditText;
  private Button addMadeItemButton;
  private Button addBoughtItemButton;
  private List<String> boughtStatusses;
  private List<String> madeStatusses;
  private Cosplay cosplay;
  private int year;
  private int month;
  private int day;
  private int cosplayItemWorkTimeHours;
  private int cosplayItemWorkTimeMinutes;
  private int cosplayItemProgressNumber;
  private int isMade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_item_main);

    addToolbar();
    getItemsBound();
    initializeItems();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

  }

  private void setListeners() {
    cosplayItemDueDateEditText.setOnClickListener(v -> onClickItemdueDate());
    cosplayItemTypeSwitch.setOnChangeListener(i -> OnItemTypeSwitchChange(i));
    addMadeItemButton.setOnClickListener(i->onClickAddButton());
    addBoughtItemButton.setOnClickListener(i->onClickAddButton());
    cosplayItemWorkTimeEditText.setOnClickListener(v->onClickWorkTimeButton());
    cosplayItemProgressEditText.setOnClickListener(v->onClickProgressButton());
  }

  private void onClickWorkTimeButton() {
    final ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.view_time_dialog, null);
    NumberPicker cosplayItemWorkTimeHourNumberPicker = (NumberPicker) constraintLayout.findViewById(R.id.CosplayItemWorkTimeHourNumberPicker);
    NumberPicker cosplayItemWorkTimeMinuteNumberPicker = (NumberPicker) constraintLayout.findViewById(R.id.CosplayItemWorkTimeMinuteNumberPicker);

    cosplayItemWorkTimeHourNumberPicker.setValue(0);
    String[] data = {"00", "15", "30", "45"};
    cosplayItemWorkTimeMinuteNumberPicker.setMinValue(1);
    cosplayItemWorkTimeMinuteNumberPicker.setMaxValue(data.length);
    cosplayItemWorkTimeMinuteNumberPicker.setDisplayedValues(data);

    final AlertDialog builder = new AlertDialog.Builder(this)
      .setTitle("Total Hours Worked")
      .setPositiveButton("Submit", null)
      .setNegativeButton("Cancel", null)
      .setView(constraintLayout)
      .setCancelable(false)
      .create();
    builder.show();

    builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener((View.OnClickListener) view -> {
      cosplayItemWorkTimeHours = cosplayItemWorkTimeHourNumberPicker.getValue();
      int pickedTime = cosplayItemWorkTimeMinuteNumberPicker.getValue();
      cosplayItemWorkTimeMinutes = Integer.parseInt(data[pickedTime-1]);
      cosplayItemWorkTimeEditText.setText("H "+cosplayItemWorkTimeHours + " : " + data[pickedTime-1]);
      builder.dismiss();
    });
  }

  private void onClickProgressButton() {
    final ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.view_single_number_dialog, null);
    NumberPicker cosplayItemProgressNumberPicker = (NumberPicker) constraintLayout.findViewById(R.id.CosplayItemProgressNumberPicker);

    cosplayItemProgressNumberPicker.setValue(0);

    final AlertDialog builder = new AlertDialog.Builder(this)
      .setTitle("Total Progress made")
      .setPositiveButton("Submit", null)
      .setNegativeButton("Cancel", null)
      .setView(constraintLayout)
      .setCancelable(false)
      .create();
    builder.show();

    builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener((View.OnClickListener) view -> {
      cosplayItemProgressNumber = cosplayItemProgressNumberPicker.getValue();
      cosplayItemProgressEditText.setText(cosplayItemProgressNumber + "%");
      builder.dismiss();
    });
  }

  private void onClickAddButton() {

    if (cosplayItemNameEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(AddCosplayItem.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> {
      });
      alertDialog.show();
    }

    CosplayItem newItem = new CosplayItem();
    newItem.cosplayId = cosplay.cosplayId;
    newItem.itemName = cosplayItemNameEditText.getText().toString();
    newItem.description = cosplayItemDescriptionEditText.getText().toString();
    newItem.dueDate = cosplayItemDueDateEditText.getText().toString();
    newItem.price = cosplayItemPriceEditText.getText().toString().isEmpty() ? null : cosplayItemPriceEditText.getCleanDoubleValue();
    newItem.isMade = isMade;
    if (isMade == 0) newItem.status = boughtStatusSpinner.getSelectedItem().toString();
    else newItem.status = madeStatusSpinner.getSelectedItem().toString();
    newItem.buylink = cosplayItemBuyLinkEditText.getText().toString();
    newItem.progress = cosplayItemProgressNumber;
    newItem.worktimeHours = cosplayItemWorkTimeHours;
    newItem.worktimeMinutes = cosplayItemWorkTimeMinutes;
    db.getCosplayItemDAO().insertItem(newItem);
    Intent intent = new Intent(AddCosplayItem.this, ShowCosplay.class);
    startActivity(intent);
  }

  private void onClickItemdueDate(){
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplayItem.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      cosplayItemDueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void initializeItems(){
    //Switch Button
    cosplayItemTypeSwitch.setCheckedPosition(0);
    madeItemLayout.setVisibility(View.GONE);
    boughtItemLayout.setVisibility(View.VISIBLE);
    //Calendar
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);
    //Currency
    cosplayItemPriceEditText.setCurrency("€");
    cosplayItemPriceEditText.setSpacing(true);
    //Create Status Array's
    boughtStatusses = new ArrayList<>();
    boughtStatusses.add(getApplicationContext().getString(R.string.ToBuy));
    boughtStatusses.add(getApplicationContext().getString(R.string.Ordered));
    boughtStatusses.add(getApplicationContext().getString(R.string.Completed));

    madeStatusses = new ArrayList<>();
    madeStatusses.add(getApplicationContext().getString(R.string.InProgress));
    madeStatusses.add(getApplicationContext().getString(R.string.Completed));
    //Add statuss arrays to spinner
    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, boughtStatusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    boughtStatusSpinner.setAdapter(adapterSpinnerStatus);

    adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, madeStatusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    madeStatusSpinner.setAdapter(adapterSpinnerStatus);
  }

  private void OnItemTypeSwitchChange(int i){
    boughtItemLayout.setVisibility(View.GONE);
    madeItemLayout.setVisibility(View.GONE);
    if (i == 0) {
      boughtItemLayout.setVisibility(View.VISIBLE);
      isMade = 0;
    }
    else {
      madeItemLayout.setVisibility(View.VISIBLE);
      isMade = 1;
    }
  }

  private void getItemsBound(){
    boughtItemLayout = (ConstraintLayout) findViewById(R.id.CosplayItemBoughtItemLayout);
    madeItemLayout = (ConstraintLayout) findViewById(R.id.CosplayItemMadeItemLayout);

    db = CosnetDb.getInstance(this);

    cosplayItemNameEditText = (EditText) findViewById(R.id.CosplayItemNameEditText);
    cosplayItemDescriptionEditText = (EditText) findViewById(R.id.CosplayItemDescriptionEditText);
    cosplayItemPriceEditText = (CurrencyEditText) findViewById(R.id.CosplayItemPriceEditText);
    cosplayItemDueDateEditText = (EditText) findViewById(R.id.CosplayItemDueDateEditText);
    cosplayItemTypeSwitch = (ToggleSwitch) findViewById(R.id.CosplayItemTypeSwitch);

    boughtStatusSpinner = (Spinner) findViewById(R.id.boughtStatusSpinner);
    cosplayItemBuyLinkEditText = (EditText) findViewById(R.id.CosplayItemBuyLinkEditText);

    madeStatusSpinner = (Spinner) findViewById(R.id.madeStatusSpinner);
    cosplayItemProgressEditText = (EditText) findViewById(R.id.CosplayItemProgressEditText);
    cosplayItemWorkTimeEditText = (EditText) findViewById(R.id.CosplayItemWorkTimeEditText);

    addBoughtItemButton = (Button) findViewById(R.id.CosplayMadeItemAddButton);
    addMadeItemButton = (Button) findViewById(R.id.CosplayBoughtItemAddButton);
  }

  private void addToolbar(){
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
