package com.kagii.clickcounter.data;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kagii.clickcounter.R;

/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 * 
 * @author kevingalligan
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	/************************************************
	 * Suggested Copy/Paste code. Everything from here to the done block.
	 ************************************************/

	private static final String DATABASE_NAME = "click.db";
	private static final int DATABASE_VERSION = 6;

	private Dao<ClickGroup, Integer> groupDao;
	private Dao<ClickCount, Integer> clickDao;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	/************************************************
	 * Suggested Copy/Paste Done
	 ************************************************/

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, ClickGroup.class);
			TableUtils.createTable(connectionSource, ClickCount.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
		try {
			TableUtils.dropTable(connectionSource, ClickGroup.class, true);
			TableUtils.dropTable(connectionSource, ClickCount.class, true);
			onCreate(sqliteDatabase, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
					+ newVer, e);
		}
	}

	public Dao<ClickGroup, Integer> getGroupDao() throws SQLException {
		if (groupDao == null) {
			groupDao = getDao(ClickGroup.class);
		}
		return groupDao;
	}

	public Dao<ClickCount, Integer> getClickDao() throws SQLException {
		if (clickDao == null) {
			clickDao = getDao(ClickCount.class);
		}
		return clickDao;
	}
}
