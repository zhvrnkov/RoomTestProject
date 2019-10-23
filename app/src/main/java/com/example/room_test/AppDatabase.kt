package com.example.room_test

import androidx.room.*
import com.example.room_test.entities.*
import com.example.room_test.entity_utils.*

internal object Tables {
    const val schools = "schools"
    const val instructors = "instructors"
    const val rubrics = "rubrics"
    const val grades = "grades"
    const val skillSets = "skill_sets"
    const val microtasks = "microtasks"
    const val assessments = "assessments"
    const val microtaskGrades = "microtask_grades"
    const val students = "students"
}

@Database(
    entities = [
        School::class,
        Instructor::class,
        Rubric::class,
        Grade::class,
        SkillSet::class,
        Microtask::class,
        Assessment::class,
        MicrotaskGrade::class,
        Student::class
    ],
    version = 1
)
@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun schooldDao(): SchoolDao
    abstract fun instructorDao(): InstructorDao
    abstract fun rubricDao(): RubricDao
    abstract fun gradeDao(): GradeDao
    abstract fun skillSetDao(): SkillSetDao
    abstract fun microtaskDao(): MicrotaskDao
    abstract fun assessmentDao(): AssessmentDao
    abstract fun microtaskGradeDao(): MicrotaskGradeDao
    abstract fun studentDao(): StudentDao
}
