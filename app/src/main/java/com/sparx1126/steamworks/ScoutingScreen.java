package com.sparx1126.steamworks;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.sparx1126.steamworks.components.Utility;

import org.gosparx.scouting.aerialassist.DatabaseHelper;
import org.gosparx.scouting.aerialassist.TeamData;
import org.gosparx.scouting.aerialassist.ScoutingData;
import org.gosparx.scouting.aerialassist.dto.Event;
import org.gosparx.scouting.aerialassist.dto.Match;
import org.gosparx.scouting.aerialassist.networking.BlueAlliance;
import org.gosparx.scouting.aerialassist.networking.NetworkCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static com.sparx1126.steamworks.R.layout.scouting_screen;

public class ScoutingScreen extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    //private TeamData currentTeam;
    //private ScoutingData scoutingBeingEntered;
    private SharedPreferences settings;
    private Map<String, String> matchMap;

    private EditText qualMatchNumber;
    private ToggleButton crossedBaseLineAutoInput;
    private EditText hoppersDumpedAutoInput;
    private CheckBox putGearLeftAuto;
    private CheckBox putGearCenterAuto;
    private CheckBox putGearRightAuto;
    private EditText gearsScoredTeleInput;
    //private EditText gearsDeliveredInput;
    private EditText numberOfGearsFromFloorInput;
    private EditText numberOfGearsFromHumanInput;
    private RadioButton scoresHighNeverAuto;
    private RadioButton scoresHighSometimesAuto;
    private RadioButton scoresHighOftenAuto;
    private RadioButton scoresLowNeverAuto;
    private RadioButton scoresLowSometimesAuto;
    private RadioButton scoresLowOftenAuto;
    private EditText numberOfBallsScoredHighGoalInput;
    private EditText timesCollectedFromHumanInput;
    private EditText timesCollectedFromHopperInput;
    private EditText timesCollectedFromFloorInput;
    private EditText fuelScoredLowGoalCycleInput;
    private EditText numberOfLowGoalCyclesInput;
    private RadioButton highGoalAccuracyScoutPoor;
    private RadioButton highGoalAccuracyScoutOk;
    private RadioButton highGoalAccuracyScoutGreat;
    private ToggleButton didScaleInput;
    private EditText scaledFromWhereInput;
    private EditText scoutingComments;
    private ToggleButton matchScoutedInput;
    private RadioButton scoresHighDuringAuto;
    private RadioButton scoresLowDuringAuto;
    private RadioButton doesntScoreDuringAuto;
    private LinearLayout allKnowingLinear;
    private Utility utility;
    private String teamNumber = "";

    private boolean redAlliance;
    private int allianceNumber;

    //   Zzzzz  |\      _,,,--,,_
    //          /,`.-'`'   ._  \-;;,_
    //         |,4-  ) )_   .;.(  `'-'
    //  doggo '---''(_/._)-'(_\_)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(scouting_screen);

         matchMap  = new HashMap<>();

        redAlliance = settings.getBoolean("red alliance", false);
        allianceNumber = settings.getInt("team selected", 0);
        allKnowingLinear = (LinearLayout) findViewById(R.id.allKnowingLinear);
        allKnowingLinear.setVisibility(View.INVISIBLE);
        qualMatchNumber = (EditText) findViewById(R.id.qualMatchEdit);
        qualMatchNumber.addTextChangedListener(matchTextEntered);

        dbHelper = DatabaseHelper.getInstance(this);
        utility = Utility.getInstance();
        //currentTeam = TeamData.getCurrentTeam();
        //String key = String.valueOf(currentTeam.getTeamNumber()) + "_" + utility.getEpoch();
        //scoutingBeingEntered = new ScoutingData(key, currentTeam.getTeamNumber(), currentTeam.getEventName(), currentTeam.getStudent());

        ImageButton home = (ImageButton) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        crossedBaseLineAutoInput = (ToggleButton) findViewById(R.id.crossedBaseLineAutoInput);
        hoppersDumpedAutoInput = (EditText) findViewById(R.id.hoppersDumpedAutoInput);
        putGearLeftAuto = (CheckBox) findViewById(R.id.putGearLeftAuto);
        putGearCenterAuto = (CheckBox) findViewById(R.id.putGearCenterAuto);
        putGearRightAuto = (CheckBox) findViewById(R.id.putGearRightAuto);
        gearsScoredTeleInput = (EditText) findViewById(R.id.gearsScoredTeleInput).findViewById(R.id.edit_text);
        //gearsDeliveredInput = (EditText) findViewById(R.id.gearsDeliveredInput).findViewById(R.id.edit_text);
        numberOfGearsFromFloorInput = (EditText) findViewById(R.id.numberOfGearsFromFloorInput).findViewById(R.id.edit_text);
        numberOfGearsFromHumanInput = (EditText) findViewById(R.id.numberOfGearsFromHumanInput).findViewById(R.id.edit_text);
        scoresHighNeverAuto = (RadioButton) findViewById(R.id.scoresHighNeverAuto);
        scoresHighSometimesAuto = (RadioButton) findViewById(R.id.scoresHighSometimesAuto);
        scoresHighOftenAuto = (RadioButton) findViewById(R.id.scoresHighOftenAuto);
        scoresLowNeverAuto = (RadioButton) findViewById(R.id.scoresLowNeverAuto);
        scoresLowSometimesAuto = (RadioButton) findViewById(R.id.scoresLowSometimesAuto);
        scoresLowOftenAuto = (RadioButton) findViewById(R.id.scoresLowOftenAuto);
        numberOfBallsScoredHighGoalInput = (EditText) findViewById(R.id.numberOfBallsScoredHighGoalInput).findViewById(R.id.edit_text);
        timesCollectedFromHumanInput = (EditText) findViewById(R.id.timesCollectedFromHumanInput).findViewById(R.id.edit_text);
        timesCollectedFromHopperInput = (EditText) findViewById(R.id.timesCollectedFromHopperInput).findViewById(R.id.edit_text);
        timesCollectedFromFloorInput = (EditText) findViewById(R.id.timesCollectedFromFloorInput).findViewById(R.id.edit_text);
        fuelScoredLowGoalCycleInput = (EditText) findViewById(R.id.fuelScoredLowGoalCycleInput).findViewById(R.id.edit_text);
        numberOfLowGoalCyclesInput = (EditText) findViewById(R.id.numberOfLowGoalCyclesInput).findViewById(R.id.edit_text);
        highGoalAccuracyScoutPoor = (RadioButton) findViewById(R.id.highGoalAccuracyScoutPoor);
        highGoalAccuracyScoutOk = (RadioButton) findViewById(R.id.highGoalAccuracyScoutOk);
        highGoalAccuracyScoutGreat = (RadioButton) findViewById(R.id.highGoalAccuracyScoutGreat);
        didScaleInput = (ToggleButton) findViewById(R.id.didTheyScale);
        scaledFromWhereInput = (EditText) findViewById(R.id.scaledFromWhereInput);
        scoutingComments = (EditText) findViewById(R.id.commentsScouting);
        matchScoutedInput = (ToggleButton) findViewById(R.id.matchScouted);
        Button submitScouting = (Button) findViewById(R.id.submitScouting);
        submitScouting.setOnClickListener(submitButtonClicked);
        scoresHighDuringAuto = (RadioButton) findViewById(R.id.scoresHighAuto);
        scoresLowDuringAuto = (RadioButton) findViewById(R.id.scoresLowAuto);
        doesntScoreDuringAuto = (RadioButton) findViewById(R.id.doesntScoreAuto);
        settings = getSharedPreferences(getResources().getString(R.string.pref_name), 0);

        downloadMatchData();


        /*ActionBar bar = getSupportActionBar();
        if(bar != null) {
            bar.setTitle(getString(R.string.scouting_title) + String.valueOf(currentTeam.getTeamNumber()));
        }*/
    }

    protected void onDestroy() {
        super.onDestroy();
        //saveData();
        /*if(scoutingBeingEntered.isMatchScouted()) {
            currentTeam.addScoutingData(scoutingBeingEntered);
            dbHelper.createScoutingData(scoutingBeingEntered);
        }*/
    }

    private final TextWatcher matchTextEntered = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {


           if((qualMatchNumber != null) || (!Objects.equals(qualMatchNumber, ""))){
               allKnowingLinear.setVisibility(View.VISIBLE);
               String key = matchMap.get(qualMatchNumber.getText().toString());
               Match match = dbHelper.getMatch(key);
               if(redAlliance){
                   teamNumber = match.getAlliances().getRed().getTeams().get(allianceNumber);
               }
               else if(!redAlliance){
                   teamNumber = match.getAlliances().getBlue().getTeams().get(allianceNumber);
               }
               System.out.println(match.getAlliances().getRed().getTeams().get(0));
           }
           else{
               allKnowingLinear.setVisibility(View.INVISIBLE);
           }
        }
    };



    private void downloadMatchData() {
        final Dialog alert = utility.createDialog(ScoutingScreen.this, getString(R.string.downloading_data), "Match Data");
        alert.show();
        //get the event
        String event_key = settings.getString(getResources().getString(R.string.pref_event_key), "");
        final Event e = dbHelper.getEvent(event_key);
        BlueAlliance ba = BlueAlliance.getInstance(this);
        //load matches for the event
        ba.loadMatches(e, new NetworkCallback() {
            @Override
            public void handleFinishDownload(final boolean success) {
                ScoutingScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alert.dismiss();
                        if (!success)
                            utility.alertUser(ScoutingScreen.this, ScoutingScreen.this.getString(R.string.failure), "Downloading match data failed").show();

                        try (Cursor matchCursor = dbHelper.createMatchCursor(e)) {
                            while (matchCursor.moveToNext()) {
                                String matchNumber = matchCursor.getString(matchCursor.getColumnIndex(DatabaseHelper.TABLE_MATCHES_MATCH_NUMBER));
                                String matchKey = matchCursor.getString(matchCursor.getColumnIndex(DatabaseHelper.TABLE_MATCHES_KEY));
                                matchMap.put(matchNumber, matchKey);
                            }
                        }
                    }
                });
            }
        });
    }

    /*private void saveData() {
        scoutingBeingEntered.setCrossedBaseline(crossedBaseLineAutoInput.isChecked());
        String valueAsSring = hoppersDumpedAutoInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setHoppersDumped(Integer.parseInt(valueAsSring));
        }
        scoutingBeingEntered.setGearScoredLeftAuto(putGearLeftAuto.isChecked());
        scoutingBeingEntered.setGearScoredCenterAuto(putGearCenterAuto.isChecked());
        scoutingBeingEntered.setGearScoredRightAuto(putGearRightAuto.isChecked());
        valueAsSring = gearsScoredTeleInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setGearsScored(Integer.parseInt(valueAsSring));
        }
        valueAsSring = numberOfGearsFromFloorInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setGearsCollectedFromFloor(Integer.parseInt(valueAsSring));
        }
        valueAsSring = numberOfGearsFromHumanInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setGearsFromHuman(Integer.parseInt(valueAsSring));
        }
        if (scoresHighNeverAuto.isChecked()) {
            scoutingBeingEntered.setScoresHighAuto("scoresHighNeverAuto");
        } else if (scoresHighSometimesAuto.isChecked()) {
            scoutingBeingEntered.setScoresHighAuto("scoresHighSometimesAuto");
        } else if (scoresHighOftenAuto.isChecked()) {
            scoutingBeingEntered.setScoresHighAuto("scoresHighOftenAuto");
        }
        if (scoresLowNeverAuto.isChecked()) {
            scoutingBeingEntered.setScoresLowAuto("scoresLowNeverAuto");
        } else if (scoresLowSometimesAuto.isChecked()) {
            scoutingBeingEntered.setScoresLowAuto("scoresLowSometimesAuto");
        } else if (scoresLowOftenAuto.isChecked()) {
            scoutingBeingEntered.setScoresLowAuto("scoresLowOftenAuto");
        }
        valueAsSring = numberOfBallsScoredHighGoalInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setBallsInHighCycle(Integer.parseInt(valueAsSring));
        }
        valueAsSring = timesCollectedFromHumanInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setBallsFromHuman(Integer.parseInt(valueAsSring));
        }
        valueAsSring = timesCollectedFromHopperInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setBallsFromHopper(Integer.parseInt(valueAsSring));
        }
        valueAsSring = timesCollectedFromFloorInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setBallsFromFloor(Integer.parseInt(valueAsSring));
        }
        valueAsSring = fuelScoredLowGoalCycleInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setFuelInLowCycle(Integer.parseInt(valueAsSring));
        }
        valueAsSring = numberOfLowGoalCyclesInput.getText().toString();
        if (!valueAsSring.isEmpty()) {
            scoutingBeingEntered.setNumberOfLowCycles(Integer.parseInt(valueAsSring));
        }
        if (highGoalAccuracyScoutPoor.isChecked()) {
            scoutingBeingEntered.setHighGoalAccuracy("highGoalAccuracyScoutPoor");
        } else if (highGoalAccuracyScoutOk.isChecked()) {
            scoutingBeingEntered.setHighGoalAccuracy("highGoalAccuracyScoutOk");
        } else if (highGoalAccuracyScoutGreat.isChecked()) {
            scoutingBeingEntered.setHighGoalAccuracy("highGoalAccuracyScoutGreat");
        }
        if (scoresHighDuringAuto.isChecked()) {
            scoutingBeingEntered.setAutoShooting("scoresHighAuto");
        } else if (scoresLowDuringAuto.isChecked()) {
            scoutingBeingEntered.setAutoShooting("scoresLowAuto");
        } else if (doesntScoreDuringAuto.isChecked()) {
            scoutingBeingEntered.setAutoShooting("doesntScoreAuto");
        }
        scoutingBeingEntered.setDidScale(didScaleInput.isChecked());
        scoutingBeingEntered.setWhereScaled(scaledFromWhereInput.getText().toString());
        scoutingBeingEntered.setScoutingComments(scoutingComments.getText().toString());
        scoutingBeingEntered.setMatchScouted(matchScoutedInput.isChecked());
    }*/

    private void clearData() {
        crossedBaseLineAutoInput.setChecked(false);
        hoppersDumpedAutoInput.setText("");
        putGearLeftAuto.setChecked(false);
        putGearCenterAuto.setChecked(false);
        putGearRightAuto.setChecked(false);
        gearsScoredTeleInput.setText("0");
        //gearsDeliveredInput.setText("0");
        numberOfGearsFromFloorInput.setText("0");
        numberOfGearsFromHumanInput.setText("0");
        scoresHighNeverAuto.setChecked(false);
        scoresHighSometimesAuto.setChecked(false);
        scoresHighOftenAuto.setChecked(false);
        scoresLowNeverAuto.setChecked(false);
        scoresLowSometimesAuto.setChecked(false);
        scoresLowOftenAuto.setChecked(false);
        numberOfBallsScoredHighGoalInput.setText("0");
        timesCollectedFromHumanInput.setText("0");
        timesCollectedFromHopperInput.setText("0");
        timesCollectedFromFloorInput.setText("0");
        fuelScoredLowGoalCycleInput.setText("0");
        numberOfLowGoalCyclesInput.setText("0");
        highGoalAccuracyScoutPoor.setChecked(false);
        highGoalAccuracyScoutOk.setChecked(false);
        highGoalAccuracyScoutGreat.setChecked(false);
        scoresHighDuringAuto.setChecked(false);
        scoresLowDuringAuto.setChecked(false);
        doesntScoreDuringAuto.setChecked(false);

        didScaleInput.setChecked(false);
        scaledFromWhereInput.setText("");
        scoutingComments.setText("");
        matchScoutedInput.setChecked(false);
    }

    private final View.OnClickListener submitButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*saveData();
            if(scoutingBeingEntered.isMatchScouted()) {
                currentTeam.addScoutingData(scoutingBeingEntered);
                dbHelper.createScoutingData(scoutingBeingEntered);
                utility.uploadScoutingData(ScoutingScreen.this, false);
                // reset data
                String key = String.valueOf(currentTeam.getTeamNumber()) + "_" + utility.getEpoch();
                scoutingBeingEntered = new ScoutingData(key, currentTeam.getTeamNumber(), currentTeam.getEventName(), currentTeam.getStudent());
                clearData();
            }
            else {
                utility.alertUser(ScoutingScreen.this, getString(R.string.scouting_not_done), getString(R.string.check_submit_buttom)).show();
            }*/
        }
    };
}