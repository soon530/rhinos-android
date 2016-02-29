package tw.com.rhinos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class FindFragment extends Fragment {
    private static final String ITEM_ID = "item_id";
    private int mItemId;

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance(int itemId) {
        FindFragment fragment = new FindFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_find, container, false);
        final ImageView find = (ImageView)rootView.findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find.setImageResource(R.drawable.find2);
            }
        });
        return rootView;
    }

}
