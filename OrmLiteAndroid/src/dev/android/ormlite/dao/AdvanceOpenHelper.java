package dev.android.ormlite.dao;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import dev.android.ormlite.R;

public class AdvanceOpenHelper extends OrmLiteSqliteOpenHelper {
	
	//name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "account.db";
	//any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;
	
	// the DAO object we use to access the SimpleData table
	private Dao<Account, Integer> simpleDao = null;
	
	private RuntimeExceptionDao<Account, Integer> simpleRuntimeDao = null;

	
	public AdvanceOpenHelper(Context context, String databaseName,
			CursorFactory factory, int databaseVersion, File configFile) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
		
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) 
	{
		try
		{
			Log.i(AdvanceOpenHelper.class.getName(), "onCreate");
			
			TableUtils.createTable(connectionSource,Account.class);
			
			//here we try to insert the 
			RuntimeExceptionDao<Account,Integer> dao = getRuntimeExceptionDAO();
			
			Account mAccount = new Account();
			
			mAccount.setName("OPEN SOURCE");
			
			mAccount.setPassword("SQLITE");
			
			dao.create(mAccount);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int arg2,int arg3)
	{
		try
		{
			Log.i(AdvanceOpenHelper.class.getName(), "onUpgrade");
			
			TableUtils.dropTable(connectionSource, Account.class, true);
			
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	
	public RuntimeExceptionDao<Account,Integer> getRuntimeExceptionDAO(){
		try
		{
			if(simpleRuntimeDao == null)
			{
				simpleRuntimeDao = getRuntimeExceptionDao(Account.class);
			}
			
		}catch(Exception e)
		{
			
		}
		
		return simpleRuntimeDao;
	}
	
	public Dao<Account,Integer> geAccountDAO(){
		try
		{
			if(simpleDao == null)
			{
				simpleDao = getDao(Account.class);
			}
			
		}catch(Exception e)
		{
			
		}
		
		return simpleDao;
	}
	
	public void close()
	{
		simpleDao = null;
		simpleRuntimeDao = null;
	}
}
