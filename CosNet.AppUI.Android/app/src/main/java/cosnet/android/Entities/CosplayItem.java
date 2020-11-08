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

  @Nullable public String description;

  @Nullable public double price;

  @ColumnInfo(name = "due_date")
  @Nullable public String dueDate;

  @ColumnInfo(name = "is_made")
  @NonNull public int isMade ;

  //itemType specific things

  @Nullable public String status;

  @Nullable public int progress;

  @ColumnInfo(name = "worktime_hours")
  @Nullable public int worktimeHours;

  @ColumnInfo(name = "worktime_minutes")
  @Nullable public int worktimeMinutes;

  @Nullable public String buylink;

  public CosplayItem() { this.itemId = UUID.randomUUID().toString(); }
}
