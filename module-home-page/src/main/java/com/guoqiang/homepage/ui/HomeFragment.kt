package com.guoqiang.homepage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.guoqiang.base.common.BaseFragment
import com.guoqiang.homepage.R
import com.guoqiang.homepage.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initData() {
        val viewPager = binding.vpContainer
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomePageFragment()
                    1 -> CarCircleFragment()
                    2 -> SelectCarFragment()
                    3 -> UsedCarFragment()
                    else -> MimeFragment()
                }
            }
        }
        viewPager.isUserInputEnabled = false

//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                binding.bnvNavigation.menu.getItem(position).isChecked = true
//            }
//        })

        binding.bnvNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> viewPager.setCurrentItem(0, true)
                R.id.action_car_circle -> viewPager.setCurrentItem(1, true)
                R.id.action_select_car -> viewPager.setCurrentItem(2, true)
                R.id.action_used_car -> viewPager.setCurrentItem(3, true)
                R.id.action_mime -> viewPager.setCurrentItem(4, true)
            }
            true
        }
    }
}