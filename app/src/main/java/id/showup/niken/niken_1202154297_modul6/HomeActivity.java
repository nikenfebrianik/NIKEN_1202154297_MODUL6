package id.showup.niken.niken_1202154297_modul6;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    //deklarasi variable
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //referencing
        //wadah dari fragment - content fragment
        viewPager = findViewById(R.id.vpFrag);
        setupViewPager(viewPager);

        //tab menu bawah actionbar
        tabLayout = (TabLayout) findViewById(R.id.tabMenu);
        tabLayout.setupWithViewPager(viewPager);
    }

    //Menu titik 3
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //kondisi untuk logout
        switch (item.getItemId()) {
            case R.id.logout:
                logoutFirebase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutFirebase() {
        //logout session dari firebase
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupViewPager(ViewPager viewPager) {
        //memanggil dan nambah adapter per fragment
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "TERBARU");
        adapter.addFragment(new TwoFragment(), "FOTO SAYA");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        //yang ngatur dan nyusun fragment
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        //nunjukin posisi fragment mana yg dipilih
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        //ngitung jumlah fragment yg dibuat
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        //bikin isi fragment mau muat apa aja
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
