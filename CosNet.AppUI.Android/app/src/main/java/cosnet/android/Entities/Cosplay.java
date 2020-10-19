package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Cosplay {
   @PrimaryKey(autoGenerate = true)
   @NonNull public int id;

   @NonNull public String cosplay_name;
   @Nullable public String cosplay_series;
   @Nullable public String start_date;
   @Nullable public String due_date;
   @Nullable public double budget;
   public String status;

   public Cosplay(String cosplay_name, @Nullable String cosplay_series, @Nullable String start_date, @Nullable String due_date, @Nullable double budget, String status){
      this.cosplay_name = cosplay_name;
      this.cosplay_series = cosplay_series;
      this.start_date = start_date;
      this.due_date = due_date;
      this.budget = budget;
      this.status = status;
   }


}
