package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vichaar.vichaar.DashboardDrawerActivity;

import Interfaces.InterfaceRefreshDashboard;
import Models.IdeaDetailsModel;


/**
 * Created by ramakant on 23/7/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static String databaseName = "IdeaDatabase";
    public static DatabaseOpenHelper dbHelper;
    private  InterfaceRefreshDashboard interfaceRefreshDashboard;


    public static String IDEA_TABLE = "ideaTable";
    public static String COMMENT_TABLE = "commentTable";

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

    public static String IDEA_ID_COMMENT = "ideaID";
    public static String IDEA_COMMENT = "ideaComment";
    public static String COMMENT_IS_ACTIVE = "isActive";


    String CreateDatabase = "CREATE TABLE IF NOT EXISTS "+IDEA_TABLE +" (_id integer primary key autoincrement, "+
            IDEA_TITLE + " TEXT, "+
            IDEA_CATEGORY + " TEXT, "+
            IDEA_DESCRIPTION + " TEXT, "+
            IDEA_UP_VOTE + " integer, "+
            IDEA_DOWN_VOTE + " integer, "+
            IDEA_VIEW_COUNT + " integer, "+
            IDEA_ENTRY_DATE + " long, "+
            IDEA_LAST_MODIFICATION_DATE + " long, "+
            PERSON_NAME + " TEXT, "+
            PERSON_EMAIL + " TEXT, "+
            PERSON_MOBILE + " TEXT, "+
            PERSON_PROFILE_IMAGE_URL + " TEXT, "+
            IDEA_IS_ACTIVE + " TEXT );";

    String CreateCommentTable = "CREATE TABLE IF NOT EXISTS "+COMMENT_TABLE +" (_id integer primary key autoincrement, "+
            IDEA_ID_COMMENT + " integer, "+
            COMMENT_IS_ACTIVE + " TEXT, "+
            IDEA_COMMENT + " TEXT );";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        interfaceRefreshDashboard = (InterfaceRefreshDashboard) context;
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
        db.execSQL(CreateCommentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIdea(String ideaTitle, String ideaCategory, String ideaDescription,
                               int ideaUpVote, int ideaDownVote, int ideaViewCount,
                               long ideaEntryDate,long ideaLastModificationDate,String personName,
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

    public void insertComment(int ideaId,String commentText){
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(IDEA_ID_COMMENT, ideaId);
            values.put(COMMENT_IS_ACTIVE,"true");
            values.put(IDEA_COMMENT,commentText);
            long rowInserted = db.insert(COMMENT_TABLE,null,values);
            Log.e("Row inserted", rowInserted + "  ");
        }
    }

    public void upVote(int current_count, int rowId){
        if(dbHelper != null){
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            String query = "UPDATE "+ IDEA_TABLE + " SET "+  IDEA_UP_VOTE + " = " + current_count + " WHERE " + "_id" + " = " + rowId;
            db.execSQL(query);
        }
    }

    public void DownVote(int current_count, int rowId){
        if(dbHelper != null){
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            String query = "UPDATE "+ IDEA_TABLE + " SET "+  IDEA_DOWN_VOTE + " = " + current_count + " WHERE " + "_id" + " = " + rowId;
            db.execSQL(query);
        }
    }

    public void Views(int current_count, int rowId){
        if(dbHelper != null){
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            String query = "UPDATE "+ IDEA_TABLE + " SET "+  IDEA_VIEW_COUNT + " = " + current_count + " WHERE " + "_id" + " = " + rowId;
            db.execSQL(query);
        }
    }

    public Cursor getIdeaDetails()
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            Cursor cursor = db.query(IDEA_TABLE,null,null,null,null,null,null);
            int totalIdeasCount = cursor.getCount();
            interfaceRefreshDashboard.refreshDashBoard(totalIdeasCount);
            return cursor;
        }

        return null;
    }

    public Cursor getNewIdeas()
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            Cursor cursor = db.query(IDEA_TABLE,null,null,null,null,null,"_id DESC");
            int totalIdeasCount = cursor.getCount();
            interfaceRefreshDashboard.refreshDashBoard(totalIdeasCount);
            return cursor;
        }

        return null;
    }

    public Cursor getTopFiveIdeas()
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            Cursor cursor = db.query(IDEA_TABLE,null,null,null,null,null,"ideaUpVote DESC","5");
            int totalIdeasCount = cursor.getCount();
            interfaceRefreshDashboard.refreshDashBoard(totalIdeasCount);
            return cursor;
        }

        return null;
    }

    public Cursor getComments(int ideaId)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            String whereNotNull = IDEA_ID_COMMENT + "= ?";
            //String whereNull = LIST_NAME + " IS NULL";
            String[] whereArgs = {String.valueOf(ideaId)};
            Cursor cursor = db.query(COMMENT_TABLE,null,whereNotNull,whereArgs,null,null,"_id DESC");
            //int totalIdeasCount = cursor.getCount();
            //interfaceRefreshDashboard.refreshDashBoard(totalIdeasCount);
            return cursor;
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