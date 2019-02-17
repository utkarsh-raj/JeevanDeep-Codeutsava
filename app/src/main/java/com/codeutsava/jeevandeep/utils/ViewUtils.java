package com.codeutsava.jeevandeep.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public final class ViewUtils {

	private ViewUtils() {
		// Never Called
	}

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showSnackbar(View view, String message) {
		Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
	}

	public static void hideKeyboard(View view) {
		if (view != null) {
			InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			view.clearFocus();
			if (manager != null) {
				manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		}
	}

	public static void showKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (!imm.isAcceptingText()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		}

	}

	public static void showAlertDialog(Context context, String title, String message) {

//		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(title)
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();


					}
				}).show();
	}
}
