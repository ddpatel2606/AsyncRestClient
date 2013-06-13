package com.dixreen.asyncrestclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

import com.dixreen.asynctask.CommonAsyncTask;

public class MainActivity extends Activity implements CommonAsyncTask.onAsynctaskCompletedListener<String>{

	 private static final String TAG = MainActivity.class.getSimpleName();
	  
	 CommonAsyncTask mTask;
	 private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	   
    @Override
    public void onBackPressed() 
    {
              
        /** If user Pressed BackButton While Running Splash screen
            this will close the Application(Not to Redirect NextScreen).
         */
        if (mTask != null && mTask.getStatus() != AsyncTask.Status.FINISHED)
        {
        	mTask.cancel(true);
        }  
        
        super.onBackPressed();
    }

    
    @Override
   	protected void onDestroy() {
   		// TODO Auto-generated method stub
   		super.onDestroy();
   		
   	    /** If user Pressed BackButton While Running Splash screen
        this will close the Application(Not to Redirect NextScreen).
     */
     if (mTask != null && mTask.getStatus() != AsyncTask.Status.FINISHED)
     {
    	mTask.cancel(true);
      }  
    
   	}


   	@Override
   	protected void onPause() {
   		// TODO Auto-generated method stub
   		super.onPause();
   		
   	 if (mProgressDialog != null)
     {
    	 if(mProgressDialog.isShowing())
    	 {
    		 mProgressDialog.dismiss();
    	 }
   		 
      }  
    
   	}

   	
	@Override
	public void onTaskCompleted(String result, int Request_id) {
		// TODO Auto-generated method stub
		
		
		if(mProgressDialog !=null && mProgressDialog.isShowing())
		{
			mProgressDialog.dismiss();
		}
		
		  try
		  {
			 

          } catch (Exception e)
          {   
        	  if(Consts.IsDebug)
              e.printStackTrace();
          }

	}

}
