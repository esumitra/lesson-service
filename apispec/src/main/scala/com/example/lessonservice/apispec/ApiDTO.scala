package com.example.lessonservice.apispec

case class QuizResponseDTO(
  questionId: String,
  responseId: String
)
case class ScoreLessonRequestDTO(
  userId: String,
  lessonId: String,
  answers: List[QuizResponseDTO]
)

case class ScoreResponseDTO(
  userId: String,
  lessonId: String,
  score: Int,
  pass: Boolean
)

object ScoreResponseDTO {
  def apply(
    userId: String,
    lessonId: String,
    score: Int): ScoreResponseDTO =
    ScoreResponseDTO(userId, lessonId, score, if (score < 75) false else true)
}

case class LessonDTO(
  id: String,
  title: String
)

case class UpcomingLessonsResponseDTO(
  userId: String,
  lessons: List[LessonDTO]
)
