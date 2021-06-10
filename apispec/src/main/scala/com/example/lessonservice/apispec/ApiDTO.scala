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

case class ScoreResponse(
  userId: String,
  lessonId: String,
  score: Int
)

case class LessonDTO(
  id: String,
  title: String
)

case class UpcomingLessonsResponseDTO(
  userId: String,
  lessons: List[LessonDTO]
)
