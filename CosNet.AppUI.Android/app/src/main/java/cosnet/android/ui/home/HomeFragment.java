package cosnet.android.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import cosnet.android.AddCosplay;
import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.R;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;
  ListView cosplayList;
  ArrayList<Cosplay> cosplays;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

    cosplayList = (ListView) root.findViewById(R.id.CosplayList);
    ImageButton addNewCosplayBTN = (ImageButton) root.findViewById(R.id.addNewCosplayBTN);

    //Get all cosplays from the database and put them in an ArrayList
    CosnetDb db = CosnetDb.getInstance(getContext());
    List<Cosplay> cosplayListDb = db.getCosplayDAO().getAllCosplays();
    cosplays = new ArrayList<>();
    cosplays.addAll(cosplayListDb);

    //Create an adapter to handle the list
    CosplayListAdapter adapter = new CosplayListAdapter(getContext(), R.layout.cosplay_list_item, cosplays);
    cosplayList.setAdapter(adapter);

    //Set event listeners
    cosplayList.setOnItemClickListener((parent, view, position, id) -> {
    });
    addNewCosplayBTN.setOnClickListener(v -> {
      Intent intent = new Intent(getActivity(), AddCosplay.class);
      startActivity(intent);
    });

    return root;
  }

  class CosplayListAdapter extends ArrayAdapter<Cosplay> {
    private Context context;
    int resource;


    CosplayListAdapter(Context c, int resource, ArrayList<Cosplay> cosplays) {
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

      //Set views
      // image.setImageResource(cosplays[position].image);
      character.setText(cosplay.cosplayName);
      Log.d("CosplayList", "getView: " + cosplay.cosplayName);
      serie.setText(cosplay.cosplaySeries);
      Log.d("CosplayList", "getView: " + cosplay.cosplaySeries);

      return convertView;
    }
  }
}
