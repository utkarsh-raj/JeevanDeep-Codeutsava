package com.codeutsava.jeevandeep.donationcampaignlist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.donationcampaignlist.data.DonationCampaignItem;
import com.codeutsava.jeevandeep.donorlist.data.DonorItem;
import com.codeutsava.jeevandeep.donorlist.view.DonorListFragment;
import com.codeutsava.jeevandeep.utils.GlideImageLoader;
import com.codeutsava.jeevandeep.utils.ImageLoader;
import com.codeutsava.jeevandeep.utils.StringToTitleCase;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class DonationCampaignListRecyclerViewAdapter extends RecyclerView.Adapter<DonationCampaignListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private DonationCampaignListFragment donationCampaignListFragment;
    private List<DonationCampaignItem> networkItems = new ArrayList<>();
    private ImageLoader imageLoader;

    public DonationCampaignListRecyclerViewAdapter(Context context, DonationCampaignListFragment donationCampaignListFragment) {
        this.context = context;
        this.donationCampaignListFragment = donationCampaignListFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campaign, parent, false);
        return new ViewHolder(view);
    }

    void setData(List<DonationCampaignItem> networkItems) {
        this.networkItems = networkItems;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = networkItems.get(position);
        imageLoader = new GlideImageLoader(context);
//        imageLoader.loadImage(networkItems.get(position).getImage(), holder.mImageView, holder.mProgressBar);
        imageLoader.load_circular_image(networkItems.get(position).getImage(), holder.mImageView);

        holder.mDonorNameView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getName()));
//        holder.mDonorNumberView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).get));
        holder.mDonorLocationView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getLocation()));
        holder.mOrganizedByView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getOrganiser()));
        holder.mDescriptionView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getDescription()));


//        holder.mEmailView.setText(networkItems.get(position).getEmail());
//        holder.mCompanyNameView.setText(networkItems.get(position).getCompany_name());
//        holder.mNameView.setText(networkItems.get(position).getProfile_image());
        holder.mView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ProfileActivity.class);
//            intent.putExtra("user_table_id", networkItems.get(position).getUser_table_id());
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return networkItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final ProgressBar mProgressBar;
        public final TextView mDonorNameView;
//        public final TextView mDonorNumberView;
        public final TextView mDonorLocationView;
        public final TextView mDescriptionView;
        public final TextView mOrganizedByView;
        public final LinearLayout mBloodBankAddressLayout;
        public final LinearLayout mBloodBankNumberLayout;
        public final LinearLayout mBloodBankLocationLayout;


        public DonationCampaignItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDonorNameView = view.findViewById(R.id.bankname);
//            mDonorNumberView = view.findViewById(R.id.banknumber);
            mDonorLocationView = view.findViewById(R.id.bankadress);
            mDescriptionView = view.findViewById(R.id.description);
            mOrganizedByView = view.findViewById(R.id.organiser);
            mImageView = view.findViewById(R.id.bloodbank_image);
            mProgressBar = view.findViewById(R.id.progressBar);

            mBloodBankAddressLayout = view.findViewById(R.id.bloodbankaddresslayout);
            mBloodBankNumberLayout = view.findViewById(R.id.bloodbankcontactlayout);
            mBloodBankLocationLayout = view.findViewById(R.id.bloodbankdistancelayout);

        }



//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }


}
