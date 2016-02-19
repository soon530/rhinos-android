package tw.com.rhinos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PointFragment extends Fragment {
    private static final String ITEM_ID = "item_id";
    private int mItemId;

    public PointFragment() {
        // Required empty public constructor
    }

    public static PointFragment newInstance(int itemId) {
        PointFragment fragment = new PointFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getInt(ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_point, container, false);
        return rootView;
    }


}
