package com.codeutsava.jeevandeep.bloodbank.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.bloodbank.data.BloodBankListResponse;
import com.codeutsava.jeevandeep.bloodbank.presenter.BloodBankListPresenter;
import com.codeutsava.jeevandeep.bloodbank.presenter.BloodBankListPresenterImpl;
import com.codeutsava.jeevandeep.bloodbank.provider.RetrofitBloodBankListProvider;
import com.codeutsava.jeevandeep.donorlist.view.DonorListRecyclerViewAdapter;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BloodBankListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BloodBankListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BloodBankListFragment extends Fragment implements BloodBankListView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.recyclerview_feed)
    RecyclerView recyclerviewNetwork;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;



    Context context;
    SharedPrefs sharedPrefs;
    BloodBankListPresenter bloodBankListPresenter;
    private OnFragmentInteractionListener mListener;

    BloodBankListRecyclerViewAdapter networkListRecyclerViewAdapter;


    public BloodBankListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BloodBankListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BloodBankListFragment newInstance(String param1, String param2) {
        BloodBankListFragment fragment = new BloodBankListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_bank_list, container, false);

        context = getContext();
        ButterKnife.bind(this, view);
        sharedPrefs = new SharedPrefs(getContext());
        bloodBankListPresenter = new BloodBankListPresenterImpl(this, new RetrofitBloodBankListProvider());
        bloodBankListPresenter.getFeedList(sharedPrefs.getAccessToken());
        // Set the adapter
        networkListRecyclerViewAdapter = new BloodBankListRecyclerViewAdapter(context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerviewNetwork.setLayoutManager(linearLayoutManager);
        recyclerviewNetwork.setHasFixedSize(true);
        recyclerviewNetwork.setAdapter(networkListRecyclerViewAdapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    }

    @Override
    public void setFeedList(BloodBankListResponse feedListResponse) {
        networkListRecyclerViewAdapter.setData(feedListResponse.getNews_feed_list());
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
