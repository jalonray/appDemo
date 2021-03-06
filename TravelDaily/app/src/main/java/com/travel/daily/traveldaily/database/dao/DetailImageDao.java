package com.travel.daily.traveldaily.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.travel.daily.traveldaily.database.dao.DetailImage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DETAIL_IMAGE".
*/
public class DetailImageDao extends AbstractDao<DetailImage, Long> {

    public static final String TABLENAME = "DETAIL_IMAGE";

    /**
     * Properties of entity DetailImage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Pic0 = new Property(1, String.class, "pic0", false, "PIC0");
        public final static Property Pic1 = new Property(2, String.class, "pic1", false, "PIC1");
        public final static Property Pic2 = new Property(3, String.class, "pic2", false, "PIC2");
    };


    public DetailImageDao(DaoConfig config) {
        super(config);
    }
    
    public DetailImageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DETAIL_IMAGE\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PIC0\" TEXT," + // 1: pic0
                "\"PIC1\" TEXT," + // 2: pic1
                "\"PIC2\" TEXT);"); // 3: pic2
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DETAIL_IMAGE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DetailImage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String pic0 = entity.getPic0();
        if (pic0 != null) {
            stmt.bindString(2, pic0);
        }
 
        String pic1 = entity.getPic1();
        if (pic1 != null) {
            stmt.bindString(3, pic1);
        }
 
        String pic2 = entity.getPic2();
        if (pic2 != null) {
            stmt.bindString(4, pic2);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DetailImage readEntity(Cursor cursor, int offset) {
        DetailImage entity = new DetailImage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // pic0
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // pic1
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // pic2
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DetailImage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPic0(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPic1(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPic2(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DetailImage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DetailImage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
