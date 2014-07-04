package dev.android.ormlite.util;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import dev.android.ormlite.dao.Account;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	
	private static Class<?>[] setClasses = new Class<?>[] { Account.class };
	
	public static void main(String arg[]) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt",setClasses);
	}
}
