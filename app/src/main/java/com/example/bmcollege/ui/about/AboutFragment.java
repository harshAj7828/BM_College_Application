package com.example.bmcollege.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bmcollege.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModal> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModal(R.drawable.ic_computer,"Computer Science","The Department of Computer Science was established in the year 2007. The department offers the degrees Bachelor of Technology (B.Tech) and Master of Technology (M.Tech). The Department of Computer Science offers instruction and performs research in the essential areas of computer science including software, Web and Internet computing, networking, hardware systems, operating systems, compilers, parallel and distributed computing, theory of computing, and computer graphics. The Department has always had close ties to mathematics and engineering. Computer science is dedicated to invention of new technologies in the broad telecommunication and networking areas"));
        list.add(new BranchModal(R.drawable.ic_settings,"Mechanical Engineering","The Mechanical Engineering Dept at BMCT, Indore is one of the largest in terms of faculty, infrastructure, students, and activities, continues to lead and expand its activities in various directions. The department has highly qualified faculty members." ));
        list.add(new BranchModal(R.drawable.ic_settings,"Electronics and Communication","Electronics and Communication Engineering is concerned with the design, development, manufacture and application of electronic devices, circuits and systems. Electronics and Communication Engineering lays greater emphasis on deep understanding of fundamental principles and state of the art knowledge about Electronic Devices and Circuits, Computer Architecture and Microprocessors, VLSI and Embedded systems, Electromagnetic Field Theory, Analog and Digital Communication, Digital Signal Processing, Microwave and Broadband Communications.\n" +
                "\n" +
                "The department has well-qualified and experienced faculty with a healthy student to staff ratio of 15:1. Faculty members have their credit in technical papers published in international and national journals and conferences. They are also members of several important academic bodies." ));
        list.add(new BranchModal(R.drawable.ic_settings,"Civil Engineering","Civil Engineering Department, BM College of Technology is one of the best departments in Central India. The department is well equipped with highly qualified faculties and state-of-art laboratories. Some of the specialties of the department are mentioned below:" ));
        adapter = new BranchAdapter(getContext(),list);

        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.college_img);
        Glide.with(getContext())
                .load("https://images.app.goo.gl/6TNHSaSNz2SJNGXg7")
                .into(imageView);

                return view;
    }
}