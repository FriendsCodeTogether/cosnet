package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class CosplayItemMaterial implements Serializable {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "material_id")
  @NonNull public String materialId;

  @ColumnInfo(name = "item_id")
  @NonNull public String itemId;

  @ColumnInfo(name = "material_name")
  @NonNull public String materialName;

  @Nullable public Double price;

  @Nullable public String description;

  @Nullable public String buylink;

  public CosplayItemMaterial() { this.materialId = UUID.randomUUID().toString(); }
}
