package mss.filterableList.model;

public interface IFilterableItem<T> {

	/**
	 * @author Juan Aguilar Guisado
	 * @since 1.0
	 * 
	 *        This interface must be implemented in order to get the filter a
	 *        String to filter with. The implementation could define different
	 *        behaviors depending on UI events, business logic etc. which makes
	 *        possible dynamic filters
	 * 
	 * @param item
	 * @return
	 */

	public String getStringForFilter(T item);
}
