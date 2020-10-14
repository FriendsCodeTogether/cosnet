package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

import java.io.IOException;

public class MyGenerator {
   public static void main(String[] args) {
      Schema schema = new Schema(1,"com.cosnet.appui.android.db");
      schema.enableKeepSectionsByDefault();

      addTables(schema);

      try {
         new DaoGenerator().generateAll(schema,"./app/src/main/java");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void addTables(final Schema schema) {
      addCosplayEntities(schema);
   }

   private static Entity addCosplayEntities(final Schema schema) {
      Entity cosplay = schema.addEntity("Cosplay");
      cosplay.addIdProperty().primaryKey().autoincrement();
      cosplay.addStringProperty("cosplay_name").notNull();
      cosplay.addStringProperty("cosplay_serie");
      cosplay.addDateProperty("start_date");
      cosplay.addDateProperty("due_date");
      cosplay.addStringProperty("status");
      return cosplay;
   }
}
