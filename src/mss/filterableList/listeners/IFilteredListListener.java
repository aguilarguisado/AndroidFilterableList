package mss.filterableList.listeners;

import java.util.List;

import android.widget.Filterable;

public interface IFilteredListListener<T> extends Filterable {

	public void onSearchResult(List<T> objects);

}
