package task.darwinlabs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView toolbar_title, transactionhist;
    NonScrollListView transachistlv;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String date, time, value;
    List<TransactionHistoryDataModel> lstDataModel;
    TransactionHistoryDataModel datamodel;
    ImageView send, receive, buysell;
    Intent i;
    TextView etc, btc, ltc,scm,dsh, balancetv, valuetv, convval;
    ProgressBar etccustpb, ltccustpb, btccustpb,scmcustpb,dshcustpb;
    LinearLayout etcll, btcll, ltcll,scmll,dshll;
    ProgressDialog pdilog;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        transachistlv = (NonScrollListView) findViewById(R.id.lvtransachist);
        lstDataModel = new ArrayList<TransactionHistoryDataModel>();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setElevation(0);

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String first = "Ledger";
        String next = "<font color='#3D6B99'><b>EX</b></font>";
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        transactionhist = (TextView) findViewById(R.id.tvtransachist);
        toolbar_title.setText(Html.fromHtml(first + next));
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.menu24);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        send = (ImageView) findViewById(R.id.ivsend);
        receive = (ImageView) findViewById(R.id.ivreceive);
        buysell = (ImageView) findViewById(R.id.ivbuysell);
        etc = (TextView) findViewById(R.id.tvetc);
        btc = (TextView) findViewById(R.id.tvbtc);
        ltc = (TextView) findViewById(R.id.tvltc);
        scm = (TextView) findViewById(R.id.tvscm);
        dsh = (TextView) findViewById(R.id.tvdsh);

        etcll = (LinearLayout) findViewById(R.id.etcll);
        btcll = (LinearLayout) findViewById(R.id.btcll);
        ltcll = (LinearLayout) findViewById(R.id.ltcll);
        scmll = (LinearLayout) findViewById(R.id.scmll);
        dshll = (LinearLayout) findViewById(R.id.dshll);

        balancetv = (TextView) findViewById(R.id.etherbal);
        valuetv = (TextView) findViewById(R.id.tvvalue);
        convval = (TextView) findViewById(R.id.tvconversionval);
        etccustpb = (ProgressBar) findViewById(R.id.pbetc);
        ltccustpb = (ProgressBar) findViewById(R.id.pbltc);
        btccustpb = (ProgressBar) findViewById(R.id.pbbtc);
        scmcustpb = (ProgressBar) findViewById(R.id.pbscm);
        dshcustpb = (ProgressBar) findViewById(R.id.pbdsh);

        res = getResources();
        Rect etcbounds = etccustpb.getProgressDrawable().getBounds();
        Rect ltcbounds = ltccustpb.getProgressDrawable().getBounds();
        Rect btcbounds = btccustpb.getProgressDrawable().getBounds();
        Rect scmbounds = scmcustpb.getProgressDrawable().getBounds();
        Rect dshbounds = dshcustpb.getProgressDrawable().getBounds();

        balancetv.setText("ETHERIUM BALANCE");
        etc.setTextColor(Color.parseColor("#3D6B99"));
        btc.setTextColor(Color.parseColor("#d6d6d6"));
        ltc.setTextColor(Color.parseColor("#d6d6d6"));
        scm.setTextColor(Color.parseColor("#d6d6d6"));
        dsh.setTextColor(Color.parseColor("#d6d6d6"));
        etccustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
        btccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
        ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
        valuetv.setText("23.97462");
        convval.setText("0.0916 ETC");
        etcll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balancetv.setText("ETHERIUM BALANCE");
                etc.setTextColor(Color.parseColor("#3D6B99"));
                btc.setTextColor(Color.parseColor("#d6d6d6"));
                ltc.setTextColor(Color.parseColor("#d6d6d6"));
                scm.setTextColor(Color.parseColor("#d6d6d6"));
                dsh.setTextColor(Color.parseColor("#d6d6d6"));
                etccustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
                btccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                scmcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                dshcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                valuetv.setText("23.97462");
                convval.setText("0.0916 ETC");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(HomePage.this, Send.class);
                startActivity(i);

            }
        });
        btcll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balancetv.setText("BITCOIN BALANCE");
                btc.setTextColor(Color.parseColor("#3D6B99"));
                etc.setTextColor(Color.parseColor("#d6d6d6"));
                ltc.setTextColor(Color.parseColor("#d6d6d6"));
                scm.setTextColor(Color.parseColor("#d6d6d6"));
                dsh.setTextColor(Color.parseColor("#d6d6d6"));
                valuetv.setText("40.45678");

                convval.setText("1.432 BTC");
                btccustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
                etccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                scmcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                dshcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));

            }
        });
        ltcll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltc.setTextColor(Color.parseColor("#3D6B99"));
                balancetv.setText("LITECOIN BALANCE");
                etc.setTextColor(Color.parseColor("#d6d6d6"));
                btc.setTextColor(Color.parseColor("#d6d6d6"));
                scm.setTextColor(Color.parseColor("#d6d6d6"));
                dsh.setTextColor(Color.parseColor("#d6d6d6"));
                valuetv.setText("30.18905");
                convval.setText("1.234 LTC");
                ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
                btccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                etccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                scmcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                dshcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
            }
        });
        scmll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scm.setTextColor(Color.parseColor("#3D6B99"));
                balancetv.setText("STM BALANCE");
                etc.setTextColor(Color.parseColor("#d6d6d6"));
                btc.setTextColor(Color.parseColor("#d6d6d6"));
                ltc.setTextColor(Color.parseColor("#d6d6d6"));
                dsh.setTextColor(Color.parseColor("#d6d6d6"));
                valuetv.setText("30.18905");
                convval.setText("1.234 STM");
                scmcustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
                btccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                etccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                dshcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
            }
        });
        dsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsh.setTextColor(Color.parseColor("#3D6B99"));
                balancetv.setText("DSH BALANCE");
                etc.setTextColor(Color.parseColor("#d6d6d6"));
                btc.setTextColor(Color.parseColor("#d6d6d6"));
                scm.setTextColor(Color.parseColor("#d6d6d6"));
                ltc.setTextColor(Color.parseColor("#d6d6d6"));
                valuetv.setText("30.18905");
                convval.setText("1.234 DSH");
                dshcustpb.setProgressDrawable(res.getDrawable(R.drawable.coloredprogress));
                btccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                etccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                scmcustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
                ltccustpb.setProgressDrawable(res.getDrawable(R.drawable.greyprogress));
            }
        });
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(HomePage.this, Receive.class);
                startActivity(i);
            }
        });
        buysell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(HomePage.this, EnterPin.class);
                startActivity(i);
            }
        });
        new transachist().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
        } else if (id == R.id.send) {
            Intent i = new Intent(HomePage.this, Send.class);
            startActivity(i);
        } else if (id == R.id.receive) {
            Intent i = new Intent(HomePage.this, Receive.class);
            startActivity(i);
        } else if (id == R.id.buysell) {
            Intent i = new Intent(HomePage.this, EnterPin.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class transachist extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String response = JSONFunctions.getJSONfromURL("http://gif.run/txn_history.json");
            try {

                jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    datamodel = new TransactionHistoryDataModel();
                    jsonObject = jsonArray.getJSONObject(i);
                    date = jsonObject.getString("date");
                    datamodel.setDate(date + " | ");
                    time = jsonObject.getString("time");
                    datamodel.setTime(time);
                    value = jsonObject.getString("value");
                    datamodel.setValue(value);
                    lstDataModel.add(datamodel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            pdilog = new ProgressDialog(HomePage.this);
            pdilog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            TransacHistoryAdapetr adapter = new TransacHistoryAdapetr(HomePage.this, R.layout.transactionhistory, lstDataModel);
            transachistlv.setAdapter(adapter);
            pdilog.dismiss();
            super.onPostExecute(s);
        }
    }
}
