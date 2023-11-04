package projects.school.comunication.model

data class Test(
    val id: Int,
    val question: String,
    val optionOne:String,
    val optionTwo:String,
    val optionThree:String,
    val optionFour:String,
    val correctAnswer: String
)