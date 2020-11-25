package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class CosplayItemNote implements Serializable {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "note_id")
  @NonNull public String noteId;

  @ColumnInfo(name = "item_id")
  @NonNull public String cosplayItemId;

  @ColumnInfo(name = "note_type")
  @Nullable public String type;

  @ColumnInfo(name = "note_title")
  @NonNull public String title;

  @ColumnInfo(name = "note_description")
  @Nullable public String description;

  @ColumnInfo(name = "note_createdDate")
  @Nullable public String createdDate;

  public CosplayItemNote() { this.noteId = UUID.randomUUID().toString(); }
}
