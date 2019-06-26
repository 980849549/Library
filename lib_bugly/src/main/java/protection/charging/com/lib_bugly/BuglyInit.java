package protection.charging.com.lib_bugly;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

import java.util.Locale;

public class BuglyInit {


    public static final String BUGLY_APP_ID = "4d58b0ee12";
    public static final String BUGLY_APP_KEY = "0e16223f-172e-4a7d-b80c-c5e13e0c3284";

    public static void initBugly(Context context) {


        Bugly.init(context, BUGLY_APP_ID, true);

    }

}
