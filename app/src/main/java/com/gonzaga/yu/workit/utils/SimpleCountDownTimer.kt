package com.R.R.utils

import android.os.Handler
import android.os.HandlerThread
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class SimpleCountDownTimer(
    fromMinutes: Long,
    fromSeconds: Long,
    delayInSeconds: Long,
    onCountDownListener: OnCountDownListener
) {
    private val onCountDownListener: OnCountDownListener
    private var fromMinutes: Long = 0
    private var fromSeconds: Long = 0
    private var delayInSeconds: Long = 1
    private val calendar = Calendar.getInstance()

    /**
     * @return This method returns seconds till countdown.
     */
    var secondsTillCountDown: Long = 0
        private set

    /**
     * @return This method returns minutes till countdown.
     */
    var minutesTillCountDown: Long = 0
        private set
    private var finished = false
    private var handler = Handler()
    private var handlerThread: HandlerThread? = null
    private var isBackgroundThreadRunning = false
    private val simpleDateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    private val runnable = Runnable { decrementMinutes() }

    /**
     * This method sets business logic for countdown operation before it starts.
     */
    private fun setCountDownValues(fromMinutes: Long, fromSeconds: Long) {
        this.fromMinutes = fromMinutes
        this.fromSeconds = fromSeconds
        minutesTillCountDown = this.fromMinutes
        if (fromMinutes > 0 && fromSeconds <= 0) {
            secondsTillCountDown = 0
            return
        }
        if (fromSeconds <= 0 || fromSeconds > 59) {
            secondsTillCountDown = 59
            return
        }
        secondsTillCountDown = this.fromSeconds
    }

    /**
     * Sets a new pattern for SimpleDateFormat for time returned on each tick.
     *
     * @param pattern only acceptable "mm:ss","m:s","mm","ss","m","s".
     */
    fun setTimerPattern(pattern: String) {
        if (pattern.equals("mm:ss", ignoreCase = true) || pattern.equals(
                "m:s",
                ignoreCase = true
            ) || pattern.equals("mm", ignoreCase = true) ||
            pattern.equals("ss", ignoreCase = true) || pattern.equals(
                "m",
                ignoreCase = true
            ) || pattern.equals("s", ignoreCase = true)
        ) simpleDateFormat.applyPattern(pattern)
    }

    /**
     * This method call will permanently move the timer to run in background thread for this instance.
     * A new thread is created which is then bound to timer's handler of main thread's message queue therefore overwriting it.
     * This method can be invoked at any time.
     * Note: onCountDownListener callbacks will not be invoked on main thread.
     */
    fun runOnBackgroundThread() {
        if (isBackgroundThreadRunning) return
        handlerThread = HandlerThread(javaClass.simpleName)
        startBackgroundThreadIfNotRunningAndEnabled()
        handler = Handler(handlerThread!!.looper)
    }

    private fun startBackgroundThreadIfNotRunningAndEnabled() {
        if (handlerThread != null && !handlerThread!!.isAlive) {
            handlerThread!!.start()
            isBackgroundThreadRunning = true
        }
    }

    /**
     * No need to quit background thread once started.
     * Quitting it kills it. Threads don't restart.
     * This is just left here if needed for any reason in future.
     */
    /*  private void quitBackgroundThreadSafelyIfRunning() {
              if (!isBackgroundThreadRunning)
                  return;
              isBackgroundThreadRunning = !handlerThread.quitSafely();
          }*/
    private val countDownTime: String
        private get() {
            calendar[Calendar.MINUTE] = minutesTillCountDown.toInt()
            calendar[Calendar.SECOND] = secondsTillCountDown.toInt()
            return simpleDateFormat.format(calendar.time)
        }

    private fun decrementMinutes() {
        secondsTillCountDown--
        if (minutesTillCountDown == 0L && secondsTillCountDown == 0L) {
            finish()
        }
        if (secondsTillCountDown < 0) {
            if (minutesTillCountDown > 0) {
                secondsTillCountDown = 59
                minutesTillCountDown--
            }
        }
        runCountdown()
    }

    private fun finish() {
        onCountDownListener.onCountDownFinished()
        finished = true
        pause()
    }

    private fun decrementSeconds() {
        handler.postDelayed(runnable, TimeUnit.SECONDS.toMillis(delayInSeconds))
    }

    /**
     * A method to start/resume countdown.
     *
     * @param resume if true it will resume from where its paused else from start.
     */
    fun start(resume: Boolean) {
        if (!resume) {
            setCountDownValues(fromMinutes, fromSeconds)
            finished = false
        }
        runCountdown()
    }

    private fun runCountdown() {
        if (!finished) {
            updateUI()
            decrementSeconds()
        }
    }

    private fun updateUI() {
        onCountDownListener.onCountDownActive(countDownTime)
    }

    /**
     * A method to pause/stop countdown.
     */
    fun pause() {
        handler.removeCallbacks(runnable)
    }

    /**
     * A countdown listener to be used to listen for ticks and finish.
     */
    interface OnCountDownListener {
        /**
         * A method continuously called on ticking.
         *
         * @param time The time at tick.
         */
        fun onCountDownActive(time: String?)

        /**
         * A method called once when countdown is finished.
         */
        fun onCountDownFinished()
    }

    /**
     * @param fromMinutes         minutes to countdown.
     * @param fromSeconds         seconds to countdown.
     * @param onCountDownListener A listener for countdown ticks.
     * @param delayInSeconds      optional delay in seconds for a tick to execute default is 1 second.
     */
    init {
        check(!(fromMinutes <= 0 && fromSeconds <= 0)) { javaClass.simpleName + " can't work in state 0:00" }
        if (delayInSeconds > 1) this.delayInSeconds = delayInSeconds
        this.onCountDownListener = onCountDownListener
        setCountDownValues(fromMinutes, fromSeconds)
    }
}