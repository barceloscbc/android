package com.puc.projeto.aluno.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.puc.projeto.aluno.service.UtilityService;
import com.puc.projeto.aluno.R;
import com.puc.projeto.aluno.common.Utils;


public class AttractionListActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AttractionListFragment())
                    .commit();
        }

        // Check fine location permission has been granted
        if (!Utils.checkFineLocationPermission(this)) {
            // See if user has denied permission in the past
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show a simple snackbar explaining the request instead
                showPermissionSnackbar();
            } else {
                // Otherwise request permission from user
                if (savedInstanceState == null) {
                    requestFineLocationPermission();
                }
            }
        } else {
            // Otherwise permission is granted (which is always the case on pre-M devices)
            fineLocationPermissionGranted();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UtilityService.requestLocation(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.test_notification:
                UtilityService.triggerWearTest(this, false);
                showDebugDialog(R.string.action_test_notification,
                        R.string.action_test_notification_dialog);
                return true;
            case R.id.test_microapp:
                UtilityService.triggerWearTest(this, true);
                showDebugDialog(R.string.action_test_microapp,
                        R.string.action_test_microapp_dialog);
                return true;
            case R.id.test_toggle_geofence:
                boolean geofenceEnabled = Utils.getGeofenceEnabled(this);
                Utils.storeGeofenceEnabled(this, !geofenceEnabled);
                Toast.makeText(this, geofenceEnabled ?
                        "Debug: Geofencing trigger disabled" :
                        "Debug: Geofencing trigger enabled", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Permissions request result callback
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fineLocationPermissionGranted();
                }
        }
    }

    /**
     * Request the fine location permission from the user
     */
    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQ);
    }

    /**
     * Run when fine location permission has been granted
     */
    private void fineLocationPermissionGranted() {
        UtilityService.addGeofences(this);
        UtilityService.requestLocation(this);
    }

    /**
     * Show a permission explanation snackbar
     */
    private void showPermissionSnackbar() {
        Snackbar.make(
                findViewById(R.id.container), R.string.permission_explanation, Snackbar.LENGTH_LONG)
                .setAction(R.string.permission_explanation_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestFineLocationPermission();
                    }
                })
                .show();
    }

    /**
     * Show a basic debug dialog to provide more info on the built-in debug
     * options.
     */
    private void showDebugDialog(int titleResId, int bodyResId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(titleResId)
                .setMessage(bodyResId)
                .setPositiveButton(android.R.string.ok, null);
        builder.create().show();
    }
}
