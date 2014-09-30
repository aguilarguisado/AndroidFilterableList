package mss.fragments.sample;

import java.util.ArrayList;
import java.util.List;

import mss.activities.R;
import mss.filterableList.adapters.ListviewAdapterWithFilter;
import mss.filterableList.model.impl.Task;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

/**
 * 
 * @author Juan Aguilar Guisado
 * 
 */
public class FilterableListFragment extends Fragment implements OnClickListener {

	private ListviewAdapterWithFilter adapter;
	private RadioButton byTitle;
	private RadioButton byDescription;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.filtering_layout, container, false);

		ListView list = (ListView) rootView.findViewById(R.id.list);
		EditText filterText = (EditText) rootView.findViewById(R.id.filter);

		filterText.addTextChangedListener(filterTextWatcher);

		List<Task> listContent = getListContent();
		adapter = new ListviewAdapterWithFilter(getActivity(), listContent);
		list.setAdapter(adapter);

		// Listeners for radio buttons
		byTitle = (RadioButton) rootView.findViewById(R.id.byTitle);
		byTitle.setOnClickListener(this);
		byDescription = (RadioButton) rootView.findViewById(R.id.byDescription);
		byDescription.setOnClickListener(this);

		return rootView;
	}

	private List<Task> getListContent() {
		List<Task> ret = new ArrayList<Task>();
		ret.add(new Task("Food", "Go shopping for food"));
		ret.add(new Task("Homework", "Do Maths, Science and History"));
		ret.add(new Task("Cooking", "Make a chocolate cake"));
		ret.add(new Task("Washing up", "Do it as soon as possible"));
		ret.add(new Task("Footing", "Running tonight"));
		ret.add(new Task("Chocolate", "Buy chocolate"));

		return ret;
	}

	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before, int count) {
			adapter.getFilter().filter(s);
		}

	};

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.byTitle) {
			adapter.changeFilterType(true);
		} else if (id == R.id.byDescription) {
			adapter.changeFilterType(false);
		}
	}
}
