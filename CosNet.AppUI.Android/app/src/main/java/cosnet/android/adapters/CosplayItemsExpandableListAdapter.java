package cosnet.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cosnet.android.Entities.CosplayItem;
import cosnet.android.R;

public class CosplayItemsExpandableListAdapter extends BaseExpandableListAdapter {

  private Context context;
  private List<String> itemTypes;
  private HashMap<String, List<CosplayItem>> itemList;

  public CosplayItemsExpandableListAdapter(Context context, List<String> itemTypes, HashMap<String, List<CosplayItem>> itemList) {
    this.context = context;
    this.itemTypes = itemTypes;
    this.itemList = itemList;
  }



  @Override
  public int getGroupCount() {
    return this.itemTypes.size();
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return this.itemList.get(this.itemTypes.get(groupPosition)).size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return this.itemTypes.get(groupPosition);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return this.itemList.get(this.itemTypes.get(groupPosition)).get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    String itemType = (String) getGroup(groupPosition);

    if (convertView ==null){
      LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.cosplay_item_list_header,null);
    }

    TextView itemTypeTextView = convertView.findViewById(R.id.CosplayItemsExpListViewHeaderTitle);
    itemTypeTextView.setText(itemType);

    return convertView;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    //Get current CosplayItem
    CosplayItem item = (CosplayItem) getChild(groupPosition,childPosition);

    LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    convertView = inflater.inflate(R.layout.cosplay_item_list_item, null);

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

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }
}
