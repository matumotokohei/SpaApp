package app.doggy.firestoresample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.doggy.firestoresample.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val text = "最新の正確な情報については公式サイトをご確認ください。"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext,text,duration)
        toast.show()



        // Firestoreをインスタンス化
        val db = Firebase.firestore

        // RecyclerViewの設定
        val taskAdapter = TaskAdapter()
        binding.recyclerView.adapter = taskAdapter
            binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // アプリ起動時に、保存されているデータを取得する
        db.collection(TASKS_PATH)
            .orderBy("spa_cost1")
            .get()
            .addOnSuccessListener { tasks ->
                val taskList = ArrayList<Task>()
                tasks.forEach { taskList.add(it.toObject(Task::class.java)) }
                taskAdapter.submitList(taskList)
            }
            .addOnFailureListener { exception ->
                Log.d(READ_TAG, "Error getting documents: ", exception)
            }

        // データの変更をリアルタイムでアプリに反映する
        val docRef = db.collection(TASKS_PATH).orderBy("spa_cost1")
        docRef.addSnapshotListener { tasks, e ->
            if (e != null) {
                Log.w(READ_TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (tasks != null) {
                val taskList = ArrayList<Task>()
                tasks.forEach { taskList.add(it.toObject(Task::class.java)) }
                taskAdapter.submitList(taskList)
            } else {
                Log.d(READ_TAG, "Current data: null")
            }
        }


    }



    companion object {
        private const val READ_TAG = "read_task"
        private const val TASKS_PATH = "tasks"
    }
}