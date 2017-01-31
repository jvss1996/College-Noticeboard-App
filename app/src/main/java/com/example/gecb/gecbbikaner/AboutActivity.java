package com.example.gecb.gecbbikaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Govt. Engineering College Bikaner, established in 1999. The college is a renowned institution in the field of Engineering, Management and Polytechnic education. 4000 students are presently studying in the college. College possesses 337 bighas of land on which construction work has been accomplished on 3 lac square feet. College has a budget of Rs 19 crores. Net worth of college building is 1400 lacs out of which, state govt. has provided 300 lacs. 4 colleges viz. Govt. Enginnering College Bikaner, Govt. College of Engineering & Technology, Govt. ECB Polytechnic and Govt. ECB ITI are running under Engineering College Bikaner society. The college runs the courses like BE, MBA, MCA, M. Sc. (IT), BBA, BCA, Diploma and ITI." +
                "Result oriented committed 200 Faculty Members, 100% results, Excellent Placement, Centre for Ethics and Indian Management, World class Ceramic Centre, magnificent computer laboratories, gymnasium, cafeteria etc are salient features of this college.\n" +
                "\n" +
                "Faculty members of this esteemed institution are providing consultancy services to business houses. For overall academic development of faculty members, college has conducted short term courses on bio fuel, communication skills, mechatronics, entrepreneurship etc. College has conducted 10 national seminars. Mechanical Engineering Dept. and Rajasthan Mission on Livelihood have conducted special courses for unemployed youth." +
                "The college has planned to get itself accredited by National Board of Accreditation, AICTE New Delhi. Engineering College Bikaner is going to start a new world class Petroleum Institute in 2009. After beginning this institution, Engineering College Bikaner will become a special institution to run 12 traits of engineering at one place.\n" +
                "\n" +
                "The placement of this institution is excellent. The college is accredited by Tata Consultancy Services. The campus interviews are conducted by blue chip companies like IBM, NBC, Kirloskar, IDBI, Simplex Infrastructure etc. The students are placed in companies like Mahindra, ICICI, HDFC, US Technologies, Aviva life insurance, Z drive, Wipro, Genus, Birla cement, Ashok Leylend etc. Campus connect programmes, Self Development coyrses, Online aptitude tests are regularly conducted for the students.");
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
