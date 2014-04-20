package com.example.swipetest;

import java.util.ArrayList;
import java.util.Locale;

import com.example.widgettest.TestWidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Log.i("position", Integer.toString(position));
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 12;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return "4월".toUpperCase(l);
			case 4:
				return "5월".toUpperCase(l);
			case 5:
				return "6월".toUpperCase(l);
			case 6:
				return "7월".toUpperCase(l);
			case 7:
				return "8월".toUpperCase(l);
				
			}
			return null;
		}
	}
	
	public ViewPager getViewPager(){
		if( mViewPager == null ){
			mViewPager = ( ViewPager ) findViewById(R.id.pager);
		}
		
		return mViewPager;
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		ListView listView;
		ListItemAdapter listAdapter;
		ArrayList<ContentsItem> itemList;
		Button firstButton;
		Button hundredButton;
		Button tenButton;
		public DummySectionFragment() {
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			final TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			firstButton = (Button) rootView.findViewById(R.id.firstPage);
			firstButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((MainActivity)getActivity()).getViewPager().setCurrentItem(0,true);
				}
			});

			
			
			
			hundredButton = (Button) rootView.findViewById(R.id.hundred);
			hundredButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent( getActivity().getApplicationContext() , TestWidget.class);
					intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
					
					// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
					// since it seems the onUpdate() is only fired on that:
				//	int[] ids = {widgetId};
					//intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
					
					
					String moneyText = (String) dummyTextView.getText();
					int money = Integer.parseInt(moneyText);
					money += 100;

					
					dummyTextView.setText( Integer.toString(money) );
					intent.putExtra("money", Integer.toString(money) );
					getActivity().sendBroadcast(intent);
				}
			});
			
			tenButton = (Button) rootView.findViewById(R.id.ten);
			tenButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String moneyText = (String) dummyTextView.getText();
					int money = Integer.parseInt(moneyText);
					money += 10;
					
					dummyTextView.setText( Integer.toString(money) );
				}
			});
			
			
			
			/*
			listAdapter = new ListItemAdapter(getActivity().getApplicationContext());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listAdapter.addItem(new ContentsItem());
			listView = (ListView) rootView.findViewById(R.id.listView);
			listView.setAdapter(listAdapter);*/
			
			return rootView;
		}
	}

}
