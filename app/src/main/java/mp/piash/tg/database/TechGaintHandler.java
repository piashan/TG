package mp.piash.tg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by piash on 6/13/16.
 */
public class TechGaintHandler {



    private static final String TAG = "TechGaintHandler";
    private TechGaintContract mTechGainContract;
    private SQLiteDatabase mDatabase;
    public TechGaintHandler(final Context context) {

        mTechGainContract = new TechGaintContract(context);
    }
    public  void open(){
        mDatabase= mTechGainContract.getWritableDatabase();
    }

    public  void close(){
        mTechGainContract.close();
    }

    public long insertBalance(int dealId){
        this.open();
        long rowAdded = 1L;

        ContentValues cv = new ContentValues();
        cv.put(TechGaintContract.WishlistTableColumns._ID, dealId);
        rowAdded += mDatabase.insert(TechGaintContract.WishlistTableColumns.TABLE_NAME, null, cv);
        return rowAdded;
    }


    public boolean delete(int dealId){
        this.open();
        mDatabase.delete(TechGaintContract.WishlistTableColumns.TABLE_NAME, TechGaintContract.WishlistTableColumns._ID + " = "+ dealId, null);
        return true;
    }


    public ArrayList<Integer>getAllWishlistData(){

        this.open();
        ArrayList<Integer> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.WishlistTableColumns.BALANCE
        };
        Cursor cursor = mDatabase.query(TechGaintContract.WishlistTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getInt(0));
        }
       // Collections.reverse(contactList);
        return contactList;
    }

    public  boolean updataData(int balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.WishlistTableColumns.BALANCE,balance);


        long check =mDatabase.update(TechGaintContract.WishlistTableColumns.TABLE_NAME, cv, TechGaintContract.WishlistTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public int getSingleData(int id) {
        this.open();

        Cursor cursor = mDatabase.query(TechGaintContract.WishlistTableColumns.TABLE_NAME,
                new String[]{TechGaintContract.WishlistTableColumns._ID, TechGaintContract.WishlistTableColumns.BALANCE},
                TechGaintContract.WishlistTableColumns._ID + "=" + 0, null, null, null, null);

        cursor.moveToFirst();

        int balance = cursor.getInt(cursor.getColumnIndex(TechGaintContract.WishlistTableColumns._ID));

        cursor.close();
        this.close();
        return balance;
    }

    public int isFavourite(int dealId){

        this.open();
        int a  = -1;
        String[] projection = {
                TechGaintContract.WishlistTableColumns._ID };
        String[] whereValues = {
                String.valueOf(dealId)};
        Cursor cursor = mDatabase.query(TechGaintContract.WishlistTableColumns.TABLE_NAME, projection, TechGaintContract.WishlistTableColumns._ID +" = ?", whereValues, null, null, null);

        if(cursor == null){
            return 0;
        }
        else {

           while (cursor.moveToNext()){
                a = cursor.getInt(0);
           }
            return a;
        }


    }
    public int getAllDataSize(){


        ArrayList<Integer> contactList = new ArrayList<>();
        Cursor cursor = mDatabase.query(TechGaintContract.WishlistTableColumns.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            contactList.add(cursor.getInt(0));
        }
        return contactList.size();
    }

    public void databaseClear(){

        this.open();
        mDatabase.delete(TechGaintContract.WishlistTableColumns.TABLE_NAME, null, null);
    }



}
