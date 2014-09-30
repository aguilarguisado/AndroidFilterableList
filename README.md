Android Filtering List
======
**Android Filtering List** 

With this component you can filter ListViews with adapters different from ArrayAdapter<String>. Main features are:

* Filter ListViews with custom filters.
* Set different filters depending on  UI state and events (clicks on buttons, checkbox buttons etc.)
* Change filters dynamically depending on events.


## Version 
* Version 1.0

## How-to use this code

###Implementation
 * Create a new **ListFilter<T>** which implements **IFilteredListListener<T>** and  **IFilterableItem<T>** and define the required methods:

 		public void onSearchResult(List<T> objects);
 		
 **onSearchResult** links filtered objects with the original list in the array adapter. This will be called every time the filter changes.
 		
 		public String getStringForFilter(T item);
 		
 **getStringForFilter** extracts the String value of T (as generic Object). In the sample, depending on the checkbox checked, with filter for their title, or their description. 
 
 **Advice:** Make sure that you are filtering texts that are being displayed in the UI, for a good user experience.


###Use

Full code is in the sample.

* Define a ListView with an adapter which implements the interfaces defined above, and code the two required methods.

* Define a TextWatcher for an EditText (from the layout)to filter the list.

* If you want to change the filter dynamically, you must telling the ListFilter the event calling the method 

		filterTypeChanged()

###TODO

* Improve performance for ListFilter<T> when the filter changes.
* Make the sample with ViewHolders/CacheHolders for the ListView

## License 
* see [LICENSE](https://github.com/juaagugui/AndroidFilteringList/blob/master/LICENSE) file

## Contact
#### Developer/Company
* e-mail: juan.aguilar.guisado@gmail.com
* Twitter: [@juaagugui](https://twitter.com/juaagugui)
* Linkedin: [Juan Aguilar Guisado](http://es.linkedin.com/in/juanaguilarguisado)