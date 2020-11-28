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
import cosnet.android.Entities.Note;
import cosnet.android.R;

public class NotesListAdapter extends ArrayAdapter<Note> {
  private Context context;
  int resource;

  public NotesListAdapter(Context c, int resource, ArrayList<Note> notes) {
    super(c, resource, notes);
    this.context = c;
    this.resource = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //Get current Cosplay
    Note note = getItem(position);

    LayoutInflater inflater = LayoutInflater.from(context);
    convertView = inflater.inflate(resource, parent, false);

    //Get views to set
    TextView noteName = convertView.findViewById(R.id.NoteListItemName);
    TextView date = convertView.findViewById(R.id.NoteListTime);

    //Set views
    // image.setImageResource(cosplays[position].image);
    noteName.setText(note.title);
    date.setText(note.createdDate);
    return convertView;
  }
}
