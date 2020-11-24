package cosnet.android.Data.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemNote;

public class ItemWithNotes {
  @Embedded
  public CosplayItem cosplayItem;
  @Relation(
    parentColumn = "cosplayItem_id",
    entityColumn = "cosplayItem_id"
  )
  public List<CosplayItemNote> cosplayItemNotes;
}
