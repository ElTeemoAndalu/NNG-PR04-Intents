package es.iessaladillo.pedrojoya.pr04.data.local.business;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import es.iessaladillo.pedrojoya.pr04.R;

public class FieldEnabler {

    private FieldEnabler() {
    }

    public static void enableOrDisableFieldState(Boolean condition, EditText txt, ImageView imgTxt, TextView lbl, String errorMsg) {
        //The conditions for these two fields apply the other way around
        if(lbl.getId() == R.id.lblName || lbl.getId() == R.id.lblAddress){
            condition = !condition;
        }

        if (!condition) {
            txt.setError(errorMsg);
        } else {
            txt.setError(null);
        }
        if (txt.getId() != R.id.txtName) {
            imgTxt.setEnabled(condition);
        }
        lbl.setEnabled(condition);
    }


}
