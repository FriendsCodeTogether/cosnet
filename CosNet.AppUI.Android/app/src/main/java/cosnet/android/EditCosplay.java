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

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cosnet.android.Entities.Cosplay;
import me.abhinay.input.CurrencyEditText;

public class EditCosplay extends AppCompatActivity {

  private static final String TAG = "EditCosplay";

  private CosnetDb db;

  private TextInputLayout characterLayout, seriesLayout, startDateLayout, dueDateLayout;
  private Cosplay cosplay;
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
    if (!validateCharacterName() | !validateSeries() | !validateDate()) {
      return;
    }
    if (characterLayout.getEditText().getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(EditCosplay.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Character name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> {
      });
      alertDialog.show();
      return;
    }

    cosplay.cosplayName = characterLayout.getEditText().getText().toString();
    cosplay.cosplaySeries = seriesLayout.getEditText().getText().toString();
    cosplay.startDate = startDateLayout.getEditText().getText().toString();
    cosplay.dueDate = dueDateLayout.getEditText().getText().toString();
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
    characterLayout = findViewById(R.id.characterNametextInput);
    seriesLayout = findViewById(R.id.seriestextInput);
    startDateLayout = findViewById(R.id.startDatetextInput);
    dueDateLayout = findViewById(R.id.dueDatetextInput);
    budgetEditText = findViewById(R.id.budgetEditText);
    statusSpinner = findViewById(R.id.statusSpinner);
    saveButton = findViewById(R.id.SaveBtn);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    characterLayout.getEditText().setText(cosplay.cosplayName);
    seriesLayout.getEditText().setText(cosplay.cosplaySeries);
    startDateLayout.getEditText().setText(cosplay.startDate);
    dueDateLayout.getEditText().setText(cosplay.dueDate);
    budgetEditText.setCurrency("€");
    budgetEditText.setSpacing(true);
    budgetEditText.setText(cosplay.budget != null ? cosplay.budget.toString() : null);

    startDateLayout.getEditText().setOnClickListener(v -> onClickStartDate());
    dueDateLayout.getEditText().setOnClickListener(v -> onClickdueDate());
    saveButton.setOnClickListener(v -> onClickSaveButton());
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private boolean validateCharacterName() {
    String characterName = characterLayout.getEditText().getText().toString();

    if (characterName.isEmpty()) {
      characterLayout.setError(getApplicationContext().getString(R.string.characterNameErrorEmpty));

      return false;
    } else if (characterName.length() > 150) {
      characterLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else {
      characterLayout.setError(null);
      return true;
    }
  }

  private boolean validateSeries() {
    String series = seriesLayout.getEditText().getText().toString();

    if (series.length() > 150) {
      seriesLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else {
      seriesLayout.setError(null);
      return true;
    }
  }

  private boolean validateDate() {
    String startdate = startDateLayout.getEditText().getText().toString();
    String duedate = dueDateLayout.getEditText().getText().toString();

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
      Date date1 = sdf.parse(startdate);
      Date date2 = sdf.parse(duedate);
      if (date1.after(date2)) {
        dueDateLayout.setError(getApplicationContext().getString(R.string.dueBeforeStart));
        startDateLayout.setError(getApplicationContext().getString(R.string.startAfterDue));
        return false;
      }
      return true;
    } catch (Exception e) {
      dueDateLayout.setError(null);
      startDateLayout.setError(null);
      return true;
    }
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(EditCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      dueDateLayout.getEditText().setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(EditCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      startDateLayout.getEditText().setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

}
