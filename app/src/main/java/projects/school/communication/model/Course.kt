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
    var id: String? = null,
    val header: String,
    val description: String,
    val category: String,
    val duration: Int,
    val imageURL: String,
    val video: List<Video>,
    val tests: List<Tests>,
): Serializable{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(imageURL.isNullOrEmpty()){
            result = 31 * result + imageURL.hashCode()
        }
        return result
    }
}