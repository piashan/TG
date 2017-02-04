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
        cv.put(TechGaintContract.TechGaintTableColumns._ID, dealId);
        rowAdded += mDatabase.insert(TechGaintContract.TechGaintTableColumns.TABLE_NAME, null, cv);
        return rowAdded;
    }
    public long insertForTrace(double id, String title){
        this.open();
        long rowAdded = 1L;

        ContentValues cv = new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.TRACE, id);
        cv.put(TechGaintContract.TechGaintTableColumns.TITLE, title);
        rowAdded += mDatabase.insert(TechGaintContract.TechGaintTableColumns.TABLE_NAME_TWO, null, cv);
        return rowAdded;
    }


    public boolean delete(int dealId){
        this.open();
        mDatabase.delete(TechGaintContract.TechGaintTableColumns.TABLE_NAME, TechGaintContract.TechGaintTableColumns._ID + " = "+ dealId, null);
        return true;
    }


    public ArrayList<Integer> getAllCash(){

        this.open();
        ArrayList<Integer> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.CASH
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getInt(0));
        }
        return contactList;
    }
    public ArrayList<Integer> getAllEquity(){

        this.open();
        ArrayList<Integer> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.EQUITY
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getInt(0));
        }
        return contactList;
    }

    public ArrayList<Integer> getAllCompanyPersonalBankAccount(){

        this.open();
        ArrayList<Integer> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.PERSONAL_BANK_ACCOUNT
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getInt(0));
        }
        return contactList;
    }
    public ArrayList<Double> getAllHealthData(){

        this.open();
        ArrayList<Double> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.HEALTH
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getDouble(0));
        }
        return contactList;
    }
    public ArrayList<Double> getAllExperienceData(){

        this.open();
        ArrayList<Double> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.EXPERIENCE
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getDouble(0));
        }
        return contactList;
    }
    public ArrayList<Double> getAllCompanyExperienceData(){

        this.open();
        ArrayList<Double> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.COMPANY_EXPERIENCE
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getDouble(0));
        }
        return contactList;
    }

    public ArrayList<String> getAllAchievement(){

        this.open();
        ArrayList<String> contactList = new ArrayList<>();

        String[] projection = {
                TechGaintContract.TechGaintTableColumns.TITLE
        };
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME_TWO, projection, null, null, null, null, null);

        while (cursor.moveToNext()){

            contactList.add(cursor.getString(0));
        }
        return contactList;
    }


    public  boolean updateCash(int balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.CASH,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }

    public  boolean updateEquity(int balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.EQUITY,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }

    public  boolean updatePersonalBankAccount(int balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.PERSONAL_BANK_ACCOUNT,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public  boolean updatePersonalCompanyBankAccount(int balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.COMPANY_BANK_ACCOUNT,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public  boolean updateHealth(double balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.HEALTH,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public  boolean updateExperience(double balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.EXPERIENCE,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public  boolean updateCompanyExperience(double balance){

        this.open();
        ContentValues cv=new ContentValues();
        cv.put(TechGaintContract.TechGaintTableColumns.COMPANY_EXPERIENCE,balance);


        long check =mDatabase.update(TechGaintContract.TechGaintTableColumns.TABLE_NAME, cv, TechGaintContract.TechGaintTableColumns._ID + " = " + 0, null);
        if (check>0){
            return  true;
        }
        else
            return  false;
    }
    public int getSingleData(int id) {
        this.open();

        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME,
                new String[]{TechGaintContract.TechGaintTableColumns._ID, TechGaintContract.TechGaintTableColumns.CASH},
                TechGaintContract.TechGaintTableColumns._ID + "=" + 0, null, null, null, null);

        cursor.moveToFirst();

        int balance = cursor.getInt(cursor.getColumnIndex(TechGaintContract.TechGaintTableColumns._ID));

        cursor.close();
        this.close();
        return balance;
    }

    public int isExists(int id){

        this.open();
        int a  = -1;
        String[] projection = {
                TechGaintContract.TechGaintTableColumns.TRACE };
        String[] whereValues = {
                String.valueOf(id)};
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME_TWO, projection, TechGaintContract.TechGaintTableColumns.TRACE +" = ?", whereValues, null, null, null);

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
        Cursor cursor = mDatabase.query(TechGaintContract.TechGaintTableColumns.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            contactList.add(cursor.getInt(0));
        }
        return contactList.size();
    }

    public void databaseClear(){

        this.open();
        mDatabase.delete(TechGaintContract.TechGaintTableColumns.TABLE_NAME, null, null);
    }



}
