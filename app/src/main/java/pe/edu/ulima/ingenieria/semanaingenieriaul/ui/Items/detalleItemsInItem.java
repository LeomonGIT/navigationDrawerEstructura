package pe.edu.ulima.ingenieria.semanaingenieriaul.ui.Items;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pe.edu.ulima.ingenieria.semanaingenieriaul.R;

/**
 * Created by Leonardo on 09/04/2015.
 */
public class detalleItemsInItem extends ActionBarActivity {
   // Declare Variables
    TextView txtname;
    String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleitemview);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        txtname = (TextView) findViewById(R.id.nameDetalleItem);
        txtname.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}