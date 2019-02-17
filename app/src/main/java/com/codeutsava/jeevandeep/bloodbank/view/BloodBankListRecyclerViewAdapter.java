package com.codeutsava.jeevandeep.bloodbank.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.bloodbank.data.BloodBankItem;
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
public class BloodBankListRecyclerViewAdapter extends RecyclerView.Adapter<BloodBankListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private BloodBankListFragment networkListFragment;
    private List<BloodBankItem> networkItems = new ArrayList<>();
    private ImageLoader imageLoader;

//    ArrayList<Location> locations;
//    Location location1;
//    Location location2;
//    Location location3;
//    Location location4;
//    Location location5;


    public BloodBankListRecyclerViewAdapter(Context context, BloodBankListFragment networkListFragment) {
        this.context = context;
        this.networkListFragment = networkListFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bloodbank, parent, false);
//
//        location1 = new Location("City blood bank", "21.2409° N", "81.6207° E");
//        location2 = new Location("Chattisgarh Blood Bank Raipur", "21.2440° N", "81.6119° E");
//        location3 = new Location("Rajdhani Blood Bank Raipur", "21.2450° N", "81.6301° E");
//        location4 = new Location("Red Cross Blood Bank", "21.2514° N", "81.6389° E");
//        location5 = new Location("Shrishti Blood Bank", "21.2462° N", "81.6309° E");

        return new ViewHolder(view);
    }

    void setData(List<BloodBankItem> networkItems) {
        this.networkItems = networkItems;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = networkItems.get(position);
        imageLoader = new GlideImageLoader(context);
//        imageLoader.loadImage(networkItems.get(position).getImage(), holder.mImageView, holder.mProgressBar);
        imageLoader.load_circular_image(networkItems.get(position).getImage(), holder.mImageView);

        holder.mDonorNameView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getName()));
        holder.mDonorNumberView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getPhoneNumber()));
        holder.mDonorLocationView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getLocation()));
        holder.mDonorLocationView.setText(StringToTitleCase.toTitleCase(networkItems.get(position).getLocation()));


//        holder.mEmailView.setText(networkItems.get(position).getEmail());
//        holder.mCompanyNameView.setText(networkItems.get(position).getCompany_name());
//        holder.mNameView.setText(networkItems.get(position).getProfile_image());
        holder.mView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, ProfileActivity.class);
//            intent.putExtra("user_table_id", networkItems.get(position).getUser_table_id());
//            context.startActivity(intent);
        });

        holder.mBloodBankNumberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkListFragment.makecall(networkItems.get(position).getPhoneNumber());
            }
        });

        holder.mBloodBankAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude;
                String longitude;
                String name;

//                for(int i = 1; i<=5; i++){
//                    Math.random(5);
//                }
//
//                int i=1;
//                String locationss = "location"+i;
//                ArrayList<Location> arr = {location1};

                Uri mapUri = Uri.parse("geo:0,0?q="+networkItems.get(position).getLatitude()+","+networkItems.get(position).getLongitude()+"(" + networkItems.get(position).getName() + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
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
        public final TextView mDonorNumberView;
        public final TextView mDonorLocationView;
        public final LinearLayout mBloodBankAddressLayout;
        public final LinearLayout mBloodBankNumberLayout;
        public final LinearLayout mBloodBankLocationLayout;


        public BloodBankItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDonorNameView = view.findViewById(R.id.bankname);
            mDonorNumberView = view.findViewById(R.id.banknumber);
            mDonorLocationView = view.findViewById(R.id.bankadress);
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
