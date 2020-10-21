package cosnet.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cosnet.android.Models.Cosplay;
import me.abhinay.input.CurrencyEditText;

public class AddCosplay extends Activity{

   private static final String TAG = "AddCosplay";
   DatePickerDialog.OnDateSetListener setListener;

   private EditText characterEditText;
   private EditText seriesEditText;
   private EditText startDateEditText;
   private EditText dueDateEditText;
   private CurrencyEditText budgetEditText;
   private Spinner statusSpinner;
   private ImageButton cancelButton;
   private Button addCosplayButton;
   private List<String> statusses;
   private int year;
   private int month;
   private int day;

   @Override
   protected void onCreate( Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.add_cosplay_main);

      Log.d(TAG, "onCreate: Starting");

      characterEditText = (EditText)findViewById(R.id.characterEditText) ;
      seriesEditText = (EditText)findViewById(R.id.seriesEditText) ;
      startDateEditText = (EditText)findViewById(R.id.startDateEditText) ;
      dueDateEditText = (EditText)findViewById(R.id.dueDateEditText) ;
      budgetEditText = (CurrencyEditText)findViewById(R.id.budgetEditText) ;
      statusSpinner = (Spinner)findViewById(R.id.statusSpinner) ;
      cancelButton = (ImageButton) findViewById(R.id.cancelNewCosplayBTN);
      addCosplayButton = (Button) findViewById(R.id.addCosplayBTN);
      Calendar calendar = Calendar.getInstance();
      year = calendar.get(Calendar.YEAR);
      month = calendar.get(Calendar.MONTH);
      day = calendar.get(Calendar.DAY_OF_MONTH);

      budgetEditText.setCurrency("€");
      budgetEditText.setSpacing(true);

      statusses = new ArrayList<>();
      statusses.add("In porgress");
      statusses.add("Planned");

      startDateEditText.setText(getCurrentDay());

      ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
      statusSpinner.setAdapter(adapterSpinnerStatus);

      cancelButton.setOnClickListener(v -> onClickCancelButton());
      startDateEditText.setOnClickListener(v -> onClickStartDate());
      dueDateEditText.setOnClickListener(v -> onClickdueDate());
      addCosplayButton.setOnClickListener(v -> onClickAddButton());
   }

  private void onClickAddButton() {
    Log.d(TAG, "onClick: clicked cancelBUttonBTN");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    if(characterEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(AddCosplay.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Character name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> { });
      alertDialog.show();
    } else {
      Cosplay newCosplay = new Cosplay();
      newCosplay.Name = characterEditText.getText().toString();

      if (!seriesEditText.getText().toString().isEmpty())
      {
        newCosplay.Serie = seriesEditText.getText().toString();
      }

      if(!startDateEditText.getText().toString().isEmpty())
      {
        try {
          newCosplay.StartDate = sdf.parse(startDateEditText.getText().toString());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      } else{
        String date = getCurrentDay();
      }

      if(!dueDateEditText.getText().toString().isEmpty()) {
        try {
          newCosplay.DueDate = sdf.parse(dueDateEditText.getText().toString());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }

      if (!budgetEditText.getText().toString().isEmpty())
      {
        newCosplay.Budget = Integer.parseInt(budgetEditText.getText().toString());

      }

      if (!statusSpinner.getSelectedItem().toString().isEmpty())
      {
        newCosplay.Status = statusSpinner.getSelectedItem().toString();
      }
      Intent intent = new Intent(AddCosplay.this, MainActivity.class);
      startActivity(intent);
    }
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        dueDateEditText.setText(date);
      }
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        startDateEditText.setText(date);
      }
    }, year, month, day);
    datePickerDialog.show();
  }

   private void onClickCancelButton(){
     Log.d(TAG, "onClick: clicked cancelButton");
     Intent intent = new Intent(AddCosplay.this, MainActivity.class);
     startActivity(intent);
   }

   private String getCurrentDay()
   {
     Date c = Calendar.getInstance().getTime(); //get current date
     System.out.println("Current time => " + c);
     SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
     return df.format(c);
   }

}
