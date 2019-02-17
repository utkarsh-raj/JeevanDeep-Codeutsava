package com.codeutsava.jeevandeep.donationcampaignlist.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignListResponse;
import com.codeutsava.jeevandeep.donationcampaignlist.presenter.DonationCampaignListPresenter;
import com.codeutsava.jeevandeep.donationcampaignlist.presenter.DonationCampaignListPresenterImpl;
import com.codeutsava.jeevandeep.donationcampaignlist.provider.RetrofitDonationCampaignListProvider;
import com.codeutsava.jeevandeep.utils.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DonationCampaignListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DonationCampaignListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonationCampaignListFragment extends Fragment implements DonationCampaignListView {
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
    DonationCampaignListPresenter donationCampaignListPresenter;
    private OnFragmentInteractionListener mListener;

    DonationCampaignListRecyclerViewAdapter networkListRecyclerViewAdapter;



    public DonationCampaignListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonationCampaignListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonationCampaignListFragment newInstance(String param1, String param2) {
        DonationCampaignListFragment fragment = new DonationCampaignListFragment();
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
        View view = inflater.inflate(R.layout.fragment_donor_campaign_list, container, false);

        context = getContext();
        ButterKnife.bind(this, view);
        sharedPrefs = new SharedPrefs(getContext());
        donationCampaignListPresenter = new DonationCampaignListPresenterImpl(this, new RetrofitDonationCampaignListProvider());
        donationCampaignListPresenter.getFeedList(sharedPrefs.getAccessToken());
        // Set the adapter
        networkListRecyclerViewAdapter = new DonationCampaignListRecyclerViewAdapter(context, this);
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
    public void setFeedList(DonationCampaignListResponse feedListResponse) {
        networkListRecyclerViewAdapter.setData(feedListResponse.getNews_feed_list());
        networkListRecyclerViewAdapter.notifyDataSetChanged();
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
