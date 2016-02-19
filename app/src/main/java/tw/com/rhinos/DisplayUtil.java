package tw.com.rhinos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DisplayUtil {
    public static void showDialog(Context mContext, String title, String mesg){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(mesg);
        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
