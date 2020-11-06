package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

import me.abhinay.input.CurrencyEditText;

public class AddCosplayItem extends AppCompatActivity {

  private static final String TAG = "AddCosplayItem";

  private CosnetDb db;

  private EditText itemNameEditText;
  private EditText itemDescriptionEditText;
  private CurrencyEditText itemPriceEditText;
  private EditText dueDateEditText;
  private Spinner itemStatusSpinner;
  private ImageButton cancelButton;
  private ImageButton addCosplayButton;
  private List<String> statusses;
  private int year;
  private int month;
  private int day;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_cosplay_item);
  }
}
