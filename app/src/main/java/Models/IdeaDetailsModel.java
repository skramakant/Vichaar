package Models;

import android.database.Cursor;

import java.io.Serializable;

import Database.DatabaseOpenHelper;

/**
 * Created by ramakant on 23/7/16.
 */
public class IdeaDetailsModel implements Serializable {

    public int id;
    public String ideaTitle ;
    public String ideaCategory;

    public String ideaDescription;

    public int ideaUpVote ;
    public int ideaDownVote ;
    public int ideaViewCount ;
    public long ideaEntryDate ;
    public long ideaLastModificationDate ;

    public String personName;
    public String personEmail ;
    public String personMobile ;
    public String personProfileImageUrl ;

    public String ideaIsActive;

    public IdeaDetailsModel(){}

    public String getIdeaDescription() {
        return ideaDescription;
    }

    public void setIdeaDescription(String ideaDescription) {
        this.ideaDescription = ideaDescription;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getIdeaCategory() {
        return ideaCategory;
    }

    public void setIdeaCategory(String ideaCategory) {
        this.ideaCategory = ideaCategory;
    }

    public int getIdeaUpVote() {
        return ideaUpVote;
    }

    public void setIdeaUpVote(int ideaUpVote) {
        this.ideaUpVote = ideaUpVote;
    }

    public int getIdeaDownVote() {
        return ideaDownVote;
    }

    public void setIdeaDownVote(int ideaDownVote) {
        this.ideaDownVote = ideaDownVote;
    }

    public int getIdeaViewCount() {
        return ideaViewCount;
    }

    public void setIdeaViewCount(int ideaViewCount) {
        this.ideaViewCount = ideaViewCount;
    }

    public long getIdeaEntryDate() {
        return ideaEntryDate;
    }

    public void setIdeaEntryDate(long ideaEntryDate) {
        this.ideaEntryDate = ideaEntryDate;
    }

    public long getIdeaLastModificationDate() {
        return ideaLastModificationDate;
    }

    public void setIdeaLastModificationDate(long ideaLastModificationDate) {
        this.ideaLastModificationDate = ideaLastModificationDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getPersonProfileImageUrl() {
        return personProfileImageUrl;
    }

    public void setPersonProfileImageUrl(String personProfileImageUrl) {
        this.personProfileImageUrl = personProfileImageUrl;
    }

    public String getIdeaIsActive() {
        return ideaIsActive;
    }

    public void setIdeaIsActive(String ideaIsActive) {
        this.ideaIsActive = ideaIsActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static IdeaDetailsModel getListItem(Cursor cursor)
    {
        IdeaDetailsModel data = new IdeaDetailsModel();

        data.setIdeaTitle(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_TITLE)));
        data.setIdeaCategory(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_CATEGORY)));
        data.setIdeaDescription(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_DESCRIPTION)));
        data.setIdeaUpVote(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_UP_VOTE)));
        data.setIdeaDownVote(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_DOWN_VOTE)));
        data.setIdeaViewCount(cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_VIEW_COUNT)));
        data.setIdeaEntryDate(cursor.getLong(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_ENTRY_DATE)));
        data.setIdeaLastModificationDate(cursor.getLong(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_LAST_MODIFICATION_DATE)));
        data.setIdeaIsActive(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_IS_ACTIVE)));

        data.setPersonName(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_NAME)));
        data.setPersonEmail(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_EMAIL)));
        data.setPersonMobile(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_MOBILE)));
        data.setPersonProfileImageUrl(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_PROFILE_IMAGE_URL)));
        data.setId(cursor.getInt(cursor.getColumnIndex("_id")));

        return data;
    }
}
