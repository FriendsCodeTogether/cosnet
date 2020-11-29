package cosnet.android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cosnet.android.Data.DAOs.CosplayDAO;
import cosnet.android.ui.cosplay.AddCosplay;
import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.R;
import cosnet.android.ui.cosplay.ShowCosplay;
import cosnet.android.adapters.CosplayListAdapter;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

  private static final int REQUEST_ADD_COSPLAY = 1;
  private static final int REQUEST_DELETE_COSPLAY = 2;

  ListView cosplayList;
  ImageButton addNewCosplayBTN;
  ArrayList<Cosplay> cosplays;
  CosnetDb db;
  CosplayDAO cosplayDAO;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.fragment_home, container, false);

    getDatabase();
    initialiseWidgets(root);
    setList();
    setListeners();

    return root;
  }

  private void getDatabase() {
    db = CosnetDb.getInstance(getContext());
    cosplayDAO = db.getCosplayDAO();
  }

  private void initialiseWidgets(View root) {
    cosplayList = root.findViewById(R.id.CosplayList);
    addNewCosplayBTN = root.findViewById(R.id.addNewCosplayBTN);
  }

  private void setList() {
    //Get all cosplays from the database and put them in an ArrayList
    List<Cosplay> cosplayListDb = cosplayDAO.getAllCosplays();
    cosplays = new ArrayList<>();
    cosplays.addAll(cosplayListDb);

    //Create an adapter to handle the list
    CosplayListAdapter adapter = new CosplayListAdapter(getContext(), R.layout.cosplay_list_item, cosplays);
    cosplayList.setAdapter(adapter);
  }

  private void setListeners() {
    //show selected cosplay from list
    cosplayList.setOnItemClickListener((parent, view, position, id) -> {
      Cosplay cosplay = cosplays.get(position);
      Intent intent = new Intent(getActivity(), ShowCosplay.class);
      intent.putExtra("cosplay", cosplay);
      startActivityForResult(intent, REQUEST_DELETE_COSPLAY);
    });

    //add new cosplay
    addNewCosplayBTN.setOnClickListener(v -> {
      Intent intent = new Intent(getActivity(), AddCosplay.class);
      startActivityForResult(intent, REQUEST_ADD_COSPLAY);
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    //switch for the requests
    switch (requestCode) {
      case REQUEST_ADD_COSPLAY:
        //switch for the results from add cosplay
        switch (resultCode) {
          case RESULT_OK:
            String addedCosplayName = data.getStringExtra("addedCosplayName");
            Toast.makeText(getContext(), addedCosplayName + " Added", Toast.LENGTH_SHORT).show();
            setList();
            break;
          case RESULT_CANCELED:
            Toast.makeText(getContext(), "Cosplay Canceled", Toast.LENGTH_SHORT).show();
            break;
        }
        break;
      case REQUEST_DELETE_COSPLAY:
        //switch for the results from delete/show cosplay
        switch (resultCode) {
          case RESULT_OK:
            //if the result is ok than the cosplay is deleted
            String deletedCosplayName = data.getStringExtra("deletedCosplayName");
            Toast.makeText(getContext(), deletedCosplayName + " Deleted", Toast.LENGTH_SHORT).show();
            setList();
            break;
        }
        break;
    }
  }
}
