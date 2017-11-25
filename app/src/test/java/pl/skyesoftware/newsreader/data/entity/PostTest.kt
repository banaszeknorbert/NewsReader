package pl.skyesoftware.newsreader.data.entity

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@RunWith(MockitoJUnitRunner::class)
class PostTest {

    @Test
    fun isMissingAnyField_returnsFalseWhenBodyIsNull() {
        Assert.assertTrue(Post(id = 0, userId= "1", title = "title", body = null).isMissingAnyField())
    }

    @Test
    fun isMissingAnyField_returnsFalseWhenTitleIsNull() {
        Assert.assertTrue(Post(id = 0, userId= "1", title = null, body = "body").isMissingAnyField())
    }

    @Test
    fun isMissingAnyField_returnsFalseWhenUserIdIsNull() {
        Assert.assertTrue(Post(id = 0, userId= null, title = "title", body = "body").isMissingAnyField())
    }

}