package com.example.bmcollege.ui.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bmcollege.R;

import java.util.List;

public class BranchAdapter extends PagerAdapter {
    private Context context;
    private List<BranchModal> list;

    public BranchAdapter(Context context, List<BranchModal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item,container,false);
        ImageView bIcon;
        TextView bTitle,bDesc;
        bIcon = view.findViewById(R.id.branch_icon);
        bTitle = view.findViewById(R.id.branch_title);
        bDesc = view.findViewById(R.id.branch_desc);

        bIcon.setImageResource(list.get(position).getImg());
        bTitle.setText(list.get(position).getTitle());
        bDesc.setText(list.get(position).getDescription());

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
