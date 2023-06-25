package play.ground

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.tvprovider.media.tv.PreviewChannelHelper
import androidx.tvprovider.media.tv.TvContractCompat
import androidx.tvprovider.media.tv.TvContractCompat.PreviewProgramColumns.TYPE_MOVIE
import androidx.tvprovider.media.tv.WatchNextProgram
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.verticalLayout
import java.time.Duration

class IndexActivity : AppCompatActivity() {

  private val provider = PreviewChannelHelper(this)

  @SuppressLint("RestrictedApi")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    verticalLayout {
      button("write program") {
        onClick {
          val intent =
            Intent().setComponent(ComponentName.unflattenFromString("com.google.android.tvlauncher/com.google.android.libraries.notifications.entrypoints.systemtray.SystemTrayActivity"))

          val watchNext = WatchNextProgram.Builder().setType(TYPE_MOVIE)
            .setWatchNextType(TvContractCompat.WatchNextPrograms.WATCH_NEXT_TYPE_CONTINUE)
            .setLastPlaybackPositionMillis(Duration.ofMinutes(17).toMillis().toInt())
            .setLastEngagementTimeUtcMillis(System.currentTimeMillis())
            .setTitle("foo")
            .setDurationMillis(Duration.ofMillis(20).toMillis().toInt())
            .setPreviewVideoUri(Uri.parse("http://www.baidu.com"))
            .setDescription("bar")
            .setPosterArtUri(Uri.parse("http://www.baidu.com"))
            .setIntent(intent)
            .setInternalProviderId("foobar")
            .setContentId("foobar")
            .build()

          provider.publishWatchNextProgram(watchNext)


        }
      }
    }
  }
}