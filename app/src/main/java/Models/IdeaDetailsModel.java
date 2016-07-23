package Models;

import android.database.Cursor;

import Database.DatabaseOpenHelper;

/**
 * Created by ramakant on 23/7/16.
 */
public class IdeaDetailsModel {

    public int id;
    public String ideaTitle ;
    public String ideaCategory;

    public String ideaDescription;
    public String ideaUpVote ;
    public String ideaDownVote ;
    public String ideaViewCount ;
    public String ideaEntryDate ;
    public String ideaLastModificationDate ;

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

    public String getIdeaUpVote() {
        return ideaUpVote;
    }

    public void setIdeaUpVote(String ideaUpVote) {
        this.ideaUpVote = ideaUpVote;
    }

    public String getIdeaDownVote() {
        return ideaDownVote;
    }

    public void setIdeaDownVote(String ideaDownVote) {
        this.ideaDownVote = ideaDownVote;
    }

    public String getIdeaViewCount() {
        return ideaViewCount;
    }

    public void setIdeaViewCount(String ideaViewCount) {
        this.ideaViewCount = ideaViewCount;
    }

    public String getIdeaEntryDate() {
        return ideaEntryDate;
    }

    public void setIdeaEntryDate(String ideaEntryDate) {
        this.ideaEntryDate = ideaEntryDate;
    }

    public String getIdeaLastModificationDate() {
        return ideaLastModificationDate;
    }

    public void setIdeaLastModificationDate(String ideaLastModificationDate) {
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
        data.setIdeaUpVote(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_UP_VOTE)));
        data.setIdeaDownVote(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_DOWN_VOTE)));
        data.setIdeaEntryDate(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_ENTRY_DATE)));
        data.setIdeaLastModificationDate(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_LAST_MODIFICATION_DATE)));
        data.setIdeaIsActive(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_IS_ACTIVE)));

        data.setPersonName(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_NAME)));
        data.setPersonEmail(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_EMAIL)));
        data.setPersonMobile(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_MOBILE)));
        data.setPersonProfileImageUrl(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_PROFILE_IMAGE_URL)));
        data.setId(cursor.getInt(cursor.getColumnIndex("_id")));

        return data;
    }
}
