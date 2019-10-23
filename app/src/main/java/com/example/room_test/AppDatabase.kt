package com.example.room_test

import android.content.Context
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
internal abstract class Daos : RoomDatabase() {
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

class AppDatabase<
        SchoolDTO : SchoolFields,
        InstructorDTO : InstructorFields,
        GradeDTO : GradeFields,
        MicrotaskDTO : MicrotaskFields,
        SkillSetDTO : SkillSetFields<MicrotaskDTO>,
        RubricDTO : RubricFields<GradeDTO, MicrotaskDTO, SkillSetDTO>,
        MicrotaskGradeDTO : MicrotaskGradeFields,
        AssessmentDTO : AssessmentFields<MicrotaskGradeDTO>,
        StudentDTO : StudentFields>
(
    context: Context,
    schoolDtoClass: Class<SchoolDTO>,
    instructorDtoClass: Class<InstructorDTO>,
    rubricDtoClass: Class<RubricDTO>,
    gradeDtoClass: Class<GradeDTO>,
    skillSetDtoClass: Class<SkillSetDTO>,
    microtaskDtoClass: Class<MicrotaskDTO>,
    microtaskGradeDtoClass: Class<MicrotaskGradeDTO>,
    assessmentDtoClass: Class<AssessmentDTO>,
    studentDtoClass: Class<StudentDTO>
) {
    val schools: EntityUtils<SchoolDTO>
    val instructors: EntityUtils<InstructorDTO>
    val rubrics: EntityUtils<RubricDTO>
    val skillSets: EntityUtils<SkillSetDTO>
    val microtasks: EntityUtils<MicrotaskDTO>
    val grades: EntityUtils<GradeDTO>
    val microtaskGrades: EntityUtils<MicrotaskGradeDTO>
    val assessments: EntityUtils<AssessmentDTO>
    val students: EntityUtils<StudentDTO>

    init {
        val daos = Room.databaseBuilder(context, Daos::class.java, "app-database").build()
        schools = SchoolUtils(daos.schooldDao(), schoolDtoClass)
        instructors = InstructorUtils(daos.instructorDao(), instructorDtoClass)
        grades = GradeUtils(daos.gradeDao(), gradeDtoClass)
        microtasks = MicrotaskUtils(daos.microtaskDao(), microtaskDtoClass)
        skillSets = SkillSetUtils(daos.skillSetDao(), skillSetDtoClass, microtaskDtoClass)
        rubrics = RubricUtils(
            daos.rubricDao(), rubricDtoClass, gradeDtoClass, skillSetDtoClass, microtaskDtoClass, skillSets::get)
        microtaskGrades = MicrotaskGradeUtils(daos.microtaskGradeDao(), microtaskGradeDtoClass)
        assessments = AssessmentUtils(daos.assessmentDao(), assessmentDtoClass, microtaskGradeDtoClass)
        students = StudentUtils(daos.studentDao(), studentDtoClass)
    }
}