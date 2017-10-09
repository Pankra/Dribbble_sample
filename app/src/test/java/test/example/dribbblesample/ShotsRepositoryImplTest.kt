package test.example.dribbblesample

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import test.example.dribbblesample.api.DribbbleService
import test.example.dribbblesample.repository.ShotsRepositoryImpl

@RunWith(MockitoJUnitRunner::class)
class ShotsRepositoryImplTest {

    @Mock
    lateinit var dribbleService: DribbbleService

    lateinit var shotsRepositoryImpl: ShotsRepositoryImpl

    lateinit var images: Images
    lateinit var user: User
    lateinit var shotItem: ShotItem
    lateinit var shotItems: List<ShotItem>

    private val page = 1
    private val shotId = 1

    @Before
    fun setUp() {
        shotsRepositoryImpl = ShotsRepositoryImpl(dribbleService)

        images = Images(null, "normal_url", "teaser_url")
        user = User(1, "user_name", "user_username")
        shotItem = ShotItem(shotId, "shot title", "shot description", images, user)

        shotItems = listOf(shotItem)
        `when`(dribbleService.getShots(anyInt())).thenReturn(FakeCall(Response.success(shotItems), null))
        `when`(dribbleService.getShot(anyInt())).thenReturn(FakeCall(Response.success(shotItem), null))
    }

    @Test
    fun testGetShots() {
        //When
        val shots = dribbleService.getShots(page)

        //Then
        verify(dribbleService).getShots(page)
        Assert.assertEquals(shotItems, shots)
    }

    @Test
    fun testGetShot() {
        //When
        val shot = dribbleService.getShot(shotId)

        //Then
        verify(dribbleService).getShot(shotId)
        Assert.assertEquals(shotItem, shot)
    }
}