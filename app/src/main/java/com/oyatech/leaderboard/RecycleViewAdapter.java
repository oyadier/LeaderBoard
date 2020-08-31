package com.oyatech.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.util.List;

import static com.oyatech.leaderboard.SkilliQFragment.isSkill;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.LeadersViewHolder> {
List<LeaderDetails>leaders ;

    public RecycleViewAdapter(List<LeaderDetails> pLeaders) {
        leaders = pLeaders;
    }

    @NonNull
    @Override
    public LeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context)
                .inflate(R.layout.leaders,parent,false);
        return new LeadersViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadersViewHolder holder, int position) {

        LeaderDetails details = leaders.get(position);
        holder.leaderName.setText(details.getName());
        holder.leaderPerformance.setText(details.getPerformance().concat(" Learner Hours,"));
        holder.leaderCountry.setText(details.getCountry().concat("."));

    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class LeadersViewHolder extends RecyclerView.ViewHolder{

        TextView leaderName, leaderPerformance,leaderCountry;

        public LeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            leaderName = itemView.findViewById(R.id.tvName);
            leaderPerformance = itemView.findViewById(R.id.tvScore);
            leaderCountry = itemView.findViewById(R.id.tvCountry);
        }


    }
}
