package com.khair.onboding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private OnboardingAdapter onboardingAdapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        onboardingAdapter = new OnboardingAdapter(this);
        viewPager.setAdapter(onboardingAdapter);

        // TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Handle tab selection
        }).attach();

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < onboardingAdapter.getItemCount() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                // Finish onboarding
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("Onboarding", MODE_PRIVATE);
        boolean hasCompletedOnboarding = sharedPreferences.getBoolean("Completed", false);

        if (hasCompletedOnboarding) {
            // Skip onboarding
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        } else {
            // Show onboarding
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Completed", true);
            editor.apply();
        }

    }
}