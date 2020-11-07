package cosnet.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;
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
  private ToggleSwitch CosplayItemTypeSwitch;

  private ConstraintLayout madeLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_item_main);
    CosplayItemTypeSwitch = (ToggleSwitch) findViewById(R.id.CosplayItemTypeSwitch);
    CosplayItemTypeSwitch.setCheckedPosition(0);

    madeLayout = (ConstraintLayout) findViewById(R.id.CosplayItemMadeItemLayout);
    madeLayout.setVisibility(View.GONE);
  }
}
