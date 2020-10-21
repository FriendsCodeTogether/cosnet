package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity
public class Cosplay {
   @PrimaryKey(autoGenerate = true)
   public int id;

   @ColumnInfo(name = "cosplay_id")
   public String cosplayId;

   @ColumnInfo(name = "cosplay_name")
   @NonNull public String cosplayName;

   @ColumnInfo(name = "cosplay_series")
   @Nullable public String cosplaySeries;

   @ColumnInfo(name = "start_date")
   @Nullable public String startDate;

   @ColumnInfo(name = "due_date")
   @Nullable public String dueDate;

   @Nullable public Double budget;
   @NonNull public String status;

   public Cosplay(@NonNull String cosplayName, @Nullable String cosplaySeries, @Nullable String startDate, @Nullable String dueDate, @Nullable Double budget,@NonNull String status){
      this.cosplayName = cosplayName;
      this.cosplaySeries = cosplaySeries;
      this.startDate = startDate;
      this.dueDate = dueDate;
      this.budget = budget;
      this.status = status;
      this.cosplayId = UUID.randomUUID().toString();
   }

   public Cosplay(){
     this.cosplayId = UUID.randomUUID().toString();
   }
}
