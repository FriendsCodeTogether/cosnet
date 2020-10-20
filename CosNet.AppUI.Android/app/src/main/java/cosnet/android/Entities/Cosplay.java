package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Cosplay {
   @PrimaryKey(autoGenerate = true)
   public int id;

   @NonNull public String cosplay_name;
   @Nullable public String cosplay_series;
   @Nullable public String start_date;
   @Nullable public String due_date;
   @Nullable public Double budget;
   @NonNull public String status;

   public Cosplay(@NonNull String cosplay_name, @Nullable String cosplay_series, @Nullable String start_date, @Nullable String due_date, @Nullable Double budget,@NonNull String status){
      this.cosplay_name = cosplay_name;
      this.cosplay_series = cosplay_series;
      this.start_date = start_date;
      this.due_date = due_date;
      this.budget = budget;
      this.status = status;
   }

}
