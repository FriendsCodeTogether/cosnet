package cosnet.android;

import android.app.Application;

import com.cosnet.appui.android.db.DaoMaster;
import com.cosnet.appui.android.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class AppController extends Application {

   public static final boolean ENCRYPTED = true;
   private DaoSession daoSession;

   @Override
   public void onCreate() {
      super.onCreate();

      DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "cosnet-db-encrypted" : "cosnet-db");
      Database db = ENCRYPTED ? helper.getEncryptedWritableDb("tensoc") : helper.getWritableDb();
      daoSession = new DaoMaster(db).newSession();
   }
}
