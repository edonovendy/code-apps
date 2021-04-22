package code.id.apps.news.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import code.id.apps.R;
import code.id.apps.news.model.ModelSource;
import code.id.apps.news.utils.general.AppData;
import code.id.apps.news.view.activity.ActivityHome;
import code.id.apps.news.view.fragment.FragmentNews;


/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class AdapterNewsSource extends RecyclerView.Adapter<AdapterNewsSource.MyViewHolder>  {
    List<ModelSource> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private int selected_position = 0;


    public AdapterNewsSource(Context context, List<ModelSource> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_source, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelSource source = data.get(position);
        if(position == selected_position){
            holder.btnSource.setBackgroundResource(R.drawable.bg_rounded_primary);
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        else
        {
            holder.btnSource.setBackgroundResource(R.drawable.bg_rounded_grey);
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }

        holder.name.setText(source.getName());
        holder.btnSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNews fragmentNews = new FragmentNews();
                fragmentNews.filter(context, source.getName());

                if (position != selected_position){
                    selected_position = position;
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout btnSource;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            btnSource = itemView.findViewById(R.id.btn_source);
        }
    }
}
