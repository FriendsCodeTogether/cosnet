package cosnet.android;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cosnet.android.Data.DAOs.*;
import cosnet.android.Entities.*;

@Database(entities = {Cosplay.class, CosplayItem.class, CosplayItemNote.class}, version = 9, exportSchema = false)
public abstract  class CosnetDb extends RoomDatabase {

   private static CosnetDb minstance;
   private static final String DB_NAME = "cosnet.db";

   public abstract CosplayDAO getCosplayDAO();
   public abstract CosplayItemDAO getCosplayItemDAO();
   public abstract CosplayItemNoteDAO getCosplayItemNoteDAO();

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
