package com.oliferov.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.oliferov.composition.R
import com.oliferov.composition.domain.entity.GameResult
import com.oliferov.composition.domain.entity.GameSettings

@BindingAdapter("requireAnswers")
fun bindRequireAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult){
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean){
    imageView.setImageResource(getSmileResId(winner))
}

    private fun getSmileResId(winner: Boolean):Int{
        return if(winner){
            R.drawable.ic_win_smile
        }else{
            R.drawable.ic_lose_smile
        }
    }

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult){
    if(countsOfQuestions== 0){
        0
    }else{
        ((countOfRightAnswers / countsOfQuestions.toDouble()) * 100).toInt()
    }
}
