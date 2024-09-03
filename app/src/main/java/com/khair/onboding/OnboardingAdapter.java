package com.khair.onboding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnboardingAdapter extends FragmentStateAdapter {

    public OnboardingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OnboardingFragment1();
            case 1:
                return new OnboardingFragment1();
            // Add more fragments as needed
            default:
                return new OnboardingFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Number of onboarding screens
    }
}
