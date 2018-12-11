package com.brijesh.testapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.brijesh.testapp.R;
import com.brijesh.testapp.utils.NetworkCheck;

import butterknife.ButterKnife;

/**
 * Created by ${Brijesh.Bhatt} on 10/12/18.
 */

public class ListActivity extends AppCompatActivity
{
    private static final String TAG = ListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /*intialize network class*/
        NetworkCheck.initNetwork(getApplicationContext());

        /*Bind view this activity to butterknife*/
        ButterKnife.bind(this);

        initFragment();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    /*Attach Fragment to Activity*/
    private void initFragment()
    {
        Fragment replacableFragment = new ListActivityFragment();
        replaceContentFragment(getSupportFragmentManager(), replacableFragment, true, R.id.fragmentContainer, 0, 0, 0, 0, false);
    }

    /**
     * Replaces a fragment on activity
     *
     * @param fragment       The fragment to show
     * @param containerId    Container ID in layout where fragment needs to be shown
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     * @param addToBackStack true if Back button should empty the fragment | false if otherwise
     */

    public void replaceContentFragment(FragmentManager fragmentManager, Fragment fragment, boolean toAdd, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {

        /*Return if fragment to be added BaseUIActivity.javais null*/
        if (fragment == null)
        {
            throw new IllegalArgumentException("Null fragment passed in " + TAG + "#replaceContentFragment");
        }
        /*getting the name of the fragment class*/
        String mFragmentName = ((Object) fragment).getClass().getSimpleName();

        /* replace with new content*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*Animation for above ICS*/
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        if (toAdd)
        {
            fragmentTransaction.add(containerId, fragment, mFragmentName);

        } else
        {
            fragmentTransaction.replace(containerId, fragment, mFragmentName);
        }

        if (addToBackStack)
        {
            fragmentTransaction.addToBackStack(mFragmentName);
        }

        fragmentTransaction.commit();
        Log.e("TAG", "Success");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
