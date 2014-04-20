package com.example.swipetest;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListItemAdapter extends BaseAdapter {

	private ArrayList<ContentsItem> items = new ArrayList<ContentsItem>();
	private Context context;

	public ListItemAdapter(Context context){
		super();
		this.context = context;
	}

	public int getCount() {
		return items.size();
	}
	
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void addItem(ContentsItem item){
		this.items.add(item);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContentsItem item;
		if( convertView == null )
		{
			item = new ContentsItem();
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.list_item, null);
			convertView.setTag(item);
	        
		}else{
			item = ( ContentsItem ) convertView.getTag();
		}
		
		return convertView;
	}
}
