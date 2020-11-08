package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import me.abhinay.input.CurrencyEditText;

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

  private int year;
  private int month;
  private int day;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_item_main);

    addToolbar();
    getItemsBound();
    initializeItems();

    cosplayItemDueDateEditText.setOnClickListener(v -> onClickItemdueDate());
    cosplayItemTypeSwitch.setOnChangeListener(i -> OnItemTypeSwitchChange(i));
    addMadeItemButton.setOnClickListener(i->onClickAddButton());
    addBoughtItemButton.setOnClickListener(i->onClickAddButton());
    cosplayItemWorkTimeEditText.setOnClickListener(v->onClickWorkTimeButton());
  }

  private void onClickWorkTimeButton() {
    openTimeDialog();
  }

  private void onClickAddButton() {

    if (cosplayItemNameEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(AddCosplayItem.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> { });
      alertDialog.show();
    }

/*
    Cosplay newCosplay = new Cosplay();
    newCosplay.cosplayName = characterEditText.getText().toString();
    newCosplay.cosplaySeries = seriesEditText.getText().toString();
    newCosplay.startDate = startDateEditText.getText().toString();
    newCosplay.dueDate = dueDateEditText.getText().toString();
    newCosplay.budget = budgetEditText.getText().toString().isEmpty() ? null : budgetEditText.getCleanDoubleValue();
    newCosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().insertCosplay(newCosplay);
    Intent intent = new Intent(AddCosplay.this, MainActivity.class);
    startActivity(intent);*/
  }

  private void onClickItemdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplayItem.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      cosplayItemDueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
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
    if (i == 0) boughtItemLayout.setVisibility(View.VISIBLE);
    else madeItemLayout.setVisibility(View.VISIBLE);
  }

  private void getItemsBound() {
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

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void openTimeDialog() {
    final LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.view_number_dialog, null);
    NumberPicker numberPicker1 = (NumberPicker) linearLayout.findViewById(R.id.numberPicker1);
    NumberPicker numberPicker2 = (NumberPicker) linearLayout.findViewById(R.id.numberPicker2);
    numberPicker1.setMinValue(0);
    numberPicker1.setMaxValue(100);
    numberPicker1.setValue(0);
    numberPicker2.setMinValue(0);
    numberPicker2.setMaxValue(59);
    numberPicker2.setValue(0);
    final AlertDialog builder = new AlertDialog.Builder(this)
      .setPositiveButton("Submit", null)
      .setNegativeButton("Cancel", null)
      .setView(linearLayout)
      .setCancelable(false)
      .create();
    builder.show();
    builder.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener((View.OnClickListener) view -> {
      NumberPicker numberPicker11 = (NumberPicker) findViewById(R.id.numberPicker1);
      NumberPicker numberPicker21 = (NumberPicker) findViewById(R.id.numberPicker2);
      int value1 = numberPicker11.getValue();
      int value2 = numberPicker21.getValue();
      String value2String = "";
      if (value2 <= 9){
          value2String = "0" + value2;
      } else {
          value2String = String.valueOf(value2);
      }
      CosplayItemWorkTimeTextView.setText(value1 + ":" + value2String);
    });
  }
}
