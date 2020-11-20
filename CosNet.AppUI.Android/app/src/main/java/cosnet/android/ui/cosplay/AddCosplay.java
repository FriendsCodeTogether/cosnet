package cosnet.android.ui.cosplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.MainActivity;
import cosnet.android.R;
import me.abhinay.input.CurrencyEditText;

public class AddCosplay extends AppCompatActivity {

  private static final String TAG = "AddCosplay";

  private CosnetDb db;

  private TextInputLayout characterLayout, seriesLayout, startDateLayout, dueDateLayout;
  private CurrencyEditText budgetEditText;
  private Spinner statusSpinner;
  private Button addCosplayButton;
  private List<String> statusses;
  private int year;
  private int month;
  private int day;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_main);

    addToolbar();

    addItems();

    addStatuses();

    db = CosnetDb.getInstance(this);

  }

  private void addItems() {
    characterLayout = findViewById(R.id.characterNametextInput);
    seriesLayout = findViewById(R.id.seriestextInput);
    startDateLayout = findViewById(R.id.startDatetextInput);
    dueDateLayout = findViewById(R.id.dueDatetextInput);
    budgetEditText = findViewById(R.id.budgetEditText);

    statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
    addCosplayButton = (Button) findViewById(R.id.createCosBTN);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    budgetEditText.setCurrency("€");
    budgetEditText.setSpacing(true);

    startDateLayout.getEditText().setOnClickListener(v -> onClickStartDate());
    dueDateLayout.getEditText().setOnClickListener(v -> onClickdueDate());
    addCosplayButton.setOnClickListener(v -> onClickAddButton());
  }

  private void addStatuses() {
    statusses = new ArrayList<>();
    statusses.add(getApplicationContext().getString(R.string.In_Progess));
    statusses.add(getApplicationContext().getString(R.string.Planned));

    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    statusSpinner.setAdapter(adapterSpinnerStatus);
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

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void onClickAddButton() {
    if (!validateCharacterName() | !validateSeries() | !validateDate()) {
      return;
    }

    Cosplay newCosplay = new Cosplay();
    newCosplay.cosplayName = characterLayout.getEditText().getText().toString();
    newCosplay.cosplaySeries = seriesLayout.getEditText().getText().toString();
    newCosplay.startDate = startDateLayout.getEditText().getText().toString();
    newCosplay.dueDate = dueDateLayout.getEditText().getText().toString();
    newCosplay.budget = budgetEditText.getText().toString().isEmpty() ? null : budgetEditText.getCleanDoubleValue();
    newCosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().insertCosplay(newCosplay);
    setResult(RESULT_OK);
    finish();
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      dueDateLayout.getEditText().setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      startDateLayout.getEditText().setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }
}
