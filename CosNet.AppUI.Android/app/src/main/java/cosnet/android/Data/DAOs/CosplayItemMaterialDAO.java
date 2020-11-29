package cosnet.android.Data.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import cosnet.android.Data.Relations.CosplayWithItems;
import cosnet.android.Data.Relations.ItemWithMaterials;
import cosnet.android.Entities.CosplayItemMaterial;


@Dao
public interface CosplayItemMaterialDAO {
  @Insert
  void insertItem(CosplayItemMaterial cosplayItemMaterial);

  @Update
  void updateItem(CosplayItemMaterial cosplayItemMaterial);

  @Delete
  void deleteItem(CosplayItemMaterial cosplayItemMaterial);

  @Query("SELECT * FROM CosplayItemMaterial")
  List<CosplayItemMaterial> getAllCosplayItemMaterials();

  @Query("SELECT * FROM CosplayItemMaterial WHERE id = :id")
  CosplayItemMaterial getCosplayItemMaterialById(int id);


  @Transaction
  @Query("SELECT * FROM CosplayItem WHERE item_id = :id")
  ItemWithMaterials getItemWithMaterials(String id);
}
