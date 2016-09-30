package cursoandroid.primeiroapp.exemplo7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import cursoandroid.primeiroapp.R;

public class WebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Button btnWebView = (Button) findViewById(R.id.btnWebView);
        Button btnWebView2 = (Button) findViewById(R.id.btnWebView2);
        final WebView webView = (WebView) findViewById(R.id.webView);


        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customHtml = "<html><body><h1>Hello, <font color='blue'>WebView</font></h1></body></html>";
                webView.loadData(customHtml, "text/html", "UTF-8");
            }
        });

        btnWebView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.getSettings().setJavaScriptEnabled(true);

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://livroandroid.com.br");
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
