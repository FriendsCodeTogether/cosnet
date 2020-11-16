package cosnet.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;

public class CosplayItemsListAdapter extends ArrayAdapter<CosplayItem> {
  private Context context;
  int resource;

  public CosplayItemsListAdapter(Context c, int resource, ArrayList<CosplayItem> cosplayItems) {
    super(c, resource, cosplayItems);
    this.context = c;
    this.resource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //Get current CosplayItem
    CosplayItem item = getItem(position);

    LayoutInflater inflater = LayoutInflater.from(context);
    convertView = inflater.inflate(resource, parent, false);

    //Get views to set
    ImageView image = convertView.findViewById(R.id.CosplayItemListItemPic);
    TextView name = convertView.findViewById(R.id.CosplayItemListItemName);
    TextView price = convertView.findViewById(R.id.CosplayItemListPrice);
    TextView time = convertView.findViewById(R.id.CosplayItemListTime);
    TextView timeLbl = convertView.findViewById(R.id.CosplayItemListTimeLbl);
    ProgressBar progressBar = convertView.findViewById(R.id.CosplayItemListProgressBar);
    ImageView status = convertView.findViewById(R.id.CosplayItemListStatusPic);

    //Set views
    // image.setImageResource(cosplays[position].image);
    name.setText(item.itemName);
    price.setText("€" + item.price);

    if (item.isMade == 0) {
      timeLbl.setVisibility(View.INVISIBLE);
      progressBar.setVisibility(View.INVISIBLE);

      if (item.status.equals(context.getString(R.string.ToBuy))) {
        status.setImageResource(R.drawable.ic_tobuy);
      } else if (item.status.equals(context.getString(R.string.Ordered))) {
        status.setImageResource(R.drawable.ic_ordered);
      } else if (item.status.equals(context.getString(R.string.Completed))) {
        status.setImageResource(R.drawable.ic_baseline_check_18);
      }
    } else {
      time.setText(item.worktimeHours + "H " + item.worktimeMinutes + "Min");
      progressBar.setProgress(item.progress);

      if (item.status.equals(context.getString(R.string.In_Progess))) {
        status.setImageResource(R.drawable.ic_construction_white_10dp);
      } else if (item.status.equals(context.getString(R.string.Completed))) {
        status.setImageResource(R.drawable.ic_baseline_check_18);
      }
    }

    return convertView;
  }
}
