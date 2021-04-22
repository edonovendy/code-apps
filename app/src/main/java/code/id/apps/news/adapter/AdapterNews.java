package code.id.apps.news.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import code.id.apps.R;
import code.id.apps.news.model.ModelNews;
import code.id.apps.news.view.activity.ActivityDetail;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
    public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

        private final Context context;
        private final List<ModelNews> list;

        public AdapterNews(Context context, List<ModelNews> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, null, false);
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            v.setLayoutParams(layoutParams);
            return new ViewHolder(v);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ModelNews news = list.get(position);
            Glide.with(context)
                    .load(news.getImgNews())
                    .placeholder(R.drawable.img_default)
                    .into(holder.image);
            holder.title.setText(news.getTitleNews());
            holder.date.setText(news.getSource().getName() + " pada "+news.getDateNews().substring(0, 10) + " " + news.getDateNews().substring(11, 16));
            if (news.getAuthorNews() == null || news.getAuthorNews().isEmpty()) {
                holder.author.setText("Penulis Tidak Diketahui");
            } else {
                holder.author.setText("Ditulis oleh "+news.getAuthorNews());
            }

            holder.cv.setOnClickListener(view -> {
                Intent i = new Intent(context, ActivityDetail.class);
                i.putExtra("imgNews", news.getImgNews());
                i.putExtra("titleNews", news.getTitleNews());
                if (news.getContentNews() == null) {
                    i.putExtra("contentNews", "No Description");
                } else {
                    i.putExtra("contentNews", news.getContentNews());
                }
                i.putExtra("dateNews", news.getDateNews().substring(0, 10) + " " + news.getDateNews().substring(11, 16));
                if (news.getAuthorNews() == null) {
                    i.putExtra("authorNews", "Unknown Author");
                } else {
                    i.putExtra("authorNews", news.getAuthorNews());
                }
                i.putExtra("sourceNews", news.getUrlNews());
                context.startActivity(i);
                ((Activity) context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final CardView cv;
            private final ImageView image;
            private final TextView title;
            private final TextView date;
            private final TextView author;

            public ViewHolder(View itemView) {
                super(itemView);
                cv = itemView.findViewById(R.id.cv);
                image = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.title);
                author = itemView.findViewById(R.id.author);
                date = itemView.findViewById(R.id.date);

            }
        }
}
