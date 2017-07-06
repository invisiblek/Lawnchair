package ch.deletescape.lawnchair;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DumbImportExportTask {
    public static void exportDB(Activity activity) {
        ContextWrapper contextWrapper = new ContextWrapper(activity);
        File db = contextWrapper.getDatabasePath(LauncherFiles.LAUNCHER_DB);
        exportFile(db, activity);
    }

    public static void importDB(Activity activity) {
        ContextWrapper contextWrapper = new ContextWrapper(activity);
        File db = contextWrapper.getDatabasePath(LauncherFiles.LAUNCHER_DB);
        importFile(db, activity);
    }

    public static void exportPrefs(Activity activity) {
        ApplicationInfo info = activity.getApplicationInfo();
        String dir = new ContextWrapper(activity).getCacheDir().getParent();
        File prefs = new File(dir, "shared_prefs/" + info.packageName + ".prefs.xml");
        exportFile(prefs, activity);
    }

    public static void importPrefs(Activity activity) {
        ApplicationInfo info = activity.getApplicationInfo();
        String dir = new ContextWrapper(activity).getCacheDir().getParent();
        File prefs = new File(dir, "shared_prefs/" + info.packageName + ".prefs.xml");
        importFile(prefs, activity);
    }

    private static void exportFile(File file, Activity activity) {
        if (!isExternalStorageWritable() || !canWriteStorage(activity)) {
            Toast.makeText(activity, "External Storage is not writable (grant Permission in settings)", Toast.LENGTH_LONG).show();
            return;
        }
        File backup = new File(getFolder(), file.getName());
        if (backup.exists()) {
            backup.delete();
        }
        if (copy(file, backup)) {
            Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Error: Failed to copy file", Toast.LENGTH_LONG).show();
        }
    }

    private static void importFile(File file, Activity activity) {
        if (!isExternalStorageReadable() || !canWriteStorage(activity)) {
            Toast.makeText(activity, "External Storage is not writable (grant Permission in settings)", Toast.LENGTH_LONG).show();
            return;
        }
        File backup = new File(getFolder(), file.getName());
        if (!backup.exists()) {
            Toast.makeText(activity, "No backup found", Toast.LENGTH_LONG).show();
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        if (copy(backup, file)) {
            Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "Error: Failed to copy file", Toast.LENGTH_LONG).show();
        }
    }

    @NonNull
    private static File getFolder() {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Lawnchair/backup");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }


    private static boolean canWriteStorage(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean copy(File inFile, File outFile) {
        FileInputStream in;
        FileOutputStream out;
        try {
            in = new FileInputStream(inFile);
            out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();

            // write the output file
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            Log.e("copy", e.getMessage(), e);
        }
        return false;
    }

    /* Checks if external storage is available for read and write */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
