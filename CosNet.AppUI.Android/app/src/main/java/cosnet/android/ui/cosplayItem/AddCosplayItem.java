package cosnet.android.ui.cosplayItem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;
import com.shawnlin.numberpicker.*;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.R;
import cosnet.android.ui.cosplay.ShowCosplay;
import me.abhinay.input.CurrencyEditText;

import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cosnet.android.Entities.CosplayItem;

public class AddCosplayItem extends AppCompatActivity {

  private static final String TAG = "Add Item";

  private CosnetDb db;
  private CosplayItem cosplayItem;
  private ConstraintLayout madeItemLayout;
  private ConstraintLayout boughtItemLayout;
  private TextInputLayout cosplayItemNameLayout;
  private TextInputLayout cosplayItemDescriptionLayout;
  private EditText cosplayItemDueDateEditText;
  private TextInputLayout cosplayItemBuyLinkLayout;
  private EditText cosplayItemProgressEditText;
  private EditText cosplayItemWorkTimeEditText;
  private CurrencyEditText cosplayItemPriceEditText;
  private ToggleSwitch cosplayItemTypeSwitch;
  private Spinner boughtStatusSpinner;
  private Spinner madeStatusSpinner;
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
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");
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
    cosplayItemDueDateEditText.setOnClickListener(v -> onClickItemdueDate());
    cosplayItemTypeSwitch.setOnChangeListener(i -> OnItemTypeSwitchChange(i));
    addMadeItemButton.setOnClickListener(i -> onClickAddButton());
    addBoughtItemButton.setOnClickListener(i -> onClickAddButton());
    cosplayItemWorkTimeEditText.setOnClickListener(v -> onClickWorkTimeButton());
    cosplayItemProgressEditText.setOnClickListener(v -> onClickProgressButton());
  }

  private void onClickItemdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplayItem.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      cosplayItemDueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
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
      cosplayItemWorkTimeMinutes = Integer.parseInt(data[pickedTime - 1]);
      cosplayItemWorkTimeEditText.setText("H " + cosplayItemWorkTimeHours + " : " + data[pickedTime - 1]);
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
    if (!validateItemName() | !validateItemDescrition() | !validateItemBuyLink()) {
      return;
    }

    CosplayItem newItem = new CosplayItem();
    newItem.cosplayId = cosplay.cosplayId;
    newItem.itemName = cosplayItemNameLayout.getEditText().getText().toString();
    newItem.description = cosplayItemDescriptionLayout.getEditText().getText().toString();
    newItem.dueDate = cosplayItemDueDateEditText.getText().toString();
    newItem.price = cosplayItemPriceEditText.getText().toString().isEmpty() ? null : cosplayItemPriceEditText.getCleanDoubleValue();
    newItem.isMade = isMade;
    if (isMade == 0) newItem.status = boughtStatusSpinner.getSelectedItem().toString();
    else newItem.status = madeStatusSpinner.getSelectedItem().toString();
    newItem.buylink = cosplayItemBuyLinkLayout.getEditText().getText().toString();
    newItem.progress = cosplayItemProgressNumber;
    newItem.worktimeHours = cosplayItemWorkTimeHours;
    newItem.worktimeMinutes = cosplayItemWorkTimeMinutes;
    db.getCosplayItemDAO().insertItem(newItem);

    Intent intent = new Intent();
    intent.putExtra("addedItemName",newItem.itemName);
    setResult(RESULT_OK,intent);
    finish();
  }

  private boolean validateItemBuyLink() {
    String buyLink = cosplayItemBuyLinkLayout.getEditText().getText().toString();
    if (buyLink.length() > 250) {
      cosplayItemBuyLinkLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else {
      cosplayItemBuyLinkLayout.setError(null);
      return true;
    }
  }

  private boolean validateItemDescrition() {
    String description = cosplayItemDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      cosplayItemDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      cosplayItemDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateItemName() {
    String itemName = cosplayItemNameLayout.getEditText().getText().toString();
    if (itemName.length() > 150) {
      cosplayItemNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(itemName.isEmpty()){
      cosplayItemNameLayout.setError(getApplicationContext().getString(R.string.characterNameErrorEmpty));
      return false;
    }
    else {
      cosplayItemNameLayout.setError(null);
      return true;
    }
  }

  private void initializeItems() {
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

  private void OnItemTypeSwitchChange(int i) {
    boughtItemLayout.setVisibility(View.GONE);
    madeItemLayout.setVisibility(View.GONE);
    if (i == 0) {
      boughtItemLayout.setVisibility(View.VISIBLE);
      isMade = 0;
    } else {
      madeItemLayout.setVisibility(View.VISIBLE);
      isMade = 1;
    }
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void getItemsBound() {
    boughtItemLayout = findViewById(R.id.cosplayItemBoughtItemLayout);
    madeItemLayout = findViewById(R.id.cosplayItemMadeItemLayout);

    cosplayItemNameLayout = findViewById(R.id.cosplayItemNameTextInput);
    cosplayItemDescriptionLayout = findViewById(R.id.cosplayItemDescriptionTextInput);
    cosplayItemPriceEditText = findViewById(R.id.cosplayItemPriceEditText);
    cosplayItemDueDateEditText = findViewById(R.id.dueDateEditText);
    cosplayItemTypeSwitch = findViewById(R.id.cosplayItemTypeSwitch);

    boughtStatusSpinner = findViewById(R.id.boughtStatusSpinner);
    cosplayItemBuyLinkLayout = findViewById(R.id.cosplayItemBuyLinkTextInput);

    madeStatusSpinner = findViewById(R.id.madeStatusSpinner);
    cosplayItemProgressEditText = findViewById(R.id.cosplayItemProgressEditText);
    cosplayItemWorkTimeEditText= findViewById(R.id.cosplayItemWorkTimeEditText);

    addBoughtItemButton = findViewById(R.id.cosplayMadeItemAddButton);
    addMadeItemButton = findViewById(R.id.cosplayBoughtItemAddButton);
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

}
