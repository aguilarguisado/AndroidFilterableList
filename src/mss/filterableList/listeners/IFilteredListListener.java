package mss.filterableList.listeners;

import java.util.List;

import android.widget.Filterable;

/**
 * 
 * @author Juan Aguilar Guisado
 * @since 1.0
 *
 * @param <T>
 */
public interface IFilteredListListener<T> extends Filterable {

	/**
	 * This method is called by the filter component after filtering the Listview.
	 * You have to link the given list to the back-list in your adapter in order to 
	 * syncronize your listview and filter component.
	 * 
	 * Most of times is an assign statement: adapterList = objects;
	 * 
	 * @param objects
	 */
	public void onSearchResult(List<T> objects);

}
