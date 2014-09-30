package mss.filterableList.adapters;

import java.util.List;

import mss.activities.R;
import mss.filterableList.listeners.IFilteredListListener;
import mss.filterableList.model.IFilterableItem;
import mss.filterableList.model.impl.ListFilter;
import mss.filterableList.model.impl.Task;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ListviewAdapterWithFilter extends BaseAdapter implements IFilteredListListener<Task>, IFilterableItem<Task> {

	private Context context;
	private List<Task> list;
	private ListFilter<Task> filter;
	private Boolean filterByTitle;

	public ListviewAdapterWithFilter(Context context, List<Task> listContent) {
		this.context = context;
		this.list = listContent;
		this.filterByTitle = true;
		this.filter = new ListFilter<Task>(list, this, this, this);
	}

	// ------------------------
	// --------BaseAdapter
	// ------------------------

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ViewHolder") //TODO
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.row_filtering_list, parent, false);
		Task task = list.get(position);
		TextView title = (TextView) itemView.findViewById(R.id.title);
		TextView description = (TextView) itemView.findViewById(R.id.description);
		title.setText(task.getTitle());
		description.setText(task.getDescription());
		return itemView;
	}

	public void changeFilterType(Boolean filterByTitle) {
		this.filterByTitle = filterByTitle;
		this.filter.filterTypeChanged();
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public void onSearchResult(List<Task> objects) {
		list = objects;
	}

	@Override
	public String getStringForFilter(Task item) {
		if (filterByTitle) {
			return item.getTitle();
		} else {
			return item.getDescription();
		}
	}

}