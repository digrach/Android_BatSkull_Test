package rach.android_swzom.orig;



import java.io.IOException;
import java.io.InputStream;

import rach.android_swzom.R;
import rach.android_swzom.R.id;
import rach.android_swzom.R.layout;
import rach.android_swzom.R.menu;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	PlaceholderFragment pf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onCreate");
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment(), "canvasfrag").commit();
		}
		
		initStuff();

	}
	
	private void initStuff() {
		AssetManager am = getAssets();
		try {
			InputStream ims = am.open("fp.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onStart");
		pf = (PlaceholderFragment)getFragmentManager().findFragmentByTag("canvasfrag");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onPause");
		MyNewSurfaceClass c = (MyNewSurfaceClass)pf.getView();
		c.pause();
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(">>>>>>>>>>>>>>>>","MainActivity.onResume");
		MyNewSurfaceClass c = (MyNewSurfaceClass)pf.getView();
		c.resume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		MyNewSurfaceClass mc;

		public PlaceholderFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d(">>>>>>>>>>>>>>>>","PlaceholderFragment.onCreateView");

			setRetainInstance(true);
			mc = new MyNewSurfaceClass(getActivity());
			return mc;
		}

	}



}
