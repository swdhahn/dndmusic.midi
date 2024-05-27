package com.countgandi.com.apis;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import com.github.kiulian.downloader.Config;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.request.RequestVideoStreamDownload;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;

public class YoutubeManager {
	
	private YoutubeDownloader downloader;
	private Config config;
	
	public YoutubeManager() {
		// or configure after init
		downloader = new YoutubeDownloader();
		config = downloader.getConfig();
		config.setMaxRetries(0);
		
		
		

	}
	
	public String downloadSong(String url) {
		String videoId = url.split("v=")[1];
		RequestVideoInfo request = new RequestVideoInfo(videoId);
		Response<VideoInfo> response = downloader.getVideoInfo(request);
		VideoInfo video = response.data();
		
		List<VideoWithAudioFormat> videoFormats = video.videoWithAudioFormats();
		/*videoFormats.forEach(it -> {
		    System.out.println(it.audioQuality() + ", " + it.videoQuality() + " : " + it.url());
		});*/
		
		Format format = videoFormats.get(1);
		
		RequestVideoFileDownload requestV = new RequestVideoFileDownload(format)
			    // optional params    
			    //.saveTo("/videos") // by default "videos" directory
			    .overwriteIfExists(true); // if false and file with such name already exits sufix will be added video(1).mp4
			Response<File> responseV = downloader.downloadVideoFile(requestV);
			File data = responseV.data();
			System.out.println("Downloaded: " + data.getPath());
			return data.getPath();

		/*ByteArrayOutputStream os = new ByteArrayOutputStream();
		RequestVideoStreamDownload requestV = new RequestVideoStreamDownload(format, os);
		Response<Void> responseV = downloader.downloadVideoStream(requestV);
		while(!responseV.ok()) {
			System.out.println("Downloading Video");
		}
		byte[] ba = os.toByteArray();*/
		
		/*URL file = YoutubeManager.class.getResource("");
	    final Media media = new Media(file.toString());
	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
	    mediaPlayer.play();*///JavaFX is not loading>
	}

}
