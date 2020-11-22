package cosnet.android;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cosnet.android.Data.DAOs.*;
import cosnet.android.Entities.*;

<<<<<<< HEAD
@Database(entities = {Cosplay.class, CosplayItem.class}, version = 6)
public abstract  class CosnetDb extends RoomDatabase {
=======
@Database(entities = {Cosplay.class, CosplayItem.class}, version = 5)
public abstract class CosnetDb extends RoomDatabase {
>>>>>>> develop

   private static CosnetDb minstance;
   private static final String DB_NAME = "cosnet.db";

   public abstract CosplayDAO getCosplayDAO();
   public abstract CosplayItemDAO getCosplayItemDAO();

   public static synchronized CosnetDb getInstance(Context ctx) {
      if (minstance == null){
         minstance = Room.databaseBuilder(ctx.getApplicationContext(), CosnetDb.class, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
      }
      return minstance;
   }
}
