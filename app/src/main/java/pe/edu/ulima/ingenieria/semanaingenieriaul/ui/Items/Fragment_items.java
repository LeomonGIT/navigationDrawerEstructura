package pe.edu.ulima.ingenieria.semanaingenieriaul.ui.Items;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import java.util.List;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import pe.edu.ulima.ingenieria.semanaingenieriaul.R;

public class Fragment_items extends Fragment {

    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;
    static private View myFragmentView;
    static private Context context;
    int value ;
    String ParseTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.fragment_item1, container, false);
        context = myFragmentView.getContext();
        value = getArguments().getInt("key");
        inicializar();
        new RemoteDataTask().execute();
        return myFragmentView;
    }

    private void inicializar(){
        if(value==1)
            ParseTable = "Eventos";
        else if(value==2)
            ParseTable = "Pabellon";

    }
    private class RemoteDataTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setTitle("Ulima");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(ParseTable);
            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (ParseException pe) {
                Log.e("Error", pe.getMessage());
                pe.printStackTrace();
            }catch(Exception e ) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listview = (ListView) myFragmentView.findViewById(R.id.listview_items);
            adapter = new ArrayAdapter<String>(context,R.layout.listview_item,R.id.text_items);
            for (ParseObject country : ob) {
                if(country.get("Nombre") != null)
                    adapter.add((String) country.get("Nombre"));
            }
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // Send single item click data to SingleItemView Class
                    Intent i = new Intent(context,
                            detalleItemsInItem.class);
                    // Pass data "name" followed by the position
                    i.putExtra("name", ob.get(position).get("Nombre").toString());
                    // Open SingleItemView.java Activity
                    startActivity(i);
                }
            });
        }
    }
}
