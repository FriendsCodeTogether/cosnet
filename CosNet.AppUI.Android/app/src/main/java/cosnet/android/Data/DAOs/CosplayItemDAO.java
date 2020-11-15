package cosnet.android.Data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import cosnet.android.Data.Relations.CosplayWithItems;
import cosnet.android.Entities.CosplayItem;

@Dao
public interface CosplayItemDAO {
  @Insert
  void insertItem(CosplayItem cosplayItem);

  @Update
  void updateItem(CosplayItem cosplayItem);

  @Delete
  void deleteItem(CosplayItem cosplayItem);

  @Query("SELECT * FROM CosplayItem")
  List<CosplayItem> getAllCosplayItems();

  @Query("SELECT * FROM CosplayItem WHERE id = :id")
  CosplayItem getCosplayItemById(int id);

  /*@Transaction
  @Query("SELECT * FROM Cosplay")
  public List<CosplayWithItems> getCosplayWithItems();*/

  @Transaction
  @Query("SELECT * FROM Cosplay WHERE cosplay_id = :id")
  CosplayWithItems getCosplayWithItems(String id);
}
