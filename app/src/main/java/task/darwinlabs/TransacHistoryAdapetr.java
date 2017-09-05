package task.darwinlabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SS0088 on 9/3/2017.
 */
public class TransacHistoryAdapetr extends ArrayAdapter<TransactionHistoryDataModel> {
    private Context context;
    private int layoutResourceId;
    ListView list;
    static final String TAG = "LISTT";
    HashMap<Integer, Integer> hashMap;
    String response;
    ProgressDialog pdilog;
    SharedPreferences prefs, prefs2;
    String serverresponse1;
    private List<TransactionHistoryDataModel> data;
    String custid;
    JSONArray _jsonarray, jsonarray;
    JSONObject jsonObject;
    String subsrvceopid;
    String quantity = "1";
    String cartcounter;
    TextView counter;
    String msg, msg1;
    String language;

    public TransacHistoryAdapetr(Context context, int layoutResourceId,
                                 List<TransactionHistoryDataModel> data) {
        super(context, R.layout.transactionhistory, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        hashMap = new HashMap<Integer, Integer>();
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.date = (TextView) row.findViewById(R.id.tvdate);
            holder.time = (TextView) row.findViewById(R.id.tvtime);
            holder.val = (TextView) row.findViewById(R.id.tvtime);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final TransactionHistoryDataModel item = data.get(position);

        holder.date.setText(item.getDate());
        holder.time.setText(item.getTime());
        holder.val.setText(item.getValue());
        return row;
    }

    static class ViewHolder {

        TextView date, time, val;

    }
}

