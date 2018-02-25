package hr.maskice.maskicehr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Marko on 25.2.2018..
 */

public class WebLoader extends Fragment {

    public WebView mWebClient;
    String getUrlWeb;

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getUrlWeb = this.getArguments().getString("link");
        mWebClient = view.findViewById(R.id.webView);
        callWebClient(getUrlWeb);

    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        return inflater.inflate(R.layout.webview_content, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void callWebClient(String getWeb) {
        mWebClient.setWebViewClient(new maskiceWebViewClient());
        mWebClient.getSettings().setJavaScriptEnabled(true);
        mWebClient.loadUrl(getWeb);
        mWebClient.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey (View vView, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) vView;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                    }
                }
                return false;
            }
        });
    }

    public class maskiceWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished (WebView view, String url) {
            mWebClient.loadUrl("javascript:(function(){" +
                    "document.getElementsByClassName('header-main show-logo-center nav-dark')[0].style.display='none';" +
                    "})()");
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (
                    (url.startsWith("http:") ||
                            url.startsWith("https:")) &&
                            Uri.parse(url).getHost().contains("maskice.hr") ||
                            Uri.parse(url).getHost().contains("google.com") ||
                            Uri.parse(url).getHost().contains("google.hr") ||
                            Uri.parse(url).getHost().contains("facebook.com")
                    )
            {
                return false;
            } else if (url.startsWith("tel:")) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(phoneIntent);
                view.reload();
                return true;
            } else if (url.startsWith("mailto:")) {
                Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(emailIntent);
            }
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(webIntent);
            return true;
        }
    }
}
