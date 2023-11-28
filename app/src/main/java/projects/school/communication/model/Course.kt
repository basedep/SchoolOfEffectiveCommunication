package projects.school.communication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(
    tableName = "CourseTable"
)

data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val header: String,
    val description: String,
    val category: String,
    val duration: Int,
    val imageURL: String,
    val progress: Int,
    val isAdded: Boolean,
    val isFavorite: Boolean,
    val video: List<Video>,
    val testList: List<Test>,
    val watchedVideos: Int,
    val passedTests: Int
): Serializable{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(imageURL.isNullOrEmpty()){
            result = 31 * result + imageURL.hashCode()
        }
        return result
    }
}