package com.example.btlandroid_covid19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 200;
    Button buttonVietNam, buttonGlobal, buttonCall, buttonNews, buttonFeedback, buttonViewSavedNews,buttonActivityLogin,buttonLogout;
    FloatingActionButton floatingActionButton;
    private FirebaseAuth mAuth;
    TextView txtTinDaLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cập nhật số liệu Covid-19");
        mAuth = FirebaseAuth.getInstance();
        buttonCall = findViewById(R.id.buttonGoiKhanCap);
        //Sự kiện gọi khẩn cấp tới số máy khẩn cấp chống Covid-19 của Bệnh viện Nhiệt đới Trung Ương
        buttonCall.setOnClickListener(v->{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE);
            callIntent.setData(Uri.parse("tel:" + "19003228"));
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,"Vui lòng cho phép ứng dụng quyền gọi điện!",Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(callIntent);
        });
        txtTinDaLuu = findViewById(R.id.textViewTinDaLuu);
        buttonViewSavedNews = findViewById(R.id.buttonXemTinDaLuu);
        buttonVietNam = findViewById(R.id.btnVietNam);
        buttonGlobal = findViewById(R.id.buttonGlobal);
        buttonNews = findViewById(R.id.buttonTintuc);
        buttonFeedback = findViewById(R.id.buttonPhanHoi);
        buttonActivityLogin = findViewById(R.id.buttonLoginActivity);
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonVietNam.setOnClickListener(v ->{
            Intent myIntent=new Intent(MainActivity.this,VietNamActivity.class);
            startActivity(myIntent);
        });
        buttonNews.setOnClickListener(v->{
            Intent myIntent = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(myIntent);
        });
        buttonGlobal.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, GlobalActivity.class);
            startActivity(i);
        });
        buttonViewSavedNews.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SavedNewsActivity.class);
            startActivity(intent);
        });
        buttonFeedback.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this, FeedBackActivity.class);
            startActivity(intent);
        });
        buttonActivityLogin.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, LoginFirebaseActivity.class);
            startActivity(intent);
        });
        buttonLogout.setOnClickListener(v->{
            mAuth.signOut();
            Toast.makeText(MainActivity.this,"Đăng xuất tài khoản thành công!",Toast.LENGTH_SHORT).show();
            buttonLogout.setVisibility(View.INVISIBLE);
            buttonViewSavedNews.setVisibility(View.INVISIBLE);
            txtTinDaLuu.setVisibility(View.INVISIBLE);
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            buttonLogout.setVisibility(View.INVISIBLE);
            buttonViewSavedNews.setVisibility(View.INVISIBLE);
            txtTinDaLuu.setVisibility(View.INVISIBLE);
        }
    }
}
