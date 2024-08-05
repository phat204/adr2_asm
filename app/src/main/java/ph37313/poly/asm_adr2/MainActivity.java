package ph37313.poly.asm_adr2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;  // Ensure using androidx.appcompat.widget.Toolbar
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerhh_layout);
        navigationView = findViewById(R.id.navition);
        toolbar = findViewById(R.id.toolbarr);  // Ensure this matches your layout

        setSupportActionBar(toolbar);  // This method requires androidx.appcompat.widget.Toolbar

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_hoat_dong) {
                    Toast.makeText(MainActivity.this, "Hoạt động", Toast.LENGTH_SHORT).show();
                    replaceFragment(new FragmentHoatDong());
                } else if (item.getItemId() == R.id.nav_profile) {
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    replaceFragment(new FragmentThongTinCN());
                } else if (item.getItemId() == R.id.nav_van_dong) {
                    Toast.makeText(MainActivity.this, "Vận động", Toast.LENGTH_SHORT).show();
                    replaceFragment(new FragmentVanDong());
                } else if (item.getItemId() == R.id.nav_doiMK) {
                    Toast.makeText(MainActivity.this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_exit) {
                    Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Log_in.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Load default fragment
        if (savedInstanceState == null) {
            replaceFragment(new FragmentHoatDong());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
