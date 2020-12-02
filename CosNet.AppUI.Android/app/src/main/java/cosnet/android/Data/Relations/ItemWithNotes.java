package cosnet.android.Data.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.Note;

public class ItemWithNotes {
  @Embedded
  public CosplayItem cosplayItem;
  @Relation(
    parentColumn = "item_id",
    entityColumn = "item_id"
  )
  public List<Note> notes;
}
