package schedule.curefit.com.curefitscheduleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import schedule.curefit.com.curefitscheduleapp.Adapters.DaysRecyclerViewAdapter;
import schedule.curefit.com.curefitscheduleapp.Constants.Constants;
import schedule.curefit.com.curefitscheduleapp.models.DaySchedule;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dayScheduleRecyclerView;
    private ArrayList<DaySchedule> dayScheduleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        dayScheduleRecyclerView = (RecyclerView) findViewById(R.id.day_schedule_recycler_view);
        dayScheduleRecyclerView.setLayoutManager(linearLayoutManager);
        dayScheduleRecyclerView.setAdapter(new DaysRecyclerViewAdapter(this,dayScheduleArrayList));

    }

    private void pushData() {

        dayScheduleArrayList = new ArrayList<DaySchedule>();
        dayScheduleArrayList.add(new DaySchedule(Constants.SECTION_HEADER,"30 Apr","Today",true));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"Meditation","Breathing",true));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"Dance","Salsa",true));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"56 steps taken","Target:1000",true));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"56 steps taken","Target:1000",true));

        dayScheduleArrayList.add(new DaySchedule(Constants.SECTION_HEADER,"29 Apr","Yesterday",false));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"S & C","Cult HSR",false));
        dayScheduleArrayList.add(new DaySchedule(Constants.SIMPLE_ITEM,"MMA","Kormangala",false));
    }

}
