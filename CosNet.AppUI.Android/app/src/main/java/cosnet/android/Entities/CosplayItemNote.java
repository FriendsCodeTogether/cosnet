package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class CosplayItemNote {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "note_id")
  @NonNull public String noteId;

  @ColumnInfo(name = "cosplayItem_id")
  @NonNull public String cosplayItemId;

  @ColumnInfo(name = "note_type")
  @NonNull
  public String type;

  @ColumnInfo(name = "note_title")
  @NonNull public String title;

  @ColumnInfo(name = "note_description")
  @NonNull public String description;

  public CosplayItemNote() { this.noteId = UUID.randomUUID().toString(); }
}
