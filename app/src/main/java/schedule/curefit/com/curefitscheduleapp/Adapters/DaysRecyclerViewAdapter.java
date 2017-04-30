package schedule.curefit.com.curefitscheduleapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

import schedule.curefit.com.curefitscheduleapp.Constants.Constants;
import schedule.curefit.com.curefitscheduleapp.R;
import schedule.curefit.com.curefitscheduleapp.models.DaySchedule;
import schedule.curefit.com.curefitscheduleapp.utils.DrawableUtils;


public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final LayoutInflater inflater;
    private ArrayList<DaySchedule> dayScheduleList;
    private Context context;
    private final int SECTION_HEADER_TYPE = 0;
    private int lengthOfTodaysItems;
    private HashMap<Integer, GradientDrawable> positionToGradientHashMap;


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView headerTitleTextView;
        private TextView headerSubtitleTextView;

        public View getVerticalLineView() {
            return verticalLineView;
        }

        private View verticalLineView;
        HeaderViewHolder(View rowView) {
            super(rowView);
            headerTitleTextView = (TextView) rowView.findViewById(R.id.header_date_text_view);
            headerSubtitleTextView = (TextView) rowView.findViewById(R.id.header_date_status_text_view);
            verticalLineView = (View) rowView.findViewById(R.id.vertical_separator_view);
        }

        public TextView getHeaderTitleTextView() {
            return headerTitleTextView;
        }

        public TextView getHeaderSubtitleTextView() {
            return headerSubtitleTextView;
        }

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitleTextView;
        private TextView itemSubtitleTextView;

        public View getVerticalLineView() {
            return verticalLineView;
        }

        private View verticalLineView;
        ItemViewHolder(View rowView) {
            super(rowView);
            itemTitleTextView = (TextView) rowView.findViewById(R.id.item_title_text_view);
            itemSubtitleTextView = (TextView) rowView.findViewById(R.id.item_subtitle_text_view);
            verticalLineView = (View) rowView.findViewById(R.id.vertical_separator_view);

        }

        public TextView getItemTitleTextView() {
            return itemTitleTextView;
        }

        public TextView getItemSubtitleTextView() {
            return itemSubtitleTextView;
        }
    }

    public DaysRecyclerViewAdapter(Context context, ArrayList<DaySchedule> dayScheduleList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.dayScheduleList = dayScheduleList;
        this.lengthOfTodaysItems = getLengthOfTodaysItems();
        preComputeGradientsForDifferentPositions();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case SECTION_HEADER_TYPE:
                View headerViewHolder = inflater.inflate(R.layout.day_schedule_header, parent, false);
                viewHolder = new HeaderViewHolder(headerViewHolder);
                break;
            default:
                View itemViewHolder = inflater.inflate(R.layout.day_schedule_layout, parent, false);
                viewHolder = new ItemViewHolder(itemViewHolder);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DaySchedule daySchedule = dayScheduleList.get(position);
        switch (viewHolder.getItemViewType()) {
            case SECTION_HEADER_TYPE:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                headerViewHolder.getHeaderTitleTextView().setText(daySchedule.getTitle());
                headerViewHolder.getHeaderSubtitleTextView().setText(daySchedule.getSubTitle());
                if(daySchedule.isCurrentDayFeed()) {
                    headerViewHolder.getVerticalLineView().setBackgroundColor(context.getResources().getColor(R.color.purple));
                }
                break;
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                itemViewHolder.getItemTitleTextView().setText(daySchedule.getTitle());
                itemViewHolder.getItemSubtitleTextView().setText(daySchedule.getSubTitle());
                if(daySchedule.isCurrentDayFeed()) {
                    itemViewHolder.getVerticalLineView().setBackground(positionToGradientHashMap.get(position));
                    //itemViewHolder.getVerticalLineView().setBackground(DrawableUtils.getGradientDrawable(Color.RED,Color.YELLOW));
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dayScheduleList.get(position).getType().equals(Constants.SECTION_HEADER)) {
            return SECTION_HEADER_TYPE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return dayScheduleList.size();
    }

    private int getLengthOfTodaysItems() {
        int counter =0;
        for(int i = 0; i < dayScheduleList.size(); i++) {
            DaySchedule daySchedule = dayScheduleList.get(i);
            if( daySchedule.getType().equals(Constants.SIMPLE_ITEM) && daySchedule.isCurrentDayFeed()) {
                counter ++;
            }
        }
        return  counter;
    }

    private void preComputeGradientsForDifferentPositions() {

        int purpleColor = context.getColor(R.color.purple);
        int blueColor = context.getColor(R.color.blue);
        int greyColor = context.getColor(R.color.lightGrey);
        Log.d("lengthInt", String.valueOf(lengthOfTodaysItems));
        positionToGradientHashMap = new HashMap<Integer, GradientDrawable>();
        int purpleToBlueInterpolatorlength = lengthOfTodaysItems/2;
        int blueToGreyInterPolatorlength = purpleToBlueInterpolatorlength;

        if(lengthOfTodaysItems % 2 ==1) {
            purpleToBlueInterpolatorlength++;
        }

        int topColor = purpleColor;
        int bottomColor = blueColor;
        int topColorOfCell = purpleColor;
        int bottomColorOfCell;

        for(int i = 1; i <=purpleToBlueInterpolatorlength; i++ ) {
            float fraction = (float)i/purpleToBlueInterpolatorlength;
            Log.d("fractionUpper", String.valueOf(fraction));
            bottomColorOfCell = DrawableUtils.getInterpolatedColor(fraction,topColor,bottomColor);
            positionToGradientHashMap.put(i,DrawableUtils.getGradientDrawable(topColorOfCell,bottomColorOfCell));
            topColorOfCell = bottomColorOfCell;
            Log.d("intPcolorupper" + i, String.valueOf(topColorOfCell));
        }

        topColor = blueColor;
        bottomColor = greyColor;
        //topColorOfCell = Constants.BLUE_COLOR;
        int num = 1;
        for(int i = purpleToBlueInterpolatorlength +1 ; i <= lengthOfTodaysItems; i++ ) {
            float fraction = (float) num/blueToGreyInterPolatorlength;
            Log.d("fractionLower", String.valueOf(fraction));
            bottomColorOfCell = DrawableUtils.getInterpolatedColor(fraction,topColor,bottomColor);
            positionToGradientHashMap.put(i,DrawableUtils.getGradientDrawable(topColorOfCell,bottomColorOfCell));
            topColorOfCell = bottomColorOfCell;
            Log.d("intPcolor Lower" + i, String.valueOf(topColorOfCell));
            num++;
        }
    }

}
