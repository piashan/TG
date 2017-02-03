package mp.piash.tg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by piash on 6/13/16.
 */
public class TechGaintContract extends DatabaseHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TechGaintTableColumns.TABLE_NAME + " ( " +
                    TechGaintTableColumns._ID +  " INTEGER"+ " PRIMERY KEY, "
                    + TechGaintTableColumns.CASH +  " INTEGER," + TechGaintTableColumns.PERSONAL_BANK_ACCOUNT +  " INTEGER," + TechGaintTableColumns.COMPANY_BANK_ACCOUNT +  " INTEGER," + TechGaintTableColumns.HEALTH +  " REAL," + TechGaintTableColumns.EXPERIENCE +  " REAL," + TechGaintTableColumns.COMPANY_EXPERIENCE +  " REAL" +" );";
    private static final String SQL_CREATE_ENTRIES_TWO =
            "CREATE TABLE " + TechGaintTableColumns.TABLE_NAME_TWO+ " ( " +
                    TechGaintTableColumns._ID +  " INTEGER"+ " PRIMERY KEY, "
                    + TechGaintTableColumns.TRACE +  " INTEGER," + TechGaintTableColumns.TITLE +  " TEXT" +" );";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TechGaintTableColumns.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_TWO =
            "DROP TABLE IF EXISTS " + TechGaintTableColumns.TABLE_NAME_TWO;

    public TechGaintContract(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_TWO);
        db.execSQL("insert into " + TechGaintTableColumns.TABLE_NAME + "(" + TechGaintTableColumns._ID   + ","
                + TechGaintTableColumns.CASH + ","
                + TechGaintTableColumns.PERSONAL_BANK_ACCOUNT + ","
                + TechGaintTableColumns.COMPANY_BANK_ACCOUNT + ","
                + TechGaintTableColumns.HEALTH + ","
                + TechGaintTableColumns.EXPERIENCE + ","
                + TechGaintTableColumns.COMPANY_EXPERIENCE + ") values(0,50,0,0,0,0,0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_TWO);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_TWO);
        onCreate(db);
    }

    public static abstract class TechGaintTableColumns implements BaseColumns {
        public static final String TABLE_NAME = "TechGaintTableOne";
        public static final String TABLE_NAME_TWO = "TechGaintTableTwo";
        public static final String _ID = "Id";
        public static final String CASH = "Cash";
        public static final String PERSONAL_BANK_ACCOUNT = "PersonalBankAccount";
        public static final String COMPANY_BANK_ACCOUNT = "CompanyBankAccount";
        public static final String HEALTH = "Health";
        public static final String EXPERIENCE = "Experience";
        public static final String COMPANY_EXPERIENCE = "Companyexperience";
        public static final String TRACE = "Trace";
        public static final String TITLE = "Title";
    }
}
