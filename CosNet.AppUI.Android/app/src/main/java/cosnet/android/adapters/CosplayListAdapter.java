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
import cosnet.android.R;

public class CosplayListAdapter extends ArrayAdapter<Cosplay> {
  private Context context;
  int resource;

  public CosplayListAdapter(Context c, int resource, ArrayList<Cosplay> cosplays) {
    super(c, resource, cosplays);
    this.context = c;
    this.resource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //Get current Cosplay
    Cosplay cosplay = getItem(position);

    LayoutInflater inflater = LayoutInflater.from(context);
    convertView = inflater.inflate(resource, parent, false);

    //Get views to set
    ImageView image = convertView.findViewById(R.id.imageView);
    TextView character = convertView.findViewById(R.id.CharacterName);
    TextView serie = convertView.findViewById(R.id.SeriesName);
    ImageView status = convertView.findViewById(R.id.StatusPic);

    //Set views
    // image.setImageResource(cosplays[position].image);
    character.setText(cosplay.cosplayName);
    serie.setText(cosplay.cosplaySeries);

    //Check status and set imageresource to the right symbol (we had to use ifelse because java is bullshit and switches dont work)
    if (cosplay.status.equals(context.getString(R.string.In_Progess))) {
      status.setImageResource(R.drawable.ic_construction_white_10dp);
    } else if (cosplay.status.equals(context.getString(R.string.Planned))) {
      status.setImageResource(R.drawable.ic_emoji_objects_white_18dp);
    }else if (cosplay.status.equals(context.getString(R.string.Done))) {
      status.setImageResource(R.drawable.ic_baseline_check_18);
    }else if (cosplay.status.equals(context.getString(R.string.OnHold))) {
      status.setImageResource(R.drawable.ic_baseline_pause_18);
    }else if (cosplay.status.equals(context.getString(R.string.Cancelled))) {
      status.setImageResource(R.drawable.ic_baseline_highlight_off_18);
    }

    return convertView;
  }
}
