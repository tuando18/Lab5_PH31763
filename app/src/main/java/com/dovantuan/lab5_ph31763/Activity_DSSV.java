package com.dovantuan.lab5_ph31763;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_DSSV extends AppCompatActivity {

    ArrayList<ListDssv> listSv;
    AdapterDSSV adapterSv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssv);

        ActivityResultLauncher<Intent> getData = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            Intent intent = result.getData();
                            Bundle bundle = intent.getExtras();
                            String name = bundle.getString("name");
                            String address = bundle.getString("address");
                            String branch = bundle.getString("branch");
                            listSv.add(new ListDssv(branch, name, address));
                            adapterSv.notifyDataSetChanged();
                        }
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            Intent intent = result.getData();
//                            Bundle bundle = intent.getExtras();
//                            String name = bundle.getString("name");
//                            String address = bundle.getString("address");
//                            String branch = bundle.getString("branch");
//                            int position = bundle.getInt("position", -1);
//                            if (position != -1) {
//                                ListDssv updatedStudent = listSv.get(position);
//                                updatedStudent.setBranch(branch);
//                                updatedStudent.setName(name);
//                                updatedStudent.setAddress(address);
//                                adapterSv.notifyDataSetChanged();
//                            }
//                        }
                    }
                });

        ListView lvdssv = findViewById(R.id.lv_sv);

        listSv = new ArrayList<>();

        // Khởi tạo dữ liệu danh sách
        listSv.add(new ListDssv("FPoly Hà Nội", "Nguyễn Văn Tuấn", "Bắc Ninh"));
        listSv.add(new ListDssv("FPoly Hồ Chí Minh", "Đỗ Văn Tuấn", "Tây Ninh"));
        listSv.add(new ListDssv("FPoly Đà Nẵng", "Nguyễn Công Thưởng", "Nha Trang"));
        listSv.add(new ListDssv("FPoly Tây Nguyên", "Nguyễn Vinh Tài", "Đắk Lắk"));
        listSv.add(new ListDssv("FPoly Cần Thơ", "Cấn Gia Khiêm", "Kiên Giang"));

        adapterSv = new AdapterDSSV(this, listSv);

        lvdssv.setAdapter(adapterSv);

        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_DSSV.this, AddStudent.class);
                getData.launch(intent);
            }
        });
    }

    private class AdapterDSSV extends BaseAdapter {

        Activity activity;
        ArrayList<ListDssv> list;

        public AdapterDSSV(Activity activity, ArrayList<ListDssv> list) {
            this.activity = activity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_listview, viewGroup, false);

            ListDssv listsv = list.get(i);

            TextView tvBranch = view.findViewById(R.id.tvBranch);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvAddress = view.findViewById(R.id.tvAddress);

            tvBranch.setText(listsv.getBranch());
            tvName.setText(listsv.getName());
            tvAddress.setText(listsv.getAddress());

            Button btnRemove = view.findViewById(R.id.btn_remove);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listSv.remove(i);
                    adapterSv.notifyDataSetChanged();
                    Toast.makeText(activity, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                }
            });

            Button btnUpdate = view.findViewById(R.id.btn_Update);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(activity, UpdateStudent.class);
//                    intent.putExtra("name", listsv.getName());
//                    intent.putExtra("address", listsv.getAddress());
//                    intent.putExtra("branch", listsv.getBranch());
//                    intent.putExtra("position", i);
//                    getData.launch(intent);
                }
            });

            return view;
        }
    }

}