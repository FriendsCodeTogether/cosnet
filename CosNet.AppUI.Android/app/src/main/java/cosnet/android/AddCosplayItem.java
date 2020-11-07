package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.util.Calendar;
import java.util.List;

import me.abhinay.input.CurrencyEditText;

public class AddCosplayItem extends AppCompatActivity {

  private static final String TAG = "AddCosplayItem";

  private CurrencyEditText itemPriceEditText;
  private EditText CosplayItemDueDateEditText;

  private int year;
  private int month;
  private int day;

  private ToggleSwitch cosplayItemTypeSwitch;
  private ConstraintLayout madeItemLayout;
  private ConstraintLayout boughtItemLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_item_main);

    getItemsBoundToViewId();
    initializeItems();

    CosplayItemDueDateEditText.setOnClickListener(v -> onClickItemdueDate());
    cosplayItemTypeSwitch.setOnChangeListener(i -> OnItemTypeSwitchChange(i));
  }

  private void onClickItemdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplayItem.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      CosplayItemDueDateEditText.setText(date);
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
    itemPriceEditText.setCurrency("€");
    itemPriceEditText.setSpacing(true);
  }

  private void OnItemTypeSwitchChange(int i) {
    boughtItemLayout.setVisibility(View.GONE);
    madeItemLayout.setVisibility(View.GONE);
    if(i == 0) boughtItemLayout.setVisibility(View.VISIBLE);
    else madeItemLayout.setVisibility(View.VISIBLE);
  }

  private void getItemsBoundToViewId(){
    cosplayItemTypeSwitch = (ToggleSwitch) findViewById(R.id.CosplayItemTypeSwitch);
    boughtItemLayout = (ConstraintLayout) findViewById(R.id.CosplayItemBoughtItemLayout);
    madeItemLayout = (ConstraintLayout) findViewById(R.id.CosplayItemMadeItemLayout);
    CosplayItemDueDateEditText = (EditText) findViewById(R.id.CosplayItemDueDateEditText);
    itemPriceEditText = (CurrencyEditText) findViewById(R.id.CosplayItemPriceEditText);
  }
}
