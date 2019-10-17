package com.example.room_test

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.room_test.entity_utils.*
import com.example.room_test.mock_dtos.*
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var database: AppDatabase

    private lateinit var rubricUtils: EntityUtils<MockRubricDTO>
    private lateinit var gradeUtils: EntityUtils<MockGradeDTO>

    private val newId: Long
        get() = UUID.randomUUID().mostSignificantBits

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val rubricDao = database.rubricDao()
        val gradeDao = database.gradeDao()

        rubricUtils = RubricUtils(rubricDao, MockRubricDTO::class.java, MockGradeDTO::class.java)
        gradeUtils = GradeUtils(gradeDao, MockGradeDTO::class.java)
    }

    @Test
    fun insertingIsCorrect() {
        val newRubric = MockRubricDTO(id = newId, title = "Just Rubric")
        rubricUtils.insert(newRubric)

        val newGrades = arrayOf(
            MockGradeDTO(title = "Lorem", id = newId, rubricId = newRubric.id, score = 25)
        )
        newGrades.forEach {
            gradeUtils.insert(it)
        }

        val postInsertData = gradeUtils.get(newGrades.map { it.id })
        val savedNewData = postInsertData.last()
        assertEquals("Lorem", savedNewData.title)
        assertEquals(25, savedNewData.score)

        val rubricWithGrades = rubricUtils.get(listOf(newRubric.id)).last()
        assertEquals(rubricWithGrades.grades.count(), newGrades.count())
        newGrades.forEach { grade ->
            val fromRubric = rubricWithGrades.grades.firstOrNull { it.id == grade.id }
            if (fromRubric == null) fail()
        }
    }

    @Test
    fun deletionIsCorrect() {
        val newRubric = MockRubricDTO(id = newId, title = "Just Rubric")
        rubricUtils.insert(newRubric)

        val newGrades = arrayOf(
            MockGradeDTO(id = newId, title = "First", score = 0, rubricId = newRubric.id),
            MockGradeDTO(id = newId, title = "Second", score = 0, rubricId = newRubric.id)
        )
        newGrades.forEach { gradeUtils.insert(it) }

        val preRubricDeleteGrades = gradeUtils.get(newGrades.map { it.id })
        rubricUtils.delete(listOf(newRubric.id))
        val rubricsPostDeletion = rubricUtils.get(listOf(newRubric.id))
        assertTrue(rubricsPostDeletion.isEmpty())
        val postRubricDeleteGrades = gradeUtils.get(newGrades.map { it.id })

        assertEquals(preRubricDeleteGrades.count() - newGrades.count(), postRubricDeleteGrades.count())
    }

    @Test
    fun relatedEntityDeletionIsCorrect() {
        val newRubric = MockRubricDTO(id = newId, title = "Just Rubric")
        rubricUtils.insert(newRubric)

        val newGrades = arrayOf(
            MockGradeDTO(id = newId, title = "First", score = 0, rubricId = newRubric.id),
            MockGradeDTO(id = newId, title = "Second", score = 0, rubricId = newRubric.id)
        )
        newGrades.forEach { gradeUtils.insert(it) }

        val rubricBeforeGradeDeletion = rubricUtils.get(listOf(newRubric.id)).last()
        newGrades.forEach { grade ->
            assertNotNull(rubricBeforeGradeDeletion.grades.firstOrNull { it.id == grade.id })
        }

        gradeUtils.delete(newGrades.map { it.id })
        val gradesAfterDeletion = gradeUtils.get(newGrades.map { it.id })
        assertTrue(gradesAfterDeletion.isEmpty())

        val rubricAfterGradeDeletion = rubricUtils.get(listOf(newRubric.id)).last()
        assertTrue(rubricAfterGradeDeletion.grades.isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }
}
