package app.doggy.firestoresample

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Task(
    @DocumentId
    val id: String = "",

    val spa_name: String = "",
    val spa_station: String = "",
    val spa_walk: String = "",
    val spa_time: String = "",

    val spa_closing_day: String = "",
    var spa_cost1: Int = 0,
    val spa_cost2: Int =0,
    val spa_note: String = "",
)