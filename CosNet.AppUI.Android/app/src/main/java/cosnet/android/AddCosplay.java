package cosnet.android;

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

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cosnet.android.Entities.Cosplay;
import cosnet.android.adapters.NumberTextWatcher;
import me.abhinay.input.CurrencyEditText;

public class AddCosplay extends AppCompatActivity {

  private static final String TAG = "AddCosplay";

  private CosnetDb db;

  private TextInputLayout characterLayout, seriesLayout, startDateLayout, dueDateLayout, budgetLayout;

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

    characterLayout=findViewById(R.id.characterNametextInput);
    seriesLayout=findViewById(R.id.seriestextInput);
    startDateLayout=findViewById(R.id.startDatetextInput);
    dueDateLayout=findViewById(R.id.dueDatetextInput);
    budgetLayout=findViewById(R.id.budgettextInput);

    statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
    addCosplayButton = (Button) findViewById(R.id.createCosBTN);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);


    statusses = new ArrayList<>();
    statusses.add(getApplicationContext().getString(R.string.In_Progess));
    statusses.add(getApplicationContext().getString(R.string.Planned));

    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
    adapterSpinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    statusSpinner.setAdapter(adapterSpinnerStatus);

    budgetLayout.getEditText().addTextChangedListener(new NumberTextWatcher(budgetLayout.getEditText(),"#,###"));
    startDateLayout.getEditText().setOnClickListener(v -> onClickStartDate());
    dueDateLayout.getEditText().setOnClickListener(v -> onClickdueDate());
    addCosplayButton.setOnClickListener(v -> onClickAddButton());
  }

  private boolean validateCharacterName(){
    String characterName = characterLayout.getEditText().getText().toString();

    if (characterName.isEmpty()){
      characterLayout.setError(getApplicationContext().getString(R.string.characterNameErrorEmpty));
      return false;
    } else if(characterName.length()>150) {
      characterLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    }else {
      characterLayout.setError(null);
      return true;
    }
  }

  private boolean validateSeries(){
    String series = seriesLayout.getEditText().getText().toString();

    if(series.length()>150) {
      seriesLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    }else {
      seriesLayout.setError(null);
      return true;
    }
  }

  private boolean validateBudget(){
    String budget = characterLayout.getEditText().getText().toString();

    if(budget.length()>12) {
      characterLayout.setError(getApplicationContext().getString(R.string.max9Numbers));
      return false;
    }else {
      characterLayout.setError(null);
      return true;
    }
  }

  private boolean validateDate(){
    String startdate = startDateLayout.getEditText().getText().toString();
    String duedate= dueDateLayout.getEditText().getText().toString();

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
      Date date1 = sdf.parse(startdate);
      Date date2 = sdf.parse(duedate);
      if(date1.after(date2)){
        dueDateLayout.setError(getApplicationContext().getString(R.string.dueBeforeStart));
        startDateLayout.setError(getApplicationContext().getString(R.string.startAfterDue));
        return false;
      } return true;
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
    if(!validateBudget() | !validateCharacterName() | !validateSeries() |!validateDate()){
      return;
    }

    Cosplay newCosplay = new Cosplay();
    newCosplay.cosplayName = characterLayout.getEditText().getText().toString();
    newCosplay.cosplaySeries = seriesLayout.getEditText().getText().toString();
    newCosplay.startDate = startDateLayout.getEditText().getText().toString();
    newCosplay.dueDate = dueDateLayout.getEditText().getText().toString();
    newCosplay.budget = Double.parseDouble(budgetLayout.getEditText().toString());
    newCosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().insertCosplay(newCosplay);
    Intent intent = new Intent(AddCosplay.this, MainActivity.class);
    startActivity(intent);
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
