package com.dixreen.asynctask;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.entity.mime.content.ByteArrayBody;

import android.content.Context;
import android.os.AsyncTask;

import com.dixreen.asyncrestclient.CommonUtils;
import com.dixreen.asyncrestclient.Consts;
import com.dixreen.http.RestClient;


/**
 * 
 * This Class extends {@link AsyncTask} & it will Handle All Network calls 
 * So, no need to make other {@link AsyncTask} class.
 *  
 *
 */


 public class CommonAsyncTask extends AsyncTask<Object, Integer, String>
{
	protected int mRequestId;
	 public onAsynctaskCompletedListener<String> CallBack;
	Context mActivityContext;
	String mKey;
		
	/**
	 * This is interface that take care of Asynctask Completed 
	 * & if Completed this will Send Result in Implemented Method of Activity
	 * @param <T>
	 */
	 public interface onAsynctaskCompletedListener<T>
	{
		  public void onTaskCompleted(String result,int Request_id);
	}
	
	/**
	 * This is constructor of CommonAsyncTask that take care of 
	 * 
	 * 1) Asynctask callback Register if not then pass null 
	 * 2) String Key if needed other Wise pass null
	 * 
	 */
	public CommonAsyncTask(Context context,onAsynctaskCompletedListener<String> callback,String key,int Request_id)
	{
		mActivityContext = context;
		CallBack         = callback;
		mKey             = key;
		setRequestId(Request_id);
	}

	
	

	 public void setRequestId (int requestId)
	 {
		 this.mRequestId = requestId;
	 }
	 
	 public int getRequestId ()
	 {  
		 return mRequestId;
	 }
	

	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}


	
	/**
	 * This is doInBackGround(Object... params) method of CommonAsyncTask that take care of Network Call
	 * 
	 * You have to Pass Params Like this way <br> 
	 * <br>
	 * CommonAsyncTask <b>mCommonTask</b> =new CommonAsyncTask(context,this,key);   <br>
	 *  <b>mCommonTask</b>.execute(Params1 ,Params2 , Params3, Params4);
	 * 
	 *  @param Params1 = URL 
	 *  @param Params2 = NetworkCallParams
	 *  @param Params3 = Request Method
	 *  @param Params4 = Image ByteData
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String doInBackground(Object... params) {
		// TODO Auto-generated method stub

		ByteArrayBody mBody=null;
		String Responce = "";
		String URL = "";
		String REQUEST_METHOD = "POST";
		LinkedHashMap<String, String> mParamsHasMap = new LinkedHashMap<String, String>();

		// First Param String Url
		try {
			URL = (String) params[0];
		} catch (Exception e) {
		}

		// Second Param LinkedHasMap Parameters
		try {
			mParamsHasMap = (LinkedHashMap<String, String>) params[1];
		} catch (Exception e) {
		}

		// Third Param String Request Method
		try {
			REQUEST_METHOD = (String) params[2];
		} catch (Exception e) {
		}

		// Fourth Param ByteArrayBody ImageByteArray
		try {
			mBody = (ByteArrayBody) params[3];
		} catch (Exception e) {
		}
		
		try {
			if (CommonUtils.isNetworkAvailable(mActivityContext)) {

				RestClient mClient = new RestClient(URL);

				// If Post image is not send
				if(mBody == null)
				{

					if (mParamsHasMap != null && mParamsHasMap.size() > 0) {
						for (Map.Entry<String, String> entry : mParamsHasMap
								.entrySet()) {
							mClient.addParam(entry.getKey(), entry.getValue());
						}
					}

					
					if (REQUEST_METHOD.compareTo("GET") == 0) {
						Responce = mClient.execute(RestClient.RequestMethod.GET);
					} else if (REQUEST_METHOD.compareTo("POST") == 0) {
						Responce = mClient.execute(RestClient.RequestMethod.POST);
					} else if (REQUEST_METHOD.compareTo("PUT") == 0) {
						Responce = mClient.execute(RestClient.RequestMethod.PUT);
					} else if (REQUEST_METHOD.compareTo("DELETE") == 0) {
						Responce = mClient.execute(RestClient.RequestMethod.DELETE);
					}
				}
				else
				{
					// If Post image is send With Params
					if (REQUEST_METHOD.compareTo("POST") == 0) {
						Responce = mClient.postImage(mBody, mParamsHasMap);
					} 
				}
			}

		} catch (Exception e) {

			if (Consts.IsDebug) {
				e.printStackTrace();
			}

		}

		return Responce;
	}
	

	@Override
	public void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
		
		if(CallBack != null)
		{
			CallBack.onTaskCompleted(result,getRequestId());
		}
				
	}


	@Override
	public void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}


	
	
}
