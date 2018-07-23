package co.touchlab.notepad.db

import co.touchlab.multiplatform.architecture.threads.MutableLiveData
import co.touchlab.notepad.utils.backgroundTask
import com.squareup.sqldelight.Query

abstract class QueryLiveData<T, Z>(val q: Query<*>) : MutableLiveData<Z>(), Query.Listener{
    init {
        backgroundTask {
            queryResultsChanged()
        }
    }

    abstract fun extractData(q: Query<*>):Z

    override fun queryResultsChanged() {
        println("queryResultsChanged: Value changed")
        postValue(extractData(q))
    }
}