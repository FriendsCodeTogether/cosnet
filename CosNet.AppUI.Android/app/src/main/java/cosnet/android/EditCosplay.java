package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosnet.android.Entities.Cosplay;
import me.abhinay.input.CurrencyEditText;

public class EditCosplay extends AppCompatActivity {

  private static final String TAG = "EditCosplay";

  private CosnetDb db;

  private Cosplay cosplay;
  private EditText characterEditText;
  private EditText seriesEditText;
  private EditText startDateEditText;
  private EditText dueDateEditText;
  private CurrencyEditText budgetEditText;
  private Spinner statusSpinner;
  private Button saveButton;
  private List<String> statusses;
  private int year;
  private int month;
  private int day;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_cosplay);

    addToolbar();

    addDatabase();

    addItems();

    addStatuses();
  }
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
  private void onClickSaveButton() {

    if (characterEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(EditCosplay.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Character name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> {
      });
      alertDialog.show();
      return;
    }

    cosplay.cosplayName = characterEditText.getText().toString();
    cosplay.cosplaySeries = seriesEditText.getText().toString();
    cosplay.startDate = startDateEditText.getText().toString();
    cosplay.dueDate = dueDateEditText.getText().toString();
    cosplay.budget = budgetEditText.getText().toString().isEmpty() ? null : budgetEditText.getCleanDoubleValue();
    cosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().updateCosplay(cosplay);
    Intent intent = new Intent(this, ShowCosplay.class);
    intent.putExtra("cosplay", (Serializable) cosplay);
    startActivity(intent);
  }

  private void addDatabase() {
    //get cosplay from intent
    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");

    db = CosnetDb.getInstance(this);
  }

  private void addStatuses() {
    statusses = new ArrayList<>();
    statusses.add(getApplicationContext().getString(R.string.In_Progess));
    statusses.add(getApplicationContext().getString(R.string.Planned));
    statusses.add(getApplicationContext().getString(R.string.Done));
    statusses.add(getApplicationContext().getString(R.string.Cancelled));
    statusses.add(getApplicationContext().getString(R.string.OnHold));

    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    statusSpinner.setAdapter(adapterSpinnerStatus);
    statusSpinner.setSelection(statusses.indexOf(cosplay.status));
  }

  private void addItems() {
    characterEditText = findViewById(R.id.characterEditText);
    seriesEditText = findViewById(R.id.seriesEditText);
    startDateEditText = findViewById(R.id.startDateEditText);
    dueDateEditText = findViewById(R.id.dueDateEditText);
    budgetEditText = findViewById(R.id.budgetEditText);
    statusSpinner = findViewById(R.id.statusSpinner);
    saveButton = findViewById(R.id.SaveBtn);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    characterEditText.setText(cosplay.cosplayName);
    seriesEditText.setText(cosplay.cosplaySeries);
    startDateEditText.setText(cosplay.startDate);
    dueDateEditText.setText(cosplay.dueDate);
    budgetEditText.setCurrency("€");
    budgetEditText.setSpacing(true);
    budgetEditText.setText(cosplay.budget != null ? cosplay.budget.toString() : null);

    startDateEditText.setOnClickListener(v -> onClickStartDate());
    dueDateEditText.setOnClickListener(v -> onClickdueDate());
    saveButton.setOnClickListener(v -> onClickSaveButton());
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(EditCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      dueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(EditCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      startDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

}
