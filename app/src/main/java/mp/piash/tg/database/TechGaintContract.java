package mp.piash.tg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by piash on 6/13/16.
 */
public class TechGaintContract extends DatabaseHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WishlistTableColumns.TABLE_NAME + " ( " +
                    WishlistTableColumns._ID +  " INTEGER"+ " PRIMERY KEY, "
                    + WishlistTableColumns.BALANCE +  " INTEGER," +WishlistTableColumns.HEALTH +  " INTEGER," +WishlistTableColumns.EXPERIENCE +  " INTEGER," + WishlistTableColumns.COMPANY_EXPERIENCE +  " INTEGER" +" );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WishlistTableColumns.TABLE_NAME;

    public TechGaintContract(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL("insert into " + WishlistTableColumns.TABLE_NAME + "(" + WishlistTableColumns._ID   + ","
                + WishlistTableColumns.BALANCE + "," + WishlistTableColumns.HEALTH + "," + WishlistTableColumns.EXPERIENCE + "," + WishlistTableColumns.COMPANY_EXPERIENCE + ") values(0,0,0,0,0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static abstract class WishlistTableColumns implements BaseColumns {
        public static final String TABLE_NAME = "Wishlist";
        public static final String _ID = "Id";
        public static final String BALANCE = "balance";
        public static final String HEALTH = "health";
        public static final String EXPERIENCE = "experience";
        public static final String COMPANY_EXPERIENCE = "companyexperience";
    }
}
