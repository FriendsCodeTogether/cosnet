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
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;
import me.abhinay.input.CurrencyEditText;

public class EditCosplayItem extends AppCompatActivity {

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
  private TextInputLayout EditcosplayItemBuyLinkLayout;
  private TextInputLayout EditcosplayItemDescriptionLayout;
  private TextInputLayout EditcosplayItemNameLayout;

  private int year;
  private int month;
  private int day;
  private int cosplayItemWorkTimeHours;
  private int cosplayItemWorkTimeMinutes;
  private int cosplayItemProgressNumber;
  private int isMade;
  private CosplayItem item;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.edit_cosplay_item_main);

    addToolbar();
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();
    fillInFields();
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
    addMadeItemButton.setOnClickListener(i -> onClickSaveButton());
    addBoughtItemButton.setOnClickListener(i -> onClickSaveButton());
    cosplayItemWorkTimeEditText.setOnClickListener(v -> onClickWorkTimeButton());
    cosplayItemProgressEditText.setOnClickListener(v -> onClickProgressButton());
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

  private void onClickSaveButton() {

    if (!validateItemName() | !validateItemDescrition() | !validateItemBuyLink()) {
      return;
    }

    item.itemName = cosplayItemNameEditText.getText().toString();
    item.description = cosplayItemDescriptionEditText.getText().toString();
    item.dueDate = cosplayItemDueDateEditText.getText().toString();
    item.price = cosplayItemPriceEditText.getText().toString().isEmpty() ? null : cosplayItemPriceEditText.getCleanDoubleValue();
    item.isMade = isMade;
    if (isMade == 0) item.status = boughtStatusSpinner.getSelectedItem().toString();
    else item.status = madeStatusSpinner.getSelectedItem().toString();
    item.buylink = cosplayItemBuyLinkEditText.getText().toString();
    item.progress = cosplayItemProgressNumber;
    item.worktimeHours = cosplayItemWorkTimeHours;
    item.worktimeMinutes = cosplayItemWorkTimeMinutes;
    db.getCosplayItemDAO().updateItem(item);

    Intent intentResult = new Intent();
    intentResult.putExtra("editedCosplayItem", item);
    setResult(RESULT_OK, intentResult);
    finish();
  }
  private boolean validateItemBuyLink() {
    String buyLink = EditcosplayItemBuyLinkLayout.getEditText().getText().toString();
    if (buyLink.length() > 250) {
      EditcosplayItemBuyLinkLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else {
      EditcosplayItemBuyLinkLayout.setError(null);
      return true;
    }
  }
  private boolean validateItemDescrition() {
    String description = EditcosplayItemDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      EditcosplayItemDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      EditcosplayItemDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateItemName() {
    String itemName = EditcosplayItemNameLayout.getEditText().getText().toString();
    if (itemName.length() > 150) {
      EditcosplayItemNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(itemName.isEmpty()){
      EditcosplayItemNameLayout.setError(getApplicationContext().getString(R.string.requiredFieldErrorEmpty));
      return false;
    }
    else {
      EditcosplayItemNameLayout.setError(null);
      return true;
    }
  }
  private void onClickItemdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(EditCosplayItem.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      cosplayItemDueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void initializeItems() {
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
    Intent incomingIntent = getIntent();
    item = (CosplayItem) incomingIntent.getSerializableExtra("cosplayItem");
    db = CosnetDb.getInstance(this);
  }

  private void getItemsBound() {
    boughtItemLayout = (ConstraintLayout) findViewById(R.id.EditcosplayItemBoughtItemLayout);
    madeItemLayout = (ConstraintLayout) findViewById(R.id.EditcosplayItemMadeItemLayout);

    cosplayItemNameEditText = (EditText) findViewById(R.id.EditcosplayItemNameEditText);
    cosplayItemDescriptionEditText = (EditText) findViewById(R.id.EditcosplayItemDescriptionEditText);
    cosplayItemPriceEditText = findViewById(R.id.EditcosplayItemPriceEditText);
    cosplayItemDueDateEditText = (EditText) findViewById(R.id.EditdueDateEditText);
    cosplayItemTypeSwitch = (ToggleSwitch) findViewById(R.id.EditcosplayItemTypeSwitch);

    boughtStatusSpinner = (Spinner) findViewById(R.id.EditboughtStatusSpinner);
    cosplayItemBuyLinkEditText = (EditText) findViewById(R.id.EditcosplayItemBuyLinkEditText);

    madeStatusSpinner = (Spinner) findViewById(R.id.EditmadeStatusSpinner);
    cosplayItemProgressEditText = (EditText) findViewById(R.id.EditcosplayItemProgressEditText);
    cosplayItemWorkTimeEditText = (EditText) findViewById(R.id.EditcosplayItemWorkTimeEditText);

    addBoughtItemButton = (Button) findViewById(R.id.EditcosplayMadeItemAddButton);
    addMadeItemButton = (Button) findViewById(R.id.EditcosplayBoughtItemAddButton);

    EditcosplayItemBuyLinkLayout = findViewById(R.id.EditcosplayItemBuyLinkTextInput);
    EditcosplayItemDescriptionLayout = findViewById(R.id.EditcosplayItemDescriptionTextInput);
    EditcosplayItemNameLayout = findViewById(R.id.EditcosplayItemNameTextInput);
  }

  private void fillInFields() {
    cosplayItemNameEditText.setText(item.itemName);
    cosplayItemDescriptionEditText.setText(item.description);
    cosplayItemPriceEditText.setText(item.price != null ? item.price.toString() : null);
    cosplayItemDueDateEditText.setText(item.dueDate);
    cosplayItemTypeSwitch.setCheckedPosition(item.isMade);

    if(item.isMade == 0)
    {
      cosplayItemBuyLinkEditText.setText(item.buylink);
      boughtStatusSpinner.setSelection(boughtStatusses.indexOf(item.status));
      madeItemLayout.setVisibility(View.GONE);
      boughtItemLayout.setVisibility(View.VISIBLE);
    }
    else
    {
      madeStatusSpinner.setSelection(madeStatusses.indexOf(item.status));
      cosplayItemProgressEditText.setText(Integer.toString(item.progress));
      cosplayItemWorkTimeEditText.setText("H " + item.worktimeHours + " : " + item.worktimeMinutes);
      madeItemLayout.setVisibility(View.VISIBLE);
      boughtItemLayout.setVisibility(View.GONE);
    }
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
