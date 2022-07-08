package com.jxufe.self.ui.dashboard;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.ListFragment;
import com.jxufe.self.R;
import com.jxufe.self.Task;
import com.jxufe.self.databinding.FragmentDashboardBinding;
import com.loper7.date_time_picker.dialog.CardDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.*;


public class DashboardFragment extends ListFragment {

    //private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private List<Task> taskList=new ArrayList<Task>();
    private SimpleAdapter adapter;
    //private ListView taskListview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Task task_first=new Task("欢迎使用",Calendar.getInstance(),"0");
        taskList.add(task_first);
        adapter = new SimpleAdapter(getActivity(), getData(taskList),
                R.layout.task_item, new String[] { "name", "time","tomato" },
                new int[] { R.id.task_name, R.id.task_time,R.id.task_long });
        //继承了ListFragment后的方法
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //taskListview=getView().findViewById(android.R.id.list);


        //SimpleDateFormat SDF=new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Calendar zero = Calendar.getInstance();
        zero.set(zero.get(Calendar.YEAR), zero.get(Calendar.MONTH), zero.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CardDatePickerDialog.Builder(getActivity())
                        .setTitle("待办时间")
                        .setOnChoose("确定", aLong -> {
                            Calendar temp = Calendar.getInstance();
                            temp.setTimeInMillis(aLong);
                            Task task = new Task();
                            task.setDeadline(temp);
                            //////////
                            final EditText inputServer = new EditText(getActivity());
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("任务名称").setView(inputServer)
                                    .setNegativeButton("Cancel", null);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    task.setName(inputServer.getText().toString());
                                    //////////
                                    new CardDatePickerDialog.Builder(getActivity())
                                            .setTitle("专注时间")
                                            .setOnChoose("确定", tLong -> {
                                                Log.d("Astral", tLong.toString());
                                                //task.setTomatoTime(tLong);//考虑改用选择器
                                                Calendar temp1 = Calendar.getInstance();
                                                temp1.setTimeInMillis(tLong);
                                                int hour=temp1.get(Calendar.HOUR_OF_DAY);
                                                int minute=temp1.get(Calendar.MINUTE);
                                                task.setTomatoTime(hour+"时"+minute+"分");
                                                Calendar temp2=Calendar.getInstance();
                                                temp2.setTimeInMillis(aLong);
                                                temp2.add(Calendar.HOUR_OF_DAY,hour);
                                                temp2.add(Calendar.MINUTE,minute);
                                                task.setFinishTime(temp2);
                                                //Log.d("Astral", SDF.format(task.getFinishTime().getTime()));
                                                taskList.add(task);
                                                adapter = new SimpleAdapter(getActivity(), getData(taskList),
                                                        R.layout.task_item, new String[] { "name", "time","tomato" },
                                                        new int[] { R.id.task_name, R.id.task_time,R.id.task_long });
                                                //继承了ListFragment后的方法
                                                setListAdapter(adapter);
                                                //taskListview.setSelection(taskList.size());
                                            return null;})
                                            .showFocusDateInfo(false)
                                            .setDefaultTime(zero.getTimeInMillis())
                                            .setPickerLayout(R.layout.tomato_picker)
                                            .showBackNow(false)
                                            .setWrapSelectorWheel(false)
                                            .setThemeColor(Color.parseColor("#FE8A80"))
                                            .build().show();
                                }
                            });
                            builder.show();
                            return null;
                        })
                        .setMinTime(Calendar.getInstance().getTimeInMillis())
                        .showBackNow(false)
                        .setWrapSelectorWheel(false)
                        .setThemeColor(Color.parseColor("#FE8A80"))
                        .build().show();
            }
        });
        return root;
    }
    private List<? extends Map<String, ?>> getData(List<Task> taskList) {
        SimpleDateFormat SDF=new SimpleDateFormat("MM月dd日 HH:mm");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < taskList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", taskList.get(i).getName());
            map.put("time", "开始时间："+SDF.format(taskList.get(i).getDeadline().getTime()));
            map.put("tomato","Tomato："+taskList.get(i).getTomatoTime());
            list.add(map);
        }
        return list;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}