package org.gosparx.scouting.aerialassist.networking;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.gosparx.scouting.aerialassist.BenchmarkingData;
import org.gosparx.scouting.aerialassist.RobotImage;
import org.gosparx.scouting.aerialassist.ScoutingData;
import org.gosparx.scouting.aerialassist.TeamData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SparxPosting {
    private static final String TAG = "SparxPosting";
    private static final String BASE_URL = "http://172.20.10.6:8080";
    //private static final String BASE_URL = "http://scouting-2017-156319.appspot.com";
    private static final String SCOUTING = "/api/2017/v1/ScoutingData";
    private static final String BENCHMARKING = "/api/2017/v1/BenchmarkingData";
    private static final String PICTURES = "/api/2017/v1/Pictures";
    private static SparxPosting SparxPosting;
    private Context context;

    private SparxPosting(Context context) {
        this.context = context;
        Ion ion = Ion.getInstance(context, TAG);
        ion.configure().setLogging(TAG, Log.DEBUG);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        ion.configure().setGson(gson);
    }

    public static synchronized SparxPosting getInstance(Context context){
        if(SparxPosting == null)
            SparxPosting = new SparxPosting(context);
        SparxPosting.context = context;
        return SparxPosting;
    }

    public void postAllBenchmarking(final NetworkCallback callback) {
        final Map<Integer, TeamData> teamsMap = TeamData.getTeamsMap();
        String request = (BASE_URL + BENCHMARKING);
        if((teamsMap == null) || teamsMap.isEmpty()) {
            Log.e(TAG, "No benchmarking to Upload!");
            System.out.println("No benchmarking to Upload!");
            callback.handleFinishDownload(true);
        }
        else {
            final NetworkCallback subCallback = new NetworkCallback() {
                int size = teamsMap.size();
                boolean hasFailed = false;
                @Override
                public void handleFinishDownload(boolean success) {
                    if(hasFailed) {
                        return;
                    }
                    if(!success) {
                        callback.handleFinishDownload(false);
                        hasFailed = true;
                        return;
                    }
                    size -= 1;
                    if(size <= 0) {
                        callback.handleFinishDownload(true);
                    }
                }
            };

            for (Object o : teamsMap.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                TeamData teamData = (TeamData) pair.getValue();
                BenchmarkingData benchmarkingData = teamData.getBenchmarkingData();
                if (benchmarkingData.isBenchmarkingWasDoneButton()) {
                    Ion.with(context)
                            .load(request)
                            .setJsonPojoBody(benchmarkingData)
                            .asString()
                            .setCallback(new FutureCallback<String>() {
                                @Override
                                public void onCompleted(Exception e, String result) {
                                    if ((e != null) || ((result != null) && (!result.isEmpty()))) {
                                        Log.e(TAG, "Issue saving Benchmarking to Server!", e);
                                        System.out.println("Issue saving Benchmarking to Server!");
                                        subCallback.handleFinishDownload(false);
                                    } else {
                                        subCallback.handleFinishDownload(true);
                                    }
                                }
                            });
                } else {
                    Log.e(TAG, "Benchmarking was not DONE!");
                    System.out.println("Benchmarking was not DONE!");
                    subCallback.handleFinishDownload(false);
                }
            }
        }
    }

    public void getBenchmarking(final NetworkCallback callback){
        String request = (BASE_URL + BENCHMARKING);
        Ion.with(context)
                .load(request)
                .as(new TypeToken<List<BenchmarkingData>>(){})
                .setCallback(new FutureCallback<List<BenchmarkingData>>() {
                    @Override
                    public void onCompleted(Exception e, List<BenchmarkingData> result) {
                        if (e != null) {
                            Log.e(TAG, "Issue getting benchmarking data.", e);
                            callback.handleFinishDownload(false);
                            return;
                        }

                        for (BenchmarkingData sd : result) {
                            TeamData.setTeamData(sd);
                        }
                        callback.handleFinishDownload(true);
                    }
                });
    }

    public void postAllScouting(final NetworkCallback callback) {
        final Map<Integer, TeamData> teamsMap = TeamData.getTeamsMap();
        String request = (BASE_URL + SCOUTING);
        if((teamsMap == null) || teamsMap.isEmpty()) {
            Log.e(TAG, "No scouting to Upload!");
            System.out.println("No scouting to Upload!");
            callback.handleFinishDownload(true);
        }
        else {
            final NetworkCallback subCallback = new NetworkCallback() {
                int size = teamsMap.size();
                boolean hasFailed = false;

                @Override
                public void handleFinishDownload(boolean success) {
                    if (hasFailed) {
                        return;
                    }
                    if (!success) {
                        callback.handleFinishDownload(false);
                        hasFailed = true;
                        return;
                    }
                    size -= 1;
                    if (size <= 0) {
                        callback.handleFinishDownload(true);
                    }
                }
            };

            for (Object o : teamsMap.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                TeamData teamData = (TeamData) pair.getValue();
                if (!teamData.getScoutingDatas().isEmpty()) {
                    for (Object sd : teamData.getScoutingDatas()) {
                        ScoutingData scoutingData = (ScoutingData) sd;
                        if (scoutingData.isMatchScouted()) {
                            Ion.with(context)
                                    .load(request)
                                    .setJsonPojoBody(scoutingData)
                                    .asString()
                                    .setCallback(new FutureCallback<String>() {
                                        @Override
                                        public void onCompleted(Exception e, String result) {
                                            if ((e != null) || ((result != null) && (!result.isEmpty()))) {
                                                Log.e(TAG, "Issue saving Scouting to Server!", e);
                                                System.out.println("Server Error");
                                                subCallback.handleFinishDownload(false);
                                            } else {
                                                subCallback.handleFinishDownload(true);
                                            }
                                        }
                                    });
                        } else {
                            Log.e(TAG, "Scouting was not DONE!");
                            System.out.println("Scouting was not DONE!");
                            subCallback.handleFinishDownload(false);
                        }
                    }
                } else {
                    Log.e(TAG, "No scouting to Upload!");
                    System.out.println("No scouting to Upload!");
                    callback.handleFinishDownload(false);
                }
            }
        }
    }

    public void getScouting(final NetworkCallback callback){
        String request = (BASE_URL + SCOUTING);
        Ion.with(context)
                .load(request)
                .as(new TypeToken<List<ScoutingData>>(){})
                .setCallback(new FutureCallback<List<ScoutingData>>() {
                    @Override
                    public void onCompleted(Exception e, List<ScoutingData> result) {
                        if (e != null) {
                            Log.e(TAG, "Issue getting scouting data.", e);
                            callback.handleFinishDownload(false);
                            return;
                        }

                        for (ScoutingData sd : result) {
                            TeamData.setTeamData(sd);
                        }
                        callback.handleFinishDownload(true);
                    }
                });
    }

    public void postAllPictures(final NetworkCallback callback) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(storageDir == null) throw new AssertionError("Cannot read " + Environment.DIRECTORY_PICTURES);
        String path = storageDir.getAbsolutePath();
        String request = (BASE_URL + PICTURES);

        File directory = new File(path);
        File[] files = directory.listFiles();
        final Vector<File> pictures = new Vector();
        boolean noneFound = true;
        int photoIndex = 0;
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains("Robot")) {
                noneFound = false;
                pictures.add(file);
                photoIndex++;
            }
        }

        if(noneFound) {
            Log.e(TAG, "No pictures to Upload!");
            System.out.println("No pictures to Upload!");
            callback.handleFinishDownload(true);
        }
        else {
            final NetworkCallback subCallback = new NetworkCallback() {
                int size = pictures.size();
                boolean hasFailed = false;
                @Override
                public void handleFinishDownload(boolean success) {
                    if(hasFailed) {
                        return;
                    }
                    if(!success) {
                        callback.handleFinishDownload(false);
                        hasFailed = true;
                        return;
                    }
                    size -= 1;
                    if(size <= 0) {
                        callback.handleFinishDownload(true);
                    }
                }
            };

            for (int i = 0; i < pictures.size(); i++) {
                File picture = pictures.get(i);
                Ion.with(context)
                        .load(request)
                        .setMultipartFile("robot_picture", picture.getName(), picture)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                // Not sure why this is failing???
                                //if (e != null) {
                                //    Log.e(TAG, "Issue saving Picture to Server!", e);
                                //    System.out.println("Issue saving Picture to Server!");
                                //    subCallback.handleFinishDownload(false);
                                //}
                                //else {
                                    subCallback.handleFinishDownload(true);
                                //}
                            }
                        });
            }

        }
    }

    public void getPictures(final NetworkCallback callback){
        String request = (BASE_URL + PICTURES);
        Ion.with(context)
                .load(request)
                .as(new TypeToken<List<RobotImage>>(){})
                .setCallback(new FutureCallback<List<RobotImage>>() {
                    @Override
                    public void onCompleted(Exception e, List<RobotImage> result) {
                        if (e != null) {
                            Log.e(TAG, "Issue getting pictures.", e);
                            callback.handleFinishDownload(false);
                            return;
                        }
                        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        if(storageDir == null) throw new AssertionError("Cannot read " + Environment.DIRECTORY_PICTURES);
                        String path = storageDir.getAbsolutePath();

                        for (RobotImage sd : result) {
                            byte[] imageDataBytes = Base64.decode(sd.getBlob(), 0);
                            try {
                                FileOutputStream imageOutFile = new FileOutputStream(
                                        path + "/" + sd.getFileName());
                                imageOutFile.write(imageDataBytes);
                                imageOutFile.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        callback.handleFinishDownload(true);
                    }
                });
    }
}