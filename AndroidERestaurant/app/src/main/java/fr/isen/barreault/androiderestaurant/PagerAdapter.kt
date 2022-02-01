package fr.isen.barreault.androiderestaurant

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentFirst()
            1 -> FragmentSecond()
            2 -> FragmentThird()
            else -> FragmentFirst()
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }


}