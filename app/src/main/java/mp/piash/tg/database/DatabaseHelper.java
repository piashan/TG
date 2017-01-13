package mp.piash.tg.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kazal on 05-Mar-16.
 */
public abstract class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "techgaint.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}