package com.android1.keshav;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener{

	EditText url;
	Button go,back,refresh,forward,clhist;
	WebView ourbrow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		ourbrow=(WebView)findViewById(R.id.wvBrowser);
		
		ourbrow.getSettings().setJavaScriptEnabled(true);
		ourbrow.getSettings().setLoadWithOverviewMode(true);
		ourbrow.getSettings().setUseWideViewPort(true);
		ourbrow.setWebViewClient(new ourViewClient());
		try{
		ourbrow.loadUrl("https://www.facebook.com/");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		go=(Button)findViewById(R.id.bGo);
		back=(Button)findViewById(R.id.bBack);
		refresh=(Button)findViewById(R.id.bRefresh);
		forward=(Button)findViewById(R.id.bForward);
		clhist=(Button)findViewById(R.id.bHistory);
		url=(EditText)findViewById(R.id.etURL);
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clhist.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bGo:
			String theWebsite=url.getText().toString();
			ourbrow.loadUrl(theWebsite);
			//hiding keyboard
			InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromInputMethod(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(ourbrow.canGoBack())
				ourbrow.goBack();
			break;
		case R.id.bRefresh:
			ourbrow.reload();
			break;
		case R.id.bForward:
			if(ourbrow.canGoForward())
				ourbrow.goForward();
			break;
		case R.id.bHistory:
			ourbrow.clearHistory();
			break;
		}
		
	}
	
	

}
