package tw.com.rhinos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AwardFragment extends Fragment implements View.OnClickListener {
    private static final String ITEM_ID = "item_id";
    private int mItemId;
    private Button guessBig;
    private Button guessSmall;

    public AwardFragment() {
        // Required empty public constructor
    }

    public static AwardFragment newInstance(int itemId) {
        AwardFragment fragment = new AwardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_award, container, false);

        guessBig = (Button) rootView.findViewById(R.id.guess_big);
        guessSmall = (Button) rootView.findViewById(R.id.guess_small);

        guessBig.setOnClickListener(this);
        guessSmall.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guess_big:
                doGuessBig();
                break;

            case R.id.guess_small:
                doGuessSmall();
                break;
        }

        DisplayUtil.showDialog(getActivity(), "恭喜您", "我們會在賽後公佈得獎名單~");
    }

    private void doGuessBig() {
        guessSmall.setAlpha(0.5f);
        disableAllButton();
    }

    private void doGuessSmall() {
        guessBig.setAlpha(0.5f);
    }

    private void disableAllButton() {
        guessBig.setEnabled(false);
        guessSmall.setEnabled(false);

    }

}
