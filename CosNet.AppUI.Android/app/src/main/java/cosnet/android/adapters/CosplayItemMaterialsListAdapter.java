package cosnet.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItemMaterial;
import cosnet.android.R;

public class CosplayItemMaterialsListAdapter extends ArrayAdapter<CosplayItemMaterial> {

  private Context context;
  int resource;

  public CosplayItemMaterialsListAdapter(Context c, int resource, ArrayList<CosplayItemMaterial> cosplays) {
    super(c, resource, cosplays);
    this.context = c;
    this.resource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //Get current Cosplay
    CosplayItemMaterial material = getItem(position);

    LayoutInflater inflater = LayoutInflater.from(context);
    convertView = inflater.inflate(resource, parent, false);

    //Get views to set
    TextView name = convertView.findViewById(R.id.MaterialListItemName);
    TextView description = convertView.findViewById(R.id.MaterialListItemDescription);
    TextView price = convertView.findViewById(R.id.MaterialListItemPrice);
    TextView priceLbl = convertView.findViewById(R.id.MaterialListItemPriceLbl);

    //Set views
    name.setText(material.materialName);
    description.setText(material.description);
    if (material.price == null){
      price.setVisibility(View.INVISIBLE);
      priceLbl.setVisibility(View.INVISIBLE);
    } else {
      price.setText("€ " + material.price);
    }

    return convertView;
  }
}
