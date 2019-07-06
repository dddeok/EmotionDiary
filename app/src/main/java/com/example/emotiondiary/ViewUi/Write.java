package com.example.emotiondiary.ViewUi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emotiondiary.EmotionDetect.APIKey;
import com.example.emotiondiary.R;
import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Emotion;
import com.microsoft.projectoxford.face.contract.Face;
import com.microsoft.projectoxford.face.contract.FaceRectangle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Write extends Fragment {
    Button button;
    View view;
    TextView currentDate;
    ImageView addpic_view;

    APIKey apiKey = new APIKey();

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;
    private String Emotion = "";

    private  final FaceServiceClient faceServiceClient =
            new FaceServiceRestClient(apiKey.getApiEndpoint(),apiKey.getSubscriptionKey());

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.write, container, false);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = format.format(date);

        currentDate = view.findViewById(R.id.current_date);
        currentDate.setText(getTime);
        addpic_view = (ImageView) view.findViewById(R.id.addPicview);
        addpic_view.setOnClickListener((v)->{
            Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent.createChooser(
                    intent, "Select Picture"), PICK_IMAGE);
        });

        detectionProgressDialog = new ProgressDialog(this.getContext());


        button = (Button)view.findViewById(R.id.addTextBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TextWrite.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContext().getContentResolver(), uri);
                ImageView imageView = view.findViewById(R.id.addPicview);
                imageView.setImageBitmap(bitmap);

                // Comment out for tutorial
                detectAndFrame(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void detectAndFrame(Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        AsyncTask<InputStream, String, Face[]> detectTask =
                new AsyncTask<InputStream, String, Face[]>() {
                    String exceptionMessage = "";

                    @Override
                    protected Face[] doInBackground(InputStream... params) {
                        try {
                            publishProgress("Detecting...");
                            Face[] result = faceServiceClient.detect(
                                    params[0],
                                    true,         // returnFaceId
                                    false,        // returnFaceLandmarks
                                    // returnFaceAttributes:
                                    new FaceServiceClient.FaceAttributeType[] {
                                            FaceServiceClient.FaceAttributeType.Age,
                                            FaceServiceClient.FaceAttributeType.Gender,
                                            FaceServiceClient.FaceAttributeType.Emotion}

                            );
                            if (result == null){
                                publishProgress(
                                        "Detection Finished. Nothing detected");
                                return null;
                            }
                            publishProgress(String.format(
                                    "Detection Finished. %d face(s) detected",
                                    result.length));

                            return result;
                        } catch (Exception e) {
                            exceptionMessage = String.format(
                                    "Detection failed: %s", e.getMessage());
                            return null;
                        }
                    }

                    @Override
                    protected void onPreExecute() {
                        //TODO: show progress dialog
                        detectionProgressDialog.show();
                    }
                    @Override
                    protected void onProgressUpdate(String... progress) {
                        //TODO: update progress
                        detectionProgressDialog.setMessage(progress[0]);
                    }
                    @Override
                    protected void onPostExecute(Face[] result) {
                        //TODO: update face frames
                        detectionProgressDialog.dismiss();

                        if(!exceptionMessage.equals("")){
                            showError(exceptionMessage);
                        }
                        if (result == null) return;

                        ImageView imageView = view.findViewById(R.id.addPicview);
                        imageView.setImageBitmap(
                                drawFaceRectanglesOnBitmap(imageBitmap, result));
                        imageBitmap.recycle();

                        List<Face> faces;
                        faces = new ArrayList<>();
                        faces = Arrays.asList(result);

                        setInfo(getEmotion(faces.get(0).faceAttributes.emotion));
                    }
                };

        detectTask.execute(inputStream);
    }


    private void showError(String message) {
        new AlertDialog.Builder(this.getContext())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }})
                .create().show();
    }

    private static Bitmap drawFaceRectanglesOnBitmap(
            Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        if (faces != null) {
            for (Face face : faces) {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint);
            }
        }
        return bitmap;
    }
    private void setInfo(String info){
        this.Emotion = info;
        System.out.println("setinfo : " + info);
        TextView emotionView = view.findViewById(R.id.emotion_text);
        emotionView.setText(this.Emotion);
    }

    private String getEmotion(Emotion emotion)
    {
        String emotionType = "";
        double emotionValue = 0.0;
        if (emotion.anger > emotionValue)
        {
            emotionValue = emotion.anger;
            emotionType = "화남";
        }
        if (emotion.contempt > emotionValue)
        {
            emotionValue = emotion.contempt;
            emotionType = "경멸";
        }
        if (emotion.disgust > emotionValue)
        {
            emotionValue = emotion.disgust;
            emotionType = "역겨움";
        }
        if (emotion.fear > emotionValue)
        {
            emotionValue = emotion.fear;
            emotionType = "분노";
        }
        if (emotion.happiness > emotionValue)
        {
            emotionValue = emotion.happiness;
            emotionType = "행복";
        }
        if (emotion.neutral > emotionValue)
        {
            emotionValue = emotion.neutral;
            emotionType = "보통";
        }
        if (emotion.sadness > emotionValue)
        {
            emotionValue = emotion.sadness;
            emotionType = "슬픔";
        }
        if (emotion.surprise > emotionValue)
        {
            emotionValue = emotion.surprise;
            emotionType = "놀람";
        }
        return String.format("%s", emotionType);
    }
}

