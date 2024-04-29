package projects.school.communication.model

import java.io.Serializable

data class Test(
    val id: Int?,
    val question: String,
    val optionOne:String?,
    val optionTwo:String?,
    val optionThree:String?,
    val optionFour:String?,
    val correctAnswer: String
): Serializable