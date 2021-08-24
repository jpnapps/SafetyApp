package com.apndev.womensafetyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import com.apndev.womensafetyapp.alert.MBaseDialog
import com.apndev.womensafetyapp.alert.OnDismissListner
import com.apndev.womensafetyapp.alert.ProfileDialog
import com.apndev.womensafetyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnDismissListner {

    private val prefutils:PrefUtils?
        get() =  PrefUtils.with(this@MainActivity)


    private val profile:Profile?
            get() =  PrefUtils.with(this@MainActivity).getProfile()


    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)
        setContentView(binding.root)
        //binding.lifecycleOwner = this
        profile?.let{


        }?:let{
            ProfileDialog.showDialog(this@MainActivity, MBaseDialog(ok_text = "Create Profile"))
        }
    }

    override fun onDismiss(obj: Any?) {
        obj?.let{
        if(obj is Profile)
        {
            prefutils?.saveProfile(obj)
        }
        }
    }
}