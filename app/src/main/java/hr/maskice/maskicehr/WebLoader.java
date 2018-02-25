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
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Marko on 25.2.2018..
 */

public class WebLoader extends Fragment {

    public WebView mWebClient;
    String getUrlWeb;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getUrlWeb = this.getArguments().getString("link");
        mWebClient = view.findViewById(R.id.webView);
        callWebClient(getUrlWeb);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        return inflater.inflate(R.layout.webview_content, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void callWebClient(String getWeb) {
        mWebClient.setWebViewClient(new maskiceWebViewClient());
        mWebClient.getSettings().setJavaScriptEnabled(true);
        mWebClient.loadUrl(getWeb);
        mWebClient.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View vView, int keyCode, KeyEvent event) {
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
        public void onPageFinished(WebView view, String url) {
            mWebClient.loadUrl("javascript:(function(){" +
                    "document.getElementsByClassName('header-main show-logo-center nav-dark')[0].style.display='none';" +
                    "})()");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView mWebView, String url) {
            if (
                    (url.startsWith("http:") ||
                            url.startsWith("https:")) &&
                            Uri.parse(url).getHost().contains("maskice.hr") ||
                            Uri.parse(url).getHost().contains("google.com") ||
                            Uri.parse(url).getHost().contains("google.hr") ||
                            Uri.parse(url).getHost().contains("facebook.com")
                    ) {
                return false;
            } else if (url.startsWith("tel:")) {
                handleTelLinks(url);
                return true;
            } else if (url.startsWith("mailto:")) {
                handleMailToLinks(url);
                Intent emailIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mWebView.getContext().startActivity(emailIntent);
                return true;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mWebView.getContext().startActivity(intent);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView mWebView, WebResourceRequest mRequest) {
            String url = mRequest.getUrl().toString();

            if (
                    (url.startsWith("http:") ||
                            url.startsWith("https:")) &&
                            Uri.parse(url).getHost().contains("maskice.hr")
                    )
            {
                return false;
            } else if (url.startsWith("tel:")) {
                handleTelLinks(url);
                return true;
            } else if (url.startsWith("mailto")) {
                handleMailToLinks(url);
                return true;
            }
            Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mWebView.getContext().startActivity(mIntent);
            return true;
        }

        protected void handleTelLinks(String link) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse(link));
            startActivity(phoneIntent);
        }

        private void handleMailToLinks(String link) {
            Intent mailtoIntent = new Intent(Intent.ACTION_SENDTO);
            mailtoIntent.setData(Uri.parse("mailto:"));
            startActivity(mailtoIntent);
        }
    }
}
