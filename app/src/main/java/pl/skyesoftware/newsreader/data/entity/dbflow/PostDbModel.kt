package pl.skyesoftware.newsreader.data.entity.dbflow

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@Table(name = "posts", cachingEnabled = true, database = DbFlowDatabase::class)
data class PostDbModel(
        @PrimaryKey(autoincrement = false) @Column(name = "id") var id: Int,
        @Column(name = "userId") var userId: Int,
        @Column(name = "title") var title: String,
        @Column(name = "body") var body: String) : BaseModel() {

    constructor() : this(0, 0, "", "")

}

