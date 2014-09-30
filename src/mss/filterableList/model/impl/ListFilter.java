package mss.filterableList.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mss.filterableList.listeners.IFilteredListListener;
import mss.filterableList.model.IFilterableItem;
import android.widget.BaseAdapter;
import android.widget.Filter;

public class ListFilter<T> extends Filter {

	private List<T> adapterFilterElements;
	private List<T> originalFilterElements;
	private final Object mLock = new Object();

	private List<String> originalFilterValues;
	private IFilteredListListener<T> listener;
	private BaseAdapter baseAdapter;
	private IFilterableItem<T> filterableItem;
	private CharSequence prefix;

	public ListFilter(List<T> originalElements, BaseAdapter baseAdapter, IFilteredListListener<T> listener, IFilterableItem<T> filterableItem) {
		this.adapterFilterElements = originalElements;
		this.originalFilterElements = originalElements;
		this.baseAdapter = baseAdapter;
		this.listener = listener;
		this.filterableItem = filterableItem;
	}

	@Override
	protected FilterResults performFiltering(CharSequence prefix) {
		this.prefix = prefix;
		FilterResults results = new FilterResults();
		if (originalFilterValues == null) {
			synchronized (mLock) {
				originalFilterValues = fillListNamesFromItems(adapterFilterElements);
			}
		}
		if (prefix == null || prefix.length() == 0) {
			ArrayList<String> list;
			synchronized (mLock) {
				list = new ArrayList<String>(originalFilterValues);
			}
			results.values = list;
			results.count = list.size();
		} else {
			String prefixString = prefix.toString().toLowerCase(Locale.UK);
			ArrayList<String> values;
			synchronized (mLock) {
				values = new ArrayList<String>(originalFilterValues);
			}
			final int count = values.size();
			final ArrayList<String> newValues = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				final String value = values.get(i);
				final String valueText = value.toString().toLowerCase(Locale.UK);
				// First match against the whole, non-splitted value
				if (valueText.startsWith(prefixString)) {
					newValues.add(value);
				} else {
					final String[] words = valueText.split(" ");
					final int wordCount = words.length;
					// Start at index 0, in case valueText starts with
					// space(s)
					for (int k = 0; k < wordCount; k++) {
						if (words[k].startsWith(prefixString)) {
							newValues.add(value);
							break;
						}
					}
				}
			}
			results.values = newValues;
			results.count = newValues.size();
		}
		return results;
	}

	public void filterTypeChanged() {
		if (originalFilterValues != null) {
			adapterFilterElements = originalFilterElements;
			originalFilterValues = null;
			filter(prefix);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		results.values = fillItemListFromNames((List<String>) results.values);
		listener.onSearchResult((List<T>) results.values);
		adapterFilterElements = (List<T>) results.values;
		if (results.count > 0) {
			baseAdapter.notifyDataSetChanged();
		} else {
			baseAdapter.notifyDataSetInvalidated();
		}
	}

	private List<T> fillItemListFromNames(List<String> names) {
		List<T> ret = new ArrayList<T>();
		if (names != null) {
			for (String s : names) {
				for (T p : originalFilterElements) {
					if (filterableItem.getStringForFilter(p).equals(s)) {
						ret.add(p);
						break;
					}
				}
			}
		}

		return ret;
	}

	private List<String> fillListNamesFromItems(List<T> items) {
		List<String> ret = new ArrayList<String>();
		if (items != null) {
			for (T item : items) {
				ret.add(filterableItem.getStringForFilter(item));
			}
		}
		return ret;
	}

}