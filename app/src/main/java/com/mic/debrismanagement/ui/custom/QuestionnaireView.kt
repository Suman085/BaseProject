package com.mic.debrismanagement.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import android.widget.*
import com.mic.debrismanagement.R


/**
 * Created by Suman on 2/17/2018.
 */
class QuestionnaireView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private var questionText: String? = ""
    private var title: TextView?
    private val radioGroup: RadioGroup?
    private var answer: Int? = -1


    private var answers: Array<out CharSequence>?

    init {
        orientation= VERTICAL
        gravity= CENTER_VERTICAL
        inflate(getContext(),R.layout.questionnaire_view, this)
        background=getContext().resources.getDrawable(R.drawable.rectangular_border)
        setPadding(10, 10, 10, 10)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.QuestionnaireView)
        questionText = typedArray.getString(R.styleable.QuestionnaireView_question)
        answers = typedArray.getTextArray(R.styleable.QuestionnaireView_answer)
        typedArray.recycle()
        radioGroup = findViewById<RadioGroup>(R.id.rg_rec_in_same_loc)
        title = findViewById<TextView>(R.id.question)
        title!!.setText(questionText)
        answers!!.forEachIndexed { index, charSequence ->
            val radioButton = RadioButton(getContext())
            radioButton.setHint(answers!!.get(index))
            radioButton.id = index
            radioGroup.addView(radioButton)
        }
        radioGroup.setOnCheckedChangeListener{group, checkedId -> answer=checkedId }
    }


    fun setQuestion(question: String) {
        questionText = question
        requestLayout()
    }


    fun getAnswer(): String {
        if (answer != -1) {
            return answers!!.get(answer!!).toString()
        }
        return "Nothing is selected"
    }


}