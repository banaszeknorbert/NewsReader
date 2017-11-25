package pl.skyesoftware.newsreader.data.entity.dbflow

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@Database(name = DbFlowDatabase.NAME, version = DbFlowDatabase.VERSION)
class DbFlowDatabase {

    companion object {
        const val NAME = "NewsReaderDatabase"

        const val VERSION = 3
    }


}