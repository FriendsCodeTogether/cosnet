package cosnet.android.Data.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.Note;

public class CosplayWithNotes {
  @Embedded
  public Cosplay cosplay;
  @Relation(
    parentColumn = "cosplay_id",
    entityColumn = "cosplay_id"
  )
  public List<Note> notes;
}
