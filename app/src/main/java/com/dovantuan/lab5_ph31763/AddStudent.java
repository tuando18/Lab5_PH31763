package com.dovantuan.lab5_ph31763;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    ArrayList<School> arrSchool = new ArrayList<>();

    SchoolSpinnerAdapter adapterSchool;

//    String selectedItemSpinner = "";

    public static final String KEY_COSO = "coso";
    public static final String KEY_TEN_SV = "ten";
    public static final String KEY_DIA_CHI = "diachi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        EditText edtname = findViewById(R.id.edt_username);
        EditText edtaddress = findViewById(R.id.edt_address);

        Spinner spSchool = findViewById(R.id.sp_ngonngu);

        arrSchool.add(new School("FPoly Hà Nội", R.drawable.ic_flag_fb));
        arrSchool.add(new School("FPoly Hồ Chí Minh", R.drawable.ic_flag_ig));
        arrSchool.add(new School("FPoly Đà Nẵng", R.drawable.ic_flag_tele));
        arrSchool.add(new School("FPoly Tây Nguyên", R.drawable.ic_flag_tiktok));
        arrSchool.add(new School("FPoly Cần Thơ", R.drawable.ic_flag_tw));

        ListDssv svModel = (ListDssv) getIntent().getSerializableExtra(Activity_DSSV.KEY_SV_MODEL);

        adapterSchool = new SchoolSpinnerAdapter(this, arrSchool);

        spSchool.setAdapter(adapterSchool);

        // Xu li su kien khi nguoi dung chon spinner
//        spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selectedItemSpinner = ((School) spSchool.getItemAtPosition(i)).getName();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // Xử lý khi không có phần tử nào được chọn (nếu cần)
//            }
//        });

        if (svModel != null) { // sua sinh vien
            edtname.setText(svModel.name);
            edtaddress.setText(svModel.address);

            int position = arrSchool.indexOf(svModel.branch);
            spSchool.setSelection(position);
        }

        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtname.getText().toString();
                String address = edtaddress.getText().toString();

                int index= spSchool.getSelectedItemPosition();
                String branch = arrSchool.get(index).name;

                if (name.trim().equals("")) {
                    Toast.makeText(AddStudent.this, "Tên SV không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (address.trim().equals("")) {
                    Toast.makeText(AddStudent.this, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();

                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_TEN_SV, name);
                    bundle.putString(KEY_DIA_CHI, address);
                    bundle.putString(KEY_COSO, branch);

                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

    }

    private class SchoolSpinnerAdapter extends BaseAdapter {

        Context context;
        ArrayList<School> list;

        public SchoolSpinnerAdapter(Context context, ArrayList<School> list) {
            this.context = context;
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

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_fpt, viewGroup, false);

            School schoolModel = list.get(i);
            ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
            TextView tvName = view.findViewById(R.id.txtName);

            ivAvatar.setImageResource(schoolModel.getIconId());
            tvName.setText(schoolModel.getName());

            return view;
        }
    }

}
