package mytextandspeech.com.mytextandspeech;
import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class UserSession {

    int gID = 0;


    private SharedPreferences prefs;

    public UserSession(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserId(String userId) {
        prefs.edit().putString("userId", userId).commit();
    }

    public String getUserId() {
        String userId = prefs.getString("userId","");
        return userId;
    }
}
