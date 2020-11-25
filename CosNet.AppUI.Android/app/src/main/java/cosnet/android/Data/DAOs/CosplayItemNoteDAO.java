package cosnet.android.Data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import cosnet.android.Data.Relations.CosplayWithItems;
import cosnet.android.Data.Relations.ItemWithNotes;
import cosnet.android.Entities.CosplayItem;
import cosnet.android.Entities.CosplayItemNote;

@Dao
public interface CosplayItemNoteDAO {
  @Insert
  void insertItem(CosplayItemNote cosplayItemNote);

  @Update
  void updateItem(CosplayItemNote cosplayItemNote);

  @Delete
  void deleteItem(CosplayItemNote cosplayItemNote);

  @Query("SELECT * FROM CosplayItemNote")
  List<CosplayItemNote> getAllCosplayItemNotes();

  @Query("SELECT * FROM CosplayItemNote WHERE id = :id")
  CosplayItemNote getCosplayItemNoteById(int id);

  @Transaction
  @Query("SELECT * FROM CosplayItem WHERE item_id = :id")
  ItemWithNotes getItemWithNotes(String id);
}
