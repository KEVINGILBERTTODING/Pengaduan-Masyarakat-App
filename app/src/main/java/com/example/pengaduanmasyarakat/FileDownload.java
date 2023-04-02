package com.example.pengaduanmasyarakat;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

public class FileDownload {
    private static final String TAG = "FileDownload";
    private static final int DOWNLOAD_NOTIFICATION_ID_DONE = 911;
    private final Context mContext;
    private DownloadManager downloadManager;

    public FileDownload(Context context) {
        this.mContext = context;
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public long downloadFile(String url, String title, String description, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(title);
        request.setDescription(description);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();

        return downloadManager.enqueue(request);
    }
}
