/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

// This activity is responsible for displaying the master list of all images
// Implement the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegIndex;

    public static final String HEAD_INDEX_KEY = "head_index";
    public static final String BODY_INDEX_KEY = "body_index";
    public static final String LEG_INDEX_KEY = "leg_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        // Completed (2) Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        int bodyPartType = position / 12;

        int listIndex = position - 12 * bodyPartType;

        switch (bodyPartType) {
            case 0:
                mHeadIndex = listIndex;
                break;
            case 1:
                mBodyIndex = listIndex;
                break;
            case 2:
                mLegIndex = listIndex;
                break;
        }

        // Completed (3) Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle bundle = new Bundle();
        bundle.putInt(HEAD_INDEX_KEY, mHeadIndex);
        bundle.putInt(BODY_INDEX_KEY, mBodyIndex);
        bundle.putInt(LEG_INDEX_KEY, mLegIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);
        // Completed (4) Get a reference to the "Next" button and launch the intent when this button is clicked
        Button button = (Button) findViewById(R.id.bt_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

}
