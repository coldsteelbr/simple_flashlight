package ru.romanbrazhnikov.simpleflashlight.views;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.romanbrazhnikov.simpleflashlight.R;

public class MainActivity extends AppCompatActivity {
    static boolean isOn = false;

    // FIELDS
    Camera mCam;

    // WIDGETS
    @BindView(R.id.b_flashlight_switcher)
    Button bFlashlightSwitcher;

    // LISTENERS
    FlashlightSwitcherListener mFlashlightSwitcherListener = new FlashlightSwitcherListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bFlashlightSwitcher.setOnClickListener(mFlashlightSwitcherListener);
    }


    class FlashlightSwitcherListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            /*
            mCam = Camera.open();
            Camera.Parameters p = mCam.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCam.setParameters(p);
            SurfaceTexture mPreviewTexture = new SurfaceTexture(0);
            try {
                mCam.setPreviewTexture(mPreviewTexture);
            } catch (IOException ex) {
                // Ignore
            }
            mCam.startPreview();
            */


            if (!isOn) {
                mCam = Camera.open();
                Camera.Parameters parameters = mCam.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCam.setParameters(parameters);
                mCam.startPreview();
                isOn = true;
                bFlashlightSwitcher.setText("Turn Off");
            } else {

                Camera.Parameters parameters = mCam.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCam.setParameters(parameters);
                mCam.stopPreview();
                mCam.release();
                isOn = false;
                bFlashlightSwitcher.setText("Turn On");
            }

        }
    }
}
