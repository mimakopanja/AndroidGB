package com.example.newapp.mvp.model.cache.room


import com.example.newapp.mvp.model.cache.IImageCache
import com.example.newapp.mvp.model.entity.RoomCachedImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.security.MessageDigest

class ImageCache(
    private val database: com.example.newapp.mvp.model.entity.room.db.Database,
    private val directory: File
): IImageCache {

    private fun String.md5() = hash("MD5")
    private fun String.hash(algorithm: String) =
        MessageDigest.getInstance(algorithm).digest(toByteArray())
            .fold("", {str, it -> "%20x".format(it)})


    override fun saveImage(url: String, bytes: ByteArray) = Completable.create{ emitter ->
        if (!directory.exists() && !directory.mkdir()) {
            emitter.onError(IOException("Failed to create cache dir!"))
            return@create
        }

        val fileFormat = if (url.contains(".jpg")) ".jpg" else ".png"
        val imageFIle = File(directory, url.md5() + fileFormat)
        try {
            FileOutputStream(imageFIle).use {
                it.write(bytes)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
        database.imageDao.insert(RoomCachedImage(url, imageFIle.path))
        emitter.onComplete()
    }.subscribeOn(Schedulers.io())

    override fun getCachedImage(url: String) = Maybe.fromCallable{
        database.imageDao.findByUrl(url)?.let {
            File(it.localPath).inputStream().readBytes()
        }
    }

}