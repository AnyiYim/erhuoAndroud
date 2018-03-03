package com.example.anyi.bomb;

import java.util.HashMap;

/**
 * Created by Anyi on 2018/2/6.
 */
public class FragmentFa {
    private static HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int pos) {
        BaseFragment baseFragment = mBaseFragments.get(pos);

        if (baseFragment == null) {
            switch (pos) {
                case 0:
                    baseFragment = new UsellFragment();//
                    break;
                case 1:
                    baseFragment = new UbuyFragment();//
                    //baseFragment = new buyFragment();//
                    break;


            }
            mBaseFragments.put(pos, baseFragment);
        }
        return baseFragment;
    }
}