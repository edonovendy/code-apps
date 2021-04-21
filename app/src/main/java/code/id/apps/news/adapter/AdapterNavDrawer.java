package code.id.apps.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import code.id.apps.R;
import code.id.apps.news.model.ModelNavDrawer;


/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class AdapterNavDrawer extends RecyclerView.Adapter<AdapterNavDrawer.MyViewHolder>  {
    List<ModelNavDrawer> data = Collections.emptyList();
    private LayoutInflater inflater;


    public AdapterNavDrawer(Context context, List<ModelNavDrawer> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_drawer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelNavDrawer current = data.get(position);
        holder.title.setText(current.getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
