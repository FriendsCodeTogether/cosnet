package cosnet.android;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class AddCosplay extends Activity {
   private static final String TAG = "AddCosplay";

   @Override
   protected void onCreate( Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.add_cosplay_main);

      Log.d(TAG, "onCreate: Starting.");
      ImageButton cancelNewButton = (ImageButton) findViewById(R.id.cancelNewCosplayBTN);
      cancelNewButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Log.d(TAG, "onClick: clicked cancelBUttonBTN");

            Intent intent = new Intent(AddCosplay.this, MainActivity.class);
            startActivity(intent);
         }
      });




      Button addCosplayBTN = (Button) findViewById(R.id.addCosplayBTN);
      addCosplayBTN.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Log.d(TAG, "onClick: clicked cancelBUttonBTN");

            Intent intent = new Intent(AddCosplay.this, MainActivity.class);
            startActivity(intent);
         }
      });

   }
}
