package cosnet.android.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class CosplayWithItems{
  @Embedded
  public Cosplay cosplay;
  @Relation(
    parentColumn = "cosplay_id",
    entityColumn = "cosplay_id"
  )
  public List<CosplayItem> cosplayItems;
}
