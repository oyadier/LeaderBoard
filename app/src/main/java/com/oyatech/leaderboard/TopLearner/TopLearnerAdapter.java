package com.oyatech.leaderboard.TopLearner;

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



public class TopLearnerAdapter extends RecyclerView.Adapter<TopLearnerAdapter.LeadersViewHolder> {
List<LeaderDetails>leaders ;
    Context context;
    String learner_badge = "https://res.cloudinary.com/mikeattara/image/upload/v1596700848/Top-learner.png";
    public TopLearnerAdapter(List<LeaderDetails> pLeaders) {
        leaders = pLeaders;
    }

    @NonNull
    @Override
    public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      context   = parent.getContext();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context)
                .inflate(R.layout.leaders,parent,false);
        return new LeadersViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersViewHolder holder, int position) {

        final     LeaderDetails details = leaders.get(position);
        holder.leaderName.setText(details.getName());
        holder.leaderPerformance.setText(details.getPerformance().concat(" learning hours,"));
        holder.leaderCountry.setText(details.getCountry());
        Picasso.with(holder.leaderName.getContext()).load(learner_badge).into(holder.leaderImage);

    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class LeadersViewHolder extends RecyclerView.ViewHolder{

        TextView leaderName, leaderPerformance,leaderCountry;
        ImageView leaderImage;

        public LeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            leaderName = itemView.findViewById(R.id.tvName);
            leaderPerformance = itemView.findViewById(R.id.tvScore);
            leaderCountry = itemView.findViewById(R.id.tvCountry);
            leaderImage = itemView.findViewById(R.id.imageLeader);
        }


    }
}
