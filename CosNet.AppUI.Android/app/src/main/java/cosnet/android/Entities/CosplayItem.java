package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;
import java.util.UUID;

@Entity
public class CosplayItem {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "item_id")
  @NonNull public String itemId;

  @ColumnInfo(name = "cosplay_id")
  @NonNull public String cosplayId;

  @ColumnInfo(name = "item_name")
  @NonNull  public String itemName;

  @Nullable public String status;

  @Nullable public String description;

  @Nullable public Double price;

  @ColumnInfo(name = "start_date")
  @Nullable public String startDate;

  @ColumnInfo(name = "due_date")
  @Nullable public String dueDate;

  @Nullable public double progress;

  @Nullable public String worktime;

  @Nullable public String buylink;

  public CosplayItem() { this.itemId = UUID.randomUUID().toString(); }
}
