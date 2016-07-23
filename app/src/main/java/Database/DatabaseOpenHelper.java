package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Models.IdeaDetailsModel;


/**
 * Created by ramakant on 23/7/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static String databaseName = "IdeaDatabase";
    public static DatabaseOpenHelper dbHelper;


    public static String IDEA_TABLE = "ideaTable";

    public static String IDEA_TITLE = "ideaTitle";
    public static String IDEA_CATEGORY = "ideaCategory";
    public static String IDEA_DESCRIPTION = "ideaDescription";
    public static String IDEA_UP_VOTE = "ideaUpVote";
    public static String IDEA_DOWN_VOTE = "ideaDownVote";
    public static String IDEA_VIEW_COUNT = "ideaViewCount";
    public static String IDEA_ENTRY_DATE = "ideaEntryDate";
    public static String IDEA_LAST_MODIFICATION_DATE = "ideaLastModificationDate";
    public static String IDEA_IS_ACTIVE = "ideaIsActive";


    public static String PERSON_NAME = "personName";
    public static String PERSON_EMAIL = "personEmail";
    public static String PERSON_MOBILE = "personMobile";
    public static String PERSON_PROFILE_IMAGE_URL = "personProfileImageUrl";


    String CreateDatabase = "CREATE TABLE IF NOT EXISTS "+IDEA_TABLE +" (_id integer primary key autoincrement, "+
            IDEA_TITLE + " TEXT, "+
            IDEA_CATEGORY + " TEXT, "+
            IDEA_DESCRIPTION + " TEXT, "+
            IDEA_UP_VOTE + " TEXT, "+
            IDEA_DOWN_VOTE + " TEXT, "+
            IDEA_VIEW_COUNT + " TEXT, "+
            IDEA_ENTRY_DATE + " TEXT, "+
            IDEA_LAST_MODIFICATION_DATE + " TEXT, "+
            PERSON_NAME + " TEXT, "+
            PERSON_EMAIL + " TEXT, "+
            PERSON_MOBILE + " TEXT, "+
            PERSON_PROFILE_IMAGE_URL + " TEXT, "+
            IDEA_IS_ACTIVE + " TEXT );";


    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DatabaseOpenHelper getInstance(Context context)
    {
        if(dbHelper == null)
        {
            dbHelper = new DatabaseOpenHelper(context,DatabaseOpenHelper.databaseName,null,1);
        }

        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIdea(String ideaTitle, String ideaCategory, String ideaDescription,
                               String ideaUpVote, String ideaDownVote, String ideaViewCount,
                               String ideaEntryDate,String ideaLastModificationDate,String personName,
                               String personEmail,String personMobile,String personProfileImageUrl,
                               String ideaIsActive)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(IDEA_TITLE, ideaTitle);
            values.put(IDEA_CATEGORY,ideaCategory);
            values.put(IDEA_DESCRIPTION,ideaDescription);
            values.put(IDEA_UP_VOTE,ideaUpVote);
            values.put(IDEA_DOWN_VOTE,ideaDownVote);
            values.put(IDEA_VIEW_COUNT,ideaViewCount);
            values.put(IDEA_ENTRY_DATE, ideaEntryDate);
            values.put(IDEA_LAST_MODIFICATION_DATE, ideaLastModificationDate);

            values.put(PERSON_NAME, personName);
            values.put(PERSON_EMAIL, personEmail);
            values.put(PERSON_MOBILE, personMobile);
            values.put(PERSON_PROFILE_IMAGE_URL, personProfileImageUrl);

            values.put(IDEA_IS_ACTIVE, ideaIsActive);
            long rowInserted = db.insert(IDEA_TABLE,null,values);
            Log.e("Row inserted", rowInserted + "  ");
        }

    }

    public Cursor getIdeaDetails()
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            return db.query(IDEA_TABLE,null,null,null,null,null,null);
        }

        return null;
    }

/*    public void updateCardData(int rowId,String cardNumber, String fromDateMonth, String fromDateYear, String toDateMonth, String toDateYear, String cvv, String name)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CardNumber, cardNumber);
            values.put(FromDateMonth,fromDateMonth);
            values.put(FromDateYear,fromDateYear);
            values.put(ToDateMonth,toDateMonth);
            values.put(ToDateYear,toDateYear);
            values.put(CVV,cvv);
            values.put(Name, name);
            long rowUpdated = db.update(CardTable,values,"_id = ?",new String[] {String.valueOf(rowId)});
            Log.e("Row Updated", rowUpdated + "  ");
        }
    }*/



    public IdeaDetailsModel getDataFromRowid(Integer rowid)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            Cursor cursor =  db.query(IDEA_TABLE, null, "_id = ?", new String[]{String.valueOf(rowid)}, null, null, null);

            if(cursor!=null)
            {
                cursor.moveToFirst();
                IdeaDetailsModel data = IdeaDetailsModel.getListItem(cursor);
                return data;
            }

        }

        return null;
    }
}