package cosnet.android.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cosnet.android.Entities.Cosplay;

@Dao
public interface CosplayDAO {

   @Insert
   void insertCosplay(Cosplay cosplay);

   @Update
   void updateCosplay(Cosplay cosplay);

   @Delete
   void deleteCosplay(Cosplay cosplay);

   @Query("SELECT * FROM cosplay")
   List<Cosplay> getAllCosplays();

   @Query("SELECT * FROM cosplay WHERE id = :id")
   Cosplay getCosplayById(int id);

}
