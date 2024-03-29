package com.example.unknownexplorer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unknownexplorer.R;
import com.example.unknownexplorer.models.Point;
import com.example.unknownexplorer.fragments.MyRoutesFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PointsOfRouteAdapter extends RecyclerView.Adapter<PointsOfRouteAdapter.PointsViewHolder> {


    private List<Point> pointList = new ArrayList<>();

    OnPointClickListener onPointClickListener;
    MyRoutesFragment context;

    public interface OnPointClickListener{
        void onDeletePoint(Point point);
    }

    public PointsOfRouteAdapter(OnPointClickListener onPointClickListener, Context _contex) {
        this.onPointClickListener = onPointClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public PointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("test","onCreateViewHolder from PointsAdapter");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_points_of_my_route, parent, false);
        return new PointsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PointsViewHolder holder, int position) {
        Log.d("test","onBindViewHolder from PointsAdapter");
        holder.bind(pointList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("test","getItemCount() from PointsAdapter");
        return pointList.size();
    }


    public void setItems(Collection<Point> points) {
        Log.d("test","setItems from PointsAdapter");
        clearItems();
        pointList.addAll(points);
        notifyDataSetChanged();
    }

    public void clearItems() {
        pointList.clear();
        notifyDataSetChanged();
    }


    class PointsViewHolder extends RecyclerView.ViewHolder {

        private TextView pointTitle;
        private TextView xCoord;
        private TextView yCoord;


        public PointsViewHolder(View itemView){
            super(itemView);
            pointTitle = itemView.findViewById(R.id.name_point);
            xCoord = itemView.findViewById(R.id.x_coord_point);
            yCoord = itemView.findViewById(R.id.y_coord_point);
            Button deleteButton = itemView.findViewById(R.id.delete_button_point);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Point point = pointList.get(getLayoutPosition());
                    onPointClickListener.onDeletePoint(point);

                }
            });

        }

        public void bind(Point point) {
            pointTitle.setText(point.getName());
            xCoord.setText(point.getXCoord());
            yCoord.setText(point.getYCoord());
        }
    }
}
