package cosnet.android.ui.cosplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.MainActivity;
import cosnet.android.R;
import me.abhinay.input.CurrencyEditText;

public class AddCosplay extends AppCompatActivity {

  private static final String TAG = "AddCosplay";

  private CosnetDb db;

  private EditText characterEditText;
  private EditText seriesEditText;
  private EditText startDateEditText;
  private EditText dueDateEditText;
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
    db = CosnetDb.getInstance(this);

    characterEditText = (EditText) findViewById(R.id.characterEditText);
    seriesEditText = (EditText) findViewById(R.id.seriesEditText);
    startDateEditText = (EditText) findViewById(R.id.startDateEditText);
    dueDateEditText = (EditText) findViewById(R.id.dueDateEditText);
    budgetEditText = (CurrencyEditText) findViewById(R.id.budgetEditText);
    statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
    addCosplayButton = (Button) findViewById(R.id.addCosBTN);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    budgetEditText.setCurrency("€");
    budgetEditText.setSpacing(true);

    statusses = new ArrayList<>();
    statusses.add(getApplicationContext().getString(R.string.In_Progess));
    statusses.add(getApplicationContext().getString(R.string.Planned));

    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    statusSpinner.setAdapter(adapterSpinnerStatus);

    startDateEditText.setOnClickListener(v -> onClickStartDate());
    dueDateEditText.setOnClickListener(v -> onClickdueDate());
    addCosplayButton.setOnClickListener(v -> onClickAddButton());
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void onClickAddButton() {

    if (characterEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(AddCosplay.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Character name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> { });
      alertDialog.show();
      return;
    }

    Cosplay newCosplay = new Cosplay();
    newCosplay.cosplayName = characterEditText.getText().toString();
    newCosplay.cosplaySeries = seriesEditText.getText().toString();
    newCosplay.startDate = startDateEditText.getText().toString();
    newCosplay.dueDate = dueDateEditText.getText().toString();
    newCosplay.budget = budgetEditText.getText().toString().isEmpty() ? null : budgetEditText.getCleanDoubleValue();
    newCosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().insertCosplay(newCosplay);
    Intent intent = new Intent(AddCosplay.this, MainActivity.class);
    startActivity(intent);
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      dueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      startDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }
}
