package cosnet.android.Data.Relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemMaterial;

public class ItemWithMaterials {
  @Embedded
  public CosplayItem cosplayItem;
  @Relation(
    parentColumn = "item_id",
    entityColumn = "item_id"
  )
  public List<CosplayItemMaterial> cosplayItemMaterials;
}
