package com.codeutsava.jeevandeep.donorlist.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.donorlist.data.DonorListResponse;
import com.codeutsava.jeevandeep.donorlist.presenter.DonorListPresenter;
import com.codeutsava.jeevandeep.donorlist.presenter.DonorListPresenterImpl;
import com.codeutsava.jeevandeep.donorlist.provider.RetrofitDonorListProvider;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.codeutsava.jeevandeep.utils.ViewUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DonorListFragment extends Fragment implements DonorListView{
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    DonorListPresenter donorListPresenter;
    SharedPrefs sharedPrefs;
    Context context;
    @BindView(R.id.recyclerview_feed)
    RecyclerView recyclerviewNetwork;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    DonorListRecyclerViewAdapter networkListRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DonorListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DonorListFragment newInstance(int columnCount) {
        DonorListFragment fragment = new DonorListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donor_list, container, false);
        context = getContext();
        ButterKnife.bind(this, view);
        sharedPrefs = new SharedPrefs(getContext());
        donorListPresenter = new DonorListPresenterImpl(this, new RetrofitDonorListProvider());
        donorListPresenter.getFeedList(sharedPrefs.getAccessToken());
        // Set the adapter
        networkListRecyclerViewAdapter = new DonorListRecyclerViewAdapter(context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerviewNetwork.setLayoutManager(linearLayoutManager);
        recyclerviewNetwork.setHasFixedSize(true);
        recyclerviewNetwork.setAdapter(networkListRecyclerViewAdapter);
//        etSearchBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                networkListPresenter.onCallCancel();
//                networkListPresenter.getNetworkList(sharedPrefs.getAccessToken(), editable.toString());
//            }
//        });



        return view;
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(context, message);
    }

    @Override
    public void setFeedList(DonorListResponse networkListResponse) {
        networkListRecyclerViewAdapter.setData(networkListResponse.getNews_feed_list());
        networkListRecyclerViewAdapter.notifyDataSetChanged();
    }


    public void makecall(String phoneno){

                Dexter.withActivity(getActivity())
                        .withPermissions(
                                Manifest.permission.CALL_PHONE
                        ).withListener(new MultiplePermissionsListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            try {

                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneno));
                                startActivity(intent);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
            }
            private void showSettingsDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Need Permissions");
                builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
                builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
                    dialog.cancel();
                    openSettings();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                builder.show();
            }

            private void openSettings() {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);

    }

//    public void openProfile(Fragment fragment){
//
//
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.fragment_container, fragment);
//
//        transaction.commit();
//    }
}
