package com.codeutsava.jeevandeep.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.RequestForBlood.view.RequestForBloodFragment;
import com.codeutsava.jeevandeep.bloodbank.view.BloodBankListFragment;
import com.codeutsava.jeevandeep.donorlist.view.DonorListFragment;
import com.codeutsava.jeevandeep.utils.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DonorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DonorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.donorlist)
    CardView donorlist;

    @BindView(R.id.bloodbanklist)
    CardView bloodbanklist;

    @BindView(R.id.requestforblood)
    CardView requestforblood;

    Context context;

    private OnFragmentInteractionListener mListener;

    public DonorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonorFragment newInstance(String param1, String param2) {
        DonorFragment fragment = new DonorFragment();
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

        View view= inflater.inflate(R.layout.fragment_donor, container, false);

        context = getContext();
        ButterKnife.bind(this,view);

        final SharedPrefs sharedPrefs= new SharedPrefs(context);

        donorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).setFragment(new DonorListFragment(),"");
            }
        });

        bloodbanklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).setFragment(new BloodBankListFragment(),"");
            }
        });

        requestforblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).setFragment(new RequestForBloodFragment(),"");
            }
        });


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
