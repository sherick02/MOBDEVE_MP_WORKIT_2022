package com.gonzaga.yu.workit

import android.content.Context
import com.R.R.ui.base.BaseActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.NavController
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gonzaga.yu.workit.R
import com.R.R.data.common.Constants
import com.R.R.data.model.ExerciseModel
import com.R.R.data.model.WorkoutModel
import com.gonzaga.yu.workit.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding?>() {

    private var mAppBarConfiguration: AppBarConfiguration? = null
    var navController: NavController? = null
    //e
    override val layoutId: Int
        get() = R.layout.activity_main

    override val context: Context
        protected get() = this

    override fun initViews(savedInstanceState: Bundle?) {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_streak,
            R.id.nav_reminder,
            R.id.nav_logout
        )
            .build()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding!!.navView, navController!!)

        /* navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.nav_home:
                        break;
                }
            }
        });*/

        //For workout data
        for (i in 0..1) {
            val exerciseModels = ArrayList<ExerciseModel>()
            for (j in 0..1) {
                val exerciseModel = ExerciseModel()
                exerciseModel.day = i + 1
                exerciseModel.title = "Jumping Jacks"
                exerciseModel.des =
                    "How to jump jacks. Start with your feet in the bottom of the shoe where the sky meets."
                exerciseModel.workout_name = ""
                exerciseModel.sets = 3
                exerciseModel.reps = 20
                exerciseModel.rest_time = 10
                exerciseModels.add(exerciseModel)
            }
            val workoutModel = WorkoutModel("Relaxing workout " + i + 1, i + 1, exerciseModels)
            Constants.workoutModelArrayList.add(workoutModel)
        }
    }

    override fun setListeners() {}

    fun onDoBack() {
        onBackPressed()
    }

    /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}