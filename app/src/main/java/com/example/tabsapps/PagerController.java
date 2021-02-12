package com.example.tabsapps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabsapps.Tabs.tabCero;
import com.example.tabsapps.Tabs.tabDos;
import com.example.tabsapps.Tabs.tabTres;
import com.example.tabsapps.Tabs.tabUno;

public class PagerController extends FragmentPagerAdapter {

    // Se crea esta variable para controlar el número de tabs
    int numoftabs;
    // Creamos el constructor de la clase
    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // aquí se determina cual fragment cargar
        switch (position){
            case 0:
                return new tabCero();
            case 1:
                return new tabUno();
            case 2:
                return new tabDos();
            case 3:
                return new tabTres();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Este método retorna el número de tabs
        return numoftabs;
    }

}
