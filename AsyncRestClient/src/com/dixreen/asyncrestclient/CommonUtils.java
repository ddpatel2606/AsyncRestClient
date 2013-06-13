package com.dixreen.asyncrestclient;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;

public class CommonUtils {

	/** 
	 *  This Method is Checked that network is Available or Not 
	 *  If available Result Will be True
	 *  If not available Result Will be False  
	 */	
	public static boolean isNetworkAvailable(Context mContext) {
		
		/* getting systems Service connectivity manager */
		ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (mConnectivityManager != null) {
			NetworkInfo[] mNetworkInfos = mConnectivityManager
					.getAllNetworkInfo();
			if (mNetworkInfos != null) {
				for (int i = 0; i < mNetworkInfos.length; i++) {
					if (mNetworkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	
	   /** this method is for getting Display 
     *   Dimensions Weather this method is Deprecated or not**/
    public static Point getDisplaySize(final Display display) {
        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }
    
	
}
