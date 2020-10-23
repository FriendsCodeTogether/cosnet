package cosnet.android.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import cosnet.android.AddCosplay;
import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.MainActivity;
import cosnet.android.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView cosplayList;
    List<Cosplay> cosplays;
    String characternames[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
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
        addNewCosplayBTN.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getActivity(), AddCosplay.class);
            startActivity(intent);
          }
        });

        CosnetDb db = CosnetDb.getInstance(getContext());
        cosplays = db.getCosplayDAO().getAllCosplays();
        int i =0;
        characternames = new String[cosplays.size()];
        for (Cosplay c : cosplays)
        {
          characternames[i] = c.cosplayName;
          i++;
        }
        MyAdapter adapter = new MyAdapter(getContext(), cosplays,characternames);
        cosplayList.setAdapter(adapter);
        cosplayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          }
        });

        return root;
    }

    class MyAdapter extends ArrayAdapter<String>{
      Context context;
      List<Cosplay> cosplays;
      String characters[];

      MyAdapter(Context c, List<Cosplay> cosplays, String characters[])
      {super(c, R.layout.row, R.id.CharacterName, characters);
        this.context = c;
        this.cosplays = cosplays;
        this.characters = characters;
      }

      @NonNull
      @Override
      public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.imageView);
        TextView character = row.findViewById(R.id.CharacterName);
        TextView serie = row.findViewById(R.id.SeriesName);

       // images.setImageResource(cosplays[position].image);
        character.setText(characters[position]);
       // serie.setText(cosplays[position].cosplaySeries);
        return row;
      }
    }
}
