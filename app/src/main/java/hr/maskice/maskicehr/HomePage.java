package hr.maskice.maskicehr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Marko on 24.2.2018..
 */

public class HomePage extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.content_main, container, false);

        final WebView mWebView;
        mWebView = container.findViewById(R.id.homepage);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("https://maskice.hr");

        return mView;
    }

}
