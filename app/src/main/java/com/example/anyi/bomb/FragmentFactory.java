package com.example.anyi.bomb;

import java.util.HashMap;

/**
 * Created by Anyi on 2018/2/6.
 */
public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int pos) {
        BaseFragment baseFragment = mBaseFragments.get(pos);

        if (baseFragment == null) {
            switch (pos) {
                case 0:
                    baseFragment = new sellFragment();//
                    break;
                case 1:
                    baseFragment = new buyFragment();//
                    //baseFragment = new buyFragment();//
                    break;


            }
            mBaseFragments.put(pos, baseFragment);
        }
        return baseFragment;
    }
}