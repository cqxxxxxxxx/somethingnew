package com.cqx.oom;

import org.apache.commons.fileupload.disk.DiskFileItem;

import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2019/3/12.
 */
public class ApacheFileUploadOOM {


    public static void mockOOM() throws UnexpectedException {
        String tmpDir = System.getProperty("java.io.tmpdir") + File.separator + "cqxtmp";
        while (true) {
            File dir = new File(tmpDir, "attachment-cache");
            try {
                if (!dir.mkdirs() && !dir.exists()) {
                    throw new UnexpectedException("Failed to create directory for attachments " + dir);
                }
                DiskFileItem dfi = new DiskFileItem(null, null, false, null, 10000, dir);
                // This causes the temp file to be created.
                dfi.getOutputStream().close();
                // Make sure this file is marked for deletion on VM exit because DiskFileItem does not.
                dfi.getStoreLocation().deleteOnExit();
                // return dfi
            } catch (IOException e) {
                throw new UnexpectedException("Failed to create new attachment temporary file.", e);
            }
        }
    }

    public static void main(String[] args) throws UnexpectedException, InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        mockOOM();
        TimeUnit.SECONDS.sleep(30);
    }
}
