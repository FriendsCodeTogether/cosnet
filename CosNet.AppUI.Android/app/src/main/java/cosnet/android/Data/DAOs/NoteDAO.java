package cosnet.android.Data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;
import cosnet.android.Entities.Note;

@Dao
public interface NoteDAO {
  @Insert
  void insertItem(Note note);

  @Update
  void updateItem(Note note);

  @Delete
  void deleteItem(Note note);

  @Query("SELECT * FROM Note")
  List<Note> getNotes();

  @Query("SELECT * FROM Note WHERE id = :id")
  Note getNoteById(int id);

  @Query("SELECT * FROM Note WHERE item_id = :id")
  List<Note> getNotesByItemId(String id);

  @Query("SELECT * FROM Note WHERE cosplay_id = :id")
  List<Note> getNotesByCosplayId(String id);
}
