package com.oliferov.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.oliferov.composition.R
import com.oliferov.composition.domain.entity.GameResult

interface OnOptionClickListener {

    fun onOptionClick(option: Int)
}

@BindingAdapter("requireAnswers")
fun bindRequireAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

@BindingAdapter("progressBar")
fun bindAnswersProgress(progressBar: ProgressBar, percent: Int) {
    progressBar.setProgress(percent, true)
}

@BindingAdapter("minPercent")
fun bindMinPercent(progressBar: ProgressBar, minPercent: Int) {
    progressBar.secondaryProgress = minPercent
}


@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, boolean: Boolean) {
    progressBar.progressTintList = ColorStateList.valueOf(
        getColorByState(progressBar.context, boolean)
    )
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, boolean: Boolean) {
    textView.setTextColor(getColorByState(textView.context, boolean))
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_win_smile
    } else {
        R.drawable.ic_lose_smile
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countsOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countsOfQuestions.toDouble()) * 100).toInt()
    }
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val resId = if (goodState) {
        android.R.color.holo_green_dark
    } else {
        android.R.color.holo_red_dark
    }
    return ContextCompat.getColor(context, resId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

@BindingAdapter("listNumberAsText")
fun bindListNumberAsText(textView: TextView,number: Any){
    textView.text = number.toString()
}
