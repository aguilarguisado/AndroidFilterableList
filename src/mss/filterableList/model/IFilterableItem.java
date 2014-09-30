package mss.filterableList.model;

public interface IFilterableItem<T> {
	public String getStringForFilter(T item);
}
