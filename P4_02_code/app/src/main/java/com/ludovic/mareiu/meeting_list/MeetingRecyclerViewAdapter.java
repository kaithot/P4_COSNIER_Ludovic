package com.ludovic.mareiu.meeting_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ludovic.mareiu.R;
import com.ludovic.mareiu.events.DeleteMeetingEvent;
import com.ludovic.mareiu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private List<Meeting> mMeetings;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        this.mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.fragment_meeting,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);
        //TODO INSERER LES CERCLES
        holder.mMeetingTopicSchedulePlace.setText(meeting.getTopic()+" - "+meeting.getStart()+" H - "+meeting.getPlace());
        //holder.mMeetingSchedule.setText(meeting.getStart()+" H");
        holder.mMeetingParticipant.setText(meeting.getParticipant());

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.findViewById(R.id.item_list_delete_button).setVisibility(View.INVISIBLE);
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));// TODO REVOIR LE PROBLEME DE SUPP.
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mAlert;
        TextView mMeetingTopicSchedulePlace;
        TextView mMeetingParticipant;
        ImageButton mDeleteButton;



        ViewHolder(View itemView) {
            super(itemView);
            mAlert = ((ImageView) itemView.findViewById(R.id.imageView_item_alert));
            mMeetingTopicSchedulePlace = ((TextView) itemView.findViewById(R.id.item_main));
            mMeetingParticipant = ((TextView) itemView.findViewById(R.id.item_list_participant));
            mDeleteButton = ((ImageButton) itemView.findViewById(R.id.item_list_delete_button));


        }
    }
}