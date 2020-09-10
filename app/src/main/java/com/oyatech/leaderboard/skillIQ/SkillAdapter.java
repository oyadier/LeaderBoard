package com.oyatech.leaderboard.skillIQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oyatech.leaderboard.R;
import com.oyatech.leaderboard.leaners.LeaderDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolderSkill> {
    //Creating a list to store leader objects
    List<LeaderDetails> mLeaderDetails;
    public Context mContext;
    String skill_image_Url = "https://res.cloudinary.com/mikeattara/image/upload/v1596700835/skill-IQ-trimmed.png";

    //A Constructor that accept a leader object
    public SkillAdapter(List<LeaderDetails> pLeaderDetails) {
        mLeaderDetails = pLeaderDetails;
    }


    @NonNull
    @Override
    public SkillAdapter.ViewHolderSkill onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        //Inflating the layout in which the recycle will use to populate the leader objects
        ViewGroup group = (ViewGroup) LayoutInflater.from(mContext)
                .inflate(R.layout.leaders, parent, false);
        return new ViewHolderSkill(group);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSkill holder, int position) {
        ///getting the objects at each position from the leaderDetail list
        final LeaderDetails leaderDetails = mLeaderDetails.get(position);
        //Binding the objects to the widgets
        holder.name.setText(leaderDetails.getName());
        holder.score.setText(leaderDetails.getPerformance().concat(" Skill IQ Score, "));
        holder.country.setText(leaderDetails.getCountry());
        Picasso.with(mContext).load(skill_image_Url).into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return mLeaderDetails.size();
    }

    public class ViewHolderSkill extends RecyclerView.ViewHolder {
        TextView name, score, country;
        ImageView badge;

        public ViewHolderSkill(@NonNull View itemView) {
            super(itemView);
            //Getting references to the view in the layouts
            name = itemView.findViewById(R.id.tvName);
            score = itemView.findViewById(R.id.tvScore);
            country = itemView.findViewById(R.id.tvCountry);
            badge = itemView.findViewById(R.id.imageLeader);
        }
    }
}
