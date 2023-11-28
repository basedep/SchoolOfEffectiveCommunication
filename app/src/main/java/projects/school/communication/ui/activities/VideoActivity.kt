package projects.school.communication.ui.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.navArgs
import projects.school.communication.R
import projects.school.communication.model.Video

class VideoActivity : AppCompatActivity() {

    private val args: VideoActivityArgs by navArgs()
    private lateinit var playerView: PlayerView
    private var player: ExoPlayer? = null
    private var playbackPosition = 0L
    private var currentItem = 0
    private var playWhenReady = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video)

        val video: Video = args.videoObject
        playerView = findViewById(R.id.playerView)

        initPlayer(video.videoUrl)
    }

    private fun initPlayer(url: String){
        player = ExoPlayer.Builder(this)
            .build()
            .also {exoPlayer ->
                playerView.player = exoPlayer

                val mediaItem = MediaItem.fromUri(url)
                exoPlayer.setMediaItem(mediaItem)

                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }
    }

    private fun releasePlayer(){
        player?.let{
            playWhenReady = it.playWhenReady
            currentItem = it.currentMediaItemIndex
            playbackPosition = it.currentPosition
            it.release()
        }
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        player = null
    }


}