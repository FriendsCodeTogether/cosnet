package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Note implements Serializable {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "note_id")
  @NonNull public String noteId;

  @ColumnInfo(name = "item_id")
  @Nullable public String itemId;

  @ColumnInfo(name = "cosplay_id")
  @Nullable public String cosplayId;

  @ColumnInfo(name = "note_type")
  @NonNull public String type;

  @ColumnInfo(name = "note_title")
  @NonNull public String title;

  @ColumnInfo(name = "note_description")
  @Nullable public String description;

  @ColumnInfo(name = "note_createdDate")
  @NonNull public String createdDate;

  public Note() { this.noteId = UUID.randomUUID().toString(); }
}
