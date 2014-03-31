package info.jackrex.androidcachefoundation.cachefoundation.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by Jackrex on 2/24/14.
 */
public class DataCache {

    private static final String dbName = "cache.db";
    private static final String TABLE = "cache";
    private static final String ID = "id";
    private static final String URL = "url";
    private static final String DATA = "data";
    private static final String TIMESTAMP = "timestamp";
    private static final String INPUT_BYTES = "input_bytes";//size 大小
    private static SQLiteDatabase database;
    private File dbFile;
    private static DataCache dataCache;
    private Context context;

    public DataCache(Context context) {
        if (init(context)) {
            initTable();

        }

    }

    public static DataCache newInstance(Context context) {

        if (dataCache == null) {
            dataCache = new DataCache(context);
        }
        return dataCache;
    }


    public static DataCache getDataCache() {

        return dataCache;
    }


    private boolean init(Context context) {

        try {
            dbFile = new File(context.getCacheDir() + File.separator + dbName);
            database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            return dbFile != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    private void initTable() {

        if (database != null) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (" +
                        ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        URL + "  TEXT UNIQUE NOT NULL, " +
                        DATA + " TEXT UNIQUE NOT NULL," +
                        TIMESTAMP + "  INTEGER NOT NULL DEFAULT 0 " +
                        " );");

            } catch (Exception e) {

                e.printStackTrace();

            }
        }

    }


    public synchronized void saveToCache(String url, String jsontext) {


        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        if (database == null) {
            return;
        }

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(URL, url);
            contentValues.put(DATA, jsontext);
            contentValues.put(TIMESTAMP, date);

            long id=  database.insertWithOnConflict(TABLE,DATA,contentValues,SQLiteDatabase.CONFLICT_REPLACE);
            Log.e("id is a ",id+"");

        } catch (Exception e) {

        } finally {

        }


    }


    public synchronized String queryCache(String url) {
        if (database == null) {
            return null;
        }

        try {

            Cursor cursor = database.rawQuery("SELECT * FROM Cache WHERE url == ?", new String[]{url});
            while (cursor.moveToNext()) {
                return cursor.getString(cursor.getColumnIndex("data"));
            }


        } catch (Exception e) {

        } finally {

        }

        return null;
    }


    public void clearCache() {
        if (database != null) {
            try {
                database.delete(TABLE, null, null);
                database.setTransactionSuccessful();
            } catch (Exception e) {

            } finally {
                database.endTransaction();
            }
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (database != null) {
            try {
                database.close();
            } catch (Exception e) {

            }

        }
    }


}
