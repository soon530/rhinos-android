package tw.com.rhinos;


import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;


public class PointFragment extends Fragment implements BeaconConsumer {
    private static final String ITEM_ID = "item_id";
    private int mItemId;

    private BeaconManager beaconManager;
    private boolean isNotYetDetectBeacon = true;
    private LinearLayout mPoint2Card;
    private TextView mPoint2Time;
    private String mCurrentDateTimeString;

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
        beaconManager = BeaconManager.getInstanceForApplication(getActivity());
        beaconManager.bind(this);

        mCurrentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_point, container, false);
        mPoint2Card = (LinearLayout) rootView.findViewById(R.id.point2_card);
        mPoint2Time = (TextView) rootView.findViewById(R.id.point2_time);
        return rootView;
    }


    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Log.e("vic", "beacons: " + beacons.toString());

                if (beacons.isEmpty()) return;

                if (isNotYetDetectBeacon) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPoint2Card.setVisibility(View.VISIBLE);
                            mPoint2Time.setText(mCurrentDateTimeString);
                        }
                    });

                    isNotYetDetectBeacon = false;
                    Log.e("vic", "isNotYetDetectBeacon: " + isNotYetDetectBeacon);

                }

            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }

    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getActivity().bindService(intent, serviceConnection, i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if( beaconManager.isBound(this) ) beaconManager.unbind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
    }

}
